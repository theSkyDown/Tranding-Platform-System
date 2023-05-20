<!-- 


  主体内容


 -->

<template>
  <!-- 主体内容 -->
  <router-view />

  <!-- 登陆弹窗 -->
  <el-dialog
    v-model="this.$store.state.dialogVisible"
    title="登陆"
    center
    width="25%"
    align-center
    draggable
  >
    <!-- 内容 -->
    <el-form label-position="top" label-width="70px" status-icon>
      <el-form-item label="手机">
        <el-input
          v-model="user.phone"
          maxlength="11"
          show-word-limit
          placeholder="请输入手机号码"
        />
      </el-form-item>

      <el-form-item label="密码">
        <el-input
          type="password"
          show-password
          v-model="user.password"
          placeholder="请输入密码"
        />
      </el-form-item>

      <el-form-item label="验证码">
        <el-input
          v-model="user.captcha"
          maxlength="4"
          placeholder="请输入验证码"
        />
      </el-form-item>

      <el-form-item>
        <img
          v-bind:src="captchaImgUrl"
          alt="验证码"
          v-on:click="reloadCaptcha"
          class="captcha"
        />
      </el-form-item>
    </el-form>

    <!-- 底部按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" v-on:click="login"> 登陆 </el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 修改密码弹窗 -->
  <el-dialog
    v-model="this.$store.state.updateDialog"
    title="修改密码"
    width="25%"
    align-center
    draggable
  >
    <!-- 内容 -->
    <el-form label-position="top" label-width="70px" status-icon>
      <el-form-item label="密码">
        <el-input
          type="password"
          show-password
          v-model="updatePasswordUser.password"
          placeholder="请输入密码"
        />
      </el-form-item>
    </el-form>

    <!-- 底部按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button v-on:click="this.$store.state.updateDialog = false">
          取消
        </el-button>
        <el-button type="primary" v-on:click="updatePassword"> 修改 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import axios from "axios";
import { ElMessage } from "element-plus";
import Cookies from "js-cookie";

export default {
  data() {
    return {
      captchaImgUrl: this.$store.state.localhost + "/captcha/img",
      user: {
        phone: "",
        password: "",
        captcha: "",
      },
      updatePasswordUser: {
        password: "",
      },
    };
  },
  methods: {
    // 刷新验证码
    reloadCaptcha() {
      this.captchaImgUrl = this.captchaImgUrl + "?" + Math.random();
    },
    //登陆
    login() {
      // 发送登陆请求
      let that = this;
      axios({
        url: that.$store.state.localhost + "/user/login",
        method: "post",
        data: JSON.stringify(that.user),
        headers: {
          "Content-Type": "application/json",
        },
        withCredentials: true,
      }).then(function (res) {
        if (res.data.status) {
          //打印成功信息
          ElMessage({
            message: "登陆成功",
            type: "success",
          });
          // 输入框重置
          that.user = {};
          //关闭登陆弹窗
          that.$store.state.dialogVisible = false;
          //将信息填入vuex
          that.$store.state.user = res.data.data;
          //进行localstore的保存
          localStorage.setItem("user", JSON.stringify(res.data.data));
          //显示用户信息
          that.$store.state.isLogin = true;
          //跳转至首页
          that.$router.push("/home");
        } else {
          ElMessage({
            message: res.data.message,
            type: "error",
          });
          //将密码重置
          that.user.password = "";
          //将验证码重置
          that.user.captcha = "";
        }
        //刷新验证码
        that.reloadCaptcha();
      });
    },
    //修改密码
    updatePassword() {
      let that = this;
      this.updatePasswordUser.userId = this.$store.state.user.userId;
      axios({
        url: this.$store.state.localhost + "/user/update/password",
        method: "put",
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
        data: JSON.stringify(this.updatePasswordUser),
      }).then(function (res) {
        ElMessage({
          message: res.data.message,
          type: res.data.status ? "success" : "error",
        });
        if (res.data.status) {
          //关闭修改弹窗
          that.$store.state.updateDialog = false;
          //清空输入框
          that.updatePasswordUser = {};
        }
      });
    },
  },
  mounted() {
    this.$router.push("/home");
    //如果cookie存在，且本地记录存在，则表示用户登陆过
    if (
      Cookies.get("user") != undefined &&
      localStorage.getItem("user") != null
    ) {
      // 打印信息
      ElMessage({
        message: "用户已登陆",
        type: "success",
      });

      // 将用户信息设置到vuex中
      let user = JSON.parse(localStorage.getItem("user"));
      this.$store.state.user = user;

      // 将登陆状态设置为已登陆
      this.$store.state.isLogin = true;
    }
  },
  watch: {
    //监听验证码，如果验证码输入完成发一次请求
  },
};
</script>

<style>
.captcha:hover {
  cursor: pointer;
}
</style>
