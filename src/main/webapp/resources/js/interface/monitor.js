//监控界面js
$(function(){
	productScatter();
	mapStamp();
	interfaceScatter();
	daySuccessTrans();
	dayFailTrans();
	currentTrans();
})
//产品分布
function productScatter(){
	var productScatter = echarts.init(document.getElementById("productScatter"));	
	var option = {
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    calculable : true,
		    series : [
		        {
		            name:'产品分布',
		            type:'pie',
		            radius : '50%',
		            center: ['50%', '40%'],
		            itemStyle: {
		    			normal: {
		                 label:{
		                        show: true,
		                        position:'outer',
		                        formatter: "{b}:{c}"
		                    } 
		              }
			     }
		           
		        }
		    ]
		   
		};	
	$.ajax({
        url:"productGroupScatter.do",
          type:"POST",
          dataType:"JSON",
          before:function(){
        	  productScatter.showLoading();  
          },
          success:function(data){   
       	   option.series[0].data =data;
       	   productScatter.setOption(option,true);
       	   productScatter.hideLoading();
          }
	})

}

//区域调用量
function mapStamp(){
	var area = echarts.init(document.getElementById("area"));
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
		            barWidth: 15,
		            itemStyle: {
		            	normal: {
		    				color: '#45ccf2',
		    			}
	                    },
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            }
		           
		        }

		    ]
		};
		                    
	$.ajax({
        url:"areacodeGroupScatter.do",
          type:"POST",
          dataType:"JSON",
          success:function(data){ 
        	  option.xAxis[0].data =data.name;
       	      option.series[0].data =data.value;
       	   area.setOption(option,true);
          }
	})
}
//接口分布
function interfaceScatter(){
	var scatter = echarts.init(document.getElementById("scatter"));
	var option = {
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },		   
		    calculable : true,
		    series : [
		        {
		            name:'产品分布',
		            type:'pie',
		            radius : '50%',
		            center: ['50%', '40%'],
		            itemStyle: {
		    			normal: {
		                 label:{
		                        show: true,
		                        position:'outer',
		                        formatter: "{b}:{c}"
		                    } 
		              }
		            }
		        }
		    ]
		};
	//获取接口分布数据
	$.ajax({
        url:"interfaceScatter.do",
          type:"POST",
          dataType:"JSON",
          success:function(data){ 
       	      option.series[0].data =data;
         	  scatter.setOption(option,true);
          }
	})
	
	
}
//成功量情况
function daySuccessTrans(){
	var daySuccessTrans = echarts.init(document.getElementById("daySuccessTrans"));
	var option = {
			noDataLoadingOption: {
                text: '十分钟内接口未调用',
                effect: 'bubble',
                effectOption: {
                    effect: {
                        n: 0
                    }
                }
            },
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data:['成功量']
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
		        }
		    ],
		    series : [
		        {
		            name:'成功量',
		            type:'line',
		            stack: '总量',
		            itemStyle : { normal: {
		            	lineStyle:{
		            		 color: '#66ea69', 
		            	 },
		            	
		            	label : {show: true, position: 'insideRight'}}},
		        }

		    ]
		};		                      
	$.ajax({
        url:"daySuccessTrans.do",
          type:"POST",
          dataType:"JSON",
          success:function(data){ 
        	  option.yAxis[0].data =data.name;
       	      option.series[0].data =data.value;
       	   daySuccessTrans.setOption(option,true);
          }
	})
}
//失败量情况
function dayFailTrans(){
	var dayFailTrans = echarts.init(document.getElementById("dayFailTrans"));
	var option = {
			noDataLoadingOption: {
                text: '十分钟内接口不存在调用失败数据',
                effect: 'bubble',
                effectOption: {
                    effect: {
                        n: 0
                    }
                }
            },
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
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
		        }
		    ],
		    series : [
		        {
		            name:'失败量',
		            type:'line',
		            stack: '总量',
		            itemStyle : { normal: {
		            	 lineStyle:{
		            		 color: '#ff383e', 
		            	 },
		            	 areaStyle:{
		            		 color: '#ff383e', 
		            	 },
		            	 label : {show: true, position: 'insideRight'}
		            }},
		        }

		    ]
		};		                    
	$.ajax({
        url:"dayFailTrans.do",
          type:"POST",
          dataType:"JSON",
          success:function(data){ 
        	  option.yAxis[0].data =data.name;
       	      option.series[0].data =data.value;
       	   dayFailTrans.setOption(option,true);
          }
	})
}
//当月调用量
function currentTrans(){
	var currentTrans = echarts.init(document.getElementById('currentTrans')); 
	var option = {
		   
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['成功量']
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
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
		            barWidth: 18,
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
		        }
		       
		    ]
		};
		                    
	$.ajax({
        url:"currentTrans.do",
          type:"POST",
          dataType:"JSON",
          success:function(data){ 
        	  option.xAxis[0].data =data.name;
       	      option.series[0].data =data.success;
       	      currentTrans.setOption(option,true);
          }
	})
}