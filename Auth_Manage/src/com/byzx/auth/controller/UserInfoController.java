/**
 * @filename UserInfoController.java
 * @author ������
 * @date 2018��8��16�� ����3:39:59
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.byzx.auth.bean.AuthInfo;
import com.byzx.auth.bean.BugInfo;
import com.byzx.auth.bean.RoleInfo;
import com.byzx.auth.bean.UserGroupInfo;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.service.BugService;
import com.byzx.auth.service.MessageService;
import com.byzx.auth.service.ProjTaskService;
import com.byzx.auth.service.RoleInfoService;
import com.byzx.auth.service.UserInfoService;
import com.byzx.auth.utils.PageBean;

/**
 * �û���Ϣcontroller��
 * 
 * @comment
 * @author ������
 * @date 2018��8��26�� ����4:15:20
 * @modifyUser ������
 * @modifyDate 2018��8��26�� ����4:15:20
 * @modifyDesc TODO
 * @version TODO
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private RoleInfoService roleInfoService;
	@Autowired
	private ProjTaskService projTaskService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private BugService bugService;
	/**
	 * ajaxǰ̨��֤
	 * 
	 * @comment
	 * @param request
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/valiCode")
	@ResponseBody
	public JSONObject validCode(HttpServletRequest request) {
		// ����ǰ�˴�������������
		String vCode = request.getParameter("vCode");
		// ��session��ȡ��image.jsp���������
		String rand = (String) request.getSession().getAttribute("rand01");
		JSONObject json = new JSONObject();
		// ��֤��У��
		if (vCode != null && vCode.equals(rand)) {
			// ��֤����ȷ
			json.put("msg", 1);
		} else {
			// ��֤�����
			json.put("msg", 0);
		}
		// ���������
		return json;
	}

	/**
	 * ��¼��֤
	 * 
	 * @comment
	 * @param userInfo
	 * @param request
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/login")
	@ResponseBody
	public JSONObject login(UserInfo userInfo, HttpServletRequest request) {
		String vCode = request.getParameter("vCode");
		HttpSession session = request.getSession();
		String rand = (String) session.getAttribute("rand01");
		JSONObject json = new JSONObject();
		// ��֤�������֤
		if (vCode != null && vCode.equals(rand)) {
			// ��¼��֤
			UserInfo info = userInfoService.login(userInfo);
			if (info != null) {
				if ("1".equals(info.getUserState())) {
					Integer messages = messageService.findUnreadMail(info);
					session.setAttribute("unread", messages);
					// ��¼�ɹ�����userinfo����session��
					session.setAttribute("userInfo", info);
					// ͨ���û�id�����󶨵Ľ�ɫcode�������ַ���
					String Rolecodes = userInfoService.findRoleIds(info);
					/*if (Rolecodes != null) {
						// ����ַ����д��ڳ�������Ա��ɫcode�������ɫcode��session��
						if (Rolecodes.contains("supper_manage")) {
							session.setAttribute("user-roleCode",
									"supper_manage");
						} else if (Rolecodes.contains("manage")) {
							// ����ַ����д��ڹ���Ա��ɫcode�������ɫcode��session��
							session.setAttribute("user-roleCode", "manage");
						}
					}*/
					session.setAttribute("Rolecodes", Rolecodes);
					HashMap<Object, Object> map = new HashMap<Object, Object>();
					// ����parentId=0
					map.put("parentId", 0);
					map.put("userInfo", info);
					StringBuffer buffer = new StringBuffer();
					// �����һ���б���Ϣ
					List<AuthInfo> firstList = userInfoService
							.findAuthInfo(map);
					if (firstList != null) {
						for (AuthInfo authInfo : firstList) {
							// ��һ���б�idΪ�ڶ����б�id
							map.put("userInfo", info);
							map.put("parentId", authInfo.getAuthId());
							// ����ڶ����б���Ϣ
							List<AuthInfo> secondList = userInfoService
									.findAuthInfo(map);
							if (secondList != null) {
								for (AuthInfo authInfo2 : secondList) {
									map.put("userInfo", info);
									map.put("parentId", authInfo2.getAuthId());
									// ��������㰴ťȨ����Ϣ
									List<AuthInfo> thirdList = userInfoService
											.findAuthInfo(map);
									for (AuthInfo authInfo3 : thirdList) {
										// �������㰴ťȨ��codeƴ�ӳ��ַ���
										buffer.append(authInfo3.getAuthCode())
												.append(",");
									}
								}
							}
							// ���ڶ����б���Ϣset����һ��Ȩ��childlist��
							authInfo.setChildList(secondList);
						}
						// ����ťcode�ַ�������һ��Ȩ�޽�ϴ����session����
						session.setAttribute("authCodes", buffer.toString());
						session.setAttribute("firstList", firstList);
					}
					json.put("msg", 1);
				} else {
					json.put("msg", 2);
				}
			} else {
				// ��¼ʧ��
				json.put("msg", -1);
			}
		} else {
			// ��֤�벻��ȷ
			json.put("msg", 0);
		}
		

		// ��ȡ�ҵ�bug����
		BugInfo bug = new BugInfo();
		bug.setBugState("1");
		String rolecodes = (String) session.getAttribute("Rolecodes");
		UserInfo user = (UserInfo) request.getSession()
				.getAttribute("userInfo");
		if (rolecodes.contains("project_member")) {
			bug.setUpdateBy(user.getUserId());
		}
		int bugCount = bugService.findBugNumByCondition(bug);
		session.setAttribute("bugCount", bugCount);

		// �����������ǰ��ҳ��
		return json;
	}

	/**
	 * ��¼�ɹ�����ҳ��
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		// ��¼�ɹ�����ҳ��
		ModelAndView view = new ModelAndView("index");
		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
		String roleCode = (String) request.getSession().getAttribute("Rolecodes");
		if(roleCode.indexOf("project_leader")!=-1||roleCode.indexOf("project_member")!=-1||roleCode.indexOf("project_manager")!=-1){
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("userId", user.getUserId());
			map.put("roleCode", roleCode);
			int tasksNum = projTaskService.selectTasksNum(map);
			view.addObject("tasksNum", tasksNum);	
		}
		return view;
	}

	/**
	 * �û�ע��
	 * 
	 * @comment
	 * @param session
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/tologin")
	public ModelAndView logout(HttpSession session) {
		// ��session�ỰʧЧ
		session.invalidate();
		// ���ص�¼ҳ��
		ModelAndView view = new ModelAndView("login");
		return view;
	}

	/**
	 * ��ҳ��ѯ�����û���Ϣ
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/list")
	public ModelAndView getAllUsers(HttpServletRequest request, UserInfo user) {
		// ��ʼ������û���������
		int userNum = 0;
		// ��־λ(��Ϊ�ձ�ʶ��ѯ�û��б����򵼳�excel)
		String flag = request.getParameter("flag");
		// �Ե�ǰҳ������ֵ������
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// ��ÿҳ��ʾ��������
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		StringBuffer buffer = new StringBuffer();
		// ҳ��������ѯ����ƴ��
		if (StringUtils.isNotBlank(user.getUserName())) {
			buffer.append("&userName=").append(user.getUserName());
		}
		if (StringUtils.isNotBlank(user.getUserState())) {
			buffer.append("&userState=").append(user.getUserState());
		}
		if (StringUtils.isNotBlank(user.getUserType())) {
			buffer.append("&userType=").append(user.getUserType());
		}
		if (StringUtils.isNotBlank(user.getStartTime())) {
			buffer.append("&startTime=").append(user.getStartTime());
		}
		if (StringUtils.isNotBlank(user.getEndTime())) {
			buffer.append("&endTime=").append(user.getEndTime());
		}
		// �����־λ��Ϊ�գ���ѯ�û��б���ת������excel����
		if (flag != null) {
			// ��ÿҳ��ʾ������Ϊ9999���ɵ������з��������б�
			PageBean page = new PageBean(9999, currNo);
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("page", page);
			map.put("user", user);
			// ��ѯ���з��������û�
			List<UserInfo> list = userInfoService.getAllUsers(map);
			page = new PageBean(list);
			// ������ת������excel����
			ModelAndView view = new ModelAndView("userExcel");
			view.addObject("page", page);
			return view;
		} else {
			// ��ѯ���н�ɫ��Ϣ
			List<RoleInfo> rolesList = roleInfoService.getAllRoles();
			List<UserGroupInfo> groupList = userInfoService.getUserGroup();
			// ����ÿҳ��ʾ��������ǰҳ��
			PageBean page = new PageBean(pageNum, currNo);
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("page", page);
			map.put("user", user);
			// ��session���ó���ǰ�û��Ľ�ɫcode
			Object userRoleCode = request.getSession().getAttribute(
					"user-roleCode");
			// �����ǰҳ��ʾ������
			List<UserInfo> list = null;
			if (userRoleCode != null) {
				// ����ǰ�û���ɫcodeΪ��������Ա����ѯ�����û���Ϣ
				if ("supper_manage".equals(userRoleCode)) {
					// �����ǰҳ��ʾ������
					list = userInfoService.getAllUsers(map);					
					// ������������û�����
					userNum = userInfoService.getUserNum(user);				
				} else if ("manage".equals(userRoleCode)) {
					// ��ǰ�û���ɫcodeΪ����Ա����ѯ���䴴�����û�
					UserInfo userInfo = (UserInfo) request.getSession()
							.getAttribute("userInfo");
					map.put("roleCode", userRoleCode);
					user.setUserId(userInfo.getUserId());
					map.put("user", user);
					// ��������������û���Ϣ�û���Ϣ
					list = userInfoService.getUserInfos(map);
					userNum = userInfoService.getUsersInfoNum(user);
				}
			} else {
				// ����Ŀֻ������������Ա������Ա������Ȩ�ޣ������û��ݿɿ�����������
				list = userInfoService.getAllUsers(map);
				userNum = userInfoService.getUserNum(user);
			}
			// �����ݷ�װ��page������
			page = new PageBean(pageNum, userNum, currNo, list,
					"/user/list.action", buffer.toString());
			// �����ݴ�����ʾ����
			ModelAndView view = new ModelAndView("user-list");
			view.addObject("page", page);
			view.addObject("listsize", list.size());
			view.addObject("rolesList", rolesList);
			view.addObject("groupList", groupList);
			return view;
		}

	}

	/**
	 * ������û�������Ψһ��У��
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/addVerify")
	@ResponseBody
	public JSONObject addVerify(UserInfo user) {
		// ���û�������Ψһ��У��
		int userNum = userInfoService.addVerify(user);
		JSONObject json = new JSONObject();
		if (userNum > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ��
		return json;
	}

	/**
	 * ������û���code����Ψһ��У��
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/addGroupVerify")
	@ResponseBody
	public JSONObject addGroupVerify(UserGroupInfo groupInfo) {
		// ���û�������Ψһ��У��
		int re = userInfoService.addGroupVerify(groupInfo);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ��
		return json;
	}

	/**
	 * ����û�
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/addUserInfo")
	@ResponseBody
	public JSONObject addUserInfo(UserInfo user) {
		// ����û�
		int re = userInfoService.addUserInfo(user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ��
		return json;
	}

	/**
	 * ɾ���û���Ϣ
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public JSONObject deleteUser(UserInfo user) {
		// ���û�ɾ��״̬��Ϊ1
		int re = userInfoService.deleteUser(user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ��
		return json;
	}

	/**
	 * �����û�����Ϊ123456
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/resetPassword")
	@ResponseBody
	public JSONObject resetPassword(UserInfo user) {
		// ���û���������Ϊ123456
		int re = userInfoService.resetPassword(user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ��
		return json;
	}

	/**
	 * �û�״̬�ı�
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/updateUserState")
	@ResponseBody
	public JSONObject userForbidden(UserInfo user) {
		// ���û�״̬��Ϊ����/����
		int re = userInfoService.updateUserState(user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ��
		return json;
	}

	/**
	 * �޸��û���Ϣ
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/updateUserInfo")
	@ResponseBody
	public JSONObject updateUserInfo(UserInfo user) {
		// ���û���Ϣ�����޸�
		int re = userInfoService.updateUserInfo(user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ��
		return json;
	}

	/**
	 * ��ҳ��ѯ�û�����Ϣ
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/grouplist")
	public ModelAndView getAllUserGroup(HttpServletRequest request,
			UserGroupInfo groupInfo) {
		// �Ե�ǰҳ����ȡֵ������
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// ��ÿҳ��ʾ��������
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		// ������������û�������
		int userNum = userInfoService.getUserGroupNum(groupInfo);
		// ҳ��������ѯ����ƴ��
		StringBuffer buffer = new StringBuffer();
		if (StringUtils.isNotBlank(groupInfo.getGroupName())) {
			buffer.append("&groupName=").append(groupInfo.getGroupName());
		}
		if (StringUtils.isNotBlank(groupInfo.getGroupState())) {
			buffer.append("&groupState=").append(groupInfo.getGroupState());
		}
		// ��ѯ���н�ɫ��Ϣ
		List<RoleInfo> rolesList = roleInfoService.getAllRoles();
		// ����ÿҳ��ʾ��������ǰҳ��
		PageBean page = new PageBean(pageNum, currNo);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);
		map.put("groupInfo", groupInfo);
		// �����ǰҳ��ʾ������
		List<UserGroupInfo> list = userInfoService.getAllUserGroup(map);
		// �����ݷ�װ��page������
		page = new PageBean(pageNum, userNum, currNo, list,
				"/user/grouplist.action", buffer.toString());
		// �����ݴ�����ʾ����
		ModelAndView view = new ModelAndView("userGroup-list");
		view.addObject("page", page);
		view.addObject("listsize", list.size());
		view.addObject("rolesList", rolesList);
		return view;
	}

	/**
	 * �޸��û���Ϣ���û���id
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/assignGroup")
	@ResponseBody
	public JSONObject assignGroup(UserInfo user) {
		// �޸��û���Ϣ���û���id
		int re = userInfoService.updateGroupId(user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ��
		return json;
	}

	/**
	 * �����û���
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/addGroupInfo")
	@ResponseBody
	public JSONObject addGroupInfo(UserGroupInfo groupInfo) {
		// ����û���
		int re = userInfoService.addGroupInfo(groupInfo);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ��
		return json;
	}

	/**
	 * ���á������û���
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/groupForbidden")
	@ResponseBody
	public JSONObject groupForbidden(UserGroupInfo groupInfo) {
		// �޸��û���״̬��0�����ã�1������
		int re = userInfoService.updateGroupState(groupInfo);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ��
		return json;
	}
}
