package com.byzx.auth.bean;

public class TestExam {
	private Integer testId;
	private Integer projId;
	private String projName;
	private String testType;
	private String testTitle;
	private String testDesc;
	private String result;
	private String executor;
	private String fileUrl;
	private String createTime;
	private Integer createBy;
	private String createName;
	private String startTime;
	private String endTime;
	private String testState;
	private String isDelete;

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

	public String getTestState() {
		return testState;
	}

	public void setTestState(String testState) {
		this.testState = testState;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getProjId() {
		return projId;
	}

	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getTestTitle() {
		return testTitle;
	}

	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}

	public String getTestDesc() {
		return testDesc;
	}

	public void setTestDesc(String testDesc) {
		this.testDesc = testDesc;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Override
	public String toString() {
		return "TestExam [testId=" + testId + ", projId=" + projId
				+ ", testType=" + testType + ", testTitle=" + testTitle
				+ ", testDesc=" + testDesc + ", result=" + result
				+ ", executor=" + executor + ", fileUrl=" + fileUrl + "]";
	}
}
