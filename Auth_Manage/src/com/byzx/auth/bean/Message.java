/**
 * @filename Message.java
 * @author ldwzz
 * @date 2018��9��4�� ����10:16:05
 * @version 1.0
 * Copyright (C) 2018
 */
package com.byzx.auth.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @comment վ����ʵ����
 * @author ldwzz
 * @date 2018��9��4�� ����10:16:14
 * @modifyUser ldwzz
 * @modifyDate 2018��9��4�� ����10:16:14
 * @modifyDesc TODO
 * @version TODO
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 2036019860958627963L;

	// վ����ID
	private Integer msgId;
	// վ���ű���
	private String msgTitle;
	// վ��������
	private String msgContext;
	// ������ַ
	private String fileUrl;
	// ��������
	private String sendType;
	// ����ʱ��
	private String createTime;
	// ������
	private Integer createBy;
	// վ���Ź�ϵ����
	private UserMessage userMessage;
	// ��ʼʱ��
	private String startTime;
	// ����ʱ��
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
