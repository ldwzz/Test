/**
 * @filename MessageController.java
 * @author ldwzz
 * @date 2018年9月4日 上午10:48:27
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
 * @comment 站内信controller层
 * @author ldwzz
 * @date 2018年9月4日 上午10:48:44
 * @modifyUser ldwzz
 * @modifyDate 2018年9月4日 上午10:48:44
 * @modifyDesc TODO
 * @version TODO
 */
@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;

	/**
	 * @comment 查找已发送站内信
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
		// 对当前页进行拿值、处理
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// 对每页显示数量处理
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		// 页面条件查询参数拼接
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
		// 查出符合条件站内信总数
		int messageNum = messageService.getMessageNum(message);

		// 设置每页显示数量，当前页码
		PageBean page = new PageBean(pageNum, currNo);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);
		map.put("message", message);
		// 查出当前页显示的数据
		List<Message> messages = messageService.findMessage(map);
		// 将数据封装至page对象中
		page = new PageBean(pageNum, messageNum, currNo, messages,
				"/message/findMessage.action", buffer.toString());
		ModelAndView view = new ModelAndView("message-list");
		view.addObject("page", page);
		view.addObject("messages", messages);
		return view;
	}

	/**
	 * @comment 查找我的站内信
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
		// 对当前页进行拿值、处理
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// 对每页显示数量处理
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		// 页面条件查询参数拼接
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
		// 查出符合条件站内信总数
		int messageNum = messageService.getMyMessageNum(message);

		// 设置每页显示数量，当前页码
		PageBean page = new PageBean(pageNum, currNo);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);
		map.put("message", message);
		// 查出当前页显示的数据
		List<Message> myMessages = messageService.findMyMessage(map);
		// 将数据封装至page对象中
		page = new PageBean(pageNum, messageNum, currNo, myMessages,
				"/message/findMyMessage.action", buffer.toString());
		ModelAndView view = new ModelAndView("myMessage-list");
		view.addObject("page", page);
		view.addObject("myMessages", myMessages);
		return view;
	}

	/**
	 * @comment 根据userIds查询用户名
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
	 * @comment 根据userName模糊查询用户名
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
	 * @comment 根据userName查询userId
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
	 * @comment 根据groupId查询部门名
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
	 * @comment 根据teamId查询项目组名
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
	 * @comment 根据projId查询项目名
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
	 * @comment 通讯录
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
	 * 站内信单发、多发
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
	 * 站内信群发（部门）
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
	 * 站内信群发（项目、项目组）
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
	 * @comment 站内信全部发送
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
	 * @comment 修改为已读
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
	 * @comment 查询项目组
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
	 * @comment 查询站内信详情
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
		// 状态改为已读
		messageService.updateMyMessageState(userMessage);
		// 查详情
		Message message = messageService.findMyMessageDetail(userMessage);

		// 查收件人
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
	 * @comment 查询站内信详情（传值）
	 * @param session
	 * @param userMessage
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/findMyMessageParent")
	public ModelAndView findMyMessageParent(HttpSession session,
			UserMessage userMessage, Integer sendType, String toUserNames) {
		ModelAndView view = new ModelAndView("replyMessage");
		// 查详情(作为原邮件)
		Message message = messageService.findMyMessageDetail(userMessage);
		String fromName = message.getUserMessage().getFromName();
		String fromUser = message.getUserMessage().getFromUser().toString();

		// 查收件人（要回复的人）
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
	 * @comment 递归查parent邮件
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
	 * 站内信回复，全部回复
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
		
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				// 获取文件名
				String fileName = file.getOriginalFilename();
				if (fileName != null && fileName != "") {
					// 生成存储名
					String saveName = UUID.randomUUID().toString() + fileName;
		
					sbf.append(saveName).append(",");
					// 定义存储路径
					String filePath = request.getSession().getServletContext()
							.getRealPath("/")
							+ "upload/" + saveName;
					File file2 = new File(filePath);
					try {
						// 存储文件
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
     * 文件下载功能  
     * @param request  
     * @param response  
     * @throws Exception  
     */  
    @RequestMapping("/download")  
    public void down(HttpServletRequest request,HttpServletResponse response,String fileName) throws Exception{  	   
    	//模拟文件，myfile.txt为需要下载的文件  
        String fileNames = request.getSession().getServletContext().getRealPath("/upload")+"\\"+fileName;  
        
        //获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileNames)));  
        
        //转码，免得文件名中文乱码  
  
        fileName = URLEncoder.encode(fileName,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
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
