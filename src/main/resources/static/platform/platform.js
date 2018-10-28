$(document).ready(
	function () {
		var mapChart = echarts.init(document.getElementById('map'));
		var name_title = ""
		var subname = ''
		var nameColor = "rgb(255, 215, 0)"
		var name_fontFamily = '宋体'
		var name_fontSize = 35
		var mapName = 'china'
		var data = []
		var geoCoordMap = {};
		var toolTipData = [];

		/* 获取地图数据 */
		mapChart.showLoading();
		var mapFeatures = echarts.getMap(mapName).geoJson.features;
		mapChart.hideLoading();
		mapFeatures.forEach(function (v) {
			// 地区名称
			var name = v.properties.name;
			// 地区经纬度
			geoCoordMap[name] = v.properties.cp;
			data.push({
				name: name,
				value: Math.round(Math.random() * 100 + 10)
			})
			toolTipData.push({
				name: name,
				value: [{
					name: "鲜花",
					value: Math.round(Math.random() * 100 + 10)
				}, {
					name: "星星",
					value: Math.round(Math.random() * 100 + 10)
				}, {
					name: "香蕉",
					value: Math.round(Math.random() * 100 + 10)
				}, {
					name: "嫌弃",
					value: Math.round(Math.random() * 100 + 10)
				}]
			})
		});

		var max = 480, min = 9; // todo
		var maxSize4Pin = 50, minSize4Pin = 20;

		var convertData = function (data) {
			var res = [];
			for (var i = 0; i < data.length; i++) {
				var geoCoord = geoCoordMap[data[i].name];
				if (geoCoord) {
					res.push({
						name: data[i].name,
						value: geoCoord.concat(data[i].value),
					});
				}
			}
			return res;
		};
		option = {
			title: {
				text: name_title,
				subtext: subname,
				x: 'center',
				textStyle: {
					color: nameColor,
					fontFamily: name_fontFamily,
					fontSize: name_fontSize
				}
			},
			tooltip: {
				trigger: 'item',
				formatter: function (params) {
					if (typeof (params.value)[2] == "undefined") {
						var toolTiphtml = ''
						for (var i = 0; i < toolTipData.length; i++) {
							if (params.name == toolTipData[i].name) {
								toolTiphtml += toolTipData[i].name
									+ ':<br>'
								for (var j = 0; j < toolTipData[i].value.length; j++) {
									toolTiphtml += toolTipData[i].value[j].name
										+ ':'
										+ toolTipData[i].value[j].value
										+ "<br>"
								}
							}
						}
						console.log(toolTiphtml)
						// console.log(convertData(data))
						return toolTiphtml;
					} else {
						var toolTiphtml = ''
						for (var i = 0; i < toolTipData.length; i++) {
							if (params.name == toolTipData[i].name) {
								toolTiphtml += toolTipData[i].name
									+ ':<br>'
								for (var j = 0; j < toolTipData[i].value.length; j++) {
									toolTiphtml += toolTipData[i].value[j].name
										+ ':'
										+ toolTipData[i].value[j].value
										+ "<br>"
								}
							}
						}
						console.log(toolTiphtml)
						// console.log(convertData(data))
						return toolTiphtml;
					}
				}
			},
			legend: {
				orient: 'vertical',
				y: 'bottom',
				x: 'right',
				data: ['credit_pm2.5'],
				textStyle: {
					color: '#fff'
				}
			},
			visualMap: {
				show: false,
				min: 0,
				max: 500,
				left: 'left',
				top: 'bottom',
				text: ['高', '低'], // 文本，默认为数值文本
				calculable: true,
				seriesIndex: [1],
				inRange: {
					// color: ['#3B5077', '#031525'] // 蓝黑
					// color: ['#ffc0cb', '#800080'] // 红紫
					// color: ['#3C3B3F', '#605C3C'] // 黑绿
					// color: ['#0f0c29', '#302b63', '#24243e'] //
					// 黑紫黑
					// color: ['#23074d', '#cc5333'] // 紫红
					// color: ['#00467F', '#A5CC82'] // 蓝绿
					// color: ['#1488CC', '#2B32B2'] // 浅蓝
					// color : [ '#00467F', '#A5CC82', '#ffc0cb' ]
					color: ['#00467F', '#A5CC82']
					// 蓝绿
					// 蓝绿红
					// color: ['#00467F', '#A5CC82'] // 蓝绿
					// color: ['#00467F', '#A5CC82'] // 蓝绿
					// color: ['#00467F', '#A5CC82'] // 蓝绿

				}
			},
			/* 工具按钮组 */
			toolbox: {
				show: false,
				orient: 'vertical',
				left: 'right',
				top: 'center',
				feature: {

					dataView: {
						readOnly: false
					},
					restore: {},
					saveAsImage: {}
				}
			},
			geo: {
				show: true,
				map: mapName,
				label: {
					normal: {
						show: false
					},
					emphasis: {
						show: false,
					}
				},
				roam: true,
				itemStyle: {
					normal: {
						areaColor: '#031525',
						borderColor: '#fff',// 省份边界颜色
						borderWidth: 2
						// 省份边界宽度
					},
					emphasis: {
						areaColor: '#2B91B7',
					}
				}
			},
			series: [
				{
					name: '散点',
					type: 'scatter',
					coordinateSystem: 'geo',
					data: convertData(data),
					symbolSize: function (val) {
						return val[2] / 10;
					},
					label: {
						normal: {
							formatter: '{b}',
							position: 'right',
							show: true
						},
						emphasis: {
							show: true
						}
					},
					itemStyle: {
						normal: {
							color: '#05C3F9'
						}
					}
				},
				{
					type: 'map',
					map: mapName,
					geoIndex: 0,
					aspectScale: 0.75, // 长宽比
					showLegendSymbol: false, // 存在legend时显示
					label: {
						normal: {
							show: true
						},
						emphasis: {
							show: false,
							textStyle: {
								color: '#fff'
							}
						}
					},
					roam: true,
					itemStyle: {
						normal: {
							areaColor: '#031525',
							borderColor: '#3B5077',
						},
						emphasis: {
							areaColor: '#2B91B7'
						}
					},
					animation: false,
					data: data
				},
				{
					name: '点',
					type: 'scatter',
					coordinateSystem: 'geo',
					symbol: 'pin', // 气泡
					symbolSize: function (val) {
						var a = (maxSize4Pin - minSize4Pin)
							/ (max - min);
						var b = minSize4Pin - a * min;
						b = maxSize4Pin - a * max;
						return a * val[2] + b;
					},
					label: {
						normal: {
							show: true,
							formatter: function (params) {
								return params.data.value[2]
							},
							textStyle: {
								color: '#fff',
								fontSize: 9,
							}
						}
					},
					itemStyle: {
						normal: {
							color: '#F62157', // 标志颜色
						}
					},
					zlevel: 6,
					data: convertData(data),
				},
				{
					name: 'Top 5',
					type: 'effectScatter',
					coordinateSystem: 'geo',
					data: convertData(data.sort(
						function (a, b) {
							return b.value - a.value;
						}).slice(0, 5)),
					symbolSize: function (val) {
						return val[2] / 10;
					},
					showEffectOn: 'render',
					rippleEffect: {
						brushType: 'stroke'
					},
					hoverAnimation: true,
					label: {
						normal: {
							formatter: '{b}',
							position: 'right',
							show: true
						}
					},
					itemStyle: {
						normal: {
							color: '#05C3F9',
							shadowBlur: 10,
							shadowColor: '#05C3F9'
						}
					},
					zlevel: 1
				},

			]
		};
		mapChart.setOption(option);
		// 取代传统设置，加入自动适应屏幕代码
		// window.addEventListener("resize", function() {
		// option.chart.resize();
		// });

	})