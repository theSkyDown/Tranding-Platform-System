<!-- 

    权限管理页面

 -->

<template>
  <!-- 权限管理卡片 -->
  <el-card shadow="hover" v-loading="!this.$store.state.isLogin">
    <template #header>
      <div class="card-header">权限管理</div>
    </template>
    <div>
      <el-button
        type="primary"
        style="margin-bottom: 5px"
        v-on:click="showAddDialog"
      >
        <el-icon><Plus /></el-icon>
        <span>添加</span>
      </el-button>

      <el-button
        style="margin-bottom: 5px"
        type="danger"
        v-on:click="deletePermissionByBatchIds"
      >
        <el-icon><Delete /></el-icon>
        <span>批量删除</span>
      </el-button>

      <el-button style="margin-bottom: 5px" v-on:click="reloadPermission">
        <el-icon><Refresh /></el-icon>
        <span>刷新</span>
      </el-button>

      <el-table
        :data="permissions"
        height="630"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        v-loading="tableLoading"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column label="序号" type="index" width="80" />
        <el-table-column
          label="角色名称"
          sortable
          prop="roleName"
          min-width="120"
        />
        <el-table-column label="资源名称" prop="moduleName" min-width="160" />

        <el-table-column fixed="right" label="操作" width="100">
          <template #default="scope">
            <el-button
              size="small"
              type="danger"
              v-on:click="deleteRole(scope.row.permissionId)"
            >
              <el-icon><Delete /></el-icon>
              <span>删除</span>
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div class="demo-pagination-block">
        <el-pagination
          :small="small"
          layout="prev, pager, next, jumper, sizes , total"
          :total="total"
          :current-page="page"
          :page-size="size"
          @size-change="sizeChange"
          @current-change="numChange"
        />
      </div>
    </div>
  </el-card>

  <!-- 添加角色弹窗 -->
  <el-dialog
    v-model="addDialog"
    title="添加权限"
    width="25%"
    align-center
    draggable
  >
    <el-form label-position="top">
      <el-form-item label="角色" required>
        <el-select
          v-model="permission.roleId"
          filterable
          placeholder="请选择用户的角色"
        >
          <el-option
            v-for="item in roles"
            :key="item.roleId"
            :label="item.roleName"
            :value="item.roleId"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="资源" required>
        <el-select
          v-model="permission.moduleId"
          filterable
          placeholder="请选择用户的资源"
        >
          <el-option
            v-for="item in modules"
            :key="item.moduleId"
            :label="item.moduleName"
            :value="item.moduleId"
          >
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <!-- 底部按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button v-on:click="addDialog = false">取消</el-button>
        <el-button type="primary" v-on:click="add">添加</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import axios from "axios";
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      //角色列表
      permissions: [],
      //分页参数
      page: 1, //当前页数
      num: 0, //从第几条开始算
      size: 10, //每页多少条数据
      total: 0, //总共多少条数据
      // 多选选中的id
      selectId: [],
      //表单加载
      tableLoading: false,
      //添加弹窗是否显示
      addDialog: false,
      //用于添加的permission
      permission: {
        roleId: "",
        moduleId: "",
      },
      //关键词
      keyword: "",
      //用于角色下拉菜单
      roles: [],
      //用于资源下拉菜单
      modules: [],
    };
  },
  methods: {
    //获取所有权限的数据
    getAllPermission() {
      let that = this;
      axios({
        url:
          this.$store.state.localhost +
          "/permission/all?num=" +
          this.num +
          "&size=" +
          this.size,
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        // 打印信息
        ElMessage({
          message: res.data.status ? "获取成功" : res.data.message,
          type: res.data.status ? "success" : "error",
        });
        if (res.data.status) {
          that.permissions = res.data.data;
        }
      });
    },
    //批量删除
    deletePermissionByBatchIds() {
      let that = this;
      //拼接字符串，用于请求的参数
      this.$confirm("此操作将永久删除这些权限, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let ids = "";
          for (let i = 0; i < this.selectId.length; i++) {
            ids = ids + this.selectId[i] + ",";
          }
          ids = ids.substring(0, ids.lastIndexOf(","));
          axios({
            url:
              this.$store.state.localhost + "/permission/delete/ids?ids=" + ids,
            method: "delete",
            withCredentials: true,
          }).then(function (res) {
            ElMessage({
              message: res.data.message,
              type: res.data.status ? "success" : "error",
            });
            that.reloadPermission();
          });
        })
        .catch(() => {
          ElMessage({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    //刷新
    reloadPermission() {
      this.tableLoading = true;
      //查询所有角色
      this.getAllPermission();
      //统计数量
      this.getTotal();
      setTimeout(() => {
        this.tableLoading = false;
      }, 300);
    },
    //多选栏，选中触发
    handleSelectionChange(val) {
      let temp = [];
      for (let i = 0; i < val.length; i++) {
        temp[i] = val[i].permissionId;
      }
      this.selectId = temp;
    },
    //删除指定权限
    deleteRole(id) {
      let that = this;
      this.$confirm("此操作将永久删除该权限, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios({
            url:
              this.$store.state.localhost +
              "/permission/delete?permissionId=" +
              id,
            method: "delete",
            withCredentials: true,
          }).then(function (res) {
            ElMessage({
              message: res.data.message,
              type: res.data.status ? "success" : "error",
            });
            that.reloadPermission();
          });
        })
        .catch(() => {
          ElMessage({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    //每页显示的数据量发生改变
    sizeChange(pageSize) {
      // 更新每页大小
      this.size = pageSize;
      //   刷新页面
      this.reloadPermission();
    },
    //当前页数发生改变
    numChange(page) {
      // 更新当前页数
      this.page = page;
      // 更新num用于发请求
      this.num = (page - 1) * this.size;
      //刷新页面
      this.reloadPermission();
    },
    //打开添加弹窗
    showAddDialog() {
      this.addDialog = true;
      let that = this;
      //获取角色列表
      if (this.roles.length == 0) {
        axios({
          url:
            this.$store.state.localhost + "/role/all?num=0&size=100&keyword=",
          method: "get",
          withCredentials: true,
        }).then(function (res) {
          that.roles = res.data.data;
        });
      }

      //获取资源列表
      if (this.modules.length == 0) {
        axios({
          url:
            this.$store.state.localhost + "/module/all?num=0&size=300&keyword=",
          method: "get",
          withCredentials: true,
        }).then(function (res) {
          that.modules = res.data.data;
        });
      }
    },
    //发送添加请求
    add() {
      let that = this;
      axios({
        url: this.$store.state.localhost + "/permission/add",
        method: "post",
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
        data: JSON.stringify(this.permission),
      }).then(function (res) {
        // 打印信息
        ElMessage({
          message: res.data.message,
          type: res.data.status ? "success" : "error",
        });
        if (res.data.status) {
          //清空表单
          that.permission = {};
          //关闭添加弹窗
          that.addDialog = false;
          //刷新表格
          that.reloadPermission();
        }
      });
    },
    //获取统计角色的数据
    getTotal() {
      let that = this;
      axios({
        url: this.$store.state.localhost + "/permission/total",
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        if (res.data.status) {
          that.total = res.data.data;
        }
      });
    },
  },
  mounted() {
    this.reloadPermission();
  },
};
</script>
