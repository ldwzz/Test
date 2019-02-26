/**
 * @filename MessageService.java
 * @author ldwzz
 * @date 2018��9��4�� ����10:54:15
 * @version 1.0
 * Copyright (C) 2018
 */
package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.Message;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.ProjectTeam;
import com.byzx.auth.bean.UserGroupInfo;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.bean.UserMessage;

/**
 *@comment վ����ҵ���ӿ�
 *@author ldwzz
 *@date 2018��9��4�� ����10:54:40
 *@modifyUser ldwzz
 *@modifyDate 2018��9��4�� ����10:54:40
 *@modifyDesc  TODO
 *@version TODO
 */
public interface MessageService {

	/**
	 * @comment ��ѯ�ҵ�վ����
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public List<Message> findMyMessage(HashMap<Object, Object> map);

	/**
	 * @comment ��ѯ�ҵ�վ��������
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int getMyMessageNum(Message message);

	/**
	 * @comment ��ѯ�ѷ���վ��������
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int getMessageNum(Message message);

	/**
	 * @comment ��ѯ�ѷ���վ����
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public List<Message> findMessage(HashMap<Object, Object> map);

	/**
	 * @comment ����userIds��ѯ�û���
	 * @param userIds
	 * @return
	 * @version 1.0
	 */
	public String findUserName(String userId);

	/**
	 * @comment ��ѯ�����û�
	 * @return
	 * @version 1.0
	 */
	public List<UserInfo> findUserInfo(UserInfo userInfo);

	/**
	 * @comment ����վ��������
	 * @param message
	 * @return
	 * @version 1.0
	 */
	public int insertMessage(Message message);

	/**
	 * @comment ����վ���Ź�ϵ
	 * @param message
	 * @return
	 * @version 1.0
	 */
	public int insertUserMessage(Message message);

	/**
	 * @comment ��ѯ���в���
	 * @return
	 * @version 1.0
	 */
	public List<UserGroupInfo> findUserGroup();

	/**
	 * @comment ����groupId��ѯ������
	 * @param groupId
	 * @return
	 * @version 1.0
	 */
	public String findGroupName(String groupId);

	/**
	 * @comment ����groupId��ѯӵ���û�
	 * @param groupId
	 * @return
	 * @version 1.0
	 */
	public String findUserIdsByGroupId(HashMap<Object, Object> map);

	/**
	 * @comment ȫ����Ϊ�Ѷ�
	 * @param toUser
	 * @return
	 * @version 1.0
	 */
	public int updateMyMessageState(UserMessage userMessage);

	/**
	 * @comment ����userNameģ����ѯ�û���
	 * @param userName
	 * @return
	 * @version 1.0
	 */
	public String findUserNameByName(UserInfo user);

	/**
	 * @comment ����userName��ѯuserId
	 * @param userName
	 * @return
	 * @version 1.0
	 */
	public Integer findUserIdByName(String userName);

	/**
	 * @comment ��ѯ������Ŀ
	 * @return
	 * @version 1.0
	 */
	public List<ProjectInfo> findAllProject();

	/**
	 * @comment ��ѯ��Ŀ��
	 * @param project
	 * @return
	 * @version 1.0
	 */
	public List<ProjectTeam> findProjectTeamByProjId(ProjectInfo project);

	/**
	 * @comment ����teamId��ѯ��Ŀ����
	 * @param team
	 * @return
	 * @version 1.0
	 */
	public String findTeamNameById(ProjectTeam team);

	/**
	 * @comment ����projId��ѯ��Ŀ��
	 * @param proj
	 * @return
	 * @version 1.0
	 */
	public String findProjNameById(ProjectInfo proj);

	/**
	 * @comment ����teamId��ѯӵ���û�
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public String findUserIdsByTeamId(HashMap<Object, Object> map);

	/**
	 * @comment ����ProjId��ѯӵ���û�
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public String findUserIdsByProjId(HashMap<Object, Object> map);

	/**
	 * @comment ��ѯվ��������
	 * @param msgId
	 * @return
	 * @version 1.0
	 */
	public Message findMyMessageDetail(UserMessage userMessage);

	/**
	 * @comment ��ѯ�����ռ���
	 * @param msgId
	 * @return
	 * @version 1.0
	 */
	public String findToUserNames(Integer msgId);

	/**
	 * @comment ��ѯվ���Ÿ��ʼ�
	 * @param msgId
	 * @return
	 * @version 1.0
	 */
	public Message findMyMessageParent(UserMessage userMessage);

	/**
	 * @comment ��ѯҪ�ظ�����
	 * @param message
	 * @return
	 * @version 1.0
	 */
	public String findReplyUserNames(Message message);

	/**
	 * @comment ��ѯҪ�ظ�����Ids
	 * @param message
	 * @return
	 * @version 1.0
	 */
	public String findReplyUserIds(Message message);
	
	/**
	 * @comment ��ѯδ���ʼ�
	 * @return
	 * @version 1.0
	 */
	public Integer findUnreadMail(UserInfo userInfo);

}
