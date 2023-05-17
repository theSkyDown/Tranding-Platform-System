<!-- 
    
    分类的echart图

-->
<template>
  <div id="classify-echart" style="width: 500px; height: 200px"></div>
</template>

<script>
export default {
  data() {
    return {
      option: {
        series: [
          {
            type: "pie",
            data: [
              {
                value: 1548,
                name: "服装",
              },
              {
                value: 900,
                name: "食品",
              },
              {
                value: 1230,
                name: "图书",
              },
              {
                value: 2312,
                name: "科技",
              },
            ],
          },
        ],
      },
    };
  },
  methods: {
    setChart() {
      var ec = this.$echarts.init(document.getElementById("classify-echart"));
      ec.setOption(this.option);
    },
  },
  mounted() {
    this.setChart();
  },
};
</script>
``
