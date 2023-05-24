<!-- 
  
  侧边栏 

-->
<template>
  <!-- 菜单 -->
  <el-menu default-active="2" class="el-menu-vertical-demo">
    <el-menu-item index="1" v-on:click="toHome">
      <el-icon><House /></el-icon>
      <span>首 页</span>
    </el-menu-item>

    <el-sub-menu index="2">
      <template #title>
        <el-icon><ShoppingCart /></el-icon>
        <span>商品交易</span>
      </template>

      <el-menu-item index="2-1" v-on:click="toGoodTrading">
        <el-icon><Goods /></el-icon>
        <span>商品购买</span>
      </el-menu-item>

      <el-menu-item index="2-3" v-on:click="toOrderTrading">
        <el-icon><GoodsFilled /></el-icon>
        <span>我的订单</span>
      </el-menu-item>

      <el-menu-item index="2-4" v-on:click="toSaleTrading">
        <el-icon><Sell /></el-icon>
        <span>我的售出</span>
      </el-menu-item>
    </el-sub-menu>

    <el-sub-menu index="3">
      <template #title>
        <el-icon><setting /></el-icon>
        <span>系统管理</span>
      </template>

      <el-menu-item index="3-1" v-on:click="toGoodAdmin">
        <el-icon><Operation /></el-icon>
        <span>商品信息管理</span>
      </el-menu-item>

      <el-menu-item index="3-2" v-on:click="toCategoryAdmin">
        <el-icon><Histogram /></el-icon>
        <span>分类管理</span>
      </el-menu-item>

      <el-menu-item index="3-3" v-on:click="toOrderAdmin">
        <el-icon><GoodsFilled /></el-icon>
        <span>订单管理</span>
      </el-menu-item>

      <el-menu-item index="3-4" v-on:click="toUserAdmin">
        <el-icon><User /></el-icon>
        <span>用户管理</span>
      </el-menu-item>

      <el-menu-item index="3-5" v-on:click="toRoleAdmin">
        <el-icon><Avatar /></el-icon>
        <span>角色管理</span>
      </el-menu-item>

      <el-menu-item index="3-6" v-on:click="toModuleAdmin">
        <el-icon><Box /></el-icon>
        <span>资源管理</span>
      </el-menu-item>

      <el-menu-item index="3-7" v-on:click="toPermissionAdmin">
        <el-icon><Lock /></el-icon>
        <span>权限管理</span>
      </el-menu-item>

      <el-menu-item index="3-8">
        <el-icon><Folder /></el-icon>
        <span>日志管理</span>
      </el-menu-item>
    </el-sub-menu>

    <el-menu-item index="5" v-on:click="toTest">
      <el-icon><Opportunity /></el-icon>
      <span>测 试</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {
    //主页
    toHome() {
      this.$router.push("/home");
    },
    //跳转到商品交易页面
    toGoodTrading() {
      this.$router.push("/goodTrading");
    },
    //跳转到我的订单页面
    toOrderTrading() {
      this.$router.push("/orderTrading");
    }, //跳转到我的出售页面
    toSaleTrading() {
      this.$router.push("/saleTrading");
    },

    //跳转到商品管理页面
    toGoodAdmin() {
      this.$router.push("/goodAdmin");
    },
    //跳转到分类管理页面
    toCategoryAdmin() {
      this.$router.push("/categoryAdmin");
    },
    //跳转到订单管理页面
    toOrderAdmin() {
      this.$router.push("/orderAdmin");
    },
    //跳转到用户管理页面
    toUserAdmin() {
      this.$router.push("/userAdmin");
    },
    //跳转到角色管理
    toRoleAdmin() {
      this.$router.push("/roleAdmin");
    },
    //跳转到资源管理页面
    toModuleAdmin() {
      this.$router.push("/moduleAdmin");
    },
    //跳转到权限管理
    toPermissionAdmin() {
      this.$router.push("/permissionAdmin");
    },
    //跳转测试页面
    toTest() {
      this.$router.push("/echartTest");
    },
  },
};
</script>

<style>
.el-menu {
  height: 100%;
}
</style>
