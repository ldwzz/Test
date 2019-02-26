package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byzx.auth.bean.LogInfo;
import com.byzx.auth.dao.LogInfoDao;

/**
 * @comment 日志serviceImpl层
 * @author tyz
 * @date 2018年9月7日 下午3:27:04
 * @modifyUser Administrator
 * @modifyDate 2018年9月7日 下午3:27:04
 * @modifyDesc TODO
 * @version TODO
 */
@Service
public class LogInfoServiceImpl implements LogInfoService {
	@Autowired
	private LogInfoDao logInfoDao;

	/**
	 * @comment admin进来查询所有日志
	 * @param map
	 * @return List<LogInfo>
	 * @version 1.0
	 */
	@Override
	public List<LogInfo> findLogInfo1(HashMap map) {
		// TODO Auto-generated method stub
		return logInfoDao.findLogInfo1(map);
	}

	/**
	 * @comment admin进来查询所有日志的总条数
	 * @param map
	 * @return int(总条数)
	 * @version 1.0
	 */
	@Override
	public int findLogInfoSum1(HashMap map) {
		// TODO Auto-generated method stub
		return logInfoDao.findLogInfoSum1(map);
	}

	/**
	 * @comment 根据不同人（项目经理、组长、组员）进来查询其自己权限的日志
	 * @param map
	 * @return List<LogInfo>
	 * @version 1.0
	 */
	@Override
	public List<LogInfo> findLogInfo2(HashMap map) {
		// TODO Auto-generated method stub
		return logInfoDao.findLogInfo2(map);
	}

	/**
	 * @comment 根据不同人（项目经理、组长、组员）进来查询其权限日志的总条数
	 * @param map
	 * @return int(总条数)
	 * @version 1.0
	 */
	@Override
	public int findLogInfoSum2(HashMap map) {
		// TODO Auto-generated method stub
		return logInfoDao.findLogInfoSum2(map);
	}

	/**
	 * @comment 新增日志Impl
	 * @param logInfo
	 * @return 状态
	 * @version 1.0
	 */
	@Override
	public int append(LogInfo logInfo) {
		// TODO Auto-generated method stub
		return logInfoDao.append(logInfo);
	}

	/**
	 * 导出日志的同时删除掉
	 */
	@Override
	public int clearLog(int logId) {
		// TODO Auto-generated method stub
		return logInfoDao.clearLog(logId);
	}

	/**
	 * @comment 根据dynamic_id查询proj_id
	 * @param dynamic_id
	 * @return proj_id
	 * @version 1.0
	 */
	@Override
	public int findProjIdByDynamicId(int dynamicId) {
		// TODO Auto-generated method stub
		return logInfoDao.findProjIdByDynamicId(dynamicId);
	}

	/**
	 * @comment 根据taskId查询proj_id
	 * @param taskId
	 * @return proj_id
	 * @version 1.0
	 */
	@Override
	public int findProjIdByTaskId(int taskId) {
		// TODO Auto-generated method stub
		return logInfoDao.findProjIdByTaskId(taskId);
	}

	/**
	 * @comment 根据versionId查询projId
	 * @param taskId
	 * @return proj_id
	 * @version 1.0
	 */
	@Override
	public int findProjIdByVersionId(int versionId) {
		// TODO Auto-generated method stub
		return logInfoDao.findProjIdByVersionId(versionId);
	}

	/**
	 * @comment 根据bugId查询proj_id
	 * @param bugId
	 * @return proj_id
	 * @version 1.0
	 */
	@Override
	public int findProjIdByBugId(int bugId) {
		// TODO Auto-generated method stub
		return logInfoDao.findProjIdByBugId(bugId);
	}

}

/**
 * @filename LogInfoServiceImpl.java
 * @author Administrator
 * @date 2018年9月7日 下午3:14:58
 * @version 1.0 Copyright (C) 2018
 */
