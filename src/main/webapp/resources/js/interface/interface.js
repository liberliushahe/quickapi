$(function() {
	setProperty();
	$("#searchInterfaceByParam").bind("click", function () {
		$('#interfacetable').bootstrapTable('refresh');
    });
	$("#addInterface").bind("click", function () {
		addInterface();
    });
	
});
//界面设置表格属性
function setProperty(){
	$('#interfacetable').bootstrapTable('destroy');
	$('#interfacetable').bootstrapTable({
		 url:'getInterfaceListByPage.do',
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
        	     {field: 'id', title: '接口编号', sortable: false, halign: 'center'},
        	     {field: 'sysid', title: '所属系统', sortable: true, halign: 'center'},
        	     {field: 'name', title: '接口名称', sortable: false, halign: 'center'},
        	     {field: 'url', title: '接口地址', sortable: false, halign: 'center'},
        	     {field: 'type', title: '类型', sortable: false, halign: 'center'},
        	     {field: 'method', title: '接口方法', sortable: false, halign: 'center'},
        	     {field: 'stat', title: '状态', sortable: false, halign: 'center'},
        	     {field: 'description', title: '接口描述', sortable: false, halign: 'center'},
        	     {field: 'timeout', title: '超时时间(秒)', sortable: false, halign: 'center'},
        	     {field: 'operate', title: '操作', sortable: false, halign: 'center', formatter : function(value,row,index) {
        		 return "<button class='btn btn-info btn-sm' onclick='editItem("+row.id+")'>编辑</button>&nbsp;&nbsp;"
        		 + '<button class="btn btn-danger btn-sm" onclick="deleteItem('
        		 + row.id
        		 + ')">删除</button>&nbsp;&nbsp;'
        		 + '<button class="btn btn-success btn-sm" onclick="testItem('
        		 + row.id
        		 + ')">测试</button>';
        		 }}

        	]
	 });
}
//请求服务数据时所传参数
function queryParams(params){
    return{
    	size : params.pageSize, 
        index : params.pageNumber,
        interfaceid:$('#queryid').val(),
        interfacemethod:$('#querymethod').val(),
        interfacename:$('#queryname').val(),
    }
}

