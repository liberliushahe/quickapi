package com.vitea.wechat.dto;
/**
 * 
 * @author liushahe
 *
 */
public class VideoMessage extends Message{

	/**
	 * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String mediaId;
	/**
	 *视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String thumbmediaId;
	public String getmediaId() {
		return mediaId;
	}
	public void setmediaId(String mediaId) {
		mediaId = mediaId;
	}
	public String getthumbmediaId() {
		return thumbmediaId;
	}
	public void setthumbmediaId(String thumbmediaId) {
		thumbmediaId = thumbmediaId;
	}
	
}
