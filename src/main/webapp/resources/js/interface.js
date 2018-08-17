$(function() {
//编辑接口
$("#interfacetable").on("click",".edit-interface",function(){

	$.ajax({
		url:"editInterface.do?id="+$(this).attr("id"),
		success:function(data){
			$("#interfaceFormContainer").html(data);
		},
		error:function(){
			toastr.error("保存失败");
		}
	})
});
//删除接口
$("#interfacetable").on("click",".del-interface",function(){
	
	$.ajax({
		url:"deleteInterface.do?id="+$(this).attr("id"),
		
		success:function(data){
			toastr.success("删除成功");
		},
		error:function(){
			toastr.error("删除出错");
		}
	})
});
//提交变更后，清空表单
$("#submitEdit").click(function() {
	$.ajax({ 
		 url: "updateInterface.do", 
		 type: 'POST',
		
		 data:$('#interfaceForm').serialize(),
		 success: function(data){
			 $('#interfaceForm')[0].reset();  
			 var obj = eval('(' + data + ')');
			 if (obj.success) {
				 // 从新刷新主界面
				 //getUersByName(0, _pageSize);
				 toastr.info("更新成功");
			 } else {
				 toastr.error("保存失败");
			 }

	     },
	     error : function() {
	    	 toastr.error("error!");
	     }
	 });
});

});