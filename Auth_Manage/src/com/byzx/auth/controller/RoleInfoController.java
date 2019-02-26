/**
 * @filename RoleInfoController.java
 * @author 刘智龙
 * @date 2018年8月20日 下午6:45:52
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.byzx.auth.bean.RoleInfo;
import com.byzx.auth.bean.UserGroupInfo;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.service.RoleInfoService;
import com.byzx.auth.utils.PageBean;

/**
 * 角色Controller层
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月20日 下午7:21:48
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月20日 下午7:21:48
 * @modifyDesc TODO
 * @version TODO
 */
@Controller
@RequestMapping("/role")
public class RoleInfoController {

	@Autowired
	private RoleInfoService roleInfoService;

	/**
	 * 根据用户Id查询角色Ids
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("getRoles")
	@ResponseBody
	public JSONObject getRolesByUserId(UserInfo user) {
		// 根据用户Id查询角色Ids
		String userIds = roleInfoService.getRoleIdsByUserId(user.getUserId());
		JSONObject json = new JSONObject();
		if (userIds != null) {
			json.put("userIds", userIds);
		}
		// 将结果返回给前端
		return json;
	}

	/**
	 * 根据用户id，角色id分配角色
	 * 
	 * @comment
	 * @param request
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/assignRoles")
	@ResponseBody
	public JSONObject assignRoles(HttpServletRequest request, UserInfo user) {
		// 根据用户id，角色id分配角色
		int re = roleInfoService.insertRolesByUser(request, user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回给前端
		return json;
	}

	/**
	 * 分页查询角色列表
	 * 
	 * @comment
	 * @param request
	 * @param role
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/list")
	public ModelAndView getAllRoles(HttpServletRequest request, RoleInfo role) {
		// 标志位(若为空标识查询用户列表，否则导出excel)
		String flag = request.getParameter("flag");
		// 对当前页进行拿值、处理
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// 对每页显示数量处理
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		// 查出符合条件角色总数
		int roleNum = roleInfoService.getRolesNum(role);
		// 页面条件查询参数拼接
		StringBuffer buffer = new StringBuffer();
		if (StringUtils.isNotBlank(role.getRoleName())) {
			buffer.append("&roleName=").append(role.getRoleName());
		}
		if (StringUtils.isNotBlank(role.getRoleState())) {
			buffer.append("&roleState=").append(role.getRoleState());
		}
		// 如果标志位不为空，查询用户列表，跳转至导出excel界面
		if (flag != null) {
			// 将每页显示数设置为9999，可导出所有符合角色条件列表
			PageBean page = new PageBean(9999, currNo);
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("page", page);
			map.put("role", role);
			// 查询所有符合条件角色
			List<RoleInfo> list = roleInfoService.findAllRoles(map);
			page = new PageBean(list);
			// 带参跳转至生成excel界面
			ModelAndView view = new ModelAndView("roleExcel");
			view.addObject("page", page);
			return view;
		} else {
			// 设置每页显示数量，当前页码
			PageBean page = new PageBean(pageNum, currNo);
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("page", page);
			map.put("role", role);
			// 查出当前页显示的数据
			List<RoleInfo> rolesList = roleInfoService.findAllRoles(map);
			// 将数据封装至page对象中
			page = new PageBean(pageNum, roleNum, currNo, rolesList,
					"/role/list.action", buffer.toString());
			// 将数据传至显示界面
			ModelAndView view = new ModelAndView("role-list");
			view.addObject("page", page);
			view.addObject("listsize", rolesList.size());
			view.addObject("rolesList", rolesList);
			return view;
		}
	}

	/**
	 * 角色code唯一性校验
	 * 
	 * @comment
	 * @param role
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("addRoleVerify")
	@ResponseBody
	public JSONObject addRoleVerify(RoleInfo role) {
		// 角色code唯一性校验
		int re = roleInfoService.addRoleVerify(role);
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
	 * 添加角色
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("addRole")
	@ResponseBody
	public JSONObject addRoleMessage(RoleInfo role) {
		// 添加角色
		int re = roleInfoService.addRoleInfo(role);
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
	 * 角色禁用/启用
	 * 
	 * @comment
	 * @param role
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("updateRoleState")
	@ResponseBody
	public JSONObject roleForbidden(RoleInfo role) {
		// 角色禁用/启用
		int re = roleInfoService.updateRoleState(role);
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
	 * 根据用户组Id查询角色Ids
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("getGroupRoles")
	@ResponseBody
	public JSONObject getGroupRoles(UserInfo user) {
		// 根据用户组Id查询角色Ids
		String rolesIds = roleInfoService.getGroupRoles(user.getGroupId());
		JSONObject json = new JSONObject();
		if (rolesIds != null) {
			json.put("groupIds", rolesIds);
		}
		// 将结果返回至前端
		return json;
	}

	/**
	 * 根据用户组id，角色id分配角色
	 * 
	 * @comment
	 * @param request
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/assignUserGroupRoles")
	@ResponseBody
	public JSONObject assignUserGroupRoles(HttpServletRequest request,
			UserGroupInfo groupInfo) {
		// 根据用户组id，角色id分配角色
		int re = roleInfoService.insertRolesByGroup(request, groupInfo);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回至前端
		return json;
	}
}
