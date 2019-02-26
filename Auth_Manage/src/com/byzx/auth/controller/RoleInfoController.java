/**
 * @filename RoleInfoController.java
 * @author ������
 * @date 2018��8��20�� ����6:45:52
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
 * ��ɫController��
 * 
 * @comment
 * @author ������
 * @date 2018��8��20�� ����7:21:48
 * @modifyUser ������
 * @modifyDate 2018��8��20�� ����7:21:48
 * @modifyDesc TODO
 * @version TODO
 */
@Controller
@RequestMapping("/role")
public class RoleInfoController {

	@Autowired
	private RoleInfoService roleInfoService;

	/**
	 * �����û�Id��ѯ��ɫIds
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("getRoles")
	@ResponseBody
	public JSONObject getRolesByUserId(UserInfo user) {
		// �����û�Id��ѯ��ɫIds
		String userIds = roleInfoService.getRoleIdsByUserId(user.getUserId());
		JSONObject json = new JSONObject();
		if (userIds != null) {
			json.put("userIds", userIds);
		}
		// ��������ظ�ǰ��
		return json;
	}

	/**
	 * �����û�id����ɫid�����ɫ
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
		// �����û�id����ɫid�����ɫ
		int re = roleInfoService.insertRolesByUser(request, user);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ��������ظ�ǰ��
		return json;
	}

	/**
	 * ��ҳ��ѯ��ɫ�б�
	 * 
	 * @comment
	 * @param request
	 * @param role
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/list")
	public ModelAndView getAllRoles(HttpServletRequest request, RoleInfo role) {
		// ��־λ(��Ϊ�ձ�ʶ��ѯ�û��б����򵼳�excel)
		String flag = request.getParameter("flag");
		// �Ե�ǰҳ������ֵ������
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// ��ÿҳ��ʾ��������
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		// �������������ɫ����
		int roleNum = roleInfoService.getRolesNum(role);
		// ҳ��������ѯ����ƴ��
		StringBuffer buffer = new StringBuffer();
		if (StringUtils.isNotBlank(role.getRoleName())) {
			buffer.append("&roleName=").append(role.getRoleName());
		}
		if (StringUtils.isNotBlank(role.getRoleState())) {
			buffer.append("&roleState=").append(role.getRoleState());
		}
		// �����־λ��Ϊ�գ���ѯ�û��б���ת������excel����
		if (flag != null) {
			// ��ÿҳ��ʾ������Ϊ9999���ɵ������з��Ͻ�ɫ�����б�
			PageBean page = new PageBean(9999, currNo);
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("page", page);
			map.put("role", role);
			// ��ѯ���з���������ɫ
			List<RoleInfo> list = roleInfoService.findAllRoles(map);
			page = new PageBean(list);
			// ������ת������excel����
			ModelAndView view = new ModelAndView("roleExcel");
			view.addObject("page", page);
			return view;
		} else {
			// ����ÿҳ��ʾ��������ǰҳ��
			PageBean page = new PageBean(pageNum, currNo);
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("page", page);
			map.put("role", role);
			// �����ǰҳ��ʾ������
			List<RoleInfo> rolesList = roleInfoService.findAllRoles(map);
			// �����ݷ�װ��page������
			page = new PageBean(pageNum, roleNum, currNo, rolesList,
					"/role/list.action", buffer.toString());
			// �����ݴ�����ʾ����
			ModelAndView view = new ModelAndView("role-list");
			view.addObject("page", page);
			view.addObject("listsize", rolesList.size());
			view.addObject("rolesList", rolesList);
			return view;
		}
	}

	/**
	 * ��ɫcodeΨһ��У��
	 * 
	 * @comment
	 * @param role
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("addRoleVerify")
	@ResponseBody
	public JSONObject addRoleVerify(RoleInfo role) {
		// ��ɫcodeΨһ��У��
		int re = roleInfoService.addRoleVerify(role);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ�˽���
		return json;
	}

	/**
	 * ��ӽ�ɫ
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("addRole")
	@ResponseBody
	public JSONObject addRoleMessage(RoleInfo role) {
		// ��ӽ�ɫ
		int re = roleInfoService.addRoleInfo(role);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ�˽���
		return json;
	}

	/**
	 * ��ɫ����/����
	 * 
	 * @comment
	 * @param role
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("updateRoleState")
	@ResponseBody
	public JSONObject roleForbidden(RoleInfo role) {
		// ��ɫ����/����
		int re = roleInfoService.updateRoleState(role);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ�˽���
		return json;
	}

	/**
	 * �����û���Id��ѯ��ɫIds
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("getGroupRoles")
	@ResponseBody
	public JSONObject getGroupRoles(UserInfo user) {
		// �����û���Id��ѯ��ɫIds
		String rolesIds = roleInfoService.getGroupRoles(user.getGroupId());
		JSONObject json = new JSONObject();
		if (rolesIds != null) {
			json.put("groupIds", rolesIds);
		}
		// �����������ǰ��
		return json;
	}

	/**
	 * �����û���id����ɫid�����ɫ
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
		// �����û���id����ɫid�����ɫ
		int re = roleInfoService.insertRolesByGroup(request, groupInfo);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// �����������ǰ��
		return json;
	}
}
