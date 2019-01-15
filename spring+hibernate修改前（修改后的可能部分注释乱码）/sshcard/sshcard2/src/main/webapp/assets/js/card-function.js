function getObjectURL(file) {    
	var url = null ; 
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}

$(document).ready(function(){
	$("#cardPhot11o").change(function(){    //预览
		Messenger.options = {extraClasses: 'messenger-fixed messenger-on-bottom messenger-on-right',theme: 'flat'}
		 var objUrl = getObjectURL(this.files[0]);
		 var http=$(this).val();
		 var type = http.split(".");
		 var xsw_type=type[type.length-1];
		 if(xsw_type!="jpg"&&xsw_type!="png"&&xsw_type!="jpeg"&&xsw_type!="bmp"&&xsw_type!="gif"){
			 Messenger().post({message: '只允许上传jpg、jpeg、bmp、png、gif格式的图片',type: 'error',showCloseButton: true});
			 $(this).val("");
		 }else{
			 $(".edit-user-cover").attr("src", objUrl);
		 }
	});
})