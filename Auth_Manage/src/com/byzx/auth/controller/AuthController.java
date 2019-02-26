/**
 * @filename AuthController.java
 * @author 刘智龙
 * @date 2018年8月21日 下午7:34:21
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.byzx.auth.bean.AuthInfo;
import com.byzx.auth.bean.RoleInfo;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.service.AuthInfoService;
import com.byzx.auth.service.RoleInfoService;
import com.byzx.auth.service.UserInfoService;

/**
 * 权限controller层
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月26日 下午12:41:56
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月26日 下午12:41:56
 * @modifyDesc TODO
 * @version TODO
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthInfoService authInfoService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private RoleInfoService roleInfoService;

	/**
	 * 权限树显示
	 * 
	 * @comment
	 * @param authInfo1
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/list")
	public ModelAndView AuthTree(AuthInfo authInfo1) {
		JSONArray jsonArray = new JSONArray();
		// 查询权限信息
		List<AuthInfo> AuthList = authInfoService.findAllAuth(authInfo1);
		// 对结果进行遍历
		for (AuthInfo authInfo : AuthList) {
			JSONObject json = new JSONObject();
			// 将权限信息放进json对象
			json.put("id", authInfo.getAuthId());
			json.put("pId",
					authInfo.getParentId() == null ? 0 : authInfo.getParentId());
			json.put("name", authInfo.getAuthName());
			json.put("open", false);
			json.put("authType", authInfo.getAuthType());
			json.put("authName", authInfo.getAuthName());
			json.put("authDesc", authInfo.getAuthDesc());
			json.put("authUrl", authInfo.getAuthUrl());
			json.put("authState", authInfo.getAuthState());
			// 若权限状态删除，增加font(红色)属性
			if ("0".equals(authInfo.getAuthState())) {
				json.put("font", "{'color':'red'}");
			}
			// 将json对象放进进jsonArray数组
			jsonArray.add(json);
		}
		ModelAndView view = new ModelAndView("authTree");
		// 将jsonArray数组返回权限树界面
		view.addObject("jsonArray", jsonArray);
		return view;
	}

	/**
	 * @comment 角色权限展示及回显
	 * @param authInfo1
	 * @param request
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/assignAuth")
	public ModelAndView assignAuth(AuthInfo authInfo1,
			HttpServletRequest request) {
		JSONArray jsonArray = new JSONArray();
		authInfo1.setAuthState("1");
		// 查出权限状态启用的权限信息
		List<AuthInfo> AuthList = authInfoService.findAllAuth(authInfo1);
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		// 查出角色已经拥有的权限信息
		String authIds = authInfoService.findAuthByroleId(roleId);
		authIds = "," + authIds + ",";
		// 对权限信息集合进行遍历，封装至json数组
		for (AuthInfo authInfo : AuthList) {
			JSONObject json = new JSONObject();
			json.put("id", authInfo.getAuthId());
			json.put("pId",
					authInfo.getParentId() == null ? 0 : authInfo.getParentId());
			json.put("name", authInfo.getAuthName());
			json.put("open", false);
			// 对角色已有权限增加checked属性
			if (authIds != null
					&& authIds.indexOf("," + authInfo.getAuthId() + ",") != -1) {
				json.put("checked", true);
			}
			jsonArray.add(json);
		}
		// 将jsonArray数组返回给角色分配权限树界面
		ModelAndView view = new ModelAndView("assignAuthTree");
		view.addObject("jsonArray", jsonArray);
		view.addObject("roleId", roleId);
		return view;
	}

	/**
	 * 给角色分配权限
	 * 
	 * @comment
	 * @param request
	 * @param role
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/assignAuthRole")
	@ResponseBody
	public JSONObject assignAuth(HttpServletRequest request, RoleInfo role) {
		// 给角色分配权限
		int re = authInfoService.addAuthByRole(request, role);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端界面
		return json;
	}

	/**
	 * 用户权限展示及回显
	 * 
	 * @comment
	 * @param authInfo1
	 * @param request
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/userAuthShow")
	public ModelAndView userAuthShow(AuthInfo authInfo1, UserInfo user,
			HttpServletRequest request) {
		JSONArray jsonArray = new JSONArray();
		authInfo1.setAuthState("1");
		// 查出权限状态启用的权限信息
		List<AuthInfo> AuthList = authInfoService.findAllAuth(authInfo1);
		int userId = Integer.parseInt(request.getParameter("userId"));
		// 查出用户已经拥有的权限信息
		String authIds = authInfoService.findAuthByUserId(userId);
		authIds = "," + authIds + ",";
		// 对权限信息集合进行遍历，封装至json数组
		for (AuthInfo authInfo : AuthList) {
			JSONObject json = new JSONObject();
			json.put("id", authInfo.getAuthId());
			json.put("pId",
					authInfo.getParentId() == null ? 0 : authInfo.getParentId());
			json.put("name", authInfo.getAuthName());
			json.put("open", false);
			// 对用户已有权限增加checked属性
			if (authIds != null
					&& authIds.indexOf("," + authInfo.getAuthId() + ",") != -1) {
				json.put("checked", true);
			}
			jsonArray.add(json);
		}
		// 将jsonArray数组返回给用户分配权限树界面
		ModelAndView view = new ModelAndView("user-assignAuthTree");
		view.addObject("jsonArray", jsonArray);
		view.addObject("userId", userId);
		return view;
	}

	/**
	 * 给用户分配权限
	 * 
	 * @comment
	 * @param request
	 * @param role
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/userassignAuth")
	@ResponseBody
	public JSONObject userassignAuth(HttpServletRequest request, UserInfo user) {
		// 给用户分配权限
		int re = authInfoService.addAuthByUser(request, user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端界面
		return json;
	}

	/**
	 * 权限code唯一校验
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/authCodeVerify")
	@ResponseBody
	public JSONObject authCodeVerify(AuthInfo authInfo) {
		// 根据输入的权限code校验code是否重复
		int re = authInfoService.authCodeVerify(authInfo.getAuthCode());
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端界面
		return json;
	}

	/**
	 * 添加权限
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/insertAuth")
	@ResponseBody
	public JSONObject insertAuth(AuthInfo authInfo) {
		// 若父id为空，设置父id为0，权限类型为1
		if (authInfo.getParentId() == null) {
			authInfo.setParentId(0);
			authInfo.setAuthType("1");
		} else {
			// 父id非空，则权限id加1
			int authType = Integer.parseInt(authInfo.getAuthType()) + 1;
			authInfo.setAuthType(String.valueOf(authType));
		}
		// 添加权限
		int re = authInfoService.insertAuth(authInfo);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果通过json对象返回前端界面
		return json;
	}

	/**
	 * 修改权限信息
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/updateAuth")
	@ResponseBody
	public JSONObject updateAuth(AuthInfo authInfo) {
		// 修改权限信息
		int re = authInfoService.updateAuth(authInfo);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果通过json对象返回前端界面
		return json;
	}

	/**
	 * 递归删除权限信息
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/deleteAuth")
	@ResponseBody
	public JSONObject deleteAuth(AuthInfo authInfo) {
		// 通过authid查询子菜单有无在用
		List<AuthInfo> auth = authInfoService.selectAuth(authInfo.getAuthId());
		JSONObject json = new JSONObject();
		// 若在使用,返回0
		if (auth.size() > 0) {
			json.put("msg", 0);
		} else {
			// 若未使用,将权限状态更改为禁用
			StringBuffer buffer = new StringBuffer();
			String childIds = authInfoService.selectChildList(authInfo,buffer);
			String[] authIds = childIds.split(",");
			int re = authInfoService.deleteChildList(authIds);
			if (re > 0) {
				json.put("msg", 1);
			} else {
				json.put("msg", -1);
			}
		}
		// 将结果通过json对象返回前端界面
		return json;
	}

	/**
	 * 递归恢复权限信息
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/recoverAuth")
	@ResponseBody
	public JSONObject recoverAuth(AuthInfo authInfo) {
		JSONObject json = new JSONObject();
		AuthInfo parent = authInfoService.findParent(authInfo);
		StringBuffer buffer = new StringBuffer();
		if (parent != null) {
			if("0".equals(parent.getAuthState())){
				// 父权限未恢复
				json.put("msg", 0);
			}else{
				String authIds = authInfoService.selectChildList(authInfo, buffer);
				int re = authInfoService.recoverChildList(authIds);
				if (re > 0) {
					json.put("msg", 1);
				} else {
					json.put("msg", -1);
				}
			}	
		} else {
			String authIds = authInfoService.selectChildList(authInfo, buffer);
			int re = authInfoService.recoverChildList(authIds);
			if (re > 0) {
				json.put("msg", 1);
			} else {
				json.put("msg", -1);
			}
		}
		// 将结果通过json对象返回前端界面
		return json;
	}

	/**
	 * 修改菜单之后，更新左边菜单栏
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/updateList")
	@ResponseBody
	public ModelAndView updateList(HttpServletRequest request, UserInfo info) {
		HttpSession session = request.getSession();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("parentId", 0);
		map.put("userInfo", info);
		// 查出第一层列表信息
		List<AuthInfo> firstList = userInfoService.findAuthInfo(map);
		if (firstList != null) {
			for (AuthInfo authInfo : firstList) {
				// 第一层列表id为第二层列表父id
				map.put("parentId", authInfo.getAuthId());
				// 查出第二层列表信息
				List<AuthInfo> secondList = userInfoService.findAuthInfo(map);
				authInfo.setChildList(secondList);
			}
			// 将列表信息存在session当中
			session.setAttribute("firstList", firstList);
		}
		// 跳转至权限树列表action
		ModelAndView view = new ModelAndView("redirect:/auth/list.action");
		return view;
	}
}
