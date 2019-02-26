package com.byzx.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byzx.auth.bean.SysClass;
import com.byzx.auth.dao.SysClassDao;

/**
 * @comment SysClassService实现层
 * @author tyz
 * @date 2018年9月3日 下午6:12:22
 * @modifyUser Administrator
 * @modifyDate 2018年9月3日 下午6:12:22
 * @modifyDesc TODO
 * @version TODO
 */
@Service
public class SysClassServiceImpl implements SysClassService {
	// 实例化对象
	@Autowired
	private SysClassDao sysClassDao;

	/**
	 * @comment 查询所有分类
	 * @param sysClass
	 * @return List<SysClass>
	 * @version 1.0
	 */
	@Override
	public List<SysClass> findAllSysClass() {
		// TODO Auto-generated method stub
		return sysClassDao.findAllSysClass();
	}

	/**
	 * @comment 先判断传过来的classCode和className是否存在
	 * @param sysClass
	 * @return int
	 * @version 1.0
	 */
	@Override
	public int findSum(SysClass sysClass) {
		// TODO Auto-generated method stub
		return sysClassDao.findSum(sysClass);
	}

	/**
	 * @comment 新增加一条系统分类数据
	 * @param sysClass
	 * @return 状态
	 * @version 1.0
	 */
	@Override
	public int insertSysClass(SysClass sysClass) {
		// TODO Auto-generated method stub
		return sysClassDao.insertSysClass(sysClass);
	}

	/**
	 * @comment 根据classId修改对应的信息
	 * @param sysClass
	 * @return 状态
	 * @version 1.0
	 */
	@Override
	public int updateSysClass(SysClass sysClass) {
		// TODO Auto-generated method stub
		return sysClassDao.updateSysClass(sysClass);
	}

	/**
	 * @comment 根据传进来的classId，查询子集，让parent_id=calssId
	 * @param sysClass
	 * @return List<SysClass>
	 * @version 1.0
	 */
	@Override
	public List<SysClass> findSysClassByPid(SysClass sysClass) {
		// TODO Auto-generated method stub
		return sysClassDao.findSysClassByPid(sysClass);
	}

	/**
	 * @comment 根据传进来的classId查询出子集的所有classids
	 * @param classId
	 * @return String
	 * @version 1.0
	 */
	@Override
	public String findIdsByPid(int classId) {
		// TODO Auto-generated method stub
		return sysClassDao.findIdsByPid(classId);
	}

}

/**
 * @filename SysClassServiceImpl.java
 * @author Administrator
 * @date 2018年9月3日 下午6:09:15
 * @version 1.0 Copyright (C) 2018
 */
