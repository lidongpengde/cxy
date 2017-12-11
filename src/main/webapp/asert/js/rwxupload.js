function uploadImg(){
    $.fn.ajaxUpload=function(options){
        return this.each(function(i){
            var $this=$(this),i=i+1;
            var settings={
                action: "/upload.do",
                hoverClass: '',
                focusClass: '',
                name:'uploadFile',
                responseType:  "json",
                onSubmit:function(file , ext){
                    var browser=navigator.appName;
                    if(browser == "Microsoft Internet Explorer"){
                        var b_version=navigator.appVersion,
                            version=b_version.split(";"),
                            trim_Version=version[1].replace(/[ ]/g,"");
                    }
                    if (ext && /^(jpg|jpeg|gif|png|bmp)$/.test(ext.toLowerCase())){
                        //this.setData({'imgPath': file});
                    }else{
                        uploadImgTips($this,"uploadSuccess","uploadError",'上传失败',"请上传图片格式为jpg/jpeg/png/gif/bmp");
                        isupload = false;
                        return false;
                    }
                    if(window.addEventListener){
                        if(trim_Version!="MSIE9.0"){
                            var fileupload=this._input,ImgFileSize;
                            ImgFileSize=Math.round(fileupload.files[0].size/1024*100)/100;
                            if(ImgFileSize>3072){
                                uploadImgTips($this,"uploadSuccess","uploadError",'上传失败',"图片大小不能超过3M");
                                isupload = false;
                                return false;
                            }
                        }
                    }
                    $this.find(".loadImage").append("<div class='waitting'><div class='waittingimg'></div></div>");
                },
                onComplete : function(file,response){
                    $(".waitting").remove();
                    if(response.success) {
                        uploadImgTips($this,"uploadError","uploadSuccess",'图片上传成功',"")
                        var images =response.imagePath;
                        $this.find('.warpUpLoadImg img').attr('src',images);
                        $this.find('.warpUpLoadImg input').attr('value',images);
                        isupload = true;
                        return true;
                    }else{
                        uploadImgTips($this,"uploadSuccess","uploadError",'上传失败',response.message);
                        isupload = false;
                        return false;
                    }
                }
            }
            op=$.extend(settings,options);
            new AjaxUpload($this,op)
        })
    }

    $('.loadImage').ajaxUpload({});
}
function uploadImgTips($_this,cls1,cls2,txt1,txt2){
    $_this.next().removeClass(cls1).addClass(cls2);
    $_this.next().show();
    $_this.find(".warpLicenseTitle").html(txt1);
    $_this.next().find('.textSign').html(txt2);
}