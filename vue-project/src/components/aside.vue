<!-- 侧边栏 -->
<template>
    <!-- 菜单 -->
    <el-menu
    default-active="2"
    class="el-menu-vertical-demo"
    >
        <el-menu-item index="1" v-on:click="toHome">
            <el-icon><House /></el-icon>
            <span>首 页</span>
        </el-menu-item>


        <el-menu-item index="2">
            <el-icon><ShoppingTrolley /></el-icon>
            <span>商品交易</span>
        </el-menu-item>


        <el-sub-menu index="3">
            <template #title>
                <el-icon><setting /></el-icon>
                <span>系统管理</span>
            </template>

            <el-menu-item index="3-1">
                <el-icon><Operation /></el-icon>
                <span>商品信息管理</span>                
            </el-menu-item>

            <el-menu-item index="3-2">
                <el-icon><User /></el-icon>
                <span>用户管理</span>
            </el-menu-item>

            <el-menu-item index="3-4">
                <el-icon><GoodsFilled /></el-icon>
                <span>订单管理</span>
            </el-menu-item>

            <el-menu-item index="3-3">
                <el-icon><Lock /></el-icon>
                <span>权限管理</span>
            </el-menu-item>

            <el-menu-item index="3-4">
                <el-icon><Folder /></el-icon>
                <span>日志管理</span>
            </el-menu-item>
        </el-sub-menu>

        <el-menu-item index="4">
            <el-icon><ChatDotRound /></el-icon>
            <span>信息反馈</span>
        </el-menu-item>

        <el-menu-item index="5" v-on:click="toTest">
            <el-icon><Opportunity /></el-icon>
            <span>测 试</span>
        </el-menu-item>

    </el-menu>
</template>

<script>
export default{
    data(){
        return {

        }
    },
    methods:{
        //主页
        toHome(){
            this.$router.push("/home")
        },
        //跳转测试页面
        toTest(){
            this.$router.push('/echartTest')
        }            
    },
}
</script>


<style>
    .el-menu{
        height: 100%;
    }
</style>
