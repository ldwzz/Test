package com.byzx.auth.bean;

import java.util.List;

public class BugReplay {
	
	@Override
	public String toString() {
		return "BugReplay [replyId=" + replyId + ", ry=" + ry + ", bugId="
				+ bugId + ", replyContent=" + replyContent + ", parentId="
				+ parentId + ", createTime=" + createTime + ", createBy="
				+ createBy + ", isDelete=" + isDelete + "]";
	}
	/**
	 * �ظ�Id
	 */
	private  Integer   replyId;
	/**
	 * bugId
	 */
	private  List<BugReplay>   ry;
    private   Integer   bugId;
    /**
     * �ظ�����
     */
    private   String   replyContent;
    /**
     * ��Id
     */
    private     Integer    parentId;
    /**
     * ����ʱ��
     */
    private    String    fatherName;
    private   String    createTime;
    /**
     * ������
     */
    private  Integer    createBy;
    private   String     nickName;
    /**
     * �Ƿ�ɾ��0 δɾ��
            1 ��ɾ��
     */
    private  String      isDelete;
	public Integer getReplyId() {
		return replyId;
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	public Integer getBugId() {
		return bugId;
	}
	public void setBugId(Integer bugId) {
		this.bugId = bugId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getCreateTime() {
		return createTime;
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
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public List<BugReplay> getRy() {
		return ry;
	}
	public void setRy(List<BugReplay> ry) {
		this.ry = ry;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
}
