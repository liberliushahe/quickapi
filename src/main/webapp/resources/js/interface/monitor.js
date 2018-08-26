//监控界面js
$(function(){
	currentTrans();
	mapStamp();
	dayTrans();
	transScatter();
})
function mapStamp(){
	var mychar = echarts.init(document.getElementById("area"));
	var option = {
		    legend: {
		        data:['调用量']
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : ['兰州','天水','武威','张掖','酒泉','甘南','兰州','天水','武威','张掖','酒泉','甘南']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'调用量',
		            type:'bar',
		            itemStyle: {
		            	normal: {
		    				color: '#45ccf2',
		    			}
	                    },
		            data:[200, 400, 7, 23, 25, 76, 135, 162, 32, 20, 6, 3],
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            }
		           
		        }

		    ]
		};
		                    
	mychar.setOption(option);
}
function transScatter(){
	var scatter = echarts.init(document.getElementById("scatter"));
	var option = {
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient : 'vertical',
		        x : 'left',
		        data:['客户信息','用户信息','短信发送','volte信息','计费清单']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {
		                show: true, 
		                type: ['pie', 'funnel'],
		                option: {
		                    funnel: {
		                        x: '25%',
		                        width: '50%',
		                        funnelAlign: 'center',
		                        max: 1548
		                    }
		                }
		            }
		         
		           
		        }
		    },
		    calculable : true,
		    series : [
		        {
		            name:'访问来源',
		            type:'pie',
		            radius : ['50%', '70%'],
		            itemStyle : {
		                normal : {
		                    label : {
		                        show : false
		                    },
		                    labelLine : {
		                        show : false
		                    }
		                },
		                emphasis : {
		                    label : {
		                        show : true,
		                        position : 'center',
		                        textStyle : {
		                            fontSize : '30',
		                            fontWeight : 'bold'
		                        }
		                    }
		                }
		            },
		            data:[
		                {value:335, name:'客户信息'},
		                {value:310, name:'用户信息'},
		                {value:234, name:'短信发送'},
		                {value:135, name:'volte信息'},
		                {value:1548, name:'计费清单'}
		            ]
		        }
		    ]
		};
	scatter.setOption(option);	                    
}
//近期调用情况
function dayTrans(){
	var dayTrans = echarts.init(document.getElementById("dayTrans"));
	var option = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data:['失败量']
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    yAxis : [
		        {
		            type : 'category',
		            data : ['客户信息','用户信息','用户信息','用户信息','用户信息','用户信息','用户信息','客户信息','用户信息','用户信息','用户信息','用户信息','用户信息','用户信息','客户信息','用户信息','用户信息','用户信息','用户信息','用户信息','用户信息','客户信息','用户信息','用户信息','用户信息','用户信息','用户信息','用户信息']
		        }
		    ],
		    series : [
		        {
		            name:'失败量',
		            type:'bar',
		            itemStyle: {
		            	normal: {
		    				color: '#45ccf2',
		    			}
	                    },
		            stack: '总量',
		            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
		            data:[120, 132, 101, 134, 90, 230, 210,320, 302, 301, 334, 390, 330, 320,120, 132, 101, 134, 90, 230, 210,320, 302, 301, 334, 390, 330, 320]
		        }

		    ]
		};
		                    
    dayTrans.setOption(option);                    
}


function currentTrans(){
	var currentTrans = echarts.init(document.getElementById('currentTrans')); 
	var option = {
		    title : {
		        text: '当月接口调用情况',
		        subtext: '接口监控界面'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['成功量','失败量']
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : ['客户信息查询','用户信2息查询','用户1信息查询','用户信息查询','用户信息查询','用户信息查询','用户信息查询','用户信息查询','用户信息查询','用户信息查询','用户信息查询','用户信息查询','客户信息查询','用户信息查询','用户信息查询','用户信息查询','用户信息查询','用户信息查询','用户信息查询','用户信息查询','用户信息查询','用户信息查询','用户信息查询','用户信息查询']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'成功量',
		            type:'bar',
		            itemStyle: {
		            	normal: {
		    				color: '#45ccf2',
		    			}
	                    },
		            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3,2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            }
		        },
		        {
		            name:'失败量',
		            type:'bar',
		            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3,2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
		            markPoint : {
		                data : [
		                    {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
		                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name : '平均值'}
		                ]
		            }
		        }
		    ]
		};
		                    
	currentTrans.setOption(option);
}