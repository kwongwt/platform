$(document).ready(function() {
    var mapChart = echarts.init(document.getElementById("map"));
    var mapName = "china";
    var data = []; //地图内容数据
    var geoCoordMap = {}; //geo地图数据

    mapChart.showLoading();
    var mapFeatures = echarts.getMap(mapName).geoJson.features;
    var data = [
        { name: "北京", value: 177 },
        { name: "天津", value: 42 },
        { name: "河北", value: 102 },
        { name: "山西", value: 81 },
        { name: "内蒙古", value: 47 },
        { name: "辽宁", value: 67 },
        { name: "吉林", value: 82 },
        { name: "黑龙江", value: 66 },
        { name: "上海", value: 24 },
        { name: "江苏", value: 92 },
        { name: "浙江", value: 114 },
        { name: "安徽", value: 109 },
        { name: "福建", value: 116 },
        { name: "江西", value: 91 },
        { name: "山东", value: 119 },
        { name: "河南", value: 137 },
        { name: "湖北", value: 116 },
        { name: "湖南", value: 114 },
        { name: "重庆", value: 91 },
        { name: "四川", value: 125 },
        { name: "贵州", value: 62 },
        { name: "云南", value: 83 },
        { name: "西藏", value: 9 },
        { name: "陕西", value: 80 },
        { name: "甘肃", value: 56 },
        { name: "青海", value: 10 },
        { name: "宁夏", value: 18 },
        { name: "新疆", value: 67 },
        { name: "广东", value: 123 },
        { name: "广西", value: 59 },
        { name: "海南", value: 14 }
    ];
    mapChart.hideLoading();
    mapFeatures.forEach(function(v) {
        // 地区名称
        var name = v.properties.name;
        // 地区经纬度
        geoCoordMap[name] = v.properties.cp;
    });

    //气泡大小区间
    var max = 480,
        min = 9;
    var maxSize4Pin = 100,
        minSize4Pin = 20;

    //数据格式化
    var convertData = function(data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                });
            }
        }
        return res;
    };
    //地图配置
    option = {
        //视觉映射组件
        visualMap: {
            show: true,
            min: 0,
            max: 100,
            left: "left",
            top: "bottom",
            text: ["高", "低"], // 文本，默认为数值文本
            calculable: true,
            seriesIndex: [1],
            inRange: {
                color: ["#CCCC99", "#006633"]
            }
        },
        //地图坐标系组建
        geo: {
            show: true,
            map: mapName,
            label: {
                normal: {
                    show: false
                },
                emphasis: {
                    show: false
                }
            },
            //开放鼠标缩放
            roam: false,
            //地图颜色配置
            itemStyle: {
                normal: {
                    //区域和区域线条颜色（无数据时候）
                    areaColor: "#CCCC99",
                    borderColor: "#fff"
                },
                emphasis: {
                    //悬停颜色
                    areaColor: "#009966"
                }
            }
        },
        //数据展示
        series: [
            {
                name: "散点",
                type: "scatter",
                coordinateSystem: "geo",
                data: convertData(data),
                symbolSize: function(val) {
                    return val[2] / 10;
                },
                label: {
                    normal: {
                        formatter: "{b}",
                        position: "right",
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: "#05C3F9"
                    }
                }
            },
            //地图颜色深浅
            {
                type: "map",
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
                            color: "#fff"
                        }
                    }
                },
                roam: true,
                itemStyle: {
                    normal: {
                        areaColor: "#031525",
                        borderColor: "#3B5077"
                    },
                    emphasis: {
                        areaColor: "#2B91B7"
                    }
                },
                animation: false,
                data: data
            },
            //红色气泡和值
            {
                name: "气泡",
                type: "scatter",
                coordinateSystem: "geo",
                symbol: "pin", // 气泡
                symbolSize: function(val) {
                    var a = (maxSize4Pin - minSize4Pin) / (max - min);
                    var b = minSize4Pin - a * min;
                    b = maxSize4Pin - a * max;
                    return a * val[2] + b;
                },
                label: {
                    normal: {
                        show: true,
                        formatter: function(params) {
                            return params.data.value[2];
                        },
                        textStyle: {
                            color: "#fff",
                            fontSize: 9
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        color: "#F62157" // 标志颜色
                    }
                },
                zlevel: 6,
                data: convertData(data)
            },
            //前5项，黄色标记
            {
                name: "Top 5",
                type: "effectScatter",
                coordinateSystem: "geo",
                data: convertData(
                    data
                        .sort(function(a, b) {
                            return b.value - a.value;
                        })
                        .slice(0, 5)
                ),
                symbolSize: function(val) {
                    return val[2] / 10;
                },
                showEffectOn: "render",
                rippleEffect: {
                    brushType: "stroke"
                },
                hoverAnimation: true,
                label: {
                    normal: {
                        formatter: "{b}",
                        position: "right",
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: "yellow",
                        shadowBlur: 10,
                        shadowColor: "yellow"
                    }
                },
                zlevel: 1
            }
        ]
    };
    mapChart.setOption(option);
});
