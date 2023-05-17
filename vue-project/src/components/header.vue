<!-- 
  
  导航栏 

  
-->
<template>
  <div class="header">
    <!-- 左边logo -->
    <img class="logo" src="/logo.png" />

    <!-- 登陆按钮 -->
    <div class="login-div" v-show="!this.$store.state.isLogin">
      <el-button type="primary" v-on:click="showLoginPage">login</el-button>
    </div>

    <!-- 登陆成功后显示的信息 -->
    <el-dropdown>
      <!-- 显示头像信息 -->
      <div class="information" v-show="this.$store.state.isLogin">
        <el-avatar>
          <img src="../assets/avatar.png" />
        </el-avatar>
        <div class="username">{{ this.$store.state.user.username }}</div>
      </div>
      <!-- 下拉选项 -->
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item>
            <el-icon><User /></el-icon>
            <span>个人中心</span>
          </el-dropdown-item>

          <el-dropdown-item>
            <el-icon><Key /></el-icon>
            <span>修改密码</span>
          </el-dropdown-item>

          <!-- 退出按钮 -->
          <el-dropdown-item v-on:click="logout">
            <el-icon><Back /></el-icon>
            <span>退出账号</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script>
import Cookies from "js-cookie";

export default {
  data() {
    return {};
  },
  methods: {
    // 显示登陆弹窗
    showLoginPage() {
      this.$store.state.dialogVisible = true;
    },
    // 退出账号
    logout() {
      //将用户信息清空
      this.$store.state.user = {};
      //将登陆状态修改为否
      this.$store.state.isLogin = false;
      //删除localstore的记录
      localStorage.removeItem("user");
      //删除Cookie
      Cookies.remove("user");
    },
  },
};
</script>

<style>
.header {
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  height: 100%;
  width: 100%;
}

/* logo图标 */
.logo {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  width: 200px;
  height: 55px;
}
.logo:hover {
  cursor: pointer;
}

/* 下拉菜单 */
.el-dropdown {
  position: absolute !important;
  right: 0px;
  top: 50%;
  transform: translateY(-50%);
}
.el-dropdown:hover {
  cursor: pointer;
}
:focus {
  outline: none;
}

/* 用户信息 */
.information {
  position: relative !important;
  width: 150px;
  height: 60px;
}

/* 头像 */
.el-avatar {
  position: absolute !important;
  left: 5px;
  top: 50%;
  transform: translateY(-50%);
}

/* 用户名 */
.username {
  position: absolute;
  right: 0;
  top: 50%;
  transform: translateY(-50%);
  width: 100px;
  height: 40px;
  line-height: 40px;
  text-align: center;
}

/* 登陆按钮所在的div */
.login-div {
  position: absolute;
  width: 100px;
  height: 100%;
  right: 0;
  top: 50%;
  transform: translateY(-50%);
}
/* 登陆按钮 */
.login-div > .el-button {
  position: absolute !important;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>