//关闭页面窗口
function closePage(){
	layer.closeAll('page');
}
//增加接口
function addInterface(){
	layer.open({
		  title : '增加接口',
		  type: 1,
		  skin: 'layui-layer-rim', //加上边框
		  area: ['760px', '640px'], //宽高
		  content:'<form role="form" id="addinterfacetable"><table class="table border  text-center" id="sampleTable">'+
			  '<tr><td>接口编号</td><td><input name="id" id="interfaceid" class="form-control form-control-lg" /></td><td>系统分类</td><td><select name="sysid" id="sysid" class="form-control form-control-lg" >'+
            '        <option value="1">短信平台</option>'+
            '        <option value="2">计费系统</option>'+
            '        <option value="3">智能网管</option>'+
            '        <option value="4">CRM系统</option>'+
            '</select></td></tr>'+
			  '<tr><td>接口名称</td><td><input name="name" id="name" class="form-control form-control-lg" /></td><td>端口号</td><td><input name="port" id="port" class="form-control form-control-lg" /></td></tr>'+
			  '<tr><td>接口类型</td><td><select name="type" id="type" class="form-control form-control-lg" >'+
            '        <option value="application/json">json</option>'+
            '        <option value="application/x-www-form-urlencoded">form</option>'+
            '        <option value="socket">socket</option>'+
            '</select></td>'+
			  ' </td><td>接口状态</td><td><select name="stat" id="stat" class="form-control form-control-lg" >'+
            '        <option value="1">有效</option>'+
            '        <option value="0">无效</option>'+
            '</select></td></td></tr>'+
			  '<tr><td>超时时间</td><td><input name="timeout" id="timeout" class="form-control form-control-lg" /></td><td>接口方法</td><td><input name="method" id="method" class="form-control form-control-lg" /></td></tr>'+
			  '<tr><td>操作类型</td><td><select name="operator" id="operator" class="form-control form-control-lg" >'+
            '        <option value="1">查询类</option>'+
            '        <option value="2">修改类</option>'+
            '</select></td></td><td>是否模拟接口</td><td><select name="istemp" id="istemp" class="form-control form-control-lg" >'+
            '        <option value="1">是</option>'+
            '        <option value="0">否</option>'+
            '</select></td></td></tr>'+
			  '<tr><td>接口地址</td><td colspan="3"><input name="url" id="url" class="form-control form-control-lg" /></td></tr>'+
			  '<tr><td>接口描述</td><td colspan="3"><textarea class="form-control" id="description" name="description" rows="5"></textarea></td></tr>'+
			  '<tr><td>接口入参</td><td colspan="3"><textarea class="form-control" id="input" name="inparam" rows="5"></textarea></td></tr>'+
			  '<tr><td>出参模板</td><td colspan="3"><textarea class="form-control" id="template" name="template" rows="5"></textarea></td></tr>'+
			  '<tr> <td colspan="4"><button onclick="addInterfaceForm()" type="button" class="btn btn-primary">确定</button> <button type="reset"  class="btn btn-primary">重置</button></td></tr>'+
			  '</table></form>'
	});
}
//初始化表单数据并提交
function addInterfaceForm(){
	var id=$("#interfaceid").val();
	var name=$("#name").val();
	var port=$("#port").val();
	var timeout=$("#timeout").val();
	var method=$("#method").val();
	var input=$("#input").val();
	var url=$("#url").val();
	if (id=="") {
		layer.alert("接口编号不能为空");
		$("#interfaceid").focus();
		return;
	}
	if (name=="") {
		layer.alert("接口名称不能为空");
		$("#name").focus();
		return;
	}
	if (port=="") {
		layer.alert("端口号不能为空");
		$("#port").focus();
		return;
	}
	if (timeout=="") {
		layer.alert("超时时间不能为空");
		$("#timeout").focus();
		return;
	}
	if (method=="") {
		layer.alert("接口方法不能为空");
		$("#method").focus();
		return;
	}
	if (input=="") {
		layer.alert("入参不能为空");
		$("#input").focus();
		return;
	}
	if (url=="") {
		layer.alert("接口地址不能为空");
		$("#url").focus();
		return;
	}
	$.ajax({
            type: "POST",
            dataType: "json",
            url: "addInterface.do" ,
            data: $('#addinterfacetable').serialize(),
            success: function (data) {
				if (data.success) {
					closePage();
					$('#interfacetable').bootstrapTable('refresh');
					toastr.success("增加成功");
					
				}else{
					closePage();
					toastr.error("增加出错");
				}
            },
            error : function() {
            	closePage();
            	toastr.error("增加出错");
            }
        });
}

