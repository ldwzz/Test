/**
 * @filename AuthController.java
 * @author ������
 * @date 2018��8��21�� ����7:34:21
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
 * Ȩ��controller��
 * 
 * @comment
 * @author ������
 * @date 2018��8��26�� ����12:41:56
 * @modifyUser ������
 * @modifyDate 2018��8��26�� ����12:41:56
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
	 * Ȩ������ʾ
	 * 
	 * @comment
	 * @param authInfo1
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/list")
	public ModelAndView AuthTree(AuthInfo authInfo1) {
		JSONArray jsonArray = new JSONArray();
		// ��ѯȨ����Ϣ
		List<AuthInfo> AuthList = authInfoService.findAllAuth(authInfo1);
		// �Խ�����б���
		for (AuthInfo authInfo : AuthList) {
			JSONObject json = new JSONObject();
			// ��Ȩ����Ϣ�Ž�json����
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
			// ��Ȩ��״̬ɾ��������font(��ɫ)����
			if ("0".equals(authInfo.getAuthState())) {
				json.put("font", "{'color':'red'}");
			}
			// ��json����Ž���jsonArray����
			jsonArray.add(json);
		}
		ModelAndView view = new ModelAndView("authTree");
		// ��jsonArray���鷵��Ȩ��������
		view.addObject("jsonArray", jsonArray);
		return view;
	}

	/**
	 * @comment ��ɫȨ��չʾ������
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
		// ���Ȩ��״̬���õ�Ȩ����Ϣ
		List<AuthInfo> AuthList = authInfoService.findAllAuth(authInfo1);
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		// �����ɫ�Ѿ�ӵ�е�Ȩ����Ϣ
		String authIds = authInfoService.findAuthByroleId(roleId);
		authIds = "," + authIds + ",";
		// ��Ȩ����Ϣ���Ͻ��б�������װ��json����
		for (AuthInfo authInfo : AuthList) {
			JSONObject json = new JSONObject();
			json.put("id", authInfo.getAuthId());
			json.put("pId",
					authInfo.getParentId() == null ? 0 : authInfo.getParentId());
			json.put("name", authInfo.getAuthName());
			json.put("open", false);
			// �Խ�ɫ����Ȩ������checked����
			if (authIds != null
					&& authIds.indexOf("," + authInfo.getAuthId() + ",") != -1) {
				json.put("checked", true);
			}
			jsonArray.add(json);
		}
		// ��jsonArray���鷵�ظ���ɫ����Ȩ��������
		ModelAndView view = new ModelAndView("assignAuthTree");
		view.addObject("jsonArray", jsonArray);
		view.addObject("roleId", roleId);
		return view;
	}

	/**
	 * ����ɫ����Ȩ��
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
		// ����ɫ����Ȩ��
		int re = authInfoService.addAuthByRole(request, role);
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
	 * �û�Ȩ��չʾ������
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
		// ���Ȩ��״̬���õ�Ȩ����Ϣ
		List<AuthInfo> AuthList = authInfoService.findAllAuth(authInfo1);
		int userId = Integer.parseInt(request.getParameter("userId"));
		// ����û��Ѿ�ӵ�е�Ȩ����Ϣ
		String authIds = authInfoService.findAuthByUserId(userId);
		authIds = "," + authIds + ",";
		// ��Ȩ����Ϣ���Ͻ��б�������װ��json����
		for (AuthInfo authInfo : AuthList) {
			JSONObject json = new JSONObject();
			json.put("id", authInfo.getAuthId());
			json.put("pId",
					authInfo.getParentId() == null ? 0 : authInfo.getParentId());
			json.put("name", authInfo.getAuthName());
			json.put("open", false);
			// ���û�����Ȩ������checked����
			if (authIds != null
					&& authIds.indexOf("," + authInfo.getAuthId() + ",") != -1) {
				json.put("checked", true);
			}
			jsonArray.add(json);
		}
		// ��jsonArray���鷵�ظ��û�����Ȩ��������
		ModelAndView view = new ModelAndView("user-assignAuthTree");
		view.addObject("jsonArray", jsonArray);
		view.addObject("userId", userId);
		return view;
	}

	/**
	 * ���û�����Ȩ��
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
		// ���û�����Ȩ��
		int re = authInfoService.addAuthByUser(request, user);
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
	 * Ȩ��codeΨһУ��
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/authCodeVerify")
	@ResponseBody
	public JSONObject authCodeVerify(AuthInfo authInfo) {
		// ���������Ȩ��codeУ��code�Ƿ��ظ�
		int re = authInfoService.authCodeVerify(authInfo.getAuthCode());
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
	 * ���Ȩ��
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/insertAuth")
	@ResponseBody
	public JSONObject insertAuth(AuthInfo authInfo) {
		// ����idΪ�գ����ø�idΪ0��Ȩ������Ϊ1
		if (authInfo.getParentId() == null) {
			authInfo.setParentId(0);
			authInfo.setAuthType("1");
		} else {
			// ��id�ǿգ���Ȩ��id��1
			int authType = Integer.parseInt(authInfo.getAuthType()) + 1;
			authInfo.setAuthType(String.valueOf(authType));
		}
		// ���Ȩ��
		int re = authInfoService.insertAuth(authInfo);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// �����ͨ��json���󷵻�ǰ�˽���
		return json;
	}

	/**
	 * �޸�Ȩ����Ϣ
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/updateAuth")
	@ResponseBody
	public JSONObject updateAuth(AuthInfo authInfo) {
		// �޸�Ȩ����Ϣ
		int re = authInfoService.updateAuth(authInfo);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// �����ͨ��json���󷵻�ǰ�˽���
		return json;
	}

	/**
	 * �ݹ�ɾ��Ȩ����Ϣ
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/deleteAuth")
	@ResponseBody
	public JSONObject deleteAuth(AuthInfo authInfo) {
		// ͨ��authid��ѯ�Ӳ˵���������
		List<AuthInfo> auth = authInfoService.selectAuth(authInfo.getAuthId());
		JSONObject json = new JSONObject();
		// ����ʹ��,����0
		if (auth.size() > 0) {
			json.put("msg", 0);
		} else {
			// ��δʹ��,��Ȩ��״̬����Ϊ����
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
		// �����ͨ��json���󷵻�ǰ�˽���
		return json;
	}

	/**
	 * �ݹ�ָ�Ȩ����Ϣ
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
				// ��Ȩ��δ�ָ�
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
		// �����ͨ��json���󷵻�ǰ�˽���
		return json;
	}

	/**
	 * �޸Ĳ˵�֮�󣬸�����߲˵���
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
		// �����һ���б���Ϣ
		List<AuthInfo> firstList = userInfoService.findAuthInfo(map);
		if (firstList != null) {
			for (AuthInfo authInfo : firstList) {
				// ��һ���б�idΪ�ڶ����б�id
				map.put("parentId", authInfo.getAuthId());
				// ����ڶ����б���Ϣ
				List<AuthInfo> secondList = userInfoService.findAuthInfo(map);
				authInfo.setChildList(secondList);
			}
			// ���б���Ϣ����session����
			session.setAttribute("firstList", firstList);
		}
		// ��ת��Ȩ�����б�action
		ModelAndView view = new ModelAndView("redirect:/auth/list.action");
		return view;
	}
}
