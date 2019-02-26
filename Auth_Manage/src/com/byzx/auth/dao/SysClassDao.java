

package com.byzx.auth.dao;

import java.util.List;

import com.byzx.auth.bean.SysClass;

public interface SysClassDao {
/**
 * @comment 查询所有分类
 * @param sysClass
 * @return  List<SysClass>
 * @version 1.0
 */
	public List<SysClass>  findAllSysClass();
	
/**
 * @comment 先判断传过来的classCode和className是否存在	
 * @param sysClass
 * @return int
 * @version 1.0
 */
	public  int findSum(SysClass sysClass);
 /**
  * @comment  新增加一条系统分类数据	 
  * @param sysClass
  * @return 状态
  * @version 1.0
  */
	public  int insertSysClass(SysClass sysClass);
	
/**
 * @comment 根据classId修改对应的信息	
 * @param sysClass
 * @return 状态
 * @version 1.0
 */
	public int updateSysClass(SysClass sysClass);
/**
 * @comment 根据传进来的classId，查询子集，让parent_id=calssId 	
 * @param sysClass
 * @return  List<SysClass>
 * @version 1.0
 */
	public List<SysClass> findSysClassByPid(SysClass sysClass);
	/**
	 * @comment 根据传进来的classId查询出子集的所有classids 
	 * @param classId
	 * @return String
	 * @version 1.0
	 */
	public String findIdsByPid(int classId);	
	
	
}

/**
 * @filename SysClassDao.java
 * @author Administrator
 * @date 2018年9月3日 下午5:54:25
 * @version 1.0
 * Copyright (C) 2018 
 */
