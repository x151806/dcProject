package com.dc.common.vo;

public class PicUploadResult {
    private Integer error=0;		//图片上传错误不能抛出，抛出就无法进行页面回调，所以设置这个标识，0表示无异常，1代表异常
    private String url;
    
	public Integer getError() {
		return error;
	}
	public void setError(Integer error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

    
    

}
