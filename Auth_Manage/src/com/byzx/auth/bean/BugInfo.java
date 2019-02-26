package com.byzx.auth.bean;

public class BugInfo {
	private Integer bugId;
	private Integer projId;
	private String projName;
	private Integer classId;
	private String className;
	private String bugTitle;
	private String bugNum;
	private String bugDesc;
	private String bugLevel;
	private String bugState;
	private String createTime;
	private Integer createBy;
	private String creator;
	private Integer updateBy;
	private String executor;
	private String isDelete;
	private String fileUrl;
	private String startTime;
	private String endTime;

	public Integer getBugId() {
		return bugId;
	}

	public void setBugId(Integer bugId) {
		this.bugId = bugId;
	}

	public Integer getProjId() {
		return projId;
	}

	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getBugTitle() {
		return bugTitle;
	}

	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}

	public String getBugNum() {
		return bugNum;
	}

	public void setBugNum(String bugNum) {
		this.bugNum = bugNum;
	}

	public String getBugDesc() {
		return bugDesc;
	}

	public void setBugDesc(String bugDesc) {
		this.bugDesc = bugDesc;
	}

	public String getBugLevel() {
		return bugLevel;
	}

	public void setBugLevel(String bugLevel) {
		this.bugLevel = bugLevel;
	}

	public String getBugState() {
		return bugState;
	}

	public void setBugState(String bugState) {
		this.bugState = bugState;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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
		return "BugInfo [bugId=" + bugId + ", projId=" + projId + ", projName="
				+ projName + ", classId=" + classId + ", className="
				+ className + ", bugTitle=" + bugTitle + ", bugNum=" + bugNum
				+ ", bugDesc=" + bugDesc + ", bugLevel=" + bugLevel
				+ ", bugState=" + bugState + ", createTime=" + createTime
				+ ", createBy=" + createBy + ", creator=" + creator
				+ ", updateBy=" + updateBy + ", executor=" + executor
				+ ", isDelete=" + isDelete + ", fileUrl=" + fileUrl
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
