package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.LogInfo;

public interface LogInfoService {
	/**
	 * @comment admin进来查询所有日志
	 * @param map
	 * @return List<LogInfo>
	 * @version 1.0
	 */
	public List<LogInfo> findLogInfo1(HashMap map);

	/**
	 * @comment admin进来查询所有日志的总条数
	 * @param map
	 * @return int(总条数)
	 * @version 1.0
	 */
	public int findLogInfoSum1(HashMap map);

	/**
	 * @comment 根据不同人（项目经理、组长、组员）进来查询其自己权限的日志
	 * @param map
	 * @return List<LogInfo>
	 * @version 1.0
	 */
	public List<LogInfo> findLogInfo2(HashMap map);

	/**
	 * @comment 根据不同人（项目经理、组长、组员）进来查询其权限日志的总条数
	 * @param map
	 * @return int(总条数)
	 * @version 1.0
	 */
	public int findLogInfoSum2(HashMap map);

	/**
	 * @comment 新增日志
	 * @param logInfo
	 * @return 状态
	 * @version 1.0
	 */
	public int append(LogInfo logInfo);

	/**
	 * @comment 根据logId删除对应的日志
	 * @param logId
	 * @return 状态
	 * @version 1.0
	 */
	public int clearLog(int logId);

	/**
	 * @comment 根据dynamic_id查询proj_id
	 * @param dynamic_id
	 * @return proj_id
	 * @version 1.0
	 */
	public int findProjIdByDynamicId(int dynamicId);

	/**
	 * @comment 根据taskId查询proj_id
	 * @param taskId
	 * @return proj_id
	 * @version 1.0
	 */
	public int findProjIdByTaskId(int taskId);

	/**
	 * @comment 根据versionId查询projId
	 * @param taskId
	 * @return proj_id
	 * @version 1.0
	 */
	public int findProjIdByVersionId(int versionId);

	/**
	 * @comment 根据bugId查询proj_id
	 * @param bugId
	 * @return proj_id
	 * @version 1.0
	 */
	public int findProjIdByBugId(int bugId);
}

/**
 * @filename LogInfoService.java
 * @author Administrator
 * @date 2018年9月7日 下午3:14:34
 * @version 1.0 Copyright (C) 2018
 */
