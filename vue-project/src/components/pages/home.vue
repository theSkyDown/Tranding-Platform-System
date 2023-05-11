<script setup>
import Traffic from './echarts/traffic.vue';
</script>

<template>
    <el-row :gutter="12" justify="space-evenly">

        <!-- 信息卡片 -->
        <el-col :span="8">
            <el-card shadow="hover">
                <template #header>
                <div class="card-header">
                    个人基本信息
                </div>
                </template>
                <div class="card-content">
                    <el-row>
                        <!-- 头像 -->
                        <el-col :span="12" id="card-avatar">
                            <el-avatar :size="70">
                                <img src="../../assets/avatar.png">
                            </el-avatar>
                        </el-col>
                        <!-- 用户名 -->
                        <el-col :span="12" class="card-username">
                            <span>用户名：Eccentric</span>
                        </el-col>
                    </el-row>
                    <el-row>
                        <!-- 基本信息 -->
                        <el-col :span="24" class="card-information">
                            <div>
                                <el-icon><User /></el-icon>
                                <span>角色：超级管理员</span>
                            </div>
                            
                            <div>
                                <el-icon><Message /></el-icon>
                                <span>联系方式：Eccentric0521@gmail.com</span>
                            </div>
                            
                            <div>
                                <el-icon><Postcard /></el-icon>
                                <span>签名：无</span>
                            </div>
                        </el-col>
                    </el-row>
                </div>
            </el-card>
        </el-col>


        <!--  访问量卡片  -->
        <el-col :span="8">
            <el-card shadow="hover">
                <template #header>
                <div class="card-header">
                    访问量
                </div>
                </template>
                <div class="card-content">
                    <Traffic/>
                </div>
            </el-card>

        </el-col>


        <!-- 商品类型卡片 -->
        <el-col :span="8">
            <el-card shadow="hover">
                <template #header>
                <div class="card-header">
                    商品种类
                </div>
                </template>
                <div class="card-content">

                </div>
            </el-card>
        </el-col>
    </el-row>




    <el-row>
        <!-- 销售额趋势卡片 -->
        <el-col :span="24">
            <el-card shadow="hover">
                <template #header>
                <div class="card-header">
                    销售额趋势
                </div>
                </template>
                <div class="card-content big-card-content">
                    
                </div>
            </el-card>
        </el-col>
    </el-row>

    
</template>






<style>
    /* 固定卡片的大小 */
    .card-header{
        height: 30px;
        text-align: center;
    }
    .card-content{
        height: 200px;
    }
    .big-card-content{
        height: 350px;
    }

    /* 设置间距 */
    .el-row{
        margin-bottom: 20px;
    }



    /* 个人基本信息卡片 */
    .card-content > .el-row{
        margin: 0;
    }
    .card-content > .el-row:first-child{
        height: 40%;
        overflow: hidden;
    }
    .card-content > .el-row:last-child{
        height: 60%;
        overflow: hidden;
    }
    .card-content .el-col{
        position: relative !important;
    }
    /* 头像 */
    .card-content .el-col > .el-avatar  {
        position: absolute !important;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
    }
    /* 用户名 */
    .card-content .el-row > .card-username{
        text-align: center;
        line-height: 80px;
    }
    /* 其他信息 */
    .card-content .el-row > .card-information > div{
        height: 35px;
        line-height: 35px;
        text-align: left;
    }


    



</style>