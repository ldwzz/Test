/**
 * @filename ProjTaskService.java
 * @author zhangpan
 * @date 2018��9��6�� ����4:30:13
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
	 * @comment��ѯ������Ŀ����
	 * @param map
	 * @return ProjTask
	 * @version 1.0
	 */
	public List<ProjTask> findprojTask(HashMap map);
	/**
	 * 
	 * @comment ��ѯ������
	 * @param projTask
	 * @return int ���͵�����
	 * @version 1.0
	 */
	public int findprojTaskcount(ProjTask projTask);
	/**
	 * 
	 * @comment ������Ŀ����
	 * @param projTask ����
	 * @return  int ���͵�״̬
	 * @version 1.0
	 */
	public int insetProjTask(ProjTask projTask);
	/**
	 * 
	 * @comment ��ѯ����ִ�е���
	 * @param userInfo ����
	 * @return UserInfo ����
	 * @version 1.0
	 */
	public List<UserInfo> findProjState(int projId);
	/**
	 * 
	 * @comment ��ѯ����ģ��
	 * @param classId ��Ӧģ��ID
	 * @return  ����
	 * @version 1.0
	 */
	public List<SysClass> findClass(int classId);
	/**
	 * 
	 * @comment �޸���Ŀ����
	 * @param projTask ����
	 * @return int ���͵�״̬
	 * @version 1.0
	 */
	public int updateProjTask(ProjTask projTask);
	/**
	 * 
	 * @comment ���������
	 * @param userId
	 * @return ���͵�״̬
	 * @version 1.0
	 */
	public int selectTasksNum(HashMap<Object, Object> map);

	/**
	 * 
	 * @comment ��ѯ���������
	 * @param userId
	 * @return ���͵�״̬
	 * @version 1.0
	 */
	public List<ProjTask> selectTasks(HashMap<Object, Object> map);
	
	/**
	 * @comment �����û�id��ѯ��Ŀid
	 * @param userId
	 * @return
	 * @version 1.0
	 */
	public String selectProjId(int userId);
	
	/**
	 * 
	 * @comment ��ѯ���������
	 * @param userId
	 * @return ���͵�״̬
	 * @version 1.0
	 */
	public int selectTasksNums(HashMap<Object, Object> map);

}
