/**
 * @filename MessageController.java
 * @author ldwzz
 * @date 2018��9��4�� ����10:48:27
 * @version 1.0
 * Copyright (C) 2018
 */
package com.byzx.auth.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;

import net.sf.json.JSONObject;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.byzx.auth.bean.Message;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.ProjectTeam;
import com.byzx.auth.bean.UserGroupInfo;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.bean.UserMessage;
import com.byzx.auth.service.MessageService;
import com.byzx.auth.utils.PageBean;

/**
 * @comment վ����controller��
 * @author ldwzz
 * @date 2018��9��4�� ����10:48:44
 * @modifyUser ldwzz
 * @modifyDate 2018��9��4�� ����10:48:44
 * @modifyDesc TODO
 * @version TODO
 */
@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;

	/**
	 * @comment �����ѷ���վ����
	 * @param request
	 * @param session
	 * @param message
	 * @param userMessage
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/findMessage")
	@ResponseBody
	public ModelAndView findMessage(HttpServletRequest request,
			HttpSession session, Message message, UserMessage userMessage) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		userMessage.setFromUser(user.getUserId());
		message.setUserMessage(userMessage);
		message.setCreateBy(user.getUserId());
		// �Ե�ǰҳ������ֵ������
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// ��ÿҳ��ʾ��������
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		// ҳ��������ѯ����ƴ��
		StringBuffer buffer = new StringBuffer();
		if (StringUtils.isNotBlank(message.getUserMessage().getToName())) {
			buffer.append("&toName=").append(
					message.getUserMessage().getToName());
		}
		if (StringUtils.isNotBlank(message.getSendType())) {
			buffer.append("&sendType=").append(message.getSendType());
		}
		if (StringUtils.isNotBlank(message.getStartTime())) {
			buffer.append("&startTime=").append(message.getStartTime());
		}
		if (StringUtils.isNotBlank(message.getEndTime())) {
			buffer.append("&endTime=").append(message.getEndTime());
		}
		// �����������վ��������
		int messageNum = messageService.getMessageNum(message);

		// ����ÿҳ��ʾ��������ǰҳ��
		PageBean page = new PageBean(pageNum, currNo);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);
		map.put("message", message);
		// �����ǰҳ��ʾ������
		List<Message> messages = messageService.findMessage(map);
		// �����ݷ�װ��page������
		page = new PageBean(pageNum, messageNum, currNo, messages,
				"/message/findMessage.action", buffer.toString());
		ModelAndView view = new ModelAndView("message-list");
		view.addObject("page", page);
		view.addObject("messages", messages);
		return view;
	}

	/**
	 * @comment �����ҵ�վ����
	 * @param request
	 * @param session
	 * @param message
	 * @param userMessage
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/findMyMessage")
	@ResponseBody
	public ModelAndView findMyMessage(HttpServletRequest request,
			HttpSession session, Message message, UserMessage userMessage) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		userMessage.setToUser(user.getUserId());
		message.setUserMessage(userMessage);
		// �Ե�ǰҳ������ֵ������
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// ��ÿҳ��ʾ��������
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		// ҳ��������ѯ����ƴ��
		StringBuffer buffer = new StringBuffer();
		if (StringUtils.isNotBlank(message.getUserMessage().getFromName())) {
			buffer.append("&fromName=").append(
					message.getUserMessage().getFromName());
		}
		if (StringUtils.isNotBlank(message.getUserMessage().getReadState())) {
			buffer.append("&readState=").append(
					message.getUserMessage().getReadState());
		}
		if (StringUtils.isNotBlank(message.getSendType())) {
			buffer.append("&sendType=").append(message.getSendType());
		}
		if (StringUtils.isNotBlank(message.getStartTime())) {
			buffer.append("&startTime=").append(message.getStartTime());
		}
		if (StringUtils.isNotBlank(message.getEndTime())) {
			buffer.append("&endTime=").append(message.getEndTime());
		}
		// �����������վ��������
		int messageNum = messageService.getMyMessageNum(message);

		// ����ÿҳ��ʾ��������ǰҳ��
		PageBean page = new PageBean(pageNum, currNo);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);
		map.put("message", message);
		// �����ǰҳ��ʾ������
		List<Message> myMessages = messageService.findMyMessage(map);
		// �����ݷ�װ��page������
		page = new PageBean(pageNum, messageNum, currNo, myMessages,
				"/message/findMyMessage.action", buffer.toString());
		ModelAndView view = new ModelAndView("myMessage-list");
		view.addObject("page", page);
		view.addObject("myMessages", myMessages);
		return view;
	}

	/**
	 * @comment ����userIds��ѯ�û���
	 * @param userIds
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/findUserNames")
	@ResponseBody
	public JSONObject findUserNames(String userIds) {
		JSONObject json = new JSONObject();
		String[] split = userIds.split(",");
		StringBuffer userNames = new StringBuffer();
		for (String userId : split) {
			if (messageService.findUserName(userId) != null) {
				userNames.append(messageService.findUserName(userId) + ",");
			}

		}
		json.put("userNames", userNames.toString());
		return json;
	}

	/**
	 * @comment ����userNameģ����ѯ�û���
	 * @param userName
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/findUserNameByName")
	@ResponseBody
	public JSONObject findUserNameByName(HttpSession session, String userName) {
		JSONObject json = new JSONObject();
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		user.setUserName(userName);
		String userNames = messageService.findUserNameByName(user);

		json.put("msg", userNames);
		return json;
	}

	/**
	 * @comment ����userName��ѯuserId
	 * @param userName
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/findUserIdByName")
	@ResponseBody
	public JSONObject findUserIdByName(String userName) {
		JSONObject json = new JSONObject();
		Integer userId = messageService.findUserIdByName(userName);
		json.put("msg", userId);
		return json;
	}

	/**
	 * @comment ����groupId��ѯ������
	 * @param groupId
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/findGroupName")
	@ResponseBody
	public JSONObject findGroupName(String groupId) {
		JSONObject json = new JSONObject();
		String groupName = messageService.findGroupName(groupId);
		json.put("groupName", groupName);
		return json;

	}

	/**
	 * @comment ����teamId��ѯ��Ŀ����
	 * @param team
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/findTeamNameById")
	@ResponseBody
	public JSONObject findTeamNameById(ProjectTeam team) {
		JSONObject json = new JSONObject();
		String teamName = messageService.findTeamNameById(team);
		json.put("teamName", teamName);
		return json;

	}

	/**
	 * @comment ����projId��ѯ��Ŀ��
	 * @param proj
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/findProjNameById")
	@ResponseBody
	public JSONObject findProjNameById(ProjectInfo proj) {
		JSONObject json = new JSONObject();
		String projName = messageService.findProjNameById(proj);
		json.put("projName", projName);
		return json;

	}

	/**
	 * @comment ͨѶ¼
	 * @param
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/findUserList")
	@ResponseBody
	public ModelAndView findUserList(HttpSession session) {
		ModelAndView view = new ModelAndView("Email");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

		List<UserInfo> userList = messageService.findUserInfo(userInfo);
		List<UserGroupInfo> userGroup = messageService.findUserGroup();
		List<ProjectInfo> projects = messageService.findAllProject();
		ProjectInfo project = new ProjectInfo();
		project.setProjId(projects.get(0).getProjId());
		List<ProjectTeam> projectTeams = messageService
				.findProjectTeamByProjId(project);
		view.addObject("userLIst", userList);
		view.addObject("userGroup", userGroup);
		view.addObject("projects", projects);
		view.addObject("projectTeams", projectTeams);
		return view;
	}
	/**
	 * վ���ŵ������෢
	 * 
	 * @comment
	 * @param message
	 * @param userMessage
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/insertMessage")
	@ResponseBody
	public JSONObject insertMessage(Message message, UserMessage userMessage) {
		JSONObject json = new JSONObject();
		message.setCreateBy(userMessage.getFromUser());
		userMessage.setParentId(0);
		String ids = userMessage.getToUserIds();
		String[] split = ids.split(",");
		if (split.length > 1) {
			message.setSendType("2");

		} else {
			message.setSendType("1");
		}

		messageService.insertMessage(message);
		int res = 0;
		for (int i = 0; i < split.length; i++) {
			userMessage.setMsgId(message.getMsgId());
			userMessage.setToUser(Integer.parseInt(split[i]));
			message.setUserMessage(userMessage);
			res = messageService.insertUserMessage(message);
		}
		if (res > 0) {
			json.put("res", res);
		} else {
			json.put("res", "0");
		}

		return json;
	}

	/**
	 * վ����Ⱥ�������ţ�
	 * 
	 * @comment
	 * @param message
	 * @param userMessage
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/insertGroupMessage")
	@ResponseBody
	public JSONObject insertGroupMessage(HttpSession session, Message message,
			UserMessage userMessage) {
		JSONObject json = new JSONObject();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		message.setCreateBy(userMessage.getFromUser());
		message.setSendType("3");
		userMessage.setParentId(0);
		Integer groupId = userMessage.getToGroupId();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("userId", userInfo.getUserId());
		map.put("groupId", groupId);
		String ids = messageService.findUserIdsByGroupId(map);
		if (ids != null && ids != "") {
			String[] split = ids.split(",");
			Integer msgId = messageService.insertMessage(message);
			int res = 0;
			for (int i = 0; i < split.length; i++) {
				userMessage.setMsgId(message.getMsgId());
				userMessage.setToUser(Integer.parseInt(split[i]));
				message.setUserMessage(userMessage);
				res = messageService.insertUserMessage(message);
			}
			if (res > 0) {
				json.put("res", res);
			} else {
				json.put("res", "0");
			}
		} else {
			json.put("res", "-1");
		}

		return json;
	}

	/**
	 * վ����Ⱥ������Ŀ����Ŀ�飩
	 * 
	 * @comment
	 * @param message
	 * @param userMessage
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/insertProjMessage")
	@ResponseBody
	public JSONObject insertProjMessage(HttpSession session, Message message,
			UserMessage userMessage) {
		JSONObject json = new JSONObject();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		message.setCreateBy(userMessage.getFromUser());
		message.setSendType("3");
		userMessage.setParentId(0);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("userId", userInfo.getUserId());
		Integer teamId = userMessage.getTeamId();
		String ids = "";
		if (teamId != null && teamId != 0) {
			map.put("teamId", teamId);
			ids = messageService.findUserIdsByTeamId(map);
		} else {
			map.put("projId", userMessage.getProjId());
			ids = messageService.findUserIdsByProjId(map);
		}

		if (ids != null && ids != "") {
			String[] split = ids.split(",");
			Integer msgId = messageService.insertMessage(message);
			int res = 0;
			for (int i = 0; i < split.length; i++) {
				userMessage.setMsgId(message.getMsgId());
				userMessage.setToUser(Integer.parseInt(split[i]));
				message.setUserMessage(userMessage);
				res = messageService.insertUserMessage(message);
			}
			if (res > 0) {
				json.put("res", res);
			} else {
				json.put("res", "0");
			}
		} else {
			json.put("res", "-1");
		}

		return json;
	}

	/**
	 * @comment վ����ȫ������
	 * @param message
	 * @param userMessage
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/insertAllMessage")
	@ResponseBody
	public JSONObject insertAllMessage(HttpSession session, Message message,
			UserMessage userMessage) {
		JSONObject json = new JSONObject();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		message.setCreateBy(userMessage.getFromUser());
		userMessage.setParentId(0);
		List<UserInfo> list = messageService.findUserInfo(userInfo);
		messageService.insertMessage(message);
		int res = 0;
		for (int i = 0; i < list.size(); i++) {
			userMessage.setMsgId(message.getMsgId());
			userMessage.setToUser(list.get(i).getUserId());
			message.setUserMessage(userMessage);
			res = messageService.insertUserMessage(message);
		}
		if (res > 0) {
			json.put("res", res);
		} else {
			json.put("res", "0");
		}

		return json;
	}

	/**
	 * @comment �޸�Ϊ�Ѷ�
	 * @param session
	 * @param userMessage
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/updateMyMessageState")
	public String updateMyMessageState(HttpSession session,
			UserMessage userMessage) {
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Integer toUser = userInfo.getUserId();
		userMessage.setToUser(toUser);
		int i = messageService.updateMyMessageState(userMessage);
		return "redirect:findMyMessage.action";
	}

	/**
	 * @comment ��ѯ��Ŀ��
	 * @param
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/findProjectTeamByProjId")
	@ResponseBody
	public JSONObject findProjectTeamByProjId(ProjectInfo project) {
		JSONObject json = new JSONObject();
		List<ProjectTeam> projectTeams = messageService
				.findProjectTeamByProjId(project);
		json.put("res", projectTeams);
		return json;
	}

	/**
	 * @comment ��ѯվ��������
	 * @param session
	 * @param userMessage
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/findMyMessageDetail")
	public ModelAndView findMyMessageDetail(HttpSession session,
			UserMessage userMessage) {
		ModelAndView view = new ModelAndView("myMessageDetail");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Integer toUser = userInfo.getUserId();
		userMessage.setToUser(toUser);
		// ״̬��Ϊ�Ѷ�
		messageService.updateMyMessageState(userMessage);
		// ������
		Message message = messageService.findMyMessageDetail(userMessage);

		// ���ռ���
		String toUserNames = messageService.findToUserNames(userMessage
				.getMsgId());
		if (message.getFileUrl()!=null && message.getFileUrl()!="") {
			String[] files = message.getFileUrl().split(",");
			view.addObject("files", files);
		}

		if (userMessage.getParentId() > 0) {
			List<Message> list = new ArrayList<Message>();
			list = getList(userMessage, list);
			for (int i = 0; i < list.size(); i++) {
				String parentToUserNames = messageService.findToUserNames(list
						.get(i).getMsgId());
				list.get(i).getUserMessage()
						.setParentToUserNames(parentToUserNames);
			}
			view.addObject("list", list);
		}

		view.addObject("message", message);
		view.addObject("toUserNames", toUserNames);

		return view;
	}

	/**
	 * @comment ��ѯվ�������飨��ֵ��
	 * @param session
	 * @param userMessage
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/findMyMessageParent")
	public ModelAndView findMyMessageParent(HttpSession session,
			UserMessage userMessage, Integer sendType, String toUserNames) {
		ModelAndView view = new ModelAndView("replyMessage");
		// ������(��Ϊԭ�ʼ�)
		Message message = messageService.findMyMessageDetail(userMessage);
		String fromName = message.getUserMessage().getFromName();
		String fromUser = message.getUserMessage().getFromUser().toString();

		// ���ռ��ˣ�Ҫ�ظ����ˣ�
		if (sendType == 2) {
			String replyUserNames = messageService.findReplyUserNames(message);
			String replyUserIds = messageService.findReplyUserIds(message);
			fromName += "," + replyUserNames;
			fromUser += "," + replyUserIds;
		}
		if (userMessage.getParentId() > 0) {
			List<Message> list = new ArrayList<Message>();
			list = getList(userMessage, list);
			for (int i = 0; i < list.size(); i++) {
				String parentToUserNames = messageService.findToUserNames(list
						.get(i).getMsgId());
				list.get(i).getUserMessage()
						.setParentToUserNames(parentToUserNames);
			}
			view.addObject("list", list);
		}
		view.addObject("replyUserNames", fromName);
		view.addObject("replyUserIds", fromUser);
		view.addObject("toUserNames", toUserNames);
		view.addObject("message", message);

		return view;
	}

	/**
	 * @comment �ݹ��parent�ʼ�
	 * @param userMessage
	 * @param list
	 * @return
	 * @version 1.0
	 */
	public List<Message> getList(UserMessage userMessage, List<Message> list) {

		Message parentMessage = messageService.findMyMessageParent(userMessage);
		list.add(parentMessage);
		if (parentMessage.getUserMessage().getParentId() > 0) {
			UserMessage userMessages = parentMessage.getUserMessage();
			getList(userMessages, list);
		}

		return list;
	}

	/**
	 * վ���Żظ���ȫ���ظ�
	 * 
	 * @comment
	 * @param message
	 * @param userMessage
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/insertReplyMessage")
	@ResponseBody
	public JSONObject insertReplyMessage(Message message,
			UserMessage userMessage) {
		JSONObject json = new JSONObject();
		message.setCreateBy(userMessage.getFromUser());
		String ids = userMessage.getToUserIds();
		String[] split = ids.split(",");
		if (split.length > 1) {
			message.setSendType("2");

		} else {
			message.setSendType("1");
		}
		messageService.insertMessage(message);
		int res = 0;
		for (int i = 0; i < split.length; i++) {
			userMessage.setMsgId(message.getMsgId());
			userMessage.setToUser(Integer.parseInt(split[i]));
			message.setUserMessage(userMessage);
			res = messageService.insertUserMessage(message);
		}
		if (res > 0) {
			json.put("res", res);
		} else {
			json.put("res", "0");
		}

		return json;
	}

	@RequestMapping("/upload")
	@ResponseBody
	public JSONObject upload(
			@RequestParam(value = "files") MultipartFile[] files,
			HttpServletRequest request) {

		JSONObject json = new JSONObject();

		StringBuffer sbf = new StringBuffer();
		System.out.println(files.toString());
		
			// ѭ����ȡfile�����е��ļ�
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				// ��ȡ�ļ���
				String fileName = file.getOriginalFilename();
				if (fileName != null && fileName != "") {
					// ���ɴ洢��
					String saveName = UUID.randomUUID().toString() + fileName;
		
					sbf.append(saveName).append(",");
					// ����洢·��
					String filePath = request.getSession().getServletContext()
							.getRealPath("/")
							+ "upload/" + saveName;
					File file2 = new File(filePath);
					try {
						// �洢�ļ�
						file.transferTo(file2);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			
			json.put("filePath", sbf.toString());
		return json;
	}

	/**  
     * �ļ����ع���  
     * @param request  
     * @param response  
     * @throws Exception  
     */  
    @RequestMapping("/download")  
    public void down(HttpServletRequest request,HttpServletResponse response,String fileName) throws Exception{  	   
    	//ģ���ļ���myfile.txtΪ��Ҫ���ص��ļ�  
        String fileNames = request.getSession().getServletContext().getRealPath("/upload")+"\\"+fileName;  
        
        //��ȡ������  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileNames)));  
        
        //ת�룬����ļ�����������  
  
        fileName = URLEncoder.encode(fileName,"UTF-8");  
        //�����ļ�����ͷ  
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);    
        //1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����    
        response.setContentType("multipart/form-data"); 
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
        bis.close();
    }

}
