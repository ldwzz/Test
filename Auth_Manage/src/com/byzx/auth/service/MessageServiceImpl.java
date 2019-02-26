/**
 * @filename MessageServiceImpl.java
 * @author ldwzz
 * @date 2018��9��4�� ����11:01:04
 * @version 1.0
 * Copyright (C) 2018
 */
package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byzx.auth.bean.Message;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.ProjectTeam;
import com.byzx.auth.bean.UserGroupInfo;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.bean.UserMessage;
import com.byzx.auth.dao.MessageDao;

/**
 * @comment վ����ҵ���ʵ����
 * @author ldwzz
 * @date 2018��9��4�� ����11:04:16
 * @modifyUser ldwzz
 * @modifyDate 2018��9��4�� ����11:04:16
 * @modifyDesc TODO
 * @version TODO
 */
@Service
public class MessageServiceImpl implements MessageService {

	// ע��վ����dao��ӿ�
	@Autowired
	private MessageDao messageDao;

	/**
	 * @comment ��ѯ�ҵ�վ����
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@Override
	public List<Message> findMyMessage(HashMap<Object, Object> map) {

		return messageDao.findMyMessage(map);
	}

	/**
	 * @comment ��ѯ�ҵ�վ��������
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@Override
	public int getMyMessageNum(Message message) {

		return messageDao.getMyMessageNum(message);
	}

	/**
	 * @comment ��ѯ�ѷ���վ��������
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@Override
	public int getMessageNum(Message message) {

		return messageDao.getMessageNum(message);
	}

	/**
	 * @comment ��ѯ�ѷ���վ����
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@Override
	public List<Message> findMessage(HashMap<Object, Object> map) {

		return messageDao.findMessage(map);
	}

	/**
	 * @comment ����userIds��ѯ�û���
	 * @param userIds
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findUserName(String userId) {

		return messageDao.findUserName(userId);
	}

	/**
	 * @comment ��ѯ�����û�
	 * @param
	 * @return
	 * @version 1.0
	 */
	@Override
	public List<UserInfo> findUserInfo(UserInfo userInfo) {

		return messageDao.findUserInfo(userInfo);
	}

	/**
	 * @comment ����վ��������
	 * @param message
	 * @return
	 * @version 1.0
	 */
	@Override
	public int insertMessage(Message message) {

		return messageDao.insertMessage(message);
	}

	/**
	 * @comment ����վ���Ź�ϵ
	 * @param message
	 * @return
	 * @version 1.0
	 */
	@Override
	public int insertUserMessage(Message message) {

		return messageDao.insertUserMessage(message);
	}

	/**
	 * @comment ��ѯ���в���
	 * @return
	 * @version 1.0
	 */
	@Override
	public List<UserGroupInfo> findUserGroup() {

		return messageDao.findUserGroup();
	}

	/**
	 * @comment ����groupId��ѯ������
	 * @param groupId
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findGroupName(String groupId) {

		return messageDao.findGroupName(groupId);
	}

	/**
	 * @comment ����groupId��ѯӵ���û�
	 * @param groupId
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findUserIdsByGroupId(HashMap<Object, Object> map) {

		return messageDao.findUserIdsByGroupId(map);
	}

	/**
	 * @comment ȫ����Ϊ�Ѷ�
	 * @param toUser
	 * @return
	 * @version 1.0
	 */
	@Override
	public int updateMyMessageState(UserMessage userMessage) {

		return messageDao.updateMyMessageState(userMessage);
	}

	/**
	 * @comment ����userNameģ����ѯ�û���
	 * @param userName
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findUserNameByName(UserInfo user) {

		return messageDao.findUserNameByName(user);
	}

	/**
	 * @comment ����userName��ѯuserId
	 * @param userName
	 * @return
	 * @version 1.0
	 */
	@Override
	public Integer findUserIdByName(String userName) {

		return messageDao.findUserIdByName(userName);
	}

	/**
	 * @comment ��ѯ������Ŀ
	 * @return
	 * @version 1.0
	 */
	@Override
	public List<ProjectInfo> findAllProject() {

		return messageDao.findAllProject();
	}

	/**
	 * @comment ��ѯ��Ŀ��
	 * @param project
	 * @return
	 * @version 1.0
	 */
	@Override
	public List<ProjectTeam> findProjectTeamByProjId(ProjectInfo project) {

		return messageDao.findProjectTeamByProjId(project);
	}

	/**
	 * @comment ����teamId��ѯ��Ŀ����
	 * @param team
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findTeamNameById(ProjectTeam team) {

		return messageDao.findTeamNameById(team);
	}

	/**
	 * @comment ����projId��ѯ��Ŀ��
	 * @param proj
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findProjNameById(ProjectInfo proj) {

		return messageDao.findProjNameById(proj);
	}

	/**
	 * @comment ����teamId��ѯӵ���û�
	 * @param map
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findUserIdsByTeamId(HashMap<Object, Object> map) {

		return messageDao.findUserIdsByTeamId(map);
	}

	/**
	 * @comment ����ProjId��ѯӵ���û�
	 * @param map
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findUserIdsByProjId(HashMap<Object, Object> map) {

		return messageDao.findUserIdsByProjId(map);
	}

	/**
	 * @comment ��ѯվ��������
	 * @param msgId
	 * @return
	 * @version 1.0
	 */
	@Override
	public Message findMyMessageDetail(UserMessage userMessage) {

		return messageDao.findMyMessageDetail(userMessage);
	}

	/**
	 * @comment ��ѯ�����ռ���
	 * @param msgId
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findToUserNames(Integer msgId) {

		return messageDao.findToUserNames(msgId);
	}

	
	/**
	 * @comment ��ѯվ���Ÿ��ʼ�
	 * @param msgId
	 * @return
	 * @version 1.0
	 */
	@Override
	public Message findMyMessageParent(UserMessage userMessage) {
		
		return messageDao.findMyMessageParent(userMessage);
	}

	/**
	 * @comment ��ѯҪ�ظ�����
	 * @param message
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findReplyUserNames(Message message) {
		
		return messageDao.findReplyUserNames(message);
	}

	/**
	 * @comment ��ѯҪ�ظ�����Ids
	 * @param message
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findReplyUserIds(Message message) {
		
		return messageDao.findReplyUserIds(message);
	}

	/**
	 * @comment ��ѯδ���ʼ�
	 * @return
	 * @version 1.0
	 */
	@Override
	public Integer findUnreadMail(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return messageDao.findUnreadMail(userInfo);
	}
}
