/**
 * @filename projTask.java
 * @author zhangpan
 * @date 2018��9��5�� ����3:08:17
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

public class ProjTask implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4593482232587696146L;
	// ����id
	private Integer taskId;
	// ��Ŀ����id
	private Integer projId;
	// ��������
	private String taskName;
	// ��������
	private String taskDesc;
	// ִ����
	private Integer excutor;
	// ����״̬
	private String taskState;
	// ����ʱ��
	private String createTime;
	// ������
	private Integer createBy;
	// ����ģ��
	private Integer classId;
	// ��ʱ
	private float ableDay;
	// ��ʼʱ��
	private String startTime;
	// ����ʱ��
	private String endTime;
	// �ƻ�ʱ��
	private float planTime;
	// ʵ������ʱ��
	private float finishTime;
	// ʵ�ʽ���
	private Integer process;
	// ɾ�����
	private String isDelete;
	// ��ע
	private String remark;
	// ִ����
	private String excutorName;
	// ������
	private String createByName;
	// ����ģ��
	private String classIdName;
	
	private String className;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Integer getProjId() {
		return projId;
	}
	public void setProjId(Integer projId) {
		this.projId = projId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public Integer getExcutor() {
		return excutor;
	}
	public void setExcutor(Integer excutor) {
		this.excutor = excutor;
	}
	public String getTaskState() {
		return taskState;
	}
	public void setTaskState(String taskState) {
		this.taskState = taskState;
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
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public float getAbleDay() {
		return ableDay;
	}
	public void setAbleDay(float ableDay) {
		this.ableDay = ableDay;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime.substring(0,10);
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime.substring(0,10);
	}
	public float getPlanTime() {
		return planTime;
	}
	public void setPlanTime(float planTime) {
		this.planTime = planTime;
	}
	public float getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(float finishTime) {
		this.finishTime = finishTime;
	}
	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getExcutorName() {
		return excutorName;
	}
	public void setExcutorName(String excutorName) {
		this.excutorName = excutorName;
	}
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	public String getClassIdName() {
		return classIdName;
	}
	public void setClassIdName(String classIdName) {
		this.classIdName = classIdName;
	}
	@Override
	public String toString() {
		return "ProjTask [taskId=" + taskId + ", projId=" + projId
				+ ", taskName=" + taskName + ", taskDesc=" + taskDesc
				+ ", excutor=" + excutor + ", taskState=" + taskState
				+ ", createTime=" + createTime + ", createBy=" + createBy
				+ ", classId=" + classId + ", ableDay=" + ableDay
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", planTime=" + planTime + ", finishTime=" + finishTime
				+ ", process=" + process + ", isDelete=" + isDelete
				+ ", remark=" + remark + ", excutorName=" + excutorName
				+ ", createByName=" + createByName + ", classIdName="
				+ classIdName + ", className=" + className + "]";
	}

	
}
