/**
 * @filename ProjTaskServiceImpl.java
 * @author zhangpan
 * @date 2018年9月6日 下午4:31:03
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byzx.auth.bean.ProjTask;
import com.byzx.auth.bean.SysClass;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.dao.ProjTaskDao;
@Service
public class ProjTaskServiceImpl implements ProjTaskService {
@Autowired
ProjTaskDao projTaskDao;
	@Override
	public List<ProjTask> findprojTask(HashMap map) {
		// TODO Auto-generated method stub
		return projTaskDao.findprojTask(map);
	}
	@Override
	public int findprojTaskcount(ProjTask projTask) {
		// TODO Auto-generated method stub
		return projTaskDao.findprojTaskcount(projTask);
	}
	@Override
	public int insetProjTask(ProjTask projTask) {
		// TODO Auto-generated method stub
		return projTaskDao.insetProjTask(projTask);
	}
	@Override
	public List<UserInfo> findProjState(int projId) {
		// TODO Auto-generated method stub
		return projTaskDao.findProjState(projId);
	}
	@Override
	public int updateProjTask(ProjTask projTask) {
		// TODO Auto-generated method stub
		return projTaskDao.updateProjTask(projTask);
	}
	@Override
	public List<SysClass> findClass(int classId) {
		// TODO Auto-generated method stub
		return projTaskDao.findClass(classId);
	}
	@Override
	public int selectTasksNum(HashMap<Object, Object> map) {
		// TODO Auto-generated method stub
		return projTaskDao.selectTasksNum(map);
	}
	/**
	 * 
	 * @comment 查询快过期任务
	 * @param userId
	 * @return 类型的状态
	 * @version 1.0
	 */
	@Override
	public List<ProjTask> selectTasks(HashMap<Object, Object> map) {
		// TODO Auto-generated method stub
		return projTaskDao.selectTasks(map);
	}
	
	/**
	 * @comment 根据用户id查询项目id
	 * @param userId
	 * @return
	 * @version 1.0
	 */
	@Override
	public String selectProjId(int userId) {
		// TODO Auto-generated method stub
		return projTaskDao.selectProjId(userId);
	}
	/**
	 * 
	 * @comment 查询快过期任务
	 * @param userId
	 * @return 类型的状态
	 * @version 1.0
	 */
	@Override
	public int selectTasksNums(HashMap<Object, Object> map) {
		// TODO Auto-generated method stub
		return projTaskDao.selectTasksNums(map);
	}
	

}
