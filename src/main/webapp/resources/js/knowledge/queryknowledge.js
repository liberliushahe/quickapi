$(function(){
	setProperty();
	$("#searchKnowledge").bind("click", function () {
		setProperty();
 })
	
});
//界面设置表格属性
function setProperty(){
	$('#table').bootstrapTable('destroy');
	$('#table').bootstrapTable({
		 url:'getKnowledgeListByPage.do',
		 method:'post',
		 dataType: "json",
		 contentType: "application/x-www-form-urlencoded",
         striped:true,//隔行变色
         cache:false,  //是否使用缓存
         //showColumns:false,// 列
         //toobar:'#toolbar',
         locale: "zh-CN", //中文支持
         escape:false,
         pagination: true, //分页
         paginationLoop:false,
         paginationPreText:'上一页',
         paginationNextText:'下一页',
         showFooter:false,//显示列脚
         showPaginationSwitch:false,//是否显示数据条数选择框
         sortable: false,           //是否启用排序
         singleSelect: false,        
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
        	     {field: 'id', title: '文章编号', sortable: false, halign: 'center'},
        	    {field: 'title', title: '文章标题', sortable: false, halign: 'center', cellStyle:{ 
        		 css:{ 
        			 "overflow": "hidden", 
        			 "text-overflow": "ellipsis", 
        			 "white-space": "nowrap" 
        			 } 
        			 }},
        	    {field: 'content', title: '文章内容', sortable: false, halign: 'center', cellStyle:{ 
        		 css:{ 
        			 "overflow": "hidden", 
        			 "text-overflow": "ellipsis", 
        			 "white-space": "nowrap" 
        			 } 
        			 }},
        	    {field: 'time', title: '发布时间', sortable: false, halign: 'center', 
        				    formatter: function (value, row, index) {
        				        return changeDateFormat(value)
        				    }},
        	    {field: 'author', title: '文章作者', sortable: true, halign: 'center'},
        	    {field: 'hits', title: '点击量', sortable: false, halign: 'center'},
        	    {field: 'copyfrom', title: '文章来源', sortable: false, halign: 'center'},
        	    {field: 'filepath', title: '文件路径', sortable: false, halign: 'center'},
        	    {field: 'operate', title: '操作', sortable: false, halign: 'center', formatter : function(value,row,index) {
        		 return "<button class='btn btn-info btn-sm' onclick='editItem("+row.id+")'>编辑</button>&nbsp;&nbsp;"
        		 + '<button class="btn btn-danger btn-sm" onclick="deleteItem('
        		 + row.id
        		 + ')">删除</button>&nbsp;&nbsp;'
        		 + '<button class="btn btn-success btn-sm" onclick="preRead('
        		 + row.id
        		 + ')">预览</button>';
        		 }}

        	]
	 });
}
//请求服务数据时所传参数
function queryParams(params){
    return{
    	pageSize : params.pageSize, 
        pageNumber : params.pageNumber,
        title:$('#querytitle').val(),
        starttime:$('#starttime').val(),
        endtime:$('#endtime').val(),
    }
}
//打开编辑界面
function editItem(id){
	$.ajax({
		url:"getKnowledgeListById.do?id="+id,
		method:'post',
		success:function(data){
			var obj = eval('(' + data + ')');
			layer.open({
				  title : '知识库编辑',
				  type: 1,
				  skin: 'layui-layer-rim', //加上边框
				  area: ['720px', '640px'], //宽高
				  content: "<div><input name='articletitle' id='articletitle' class='form-control form-control-lg' value="+obj.title+" /><br/>"+
				  "<input name='copyfrom' id='copyfrom' class='form-control form-control-lg' value="+obj.copyfrom+" /><br/>"+
				  "<input name='titledesc' id='titledesc' class='form-control form-control-lg' value="+obj.titledesc+" /><br/>"+
				  "<textarea cols='91' rows='15' name='content' id='content'>"+obj.content+"</textarea><br/>"+
				  "<button class='btn btn-primary' onclick='editKnowledge("+obj.id+")'>确定</button>&nbsp;&nbsp;<button class='btn btn-primary' onclick='closePage()'>关闭</button>"+
				  "</div>"				 
			});
		},
		error:function(){
			console.log("error");
		}
	})
	
}
//关闭页面窗口
function closePage(){
	layer.closeAll('page');
}
//获取编辑数据并提交
function editKnowledge(id){
	var articletitle=$("#articletitle").val();
	var copyfrom=$("#copyfrom").val();
	var titledesc=$("#titledesc").val();
	var content=$("#content").val();
	$.ajax({
		url : "editKnowledge.do",
		type : "post",
		dataType : "json",
		data : {
			"id":id,
			"articletitle" : articletitle,
			"copyfrom" : copyfrom,
			"titledesc" : titledesc,
			"content" : content
		},
		success : function(res) {
			if (res.success) {
				layer.closeAll('page');
				toastr.success("修改成功");
			}else{
				toastr.error("更新出错");
			}
		},
		error : function() {
			layer.closeAll('page');
			toastr.error("修改失败");
		}
	});
}
//删除数据
function deleteItem(id){
	layer.confirm('你确定删除该条数据吗?', {
		  btn: ['是','否'] //按钮
		  ,cancel: function(index, layero){
		  }
		}, function(){
			$.ajax({
				url:"deleteKnowledge.do?id="+id,
				
				success:function(data){
					var obj = eval('(' + data + ')');
					if (obj.success) {
						layer.close();
						toastr.success("删除成功");
						
					}else{
						toastr.error("删除出错");
					}
					
				},
				error:function(){
					
					toastr.error("删除出错");
				}
			})
		}, function(){
		layer.close();
		});
}
//预览
function preRead(id){ 
	var tmp=window.open("api/knowledge/detail.do?id="+id,"","fullscreen=1") 
	tmp.moveTo(0,0); 
	tmp.resizeTo(screen.width+20,screen.height); 
	tmp.focus(); 
	tmp.location=no; 
	
	} 

//转换日期格式(时间戳转换为datetime格式)
function changeDateFormat(cellval) {
	    var dateVal = cellval + "";
	    if (cellval != null) {
	        var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
	        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
	        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
	        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
	        return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
	    }
	
}
