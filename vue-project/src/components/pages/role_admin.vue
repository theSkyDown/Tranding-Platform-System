<!-- 

    角色管理页面

 -->

<template>
  <!-- 角色管理卡片 -->
  <el-card shadow="hover" v-loading="!this.$store.state.isLogin">
    <template #header>
      <div class="card-header">角色管理</div>
    </template>
    <div>
      <el-button
        type="primary"
        style="margin-bottom: 5px"
        v-on:click="addDialog = true"
      >
        <el-icon><Plus /></el-icon>
        <span>添加</span>
      </el-button>
      <el-button
        style="margin-bottom: 5px"
        type="danger"
        v-on:click="deleteRolesByBatchIds"
      >
        <el-icon><Delete /></el-icon>
        <span>批量删除</span>
      </el-button>
      <el-button style="margin-bottom: 5px" v-on:click="reloadRoles">
        <el-icon><Refresh /></el-icon>
        <span>刷新</span>
      </el-button>
      <el-input
        v-model="keyword"
        placeholder="角色名称"
        style="width: 200px; margin-left: 20px; margin-bottom: 5px"
      />
      <el-button
        type="primary"
        style="margin-bottom: 5px; margin-left: 5px"
        v-on:click="reloadRoles"
      >
        <el-icon><Search /></el-icon>
        <span>搜索</span>
      </el-button>

      <el-table
        :data="roles"
        height="630"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        v-loading="tableLoading"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column label="序号" type="index" width="80" />
        <el-table-column label="角色名" prop="roleName" min-width="120" />
        <el-table-column label="权限等级" prop="level" min-width="120" />
        <el-table-column
          label="创建时间"
          sortable
          prop="createTime"
          min-width="160"
        />

        <el-table-column fixed="right" label="操作" width="170">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              v-on:click="showUpdateDialog(scope.row)"
            >
              <el-icon><EditPen /></el-icon>
              <span>修改</span>
            </el-button>
            <el-button
              size="small"
              type="danger"
              v-on:click="deleteRole(scope.row.roleId)"
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
    title="添加角色"
    width="25%"
    align-center
    draggable
  >
    <el-form label-position="top">
      <el-form-item label="角色名" required>
        <el-input
          placeholder="请输入角色名"
          maxlength="10"
          show-word-limit
          v-model="role.roleName"
        />
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

  <!-- 修改弹窗 -->
  <el-dialog
    v-model="updateDialog"
    title="修改角色"
    width="25%"
    align-center
    draggable
  >
    <el-form label-position="top">
      <el-form-item label="角色名" required>
        <el-input
          placeholder="请输入角色名"
          maxlength="10"
          show-word-limit
          v-model="updateRole.roleName"
        />
      </el-form-item>

      <el-form-item label="权限等级" required>
        <el-input
          placeholder="请输入角色等级"
          onkeyup="value=value.replace(/[^\d]/g,'')"
          maxlength="5"
          v-model="updateRole.level"
        />
      </el-form-item>
    </el-form>
    <!-- 底部按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button
          v-on:click="
            updateDialog = false;
            this.reloadRoles();
          "
          >取消</el-button
        >
        <el-button type="primary" v-on:click="update">修改</el-button>
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
      roles: [],
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
      //用于添加的role
      role: {
        roleName: "",
      },
      //修改弹窗是否显示
      updateDialog: false,
      //用于修改的role
      updateRole: {},
      //关键词
      keyword: "",
    };
  },
  methods: {
    //获取所有角色的数据
    getAllRole() {
      let that = this;
      axios({
        url:
          this.$store.state.localhost +
          "/role/all?num=" +
          this.num +
          "&size=" +
          this.size +
          "&keyword=" +
          this.keyword,
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        // 打印信息
        ElMessage({
          message: res.data.status ? "获取成功" : res.data.message,
          type: res.data.status ? "success" : "error",
        });
        if (res.data.status) {
          that.roles = res.data.data;
        }
      });
    },
    //批量删除
    deleteRolesByBatchIds() {
      let that = this;
      //拼接字符串，用于请求的参数
      this.$confirm("此操作将永久删除这些角色, 是否继续?", "提示", {
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
            url: this.$store.state.localhost + "/role/delete/ids?ids=" + ids,
            method: "delete",
            withCredentials: true,
          }).then(function (res) {
            ElMessage({
              message: res.data.message,
              type: res.data.status ? "success" : "error",
            });
            that.reloadRoles();
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
    reloadRoles() {
      this.tableLoading = true;
      //查询所有角色
      this.getAllRole();
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
        temp[i] = val[i].roleId;
      }
      this.selectId = temp;
    },
    //显示修改弹窗
    showUpdateDialog(role) {
      this.updateDialog = true;
      //信息回显
      this.updateRole = role;
    },
    //删除指定角色
    deleteRole(id) {
      let that = this;
      this.$confirm("此操作将永久删除该角色, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios({
            url: this.$store.state.localhost + "/role/delete?roleId=" + id,
            method: "delete",
            withCredentials: true,
          }).then(function (res) {
            ElMessage({
              message: res.data.message,
              type: res.data.status ? "success" : "error",
            });
            that.reloadRoles();
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
      this.reloadRoles();
    },
    //当前页数发生改变
    numChange(page) {
      // 更新当前页数
      this.page = page;
      // 更新num用于发请求
      this.num = (page - 1) * this.size;
      //刷新页面
      this.reloadRoles();
    },
    //发送添加请求
    add() {
      let that = this;
      axios({
        url: this.$store.state.localhost + "/role/add",
        method: "post",
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
        data: JSON.stringify(this.role),
      }).then(function (res) {
        // 打印信息
        ElMessage({
          message: res.data.message,
          type: res.data.status ? "success" : "error",
        });
        if (res.data.status) {
          //清空表单
          that.role = {};
          //关闭添加弹窗
          that.addDialog = false;
          //刷新表格
          that.reloadRoles();
        }
      });
    },
    //发送修改请求
    update() {
      let that = this;
      axios({
        url: this.$store.state.localhost + "/role/update",
        method: "put",
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
        data: JSON.stringify(this.updateRole),
      }).then(function (res) {
        ElMessage({
          message: res.data.message,
          type: res.data.status ? "success" : "error",
        });
        //如果修改成功
        if (res.data.status) {
          //清空表单
          that.updateRole = {};
          //关闭添加弹窗
          that.updateDialog = false;
          //刷新表格
          that.reloadRoles();
        }
      });
    },
    //获取统计角色的数据
    getTotal() {
      let that = this;
      axios({
        url:
          this.$store.state.localhost + "/role/total?keyword=" + this.keyword,
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
    this.reloadRoles();
  },
};
</script>
