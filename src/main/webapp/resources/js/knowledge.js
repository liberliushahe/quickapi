$(function() {
	//配置markdown编辑器
	editormd("editormd", {
		width : "100%",
		height : 640,
		syncScrolling : "single",
		//lib目录的路径，此处写死
		path : "/quickapi/resources/lib/",
		//这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
		saveHTMLToTextarea : true,
		imageUpload : true,
		imageFormats : [ "jpg", "jpeg", "gif", "png", "bmp", "webp" ],
		imageUploadURL : "upload.do",
	});
})
//异步提交知识库表单数据
function addKnowledge() {
	var flag = $("#addknowledge").valid();
	if (!flag) {
		//没有通过验证
		return;
	}
	//获取需要提交的内容
	var title = $("#title").val();
	var copyfrom = $("#copyfrom").val();
	var titledesc = $("#titledesc").val();
	var content = $("#text").val();
	$.ajax({
		//是否需要安全协议，一般设置为false
		secureuri : false,
		type : "post",
		dataType : "json",
		data : {
			"title" : title,
			"copyfrom" : copyfrom,
			"titledesc" : titledesc,
			"content" : content
		},
		url : "addFormKnowledge.do",
		success : function(data) {
			if (data.success) {
				$("#addknowledge input[name='title']").val("");
				$("#addknowledge input[name='copyfrom']").val("");
				$("#addknowledge input[name='titledesc']").val("");
				toastr.success("保存成功");
			}
		},
		error : function() {
			toastr.error("保存失败");
		}
	});
}
//校验表单数据
$("#addknowledge").validate(
		{
			rules : {
				title : {
					required : true
				},
				titledesc : {
					required : true
				},
				copyfrom : {
					required : true
				}
			},
			messages : {
				title : {
					required : "标题不能为空",

				},
				titledesc : {
					required : "简介不能为空",
				},
				copyfrom : {
					required : "来源不能为空",

				}

			},
			errorPlacement : function(error, element) {
				if ($(element).next("input").hasClass("tooltip")) {
					$(element).attr("data-original-title", $(error).text())
							.tooltip("show");
				} else {
					$(element).attr("title", $(error).text()).tooltip("show");
				}
			},

		});
