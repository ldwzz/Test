/**
 * @filename UserInfoController.java
 * @author 刘智龙
 * @date 2018年8月16日 下午3:39:59
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
 * 用户信息controller层
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月26日 下午4:15:20
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月26日 下午4:15:20
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
	 * ajax前台验证
	 * 
	 * @comment
	 * @param request
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/valiCode")
	@ResponseBody
	public JSONObject validCode(HttpServletRequest request) {
		// 接收前端传过来的输入码
		String vCode = request.getParameter("vCode");
		// 从session中取出image.jsp生成随机数
		String rand = (String) request.getSession().getAttribute("rand01");
		JSONObject json = new JSONObject();
		// 验证码校验
		if (vCode != null && vCode.equals(rand)) {
			// 验证码正确
			json.put("msg", 1);
		} else {
			// 验证码错误
			json.put("msg", 0);
		}
		// 将结果返回
		return json;
	}

	/**
	 * 登录验证
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
		// 验证码二次验证
		if (vCode != null && vCode.equals(rand)) {
			// 登录验证
			UserInfo info = userInfoService.login(userInfo);
			if (info != null) {
				if ("1".equals(info.getUserState())) {
					Integer messages = messageService.findUnreadMail(info);
					session.setAttribute("unread", messages);
					// 登录成功，将userinfo存入session中
					session.setAttribute("userInfo", info);
					// 通过用户id查出其绑定的角色code，返回字符串
					String Rolecodes = userInfoService.findRoleIds(info);
					/*if (Rolecodes != null) {
						// 如果字符串中存在超级管理员角色code，将其角色code存session中
						if (Rolecodes.contains("supper_manage")) {
							session.setAttribute("user-roleCode",
									"supper_manage");
						} else if (Rolecodes.contains("manage")) {
							// 如果字符串中存在管理员角色code，将其角色code存session中
							session.setAttribute("user-roleCode", "manage");
						}
					}*/
					session.setAttribute("Rolecodes", Rolecodes);
					HashMap<Object, Object> map = new HashMap<Object, Object>();
					// 设置parentId=0
					map.put("parentId", 0);
					map.put("userInfo", info);
					StringBuffer buffer = new StringBuffer();
					// 查出第一层列表信息
					List<AuthInfo> firstList = userInfoService
							.findAuthInfo(map);
					if (firstList != null) {
						for (AuthInfo authInfo : firstList) {
							// 第一层列表id为第二层列表父id
							map.put("userInfo", info);
							map.put("parentId", authInfo.getAuthId());
							// 查出第二层列表信息
							List<AuthInfo> secondList = userInfoService
									.findAuthInfo(map);
							if (secondList != null) {
								for (AuthInfo authInfo2 : secondList) {
									map.put("userInfo", info);
									map.put("parentId", authInfo2.getAuthId());
									// 查出第三层按钮权限信息
									List<AuthInfo> thirdList = userInfoService
											.findAuthInfo(map);
									for (AuthInfo authInfo3 : thirdList) {
										// 将第三层按钮权限code拼接成字符串
										buffer.append(authInfo3.getAuthCode())
												.append(",");
									}
								}
							}
							// 将第二层列表信息set至第一层权限childlist中
							authInfo.setChildList(secondList);
						}
						// 将按钮code字符串，第一层权限结合存放在session当中
						session.setAttribute("authCodes", buffer.toString());
						session.setAttribute("firstList", firstList);
					}
					json.put("msg", 1);
				} else {
					json.put("msg", 2);
				}
			} else {
				// 登录失败
				json.put("msg", -1);
			}
		} else {
			// 验证码不正确
			json.put("msg", 0);
		}
		

		// 获取我的bug数量
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

		// 将结果返回至前端页面
		return json;
	}

	/**
	 * 登录成功跳主页面
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		// 登录成功跳主页面
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
	 * 用户注销
	 * 
	 * @comment
	 * @param session
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/tologin")
	public ModelAndView logout(HttpSession session) {
		// 让session会话失效
		session.invalidate();
		// 返回登录页面
		ModelAndView view = new ModelAndView("login");
		return view;
	}

	/**
	 * 分页查询所有用户信息
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/list")
	public ModelAndView getAllUsers(HttpServletRequest request, UserInfo user) {
		// 初始化查出用户条数变量
		int userNum = 0;
		// 标志位(若为空标识查询用户列表，否则导出excel)
		String flag = request.getParameter("flag");
		// 对当前页进行拿值、处理
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// 对每页显示数量处理
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		StringBuffer buffer = new StringBuffer();
		// 页面条件查询参数拼接
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
		// 如果标志位不为空，查询用户列表，跳转至导出excel界面
		if (flag != null) {
			// 将每页显示数设置为9999，可导出所有符合条件列表
			PageBean page = new PageBean(9999, currNo);
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("page", page);
			map.put("user", user);
			// 查询所有符合条件用户
			List<UserInfo> list = userInfoService.getAllUsers(map);
			page = new PageBean(list);
			// 带参跳转至生成excel界面
			ModelAndView view = new ModelAndView("userExcel");
			view.addObject("page", page);
			return view;
		} else {
			// 查询所有角色信息
			List<RoleInfo> rolesList = roleInfoService.getAllRoles();
			List<UserGroupInfo> groupList = userInfoService.getUserGroup();
			// 设置每页显示数量，当前页码
			PageBean page = new PageBean(pageNum, currNo);
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("page", page);
			map.put("user", user);
			// 从session中拿出当前用户的角色code
			Object userRoleCode = request.getSession().getAttribute(
					"user-roleCode");
			// 查出当前页显示的数据
			List<UserInfo> list = null;
			if (userRoleCode != null) {
				// 若当前用户角色code为超级管理员，查询所有用户信息
				if ("supper_manage".equals(userRoleCode)) {
					// 查出当前页显示的数据
					list = userInfoService.getAllUsers(map);					
					// 查出符合条件用户总数
					userNum = userInfoService.getUserNum(user);				
				} else if ("manage".equals(userRoleCode)) {
					// 当前用户角色code为管理员，查询由其创建的用户
					UserInfo userInfo = (UserInfo) request.getSession()
							.getAttribute("userInfo");
					map.put("roleCode", userRoleCode);
					user.setUserId(userInfo.getUserId());
					map.put("user", user);
					// 查出符合条件的用户信息用户信息
					list = userInfoService.getUserInfos(map);
					userNum = userInfoService.getUsersInfoNum(user);
				}
			} else {
				// 本项目只做到超级管理员，管理员的数据权限，其余用户暂可看到所有数据
				list = userInfoService.getAllUsers(map);
				userNum = userInfoService.getUserNum(user);
			}
			// 将数据封装至page对象中
			page = new PageBean(pageNum, userNum, currNo, list,
					"/user/list.action", buffer.toString());
			// 将数据传至显示界面
			ModelAndView view = new ModelAndView("user-list");
			view.addObject("page", page);
			view.addObject("listsize", list.size());
			view.addObject("rolesList", rolesList);
			view.addObject("groupList", groupList);
			return view;
		}

	}

	/**
	 * 对添加用户名进行唯一性校验
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/addVerify")
	@ResponseBody
	public JSONObject addVerify(UserInfo user) {
		// 对用户名进行唯一性校验
		int userNum = userInfoService.addVerify(user);
		JSONObject json = new JSONObject();
		if (userNum > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端
		return json;
	}

	/**
	 * 对添加用户组code进行唯一性校验
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/addGroupVerify")
	@ResponseBody
	public JSONObject addGroupVerify(UserGroupInfo groupInfo) {
		// 对用户名进行唯一性校验
		int re = userInfoService.addGroupVerify(groupInfo);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端
		return json;
	}

	/**
	 * 添加用户
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/addUserInfo")
	@ResponseBody
	public JSONObject addUserInfo(UserInfo user) {
		// 添加用户
		int re = userInfoService.addUserInfo(user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端
		return json;
	}

	/**
	 * 删除用户信息
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public JSONObject deleteUser(UserInfo user) {
		// 将用户删除状态改为1
		int re = userInfoService.deleteUser(user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端
		return json;
	}

	/**
	 * 重置用户密码为123456
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/resetPassword")
	@ResponseBody
	public JSONObject resetPassword(UserInfo user) {
		// 将用户密码重置为123456
		int re = userInfoService.resetPassword(user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端
		return json;
	}

	/**
	 * 用户状态改变
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/updateUserState")
	@ResponseBody
	public JSONObject userForbidden(UserInfo user) {
		// 将用户状态改为禁用/启用
		int re = userInfoService.updateUserState(user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端
		return json;
	}

	/**
	 * 修改用户信息
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/updateUserInfo")
	@ResponseBody
	public JSONObject updateUserInfo(UserInfo user) {
		// 对用户信息进行修改
		int re = userInfoService.updateUserInfo(user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端
		return json;
	}

	/**
	 * 分页查询用户组信息
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/grouplist")
	public ModelAndView getAllUserGroup(HttpServletRequest request,
			UserGroupInfo groupInfo) {
		// 对当前页进行取值、处理
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// 对每页显示数量处理
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		// 查出符合条件用户组总数
		int userNum = userInfoService.getUserGroupNum(groupInfo);
		// 页面条件查询参数拼接
		StringBuffer buffer = new StringBuffer();
		if (StringUtils.isNotBlank(groupInfo.getGroupName())) {
			buffer.append("&groupName=").append(groupInfo.getGroupName());
		}
		if (StringUtils.isNotBlank(groupInfo.getGroupState())) {
			buffer.append("&groupState=").append(groupInfo.getGroupState());
		}
		// 查询所有角色信息
		List<RoleInfo> rolesList = roleInfoService.getAllRoles();
		// 设置每页显示数量，当前页码
		PageBean page = new PageBean(pageNum, currNo);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);
		map.put("groupInfo", groupInfo);
		// 查出当前页显示的数据
		List<UserGroupInfo> list = userInfoService.getAllUserGroup(map);
		// 将数据封装至page对象中
		page = new PageBean(pageNum, userNum, currNo, list,
				"/user/grouplist.action", buffer.toString());
		// 将数据传至显示界面
		ModelAndView view = new ModelAndView("userGroup-list");
		view.addObject("page", page);
		view.addObject("listsize", list.size());
		view.addObject("rolesList", rolesList);
		return view;
	}

	/**
	 * 修改用户信息的用户组id
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/assignGroup")
	@ResponseBody
	public JSONObject assignGroup(UserInfo user) {
		// 修改用户信息的用户组id
		int re = userInfoService.updateGroupId(user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端
		return json;
	}

	/**
	 * 增加用户组
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/addGroupInfo")
	@ResponseBody
	public JSONObject addGroupInfo(UserGroupInfo groupInfo) {
		// 添加用户组
		int re = userInfoService.addGroupInfo(groupInfo);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端
		return json;
	}

	/**
	 * 启用、禁用用户组
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/groupForbidden")
	@ResponseBody
	public JSONObject groupForbidden(UserGroupInfo groupInfo) {
		// 修改用户组状态，0：禁用，1：启用
		int re = userInfoService.updateGroupState(groupInfo);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端
		return json;
	}
}
