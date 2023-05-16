import { createApp } from 'vue'
import App from './App.vue'
// element组件
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
//图标库
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
//Echart
import * as echarts from 'echarts'
//路由
import router from './router/index.js'
// vuex，全局状态管理
import store from './store/index.js'




const app = createApp(App)

//element组件
app.use(ElementPlus)

// 图标库
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

//配置全局的echarts
app.config.globalProperties.$echarts = echarts

//路由
app.use(router)

//vuex
app.use(store)



app.mount('#app')


