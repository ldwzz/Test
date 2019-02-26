package com.byzx.auth.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.byzx.auth.bean.LogInfo;
import com.byzx.auth.bean.Recycle;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.service.LogInfoService;
import com.byzx.auth.service.UserInfoService;

/**
 * @comment AOP切面
 * @author tyz
 * @date 2018年9月10日 上午8:42:25
 * @modifyUser Administrator
 * @modifyDate 2018年9月10日 上午8:42:25
 * @modifyDesc TODO
 * @version TODO
 */
@Aspect
public class CheckUser {
	@Autowired
	LogInfoService logInfoService;

	@Autowired
	UserInfoService userInfoService;

	// 添加的时候获取方法参数中的项目id
	public int getParams(Object[] os, String ParamId) {
		if(os[0].toString().indexOf(ParamId)!=-1){
			int i = os[0].toString().indexOf(ParamId);
			String sub = os[0].toString().substring(i);
			System.out.println(os[0].toString().substring(i));
			String[] sp = sub.split(",")[0].split("=");
			
			 if(sp!=null  && sp.length>0){
				 
		   		 return Integer.parseInt(!("null".equals(sp[1]))?sp[1]:"0");
		   	 }else{
		   		return 0; 
		   	 }
		}else{
			return 0;
		}
		
	}

	// 删除的时候
	// 获取目标参数
	public int getParam(Object[] os) {
		Recycle recycle = (Recycle) os[0];
		return recycle.getKeyValue();

	}

	// 获取当前方法参数
		public int getParamf(Object[] os, int projId) {
			if (os[0].toString().indexOf("dynamic_id") != -1) {
				projId = logInfoService.findProjIdByDynamicId(getParam(os));
			} else if (os[0].toString().indexOf("task_id") != -1) {
				projId = logInfoService.findProjIdByTaskId(getParam(os));
			} else if (os[0].toString().indexOf("version_id") != -1) {
				projId = logInfoService.findProjIdByVersionId(getParam(os));
			} else if(os[0].toString().indexOf("bug_id")!=-1){
				projId=logInfoService.findProjIdByBugId(projId=getParam(os));
			  }else if(os[0].toString().indexOf("projId")!=-1){
				  	projId=getParams(os, "projId");
			 }
			//return projId;}
				// is_delete
			 else if (os[0].toString().indexOf("projId") != -1) {
				if (os[0].toString().indexOf("testId") != -1) {
					return 0;
				} else {
					projId = getParams(os, "projId");
				}
			}
			return projId;
		}
	/**
	 * 回环通知需要携带ProceedingJoinPoint类型参数
	 * 回环通知类似于动态代理的全过程：ProceedingJoinPoint类型的参数可以决定是否执行目标方法
	 * 且回环通知必须有返回值，返回值即为目标方法的返回值
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.byzx.auth.service.*.login(..))")
	public Object login(ProceedingJoinPoint joinPoint) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String userName = "";// 初始化userName
		int userId = 0;// 初始化userId
		String logDesc = "";// 初始化日志内容
		Object[] os = joinPoint.getArgs();// 获取目标参数的形参(这个是所有的参数)

		// 获得日志类型
		// Map<String, String[]> parameterMap = request.getParameterMap();
		// LogInfo
		// StringBuilder sb = new StringBuilder();

		UserInfo userInfo = (UserInfo) os[0];
		userName = userInfo.getUserName();
		System.out.println(SysTime.sysTime() + "用户----进行登录---Around--before"
				+ "---" + userName);
		System.out.println(request.getServletPath());// 获取url
		// System.out.println(request.getRemoteAddr());
		String ipAddress = GetIpAddress.getIpAddr(request);// 获取请求的IP
		System.out.println(ipAddress);

		UserInfo rs = (UserInfo) joinPoint.proceed();// 获取目标方法执行之后的返回值
		if (rs != null) {
			logDesc = "在" + SysTime.sysTime() + "---" + rs.getUserName()
					+ "登录成功";
			// System.out.println(logDesc);
			userId = rs.getUserId();
			System.out.println(userId);// 获得登录人id
		} else {
			userId = userInfoService.login2(userInfo).getUserId();
			logDesc = "在" + SysTime.sysTime() + "---" + userInfo.getUserName()
					+ "登录失败";
			System.out.println(logDesc);

		}

		String methodName = joinPoint.getSignature().getName(); // 获取方法名称
		System.out.println(methodName);

		LogInfo logInfo = new LogInfo(ipAddress, request.getServletPath(), 0,
				"1", logDesc, null, userId);
		// 把日志信息插入数据库
		logInfoService.append(logInfo);
		return rs;
	}

	/**
	 * @comment 添加的切面
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 * @version 1.0
	 */

