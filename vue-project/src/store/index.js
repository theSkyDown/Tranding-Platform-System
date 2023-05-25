import Vuex from 'vuex';

const store = new Vuex.Store({
  state: {
    //全局状态

    // 登陆弹窗显示状态
    dialogVisible : false,
    //是否登陆
    isLogin : false,
    //登陆后用户的信息
    user:{},
    //默认后端地址
    localhost:"http://localhost:8080",
    //修改弹窗显示状态
    updateDialog:false,
  

  },
});

export default store;
