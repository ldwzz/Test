/**
 * @filename Message.java
 * @author ldwzz
 * @date 2018年9月4日 上午10:16:05
 * @version 1.0
 * Copyright (C) 2018
 */
package com.byzx.auth.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @comment 站内信实体类
 * @author ldwzz
 * @date 2018年9月4日 上午10:16:14
 * @modifyUser ldwzz
 * @modifyDate 2018年9月4日 上午10:16:14
 * @modifyDesc TODO
 * @version TODO
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 2036019860958627963L;

	// 站内信ID
	private Integer msgId;
	// 站内信标题
	private String msgTitle;
	// 站内信内容
	private String msgContext;
	// 附件地址
	private String fileUrl;
	// 发送类型
	private String sendType;
	// 创建时间
	private String createTime;
	// 创建人
	private Integer createBy;
	// 站内信关系集合
	private UserMessage userMessage;
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContext() {
		return msgContext;
	}

	public void setMsgContext(String msgContext) {
		this.msgContext = msgContext;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getCreateTime() {
		return createTime.substring(0, 16);
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public UserMessage getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(UserMessage userMessage) {
		this.userMessage = userMessage;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Message [msgId=" + msgId + ", msgTitle=" + msgTitle
				+ ", msgContext=" + msgContext + ", fileUrl=" + fileUrl
				+ ", sendType=" + sendType + ", createTime=" + createTime
				+ ", createBy=" + createBy + ", userMessage=" + userMessage
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
