package com.vitea.wechat.dto;
/**
 * 
 * @author liushahe
 *
 */
public class Message {
	/**
	 * 开发者微信号
	 */
	private String toUserName;
	/**
	 * 发送方帐号（一个OpenID）
	 */
	private String fromUserName;
	/**
	 * 消息创建时间 （整型）
	 */
	private long createTime;
	/**
	 * 消息类型
	 */
	private String msgType;
	/**
	 * 文本消息内容
	 */
	private String content;
	/**
	 * 消息id
	 */
	private String msgId;
	
	public long getcreateTime() {
		return createTime;
	}
	public void setcreateTime(long createTime) {
		createTime = createTime;
	}
	public String gettoUserName() {
		return toUserName;
	}
	public void settoUserName(String toUserName) {
		toUserName = toUserName;
	}
	public String getfromUserName() {
		return fromUserName;
	}
	public void setfromUserName(String fromUserName) {
		fromUserName = fromUserName;
	}
	public String getmsgType() {
		return msgType;
	}
	public void setmsgType(String msgType) {
		msgType = msgType;
	}
	public String getcontent() {
		return content;
	}
	public void setcontent(String content) {
		content = content;
	}
	public String getmsgId() {
		return msgId;
	}
	public void setmsgId(String msgId) {
		msgId = msgId;
	}

}
