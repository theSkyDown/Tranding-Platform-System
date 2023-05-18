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
      <el-button type="primary" v-on:click="showAddDialog">
        <el-icon><Plus /></el-icon>
        <span>添加</span>
      </el-button>
      <el-button type="danger" v-on:click="deleteRolesByBatchIds">
        <el-icon><Delete /></el-icon>
        <span>批量删除</span>
      </el-button>
      <el-button v-on:click="reloadRoles">
        <el-icon><Refresh /></el-icon>
        <span>刷新</span>
      </el-button>
      <el-input
        v-model="keyword"
        placeholder="请输入关键词"
        style="width: 200px; margin-left: 20px"
      />
      <el-button v-on:click="reloadRoles">
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
              v-on:click="deleteRole(scope.row.userId)"
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
      <el-form-item label="角色名">
        <el-input
          placeholder="请输入角色名"
          maxlength="10"
          show-word-limit
          v-model="user.username"
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
      <el-form-item label="角色名">
        <el-input
          placeholder="请输入角色名"
          maxlength="10"
          show-word-limit
          v-model="updateUser.username"
        />
      </el-form-item>
    </el-form>
    <!-- 底部按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button v-on:click="updateDialog = false">取消</el-button>
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
      total: 3, //总共多少条数据
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
    //显示添加弹窗
    showAddDialog() {},
    //批量删除
    deleteRolesByBatchIds() {},
    //刷新
    reloadRoles() {},
    //多选栏，选中触发
    handleSelectionChange() {},
    //显示修改弹窗
    showUpdateDialog(role) {},
    //删除指定角色
    deleteRole(id) {},
    //每页显示的数据量发生改变
    sizeChange() {},
    //当前页数发生改变
    numChange() {},
    //发送添加请求
    add() {},
    //发送修改请求
    update() {},
  },
  mounted() {
    this.getAllRole();
    // this.reloadRoles();
  },
};
</script>
