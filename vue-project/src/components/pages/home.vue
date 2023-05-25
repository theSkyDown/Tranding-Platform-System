<!-- 

  主页面

 -->

<script setup>
import Traffic from "./echarts/traffic.vue";
import Classify from "./echarts/classify.vue";
import Trend from "./echarts/trend.vue";
</script>

<template>
  <el-row :gutter="12" justify="space-evenly">
    <!-- 信息卡片 -->
    <el-col :span="8" v-loading="!this.$store.state.isLogin">
      <el-card shadow="hover">
        <template #header>
          <div class="card-header">个人基本信息</div>
        </template>

        <div class="card-content father-div">
          <div class="card-content-left">
            <!-- 头像 -->
            <el-avatar :size="180">
              <!-- <img src="../../assets/avatar.png" /> -->
              <el-image
                :src="this.$store.state.user.avatar"
                :preview-src-list="[this.$store.state.user.avatar]"
                preview-teleported
              ></el-image>
            </el-avatar>
          </div>
          <div class="card-content-right">
            <div class="card-content-right-info">
              用户名：{{ this.$store.state.user.username }}
            </div>
            <div class="card-content-right-info">
              联系方式：{{ this.$store.state.user.phone }}
            </div>
            <div class="card-content-right-info">
              角色：{{ this.$store.state.user.roleName }}
            </div>
            <div class="card-content-right-info">
              性别：{{ this.$store.state.user.gender == 1 ? "男" : "女" }}
            </div>
            <div class="card-content-right-info">
              评分：<el-rate
                v-model="rate"
                disabled
                show-score
                score-template="{value} 分"
              />
            </div>
          </div>
        </div>
      </el-card>
    </el-col>

    <!--  访问量卡片  -->
    <el-col :span="8" v-loading="!this.$store.state.isLogin">
      <el-card shadow="hover">
        <template #header>
          <div class="card-header">访问量</div>
        </template>
        <div class="card-content">
          <Traffic />
        </div>
      </el-card>
    </el-col>

    <!-- 商品类型卡片 -->
    <el-col :span="8" v-loading="!this.$store.state.isLogin">
      <el-card shadow="hover">
        <template #header>
          <div class="card-header">商品种类</div>
        </template>
        <div class="card-content">
          <Classify />
        </div>
      </el-card>
    </el-col>
  </el-row>

  <el-row>
    <!-- 销售额趋势卡片 -->
    <el-col :span="24" v-loading="!this.$store.state.isLogin">
      <el-card shadow="hover">
        <template #header>
          <div class="card-header">销售额趋势</div>
        </template>
        <div class="card-content big-card-content">
          <Trend />
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      //分数
      rate: 0,
    };
  },
  methods: {
    getUserRate() {
      let that = this;
      axios({
        url: this.$store.state.localhost + "/user/rate",
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        if (res.data.status) {
          that.rate = res.data.data;
          that.rate = parseFloat(that.rate).toFixed(1);
        }
      });
    },
  },
  mounted() {
    //获取用户的分数
    this.getUserRate();
  },
};
</script>

<style>
/* 固定卡片的大小 */
.card-header {
  height: 30px;
  text-align: center;
}
.card-content {
  height: 200px;
}
.big-card-content {
  height: 350px;
}

/* 设置间距 */
.el-row {
  margin-bottom: 20px;
}

.father-div {
  position: relative !important;
}

/* 头像 */
.card-content-left {
  position: absolute !important;
  left: 0 !important;
  width: 40% !important;
  height: 100% !important;
}
.card-content-left .el-avatar {
  position: absolute !important;
  top: 50% !important;
  left: 50% !important;
  transform: translate(-50%, -50%) !important;
}

/* 用户信息 */
.card-content-right {
  overflow: hidden !important;
  position: absolute !important;
  right: 0 !important;
  width: 60% !important;
  height: 100% !important;
}
.card-content-right > .card-content-right-info {
  width: 100% !important;
  height: 20% !important;
  line-height: 40px !important;
  padding-left: 60px;
}
</style>
