<template>
    <div>
        <div ref="myechart" style="width: 600px; height: 400px"></div>
    </div>
</template>

   
<script>
export default {
	mounted() {
		this.setChart()
	},
	methods: {
		setChart() {
			var myChart = this.$echarts.init(this.$refs.myechart)
			// 指定图表的配置项和数据
			var option = {
				title: {
					text: 'Temperature Change in the Coming Week'
				},
				tooltip: {
					trigger: 'axis'
				},
				legend: {},
				toolbox: {
					show: true,
					feature: {
						dataZoom: {
							yAxisIndex: 'none'
						},
						dataView: { readOnly: false },
						magicType: { type: ['line', 'bar'] },
						restore: {},
						saveAsImage: {}
					}
				},
				xAxis: {
					type: 'category',
					boundaryGap: false,
					data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
				},
				yAxis: {
					type: 'value',
					axisLabel: {
					formatter: '{value} °C'
					}
				},
				series: [
					{
						name: 'Highest',
						type: 'line',
						data: [10, 11, 13, 11, 12, 12, 9],
						markPoint: {
							data: [
								{ type: 'max', name: 'Max' },
								{ type: 'min', name: 'Min' }
							]
						},
						markLine: {
							data: [{ type: 'average', name: 'Avg' }]
						}
					},
					{
					name: 'Lowest',
					type: 'line',
					data: [1, -2, 2, 5, 3, 2, 0],
					markPoint: {
						data: [{ name: '周最低', value: -2, xAxis: 1, yAxis: -1.5 }]
					},
					markLine: {
						data: [
						{ type: 'average', name: 'Avg' },
						[
							{
							symbol: 'none',
							x: '90%',
							yAxis: 'max'
							},
							{
							symbol: 'circle',
							label: {
								position: 'start',
								formatter: 'Max'
							},
							type: 'max',
							name: '最高点'
							}
						]
						]
					}
					}
				]
			};
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option)
		},
	},
}
</script>

   