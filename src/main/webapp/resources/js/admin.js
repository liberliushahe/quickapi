$(function(){
	weekChart();
	currentTrans();
	currentCpu();
	memoryInfo();
	getCpuDetail();
	getMemoryDetail();
	currentNetInfo();
});
//周调用量
function weekChart(){
var lineChart = echarts.init(document.getElementById('lineChart')); 
var option = {
	  
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['本周最高','本周最低']
	    },
	    toolbox: {
	        show : true
	     
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : ['周一','周二','周三','周四','周五','周六','周日']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value} °C'
	            }
	        }
	    ],
	    series : [
	        {
	            name:'最高调用量',
	            type:'line',
	            data:[11, 11, 15, 13, 12, 13, 10],
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
	            name:'最低调用量',
	            type:'line',
	            data:[1, -2, 2, 5, 3, 2, 0],
	            markPoint : {
	                data : [
	                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
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
lineChart.setOption(option);
}      

//CPU实时信息
function currentCpu(){
var memoryChart = echarts.init(document.getElementById('cpuChart')); 	
option = {
	    tooltip : {
	        formatter: "{a} <br/>{b} : {c}%"
	    },
	    toolbox: {
	        show : true
	       
	    },
	    series : [
	        {
	            name:'使用量',
	            type:'gauge',
	            detail : {formatter:'{value}%'},
	            data:[{value: 50, name: 'CPU使用量'}]
	        }
	    ]
	};


	timeTicket = setInterval(function (){
		//异步请求数据
		 $.ajax({
	         url:"currentmemory.do",
	           type:"POST",
	           dataType:"JSON",
	           success:function(data){ 
	        	   option.series[0].data[0].value =(data.usedpercent) - 0;
	       	       memoryChart.setOption(option,true);
	        	  
	           },
	           error:function(data){
	        	   console.log('error');
	           }
	          
	           })
	    
	},2000);
	                    	                		
}
//实时调用量
function currentTrans(){
	var currentChart = echarts.init(document.getElementById('currentChart')); 	

	option = {

		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['实时调用量']
		    },
		    toolbox: {
		        show : true
		      
		    },
		    dataZoom : {
		        show : false,
		        start : 0,
		        end : 100
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : true,
		            data : (function (){
		                var now = new Date();
		                var res = [];
		                var len = 10;
		                while (len--) {
		                    res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
		                    now = new Date(now - 2000);
		                }
		                return res;
		            })()
		        },
		        {
		            type : 'category',
		            boundaryGap : true,
		            data : (function (){
		                var res = [];
		                var len = 10;
		                while (len--) {
		                    res.push(len + 1);
		                }
		                return res;
		            })()
		        }
		    ],
		    yAxis : [

		        {
		            type : 'value',
		            scale: true,
		            name : '预购量',
		            boundaryGap: [0.2, 0.2]
		        }
		    ],
		    series : [
		        {
		            name:'最新成交价',
		            type:'line',
		            data:(function (){
		                var res = [];
		                var len = 10;
		                while (len--) {
		                    res.push((Math.random()*10 + 5).toFixed(1) - 0);
		                }
		                return res;
		            })()
		        }
		    ]
		};
	currentChart.setOption(option);		

		var lastData = 11;
		var axisData;
		var timeTicket;
		clearInterval(timeTicket); 
		timeTicket = setInterval(function (){
		    lastData += Math.random() * ((Math.round(Math.random() * 10) % 2) == 0 ? 1 : -1);
		    lastData = lastData.toFixed(1) - 0;
		    axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
		    
		    // 动态数据接口 addData
		    currentChart.addData([
		        [
		            0,        // 系列索引
		            Math.round(Math.random() * 1000), // 新增数据
		            true,     // 新增数据是否从队列头部插入
		            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		        ],
		        [
		            1,        // 系列索引
		            lastData, // 新增数据
		            false,    // 新增数据是否从队列头部插入
		            false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		            axisData  // 坐标轴标签
		        ]
		    ]);
		}, 2100);                    
}
//内存信息
function memoryInfo(){
	var interfaceChart = echarts.init(document.getElementById('memoryChart')); 	
	option = {
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    toolbox: {
		        show : true
		     
		    },
		    series : [
		        {
		            name:'使用量',
		            type:'gauge',
		            detail : {formatter:'{value}%'},
		            data:[{value: 50, name: '使用量'}]
		        }
		    ]
		};


		timeTicket = setInterval(function (){
			$.ajax({
		         url:"currentcpu.do",
		           type:"POST",
		           dataType:"JSON",
		           success:function(data){ 
		        	  option.series[0].data[0].value =(data.combined).toFixed(2) - 0;
		   		    interfaceChart.setOption(option,true);
		           }
			})
		    
		},4000);
	

}

//磁盘使用量
function diskInfo(){
 var diskChart = echarts.init(document.getElementById('diskChart')); 	
 	

 diskChart.setOption({
  series : [
 {
     name: '访问来源',
     type: 'pie',
     radius: '70%',
     data:[
         {value:235, name:'网厅'},
         {value:274, name:'计费'},
         {value:310, name:'客户关系'},
         {value:335, name:'计费系统'},
         {value:400, name:'激活系统'}
     ]
 }
]
})			                   
}

//CPU详细信息
function getCpuDetail(){
	var cpudetail = echarts.init(document.getElementById('cpudetail')); 	
    var option = {
    	  
    	    tooltip : {
    	        trigger: 'item',
    	        formatter: "{b}: {c}"
    	    },
    	    toolbox: {
    	        show : true
    	       
    	    },
    	    calculable : false,
    	    series : [
    	        {
    	        	name:'CPU使用信息',
    	            type:'treemap',
    	            itemStyle: {
    	                normal: {
    	                    label: {
    	                        show: true,
    	                        formatter: "{b}"
    	                    },
    	                    borderWidth: 1
    	                },
    	                emphasis: {
    	                    label: {
    	                        show: true
    	                    }
    	                }
    	            },
    	            data:[
    	                {
    	                    name: '总内存',
    	                    value: 6
    	                },
    	                {
    	                    name: '被使用',
    	                    value: 4
    	                },
    	                {
    	                    name: '未使用',
    	                    value: 4
    	                },
    	                {
    	                    name: '交换分区总大小',
    	                    value: 2
    	                },
    	                {
    	                    name: '交换分区已使用',
    	                    value: 2
    	                },
    	                {
    	                    name: '交换分区未使用',
    	                    value: 1
    	                }
    	               
    	            ]
    	        }
    	    ]
    	};
    	                    
	
    cpudetail.setOption(option);
}



//内存详细信息
function getMemoryDetail(){
  var memorydetail = echarts.init(document.getElementById('memorydetail')); 	
  var option = {
    	  
  	    tooltip : {
  	        trigger: 'item',
  	        formatter: "{b}: {c}"
  	    },
  	    toolbox: {
  	        show : true
  	       
  	    },
  	    calculable : false,
  	    series : [
  	        {
  	        	name:'服务器内存使用信息',
  	            type:'treemap',
  	            itemStyle: {
  	                normal: {
  	                    label: {
  	                        show: true,
  	                        formatter: "{b}"
  	                    },
  	                    borderWidth: 1
  	                },
  	                emphasis: {
  	                    label: {
  	                        show: true
  	                    }
  	                }
  	            },
  	            data:[
  	                {
  	                    name: '总内存',
  	                    value: 6
  	                },
  	                {
  	                    name: '被使用',
  	                    value: 4
  	                },
  	                {
  	                    name: '未使用',
  	                    value: 4
  	                },
  	                {
  	                    name: '交换分区总大小',
  	                    value: 2
  	                },
  	                {
  	                    name: '交换分区已使用',
  	                    value: 2
  	                },
  	                {
  	                    name: '交换分区未使用',
  	                    value: 1
  	                }
  	               
  	            ]
  	        }
  	    ]
  	};
  memorydetail.setOption(option);
}

//显示实时网络信息
function currentNetInfo(){
	
	var currentNetChart = echarts.init(document.getElementById('currentNetChart')); 	

	var option = {

		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['实时调用量']
		    },
		    toolbox: {
		        show : true
		      
		    },
		    dataZoom : {
		        show : false,
		        start : 0,
		        end : 100
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : true,
		            data : (function (){
		                var now = new Date();
		                var res = [];
		                var len = 10;
		                while (len--) {
		                    res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
		                    now = new Date(now - 2000);
		                }
		                return res;
		            })()
		        },
		        {
		            type : 'category',
		            boundaryGap : true,
		            data : (function (){
		                var res = [];
		                var len = 10;
		                while (len--) {
		                    res.push(len + 1);
		                }
		                return res;
		            })()
		        }
		    ],
		    yAxis : [

		        {
		            type : 'value',
		            scale: true,
		            name : '预购量',
		            boundaryGap: [0.2, 0.2]
		        }
		    ],
		    series : [
		        {
		            name:'eth0',
		            type:'line',
		            data:(function (){
		                var res = [];
		                var len = 10;
		                while (len--) {
		                    res.push((Math.random()*10 + 5).toFixed(1) - 0);
		                }
		                return res;
		            })()
		        },
		        {
		            name:'wlan',
		            type:'line',
		            data:(function (){
		                var res = [];
		                var len = 10;
		                while (len--) {
		                    res.push((Math.random()*20 + 4).toFixed(1) - 0);
		                }
		                return res;
		            })()
		        },
		        {
		            name:'wlan0',
		            type:'line',
		            data:(function (){
		                var res = [];
		                var len = 10;
		                while (len--) {
		                    res.push((Math.random()*40 + 4).toFixed(1) - 0);
		                }
		                return res;
		            })()
		        }
		    ]
		};
	currentNetChart.setOption(option);		

		var lastData = 11;
		var axisData;
		var timeTicket;
		clearInterval(timeTicket); 
		timeTicket = setInterval(function (){
		    lastData += Math.random() * ((Math.round(Math.random() * 10) % 2) == 0 ? 1 : -1);
		    lastData = lastData.toFixed(1) - 0;
		    axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
		    
		    // 动态数据接口 addData
		    currentNetChart.addData([
		        [
		            0,        // 系列索引
		            Math.round(Math.random() * 1000), // 新增数据
		            true,     // 新增数据是否从队列头部插入
		            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		        ],
		        [
		            1,        // 系列索引
		            lastData, // 新增数据
		            false,    // 新增数据是否从队列头部插入
		            false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		            axisData  // 坐标轴标签
		        ]
		    ]);
		}, 3100);                    
}











