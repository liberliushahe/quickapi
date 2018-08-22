$(function(){

	 $("#searchlog").bind("click", function () {
			//getData();
			setProperty();
		    //$('#table').bootstrapTable('refresh');
     })
});

//设置表格属性
function setProperty(){
	$('#table').bootstrapTable('destroy');
	$('#table').bootstrapTable({
		 url:'getInterfaceLogByPage.do',
		 method:'post',
		 dataType: "json",
		 contentType: "application/x-www-form-urlencoded",
         striped:true,//隔行变色
         cache:false,  //是否使用缓存
         //showColumns:false,// 列
         //toobar:'#toolbar',
         locale: "zh-CN", //中文支持
         pagination: true, //分页
         paginationLoop:false,
         paginationPreText:'上一页',
         paginationNextText:'下一页',
         showFooter:false,//显示列脚
         showPaginationSwitch:false,//是否显示数据条数选择框
         sortable: false,           //是否启用排序
         singleSelect: false,
         onClickCell: function (field, value, row, $element) {
        	 if(isJsonString(value)){
        		 value=formatJson(value);
        	 }else{
        		 value=formatXml(value);
             	 value=value.replace(new RegExp("<","g"),"&lt;").replace(new RegExp(">","g"),"&gt;");

        	 }
        	layer.open({
        		  title:'日志信息',
        	      type: 1,
        	      area: ['700px', '380px'],
        	      shadeClose: true,
        	      content: '\<\div style="padding:20px;text-align:left;">\<\pre>'+value+'\<\/pre>\<\/div>'
        	    });
             return false;
         },         
          queryParamsType:"",//查询参数组织方式
          queryParams:queryParams,//请求服务器时所传的参数
         //search:true, //显示搜索框
         //buttonsAlign: "right", //按钮对齐方式
         //paginationVAlign:"bottom",
         showRefresh:false,//是否显示刷新按钮
         sidePagination: "server", //服务端处理分页
         pageNumber:1,
         pageSize:10,
         pageList:[10,15],
         undefinedText:'--',
         //uniqueId: "id", //每一行的唯一标识，一般为主键列
         columns: [
        	 {field: 'interid', title: '接口编号', sortable: false, halign: 'center'},
        	 {field: 'ip', title: 'IP', sortable: false, halign: 'center'},
        	    {field: 'accnum', title: '号码', sortable: false, halign: 'center'},
        	    {field: 'timestamp', title: '调用时间', sortable: true, halign: 'center'},
        	    {field: 'reqstr', title: '请求报文', sortable: false, halign: 'center', cellStyle:{ 
        		 css:{ 
        			 "overflow": "hidden", 
        			 "text-overflow": "ellipsis", 
        			 "white-space": "nowrap" 
        			 } 
        			 }},
        	    {field: 'retstr', title: '响应报文', sortable: false, halign: 'center', cellStyle:{ 
        		 css:{ 
        			 "overflow": "hidden", 
        			 "text-overflow": "ellipsis", 
        			 "white-space": "nowrap" 
        			 } 
        			 }},
        	    {field: 'cost', title: '消耗时间(单位:毫秒)', sortable: false, halign: 'center'}

        	]
	 });
}
//请求服务数据时所传参数
function queryParams(params){
    return{
    	pageSize : params.pageSize, 
        pageNumber : params.pageNumber,
        number:$('#number').val(),
        starttime:$('#starttime').val(),
        endtime:$('#endtime').val(),
    }
}
function isJsonString(str) {
	  try {
	    JSON.parse(str)
	    return true
	  } catch (err) {
	    return false
	  }
	}