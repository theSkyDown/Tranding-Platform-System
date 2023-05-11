<script setup>
  import Header from './components/header.vue'
  import Aside from './components/aside.vue'
  import Main from './components/main.vue'
</script>

<template>

<!-- element 布局 -->
  <el-container>

    <!-- 头部 -->
    <el-header>
      <Header/>
    </el-header>

    <!-- 主体内容 -->
    <el-container>

      <!-- 侧边栏 -->
      <el-aside>
        <Aside/>
      </el-aside>


      <!-- 内容 -->
      <el-main>
        <Main />
      </el-main>

    </el-container>

  </el-container>

</template>  


<style>
  *{
    padding: 0; 
    margin: 0;
  }
  .el-header{
    border-bottom: 1px solid grey;
  }
  .el-aside{
    height: 850px;
  }
  .el-main{
    height: 850px;
  }
</style>