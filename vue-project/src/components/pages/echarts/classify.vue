<!-- 
    
    出售分类的echart图

-->
<template>
  <div id="classify-echart"></div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {};
  },
  methods: {
    setChart() {
      //获取出售商品种类的数据
      let that = this;
      axios({
        url: this.$store.state.localhost + "/user/sale/category/echarts",
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        if (res.data.status) {
          // 给元素加上echart图
          var ec = that.$echarts.init(
            document.getElementById("classify-echart")
          );
          ec.setOption({
            series: [
              {
                type: "pie",
                stillShowZeroSum: false,
                data: res.data.data,
              },
            ],
            tooltip: {
              //提示框组件
              trigger: "item", //item数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
              axisPointer: {
                // 坐标轴指示器，坐标轴触发有效
                type: "shadow", // 默认为直线，可选为：'line' | 'shadow'
              },
              formatter: "{b} : {c} <br/>百分比 : {d}%", //{a}（系列名称），{b}（数据项名称），{c}（数值）, {d}（百分比）
            },
          });
        }
      });
    },
    echartsMouse() {},
  },
  mounted() {
    this.setChart();
  },
};
</script>
<style>
#classify-echart {
  position: relative !important;
  left: 50% !important;
  top: 50% !important;
  transform: translate(-50%, -50%) !important;
  width: 500px;
  height: 200px;
}
</style>
