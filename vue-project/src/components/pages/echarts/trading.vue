<!-- 
    
    一年销售量的echart图

-->
<template>
  <div id="trading-echart"></div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      option: {
        xAxis: {
          name: "月份",
          data: [
            "一月",
            "二月",
            "三月",
            "四月",
            "五月",
            "六月",
            "七月",
            "八月",
            "九月",
            "十月",
            "十一月",
            "十二月",
          ],
        },
        yAxis: {
          name: "金额",
        },
        series: [
          {
            type: "bar",
            data: [23, 24, 18, 25, 27, 28, 25],
          },
        ],
      },
    };
  },
  methods: {
    setChart() {
      //获取出售商品种类的数据
      let that = this;
      axios({
        url: this.$store.state.localhost + "/user/trading/echarts",
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        if (res.data.status) {
          // 给元素加上echart图
          var ec = that.$echarts.init(
            document.getElementById("trading-echart")
          );
          ec.setOption({
            xAxis: {
              name: "月份",
              data: [
                "一月",
                "二月",
                "三月",
                "四月",
                "五月",
                "六月",
                "七月",
                "八月",
                "九月",
                "十月",
                "十一月",
                "十二月",
              ],
            },
            yAxis: {
              name: "金额",
            },
            series: [
              {
                type: "bar",
                data: res.data.data,
              },
            ],
            tooltip: {
              trigger: "item",
              formatter: "今年 {b} 共收入 {c} 元",
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
#trading-echart {
  position: relative !important;
  left: 50% !important;
  top: 50% !important;
  transform: translate(-50%, -50%) !important;
  width: 1600px;
  height: 400px;
}
</style>
