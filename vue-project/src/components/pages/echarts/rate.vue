<!-- 
    
    一年销售额趋势的echart图
    
 -->
<template>
  <div id="rate-echart"></div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {};
  },
  methods: {
    setChart() {
      let that = this;

      axios({
        url: this.$store.state.localhost + "/user/rate/echarts",
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        if (res.data.status) {
          // 给元素加上echart图
          var ec = that.$echarts.init(document.getElementById("rate-echart"));
          ec.setOption({
            xAxis: {
              name: "分",
              data: ["0", "1", "2", "3", "4", "5"],
            },
            yAxis: {
              name: "人",
            },
            series: [
              {
                type: "line",
                stillShowZeroSum: false,
                data: res.data.data,
              },
            ],
            tooltip: {
              trigger: "item",
              formatter: "评分为 {b} 分有 {c} 人",
            },
          });
        }
      });
    },
  },
  mounted() {
    this.setChart();
  },
};
</script>
<style>
#rate-echart {
  position: relative !important;
  left: 50% !important;
  top: 50% !important;
  transform: translate(-50%, -50%) !important;
  width: 300px;
  height: 200px;
}
</style>
