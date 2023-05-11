<!-- 销售额趋势的echart图 -->
<template>
    <div id="trend-echart" style="width:1600px ; height: 400px;"></div>
</template>

<script>
    export default{
        data(){
            return{
                option:{
                    xAxis: {
                        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                    },
                    yAxis: {},
                    series: [
                        {
                        type: 'bar',
                        data: [23, 24, 18, 25, 27, 28, 25]
                        }
                    ]
                }
            }
        },
        methods:{
            setChart(){
                var ec = this.$echarts.init(document.getElementById('trend-echart'))
                ec.setOption(this.option);
            }
        },
        mounted(){
            this.setChart();
        }
    }

</script>