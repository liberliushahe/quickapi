package com.vitea.wechat.dto;
/**
 * 
 * @author liushahe
 *
 */
public class VoiceMessage extends Message{
	 /**
	  * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	  */
	private String mediaId;
	/**
	 * 语音格式，如amr，speex等
	 */
	private String format;
	public String getmediaId() {
		return mediaId;
	}
	public void setmediaId(String mediaId) {
		mediaId = mediaId;
	}
	public String getformat() {
		return format;
	}
	public void setformat(String format) {
		format = format;
	}
	
	
}
