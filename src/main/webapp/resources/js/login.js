$("#login").validate({
    rules:{
        password:{
            required:true
        },
        username:"required"    
    },
    messages : {
		password : {
			required : "密码不能为空",
			
		},
		username : "用户名不能为空",
		
	},
	errorPlacement: function (error, element) {
        if ($(element).next("input").hasClass("tooltip")) {
                $(element).attr("data-original-title", $(error).text()).tooltip("show");
         } else {
            $(element).attr("title",     
            $(error).text()).tooltip("show");
        }
    },
	
});