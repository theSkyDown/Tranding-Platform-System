<!-- 导航栏 -->
<template>

  <div class="header">
    <img class="logo" src="/logo.png" />



    <el-dropdown>
      <div class="information">
        <el-avatar>
          <img src="../assets/avatar.png">
        </el-avatar>
        <div class="username">Eccentric</div>
      </div>
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

          <el-dropdown-item>
            <el-icon><Back /></el-icon>
            <span>退出账号</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>


  </div>
 
</template>

<style>
  .header{
    position: relative;
    left: 50%;
    transform: translateX(-50%);
    height: 100%;
    width: 100%;
  }

  /* logo图标 */
  .logo{
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    width: 200px;
    height: 55px;
  }
  .logo:hover{
    cursor: pointer;
  }

  /* 下拉菜单 */
  .el-dropdown{
    position: absolute !important;
    right: 0px;
    top: 50%;
    transform: translateY(-50%);
  }
  .el-dropdown:hover{
    cursor: pointer;
  }
  :focus{
    outline: none;
  }
  /* 用户信息 */
  .information{
    position: relative;
    width: 150px;
    height:60px;
  }

  /* 头像 */
  .el-avatar{
    position: absolute !important;
    left: 5px;
    top: 50%;
    transform: translateY(-50%);
  }

  /* 用户名 */
  .username{
    position: absolute;
    right: 0;
    top: 50%;
    transform: translateY(-50%);
    width: 100px;
    height: 40px;
    line-height: 40px;
    text-align: center;
  }



</style>


