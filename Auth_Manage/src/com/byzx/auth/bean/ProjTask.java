/**
 * @filename projTask.java
 * @author zhangpan
 * @date 2018年9月5日 下午3:08:17
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
	// 任务id
	private Integer taskId;
	// 项目主键id
	private Integer projId;
	// 任务名称
	private String taskName;
	// 任务描述
	private String taskDesc;
	// 执行人
	private Integer excutor;
	// 任务状态
	private String taskState;
	// 创建时间
	private String createTime;
	// 创建人
	private Integer createBy;
	// 所属模块
	private Integer classId;
	// 工时
	private float ableDay;
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	// 计划时间
	private float planTime;
	// 实际消耗时间
	private float finishTime;
	// 实际进度
	private Integer process;
	// 删除标记
	private String isDelete;
	// 备注
	private String remark;
	// 执行人
	private String excutorName;
	// 创建人
	private String createByName;
	// 所属模块
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
