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
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getThumbmediaId() {
		return thumbmediaId;
	}
	public void setThumbmediaId(String thumbmediaId) {
		this.thumbmediaId = thumbmediaId;
	}

	
}