//打开编辑界面
function editItem(id){
	$.ajax({
		url:"getInterfaceById.do?id="+id,
		method:'post',
		success:function(data){
			console.log(data)
			var obj = eval('(' + data + ')');
			layer.open({
				  title : '接口编辑',
				  type: 1,
				  skin: 'layui-layer-rim', //加上边框
				  area: ['760px', '640px'], //宽高
				  content:'<form role="form" id="updateinterfacetable"><table class="table border  text-center" id="sampleTable">'+
				  '<tr><td>接口编号</td><td><input name="id" id="interfaceid" class="form-control form-control-lg" value='+obj.id+' /></td><td>系统分类</td><td><select name="sysid" id="sysid" class="form-control form-control-lg" >'+
	            '        <option value="1">短信平台</option>'+
	            '        <option value="2">计费系统</option>'+
	            '        <option value="3">智能网管</option>'+
	            '        <option value="4">CRM系统</option>'+
	            '</select></td></tr>'+
				  '<tr><td>接口名称</td><td><input name="name" id="name" class="form-control form-control-lg" value='+obj.name+' /></td><td>端口号</td><td><input name="port" id="port" class="form-control form-control-lg" value='+obj.port+' /></td></tr>'+
				  '<tr><td>接口类型</td><td><select name="type" id="type" class="form-control form-control-lg" >'+
	            '        <option value="application/json">json</option>'+
	            '        <option value="application/x-www-form-urlencoded">form</option>'+
	            '        <option value="socket">socket</option>'+
	            '</select></td>'+
				  ' </td><td>接口状态</td><td><select name="stat" id="stat" class="form-control form-control-lg" >'+
	            '        <option value="1">有效</option>'+
	            '        <option value="0">无效</option>'+
	            '</select></td></td></tr>'+
				  '<tr><td>超时时间</td><td><input name="timeout" id="timeout" class="form-control form-control-lg" value='+obj.timeout+' /></td><td>接口方法</td><td><input name="method" id="method" class="form-control form-control-lg" value='+obj.method+' /></td></tr>'+
				  '<tr><td>操作类型</td><td><select name="operator" id="operator" class="form-control form-control-lg" >'+
	            '        <option value="1">查询类</option>'+
	            '        <option value="2">修改类</option>'+
	            '</select></td></td><td>是否模拟接口</td><td><select name="istemp" id="istemp" class="form-control form-control-lg" >'+
	            '        <option value="1">是</option>'+
	            '        <option value="0">否</option>'+
	            '</select></td></td></tr>'+
				  '<tr><td>接口地址</td><td colspan="3"><input name="url" id="url" class="form-control form-control-lg" value='+obj.url+' /></td></tr>'+
				  '<tr><td>接口描述</td><td colspan="3"><textarea class="form-control" id="description" name="description" rows="5">'+obj.description+'</textarea></td></tr>'+
				  '<tr><td>接口入参</td><td colspan="3"><textarea class="form-control" id="input" name="inparam" rows="5">'+obj.inparam+'</textarea></td></tr>'+
				  '<tr><td>出参模板</td><td colspan="3"><textarea class="form-control" id="template" name="template" rows="5">'+obj.template+'</textarea></td></tr>'+
				  '<tr> <td colspan="4"><button onclick="updateInterfaceForm()" type="button" class="btn btn-primary">确定</button> <button type="reset"  class="btn btn-primary">重置</button></td></tr>'+
				  '</table></form>'
			});
		},
		error:function(){
			console.log("error");
		}
	})
	
}
//初始化表单数据并提交
function updateInterfaceForm(){
	
	$.ajax({
            type: "POST",
            dataType: "json",
            url: "updateInterface.do" ,
            data: $('#updateinterfacetable').serialize(),
            success: function (data) {
				if (data.success) {
					closePage();
					$('#interfacetable').bootstrapTable('refresh');
					toastr.success("增加成功");
					
				}else{
					closePage();
					toastr.error("增加出错");
				}
            },
            error : function() {
            	closePage();
            	toastr.error("增加出错");
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
				url:"deleteInterface.do?id="+id,
				success:function(data){
					var obj = eval('(' + data + ')');
					if (obj.success) {
						layer.closeAll('dialog');
						$('#interfacetable').bootstrapTable('refresh');
						toastr.success("删除成功");
					}else{
						layer.closeAll('dialog');
						toastr.error("删除出错");
					}
					
				},
				error:function(){
					layer.closeAll('dialog');
					toastr.error("删除出错");
				}
			})
		}, function(){
		layer.close();
		});
}
function validate(){
	var id=$("#interfaceid").val();
	var name=$("#name").val();
	var port=$("#port").val();
	var timeout=$("#timeout").val();
	var method=$("#method").val();
	var input=$("#input").val();
	var url=$("#url").val();
	if (id=="") {
		layer.alert("接口编号不能为空");
		$("#interfaceid").focus();
		return ;
	}
	if (name=="") {
		layer.alert("接口名称不能为空");
		$("#name").focus();
		return;
	}
	if (port=="") {
		layer.alert("端口号不能为空");
		$("#port").focus();
		return;
	}
	if (timeout=="") {
		layer.alert("超时时间不能为空");
		$("#timeout").focus();
		return;
	}
	if (method=="") {
		layer.alert("接口方法不能为空");
		$("#method").focus();
		return;
	}
	if (input=="") {
		layer.alert("入参不能为空");
		$("#input").focus();
		return;
	}
	if (url=="") {
		layer.alert("接口地址不能为空");
		$("#url").focus();
		return;
	}
	return true;
}
//打开接口测试界面
function testItem(id){
	$.ajax({
		url:"getInterfaceById.do?id=1",
		method:'post',
		success:function(data){
			var obj = eval('(' + data + ')');
			layer.open({
				  title : '接口编辑',
				  type: 1,
				  skin: 'layui-layer-rim', //加上边框
				  area: ['760px', '640px'], //宽高
				  content:'<form role="form" id="addinterfacetable"><table class="table border  text-center" id="sampleTable">'+
				  '<tr><td>接口编号</td><td><input name="id" id="interfaceid" class="form-control form-control-lg" /></td><td>系统分类</td><td><select name="sysid" id="sysid" class="form-control form-control-lg" >'+
	            '        <option value="1">短信平台</option>'+
	            '        <option value="2">计费系统</option>'+
	            '        <option value="3">智能网管</option>'+
	            '        <option value="4">CRM系统</option>'+
	            '</select></td></tr>'+
				  '<tr><td>接口名称</td><td><input name="name" id="name" class="form-control form-control-lg" /></td><td>端口号</td><td><input name="port" id="port" class="form-control form-control-lg" /></td></tr>'+
				  '<tr><td>接口类型</td><td><select name="type" id="type" class="form-control form-control-lg" >'+
	            '        <option value="application/json">json</option>'+
	            '        <option value="application/x-www-form-urlencoded">form</option>'+
	            '        <option value="socket">socket</option>'+
	            '</select></td>'+
				  ' </td><td>接口状态</td><td><select name="stat" id="stat" class="form-control form-control-lg" >'+
	            '        <option value="1">有效</option>'+
	            '        <option value="0">无效</option>'+
	            '</select></td></td></tr>'+
				  '<tr><td>超时时间</td><td><input name="timeout" id="timeout" class="form-control form-control-lg" /></td><td>接口方法</td><td><input name="method" id="method" class="form-control form-control-lg" /></td></tr>'+
				  '<tr><td>操作类型</td><td><select name="operator" id="operator" class="form-control form-control-lg" >'+
	            '        <option value="1">查询类</option>'+
	            '        <option value="2">修改类</option>'+
	            '</select></td></td><td>是否模拟接口</td><td><select name="istemp" id="istemp" class="form-control form-control-lg" >'+
	            '        <option value="1">是</option>'+
	            '        <option value="0">否</option>'+
	            '</select></td></td></tr>'+
				  '<tr><td>接口地址</td><td colspan="3"><input name="url" id="url" class="form-control form-control-lg" /></td></tr>'+
				  '<tr><td>接口描述</td><td colspan="3"><textarea class="form-control" id="description" name="description" rows="5"></textarea></td></tr>'+
				  '<tr><td>接口入参</td><td colspan="3"><textarea class="form-control" id="input" name="inparam" rows="5"></textarea></td></tr>'+
				  '<tr><td>出参模板</td><td colspan="3"><textarea class="form-control" id="template" name="template" rows="5"></textarea></td></tr>'+
				  '<tr> <td colspan="4"><button onclick="editInterfaceForm()" type="button" class="btn btn-primary">确定</button> <button type="reset"  class="btn btn-primary">重置</button></td></tr>'+
				  '</table></form>'
			});
		},
		error:function(){
			console.log("error");
		}
	})
}

