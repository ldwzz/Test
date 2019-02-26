/**
 * @filename ProjTaskService.java
 * @author zhangpan
 * @date 2018年9月6日 下午4:30:13
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.ProjTask;
import com.byzx.auth.bean.SysClass;
import com.byzx.auth.bean.UserInfo;

public interface ProjTaskService {
	/**
	 * @comment查询所有项目任务
	 * @param map
	 * @return ProjTask
	 * @version 1.0
	 */
	public List<ProjTask> findprojTask(HashMap map);
	/**
	 * 
	 * @comment 查询总条数
	 * @param projTask
	 * @return int 类型的条数
	 * @version 1.0
	 */
	public int findprojTaskcount(ProjTask projTask);
	/**
	 * 
	 * @comment 增加项目任务
	 * @param projTask 对象
	 * @return  int 类型的状态
	 * @version 1.0
	 */
	public int insetProjTask(ProjTask projTask);
	/**
	 * 
	 * @comment 查询可以执行的人
	 * @param userInfo 对象
	 * @return UserInfo 集合
	 * @version 1.0
	 */
	public List<UserInfo> findProjState(int projId);
	/**
	 * 
	 * @comment 查询所属模块
	 * @param classId 对应模块ID
	 * @return  集合
	 * @version 1.0
	 */
	public List<SysClass> findClass(int classId);
	/**
	 * 
	 * @comment 修改项目任务
	 * @param projTask 对象
	 * @return int 类型的状态
	 * @version 1.0
	 */
	public int updateProjTask(ProjTask projTask);
	/**
	 * 
	 * @comment 快过期任务
	 * @param userId
	 * @return 类型的状态
	 * @version 1.0
	 */
	public int selectTasksNum(HashMap<Object, Object> map);

	/**
	 * 
	 * @comment 查询快过期任务
	 * @param userId
	 * @return 类型的状态
	 * @version 1.0
	 */
	public List<ProjTask> selectTasks(HashMap<Object, Object> map);
	
	/**
	 * @comment 根据用户id查询项目id
	 * @param userId
	 * @return
	 * @version 1.0
	 */
	public String selectProjId(int userId);
	
	/**
	 * 
	 * @comment 查询快过期任务
	 * @param userId
	 * @return 类型的状态
	 * @version 1.0
	 */
	public int selectTasksNums(HashMap<Object, Object> map);

}
