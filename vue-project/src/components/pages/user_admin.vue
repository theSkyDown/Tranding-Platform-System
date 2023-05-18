<!-- 

    用户管理页面

 -->

<template>
  <!-- 用户管理卡片 -->
  <el-card shadow="hover" v-loading="!this.$store.state.isLogin">
    <template #header>
      <div class="card-header">用户管理</div>
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
        type="danger"
        style="margin-bottom: 5px"
        v-on:click="deleteUsersByBatchIds"
      >
        <el-icon><Delete /></el-icon>
        <span>批量删除</span>
      </el-button>
      <el-button style="margin-bottom: 5px" v-on:click="reloadUsers">
        <el-icon><Refresh /></el-icon>
        <span>刷新</span>
      </el-button>
      <el-input
        v-model="keyword"
        placeholder="用户名或手机号码"
        style="width: 200px; margin-left: 20px; margin-bottom: 5px"
      />
      <el-button
        style="margin-bottom: 5px; margin-left: 5px"
        v-on:click="reloadUsers"
        type="primary"
      >
        <el-icon><Search /></el-icon>
        <span>搜索</span>
      </el-button>

      <el-table
        :data="users"
        height="630"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        v-loading="tableLoading"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column label="序号" type="index" width="80" />
        <el-table-column label="用户名" prop="username" min-width="120" />
        <el-table-column label="手机号" sortable prop="phone" min-width="120" />
        <el-table-column label="性别" prop="genderName" width="80" />
        <el-table-column label="角色" sortable prop="roleName" width="100" />
        <el-table-column label="头像" prop="avatar" min-width="160" />
        <el-table-column
          label="创建时间"
          sortable
          prop="createTime"
          min-width="160"
        />
        <el-table-column
          label="修改时间"
          sortable
          prop="updateTime"
          min-width="160"
        />
        <el-table-column label="状态" prop="status" width="80">
          <template #default="scope">
            <el-switch
              v-model="scope.row.status"
              :active-value="1"
              :inactive-value="2"
              style="
                --el-switch-on-color: #13ce66;
                --el-switch-off-color: #ff4949;
              "
              :before-change="switchUserStatus.bind(this, scope.row)"
            />
          </template>
        </el-table-column>

        <el-table-column fixed="right" label="操作" width="300">
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
              v-on:click="resetPassword(scope.row.userId)"
            >
              <el-icon><setting /></el-icon>
              <span>重置密码</span>
            </el-button>

            <el-button
              size="small"
              type="danger"
              v-on:click="deleteUser(scope.row.userId)"
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

  <!-- 添加用户弹窗 -->
  <el-dialog
    v-model="addDialog"
    title="添加用户"
    width="25%"
    align-center
    draggable
  >
    <el-form label-position="top">
      <el-form-item label="用户名" required>
        <el-input
          placeholder="请输入用户名"
          maxlength="20"
          show-word-limit
          v-model="user.username"
        />
      </el-form-item>
      <el-form-item label="手机号" required>
        <el-input
          placeholder="请输入手机号码"
          v-model="user.phone"
          maxlength="11"
          show-word-limit
          onkeyup="value=value.replace(/[^\d]/g,'')"
        />
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="user.gender" placeholder="请选择性别">
          <el-option label="男" value="1" />
          <el-option label="女" value="2" />
        </el-select>
      </el-form-item>

      <el-form-item label="用户的状态">
        <el-radio-group v-model="user.status">
          <el-radio :label="1">启用</el-radio>
          <el-radio :label="2">禁用</el-radio>
        </el-radio-group>
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
    title="修改用户信息"
    width="25%"
    align-center
    draggable
  >
    <el-form label-position="top">
      <el-form-item label="用户名" required>
        <el-input
          placeholder="请输入用户名"
          maxlength="20"
          show-word-limit
          v-model="updateUser.username"
        />
      </el-form-item>
      <el-form-item label="手机号" required>
        <el-input
          placeholder="请输入手机号码"
          v-model="updateUser.phone"
          maxlength="11"
          show-word-limit
          onkeyup="value=value.replace(/[^\d]/g,'')"
        />
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="updateUser.gender" placeholder="请选择性别">
          <el-option label="男" :value="1" />
          <el-option label="女" :value="2" />
        </el-select>
      </el-form-item>

      <el-form-item label="角色">
        <el-select v-model="updateUser.roleId" placeholder="请选择用户的角色">
          <el-option
            v-for="item in roles"
            :key="item.roleId"
            :label="item.roleName"
            :value="item.roleId"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="用户的状态">
        <el-radio-group v-model="updateUser.status">
          <el-radio :label="1">启用</el-radio>
          <el-radio :label="2">禁用</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <!-- 底部按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button
          v-on:click="
            updateDialog = false;
            this.reloadUsers();
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
      //用户列表
      users: [],
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
      //用于添加的user
      user: {
        username: "",
        phone: "",
        gender: "",
        roleId: "",
        status: "",
      },
      //修改弹窗是否显示
      updateDialog: false,
      //用于修改的user
      updateUser: {},
      //角色列表
      roles: [],
      //关键词
      keyword: "",
    };
  },
  methods: {
    // 获取用户列表
    getUsersInfo() {
      let that = this;
      axios({
        url:
          this.$store.state.localhost +
          "/user/all?num=" +
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
          that.users = res.data.data;
        }
      });
    },
    //获取用户总共有多少条数据
    getTotal() {
      let that = this;
      axios({
        url:
          this.$store.state.localhost + "/user/total?keyword=" + this.keyword,
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        if (res.data.status) {
          that.total = res.data.data;
        }
      });
    },
    // 多选向处理器，将选中的选项设置到selectId变量中
    handleSelectionChange(val) {
      let temp = [];
      for (let i = 0; i < val.length; i++) {
        temp[i] = val[i].userId;
      }
      this.selectId = temp;
    },
    // 切换按钮发送请求
    switchUserStatus(row) {
      let that = this;
      return new Promise((resolve) => {
        axios({
          url:
            that.$store.state.localhost +
            "/user/switch/status?userId=" +
            row.userId,
          method: "put",
          withCredentials: true,
        }).then(function (res) {
          ElMessage({
            message: res.data.message,
            type: res.data.status ? "success" : "error",
          });
          if (res.data.status) {
            return resolve(true);
          } else {
            return resolve(false);
          }
        });
      });
    },
    //刷新按钮
    reloadUsers() {
      this.tableLoading = true;
      // 查询所有用户数据
      this.getUsersInfo();
      //统计数量
      this.getTotal();
      setTimeout(() => {
        this.tableLoading = false;
      }, 300);
    },
    //打开添加弹窗后加载角色列表数据
    showAddDialog() {
      this.addDialog = true;
      let that = this;
      if (this.roles.length == 0) {
        //获取角色列表
        axios({
          url:
            this.$store.state.localhost + "/role/all?num=0&size=100&keyword=",
          method: "get",
          withCredentials: true,
        }).then(function (res) {
          that.roles = res.data.data;
        });
      }
    },
    //发送添加用户请求
    add() {
      let that = this;
      axios({
        url: this.$store.state.localhost + "/user/add",
        method: "post",
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
        data: JSON.stringify(this.user),
      }).then(function (res) {
        // 打印信息
        ElMessage({
          message: res.data.message,
          type: res.data.status ? "success" : "error",
        });
        //如果添加成功
        if (res.data.status) {
          //清空表单
          that.user = {};
          //关闭添加弹窗
          that.addDialog = false;
          //刷新表格
          that.reloadUsers();
        }
      });
    },
    //分页大小发生改变
    sizeChange(pageSize) {
      // 更新每页大小
      this.size = pageSize;
      //   刷新页面
      this.reloadUsers();
    },
    //当前页数发生改变
    numChange(page) {
      // 更新当前页数
      this.page = page;
      // 更新num用于发请求
      this.num = (page - 1) * this.size;
      //刷新页面
      this.reloadUsers();
    },
    //删除用户
    deleteUser(userId) {
      let that = this;
      this.$confirm("此操作将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios({
            url: this.$store.state.localhost + "/user/delete?userId=" + userId,
            method: "delete",
            withCredentials: true,
          }).then(function (res) {
            ElMessage({
              message: res.data.message,
              type: res.data.status ? "success" : "error",
            });
            that.reloadUsers();
          });
        })
        .catch(() => {
          ElMessage({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    //批量删除
    deleteUsersByBatchIds() {
      let that = this;
      //拼接字符串，用于请求的参数
      this.$confirm("此操作将永久删除这些用户, 是否继续?", "提示", {
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
            url: this.$store.state.localhost + "/user/delete/ids?ids=" + ids,
            method: "delete",
            withCredentials: true,
          }).then(function (res) {
            ElMessage({
              message: res.data.message,
              type: res.data.status ? "success" : "error",
            });
            that.reloadUsers();
          });
        })
        .catch(() => {
          ElMessage({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    //显示修改按钮弹窗
    showUpdateDialog(user) {
      this.updateDialog = true;
      let that = this;
      if (this.roles.length == 0) {
        //获取角色列表
        axios({
          url:
            this.$store.state.localhost + "/role/all?num=0&size=100&keyword=",
          method: "get",
          withCredentials: true,
        }).then(function (res) {
          that.roles = res.data.data;
        });
      }
      //信息回显
      this.updateUser = user;
    },
    //发送修改请求
    update() {
      let that = this;
      axios({
        url: this.$store.state.localhost + "/user/update",
        method: "put",
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
        data: JSON.stringify(this.updateUser),
      }).then(function (res) {
        ElMessage({
          message: res.data.message,
          type: res.data.status ? "success" : "error",
        });
        //如果修改成功
        if (res.data.status) {
          //清空表单
          that.updateUser = {};
          //关闭添加弹窗
          that.updateDialog = false;
          //刷新表格
          that.reloadUsers();
        }
      });
    },
    //重置密码发送请求
    resetPassword(userId) {
      this.$confirm("此操作将重置用户的密码, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios({
            url:
              this.$store.state.localhost +
              "/user/reset/password?userId=" +
              userId,
            method: "put",
            withCredentials: true,
          }).then(function (res) {
            ElMessage({
              message: res.data.message,
              type: res.data.status ? "success" : "error",
            });
          });
        })
        .catch(() => {
          ElMessage({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },
  mounted() {
    this.reloadUsers();
  },
};
</script>