	@Around("execution(* com.byzx.auth.service.*.add*(..))")
	public Object add(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName(); // 获取方法名称
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		System.out.println(request.getServletPath());// 获取url
		String ipAddress = GetIpAddress.getIpAddr(request);// 获取请求的IP
		Object object = joinPoint.proceed();// 获取目标方法执行之后的返回值
		UserInfo user = (UserInfo) request.getSession()
				.getAttribute("userInfo");
		String logDesc = user.getUserName() + "在" + SysTime.sysTime() + "执行了"
				+ methodName + "方法进行添加";

		int log = logInfoService.append(new LogInfo(ipAddress, request
				.getServletPath(), 0, "1", logDesc, null, user.getUserId()));
		return object;

	}

	@Around("execution(* com.byzx.auth.service.*.del*(..))")
	public Object delete(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName(); // 获取方法名称
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		Object[] os = joinPoint.getArgs();// 获取目标参数的形参(这个是所有的参数)
		int projId = 0;
		int param = getParamf(os, projId);

		System.out.println(request.getServletPath());// 获取url
		String ipAddress = GetIpAddress.getIpAddr(request);// 获取请求的IP
		Object object = joinPoint.proceed();// 获取目标方法执行之后的返回值
		UserInfo user = (UserInfo) request.getSession()
				.getAttribute("userInfo");
		String logDesc = user.getUserName() + "在" + SysTime.sysTime() + "执行了"
				+ methodName + "方法进行删除";
		int log = logInfoService
				.append(new LogInfo(ipAddress, request.getServletPath(), param,
						"2", logDesc, null, user.getUserId()));
		return object;

	}

	@Around("execution(* com.byzx.auth.service.*.update*(..))")
	public Object update(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName(); // 获取方法名称
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		Object[] os = joinPoint.getArgs();// 获取目标参数的形参(这个是所有的参数)
		int projId = 0;
		int param = getParamf(os, projId);

		System.out.println(request.getServletPath());// 获取url
		String ipAddress = GetIpAddress.getIpAddr(request);// 获取请求的IP
		Object object = joinPoint.proceed();// 获取目标方法执行之后的返回值
		UserInfo user = (UserInfo) request.getSession()
				.getAttribute("userInfo");
		String logDesc = user.getUserName() + "在" + SysTime.sysTime() + "执行了"
				+ methodName + "方法进行修改";
		int log = logInfoService
				.append(new LogInfo(ipAddress, request.getServletPath(), param,
						"1", logDesc, null, user.getUserId()));
		return object;

	}

	/*
	 * @Around("execution(* com.byzx.auth.service.*.insert*(..))") public Object
	 * apends(ProceedingJoinPoint joinPoint) throws Throwable {
	 * 
	 * String methodName = joinPoint.getSignature().getName(); // 获取方法名称
	 * HttpServletRequest request = ((ServletRequestAttributes)
	 * RequestContextHolder .getRequestAttributes()).getRequest(); Object[] os =
	 * joinPoint.getArgs();// 获取目标参数的形参(这个是所有的参数) HashMap paramf =
	 * getParamf(os); int projId = 0; if (paramf.get("projId") != null &&
	 * paramf.get("projId") != "") { projId = (Integer) paramf.get("projId");//
	 * 拿到项目id }
	 * 
	 * String logType ="2"; //String logType = (String) paramf.get("logType");//
	 * 拿到类型
	 * 
	 * System.out.println(request.getServletPath());// 获取url String ipAddress =
	 * GetIpAddress.getIpAddr(request);// 获取请求的IP Object object=
	 * joinPoint.proceed();// 获取目标方法执行之后的返回值
	 * 
	 * 
	 * UserInfo user = (UserInfo) request.getSession()
	 * .getAttribute("userInfo"); String logDesc = user.getUserName() + "在" +
	 * SysTime.sysTime() + "执行了" + methodName + "方法进行添加";
	 * 
	 * int log = logInfoService.append(new LogInfo(ipAddress, request
	 * .getServletPath(), projId, logType, logDesc, null, user .getUserId()));
	 * 
	 * return object; }
	 */
}
