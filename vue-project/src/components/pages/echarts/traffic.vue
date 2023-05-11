<!-- 访问量的echart图 -->
<template>
    <div id="traffic-echart" style="width:500px ; height: 200px;"></div>
</template>

<script>
    export default{
        data(){
            return{
                option:{
                    tooltip: {
                        trigger: 'axis',
                        formatter: function (params) {
                            return params[0].name + ': ' + params[0].data;
                        }
                    },
                    xAxis: {
                        type: 'category',
                        data:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                        axisLabel: { 
                            show: false
                        },
                        // 设置横轴指示器的样式 
                        show: true,
                        axisPointer: { 
                            type: 'line',
                            label: {
                                show: false
                            }
                        }
                    },
                    yAxis: {
                        type: 'value',
                        axisLabel: { 
                            show: false
                        },
                    },
                    series: [
                        {
                            data: [820, 932, 901, 934, 1290, 1330, 1320,820, 932, 901, 934, 1290],
                            type: 'line',
                            smooth: true
                        }
                    ]
                }
            }
        },
        methods:{
            setChart(){
                var ec = this.$echarts.init(document.getElementById('traffic-echart'))
                ec.setOption(this.option);
            }
        },
        mounted(){
            this.setChart();
        }
    }

</script>``