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
                        data:['2023/9/10', '2023/9/11', '2023/9/12', '2023/9/13', '2023/9/14', '2023/9/15', '2023/9/16', '2023/9/17', '2023/9/18', '2023/9/19', '2023/9/20', '2023/9/21','2023/9/22','2023/9/23'],
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
                            data: [820, 932, 901, 934, 1290, 1330, 1320,820, 932, 901, 934, 1290,899,1000],
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