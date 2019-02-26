package com.byzx.auth.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.byzx.auth.bean.BugInfo;
import com.byzx.auth.bean.BugReplay;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.Recycle;
import com.byzx.auth.bean.SysClass;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.service.BugService;
import com.byzx.auth.service.ProjectInfoService;
import com.byzx.auth.service.RecycleService;
import com.byzx.auth.service.SysClassService;
import com.byzx.auth.utils.ImgUtil;
import com.byzx.auth.utils.PageBean;

@Controller
@RequestMapping("/bug")
public class BugController {
	@Autowired
	BugService bugserve;
	@Autowired
	private ProjectInfoService projectInfoService;
	@Autowired
	SysClassService sys;
	@Autowired
	RecycleService recy;

	/**
	 * 查询不同角色的所有项目，并查询出第一个项目所对应的所有BUG
	 * 
	 * @param request
	 * @param project
	 * @param bug
	 * @return
	 */
	@RequestMapping("/buginfo")
	public ModelAndView getProjectAndBug(HttpServletRequest request,
			BugInfo bug, String turn, HttpSession session) {
		if (turn != null && turn != "") {
			session.setAttribute("turn", turn);
		}
		if ("myBug".equals(session.getAttribute("turn"))) {
			bug.setBugState("1");
			String rolecodes = (String) session.getAttribute("Rolecodes");
			UserInfo user = (UserInfo) request.getSession().getAttribute(
					"userInfo");
			if (rolecodes.contains("project_member")) {
				bug.setUpdateBy(user.getUserId());
			}
		}
		List<ProjectInfo> prolist = bugserve.findProject(request);
		PageBean bugbean = null;
		if (prolist != null) {
			if (bug.getProjId() == null) {
				if (!"myBug".equals(turn)) {
					bug.setProjId(prolist.get(0).getProjId());
				}
				bugbean = bugserve.findBug(request, bug);
			} else {
				bugbean = bugserve.findBug(request, bug);
			}
		}
		ModelAndView view = new ModelAndView();
		if (("9999").equals(request.getParameter("pageNum"))) {
			view.setViewName("bugexcel");
		} else {
			view.setViewName("buginfo");
		}
		view.addObject("page", bugbean);
		view.addObject("project", prolist);
		return view;
	}

	@ResponseBody
	@RequestMapping("/searchmodel")
	public JSONObject searchModel(Integer projId) {
		JSONObject json = new JSONObject();
		if (projId != null) {
			ProjectInfo proj = projectInfoService.findProjectInfoByProjId(projId);
			SysClass sy = new SysClass();
			sy.setClassId(proj.getClassId());
			List<SysClass> sysclass = sys.findSysClassByPid(sy);
			String sysname = "";
			String sysid = "";
			for (int i = 0; i < sysclass.size(); i++) {
				sysname += sysclass.get(i).getClassName() + ",";
				sysid += sysclass.get(i).getClassId() + ",";
			}
			json.put("sb", 0);
			json.put("syname", sysname);
			json.put("syid", sysid);
		} else {
			json.put("sb", 1);
		}
		return json;
	}

	@RequestMapping("/insertbug")
	public String insertBug(BugInfo buginf, MultipartFile pictureFile,
			HttpServletRequest request) throws IllegalStateException,
			IOException {
		String imgPath = ImgUtil.loadImg(request, pictureFile);
		if (imgPath != null) {
			// 把图片存储路径保存到数据库
			buginf.setFileUrl(imgPath);
		}
		int b = bugserve.insertBug(buginf);
		return "redirect:buginfo.action";
	}

	@RequestMapping("/updatebug")
	public String updateBug(BugInfo buginf, MultipartFile pictureFileUpdate,
			HttpServletRequest request) throws IllegalStateException,
			IOException {
		String imgPath = ImgUtil.loadImg(request, pictureFileUpdate);
		if (imgPath != null) {
			// 把图片存储路径保存到数据库
			buginf.setFileUrl(imgPath);
		}
		int a = bugserve.updateBug(buginf);
		return "redirect:buginfo.action";
	}

	@ResponseBody
	@RequestMapping("/checkednum")
	public JSONObject checkedNum(BugInfo buginf) {
		int num = bugserve.findBugNumExist(buginf);
		JSONObject json = new JSONObject();
		json.put("ok", num);
		return json;
	}

	@ResponseBody
	@RequestMapping("/updatebugstate")
	public JSONObject updateBugState(BugInfo buginf) {
		JSONObject json = new JSONObject();
		int a = bugserve.updateBugState(buginf);
		json.put("update", a);
		return json;
	}

	@ResponseBody
	@RequestMapping("/doitman")
	public JSONObject doItMan(BugInfo buginf) {
		JSONObject json = new JSONObject();
		List<UserInfo> user = bugserve.finduserByProj(buginf);
		String userid = "";
		String username = "";
		for (int i = 0; i < user.size(); i++) {
			userid += user.get(i).getUserId() + ",";
			username += user.get(i).getUserName() + ",";
		}
		json.put("userid", userid);
		json.put("username", username);
		return json;
	}

	@ResponseBody
	@RequestMapping("/assignBug")
	public JSONObject assignBug(BugInfo buginf) {
		JSONObject json = new JSONObject();
		if (buginf.getUpdateBy() != null && buginf.getUpdateBy() > 0) {
			buginf.setBugState("1");
		} else {
			buginf.setBugState("0");
		}
		int a = bugserve.updateAssignBug(buginf);
		if (a > 0) {
			bugserve.updateBugState(buginf);
		}
		json.put("ok", a);
		return json;
	}

	@RequestMapping("/detail")
	public String bugDetail(Model model, BugInfo buginf) {
		BugInfo bug = bugserve.findBugDetail(buginf);
		HashMap map = new HashMap();
		map.put("bugId", buginf.getBugId());
		List<BugReplay> bugmake = bugserve.findBugRemake(map);
		model.addAttribute("detail", bug);
		model.addAttribute("bugmake", bugmake);
		return "bugdetail";
	}

	@ResponseBody
	@RequestMapping("/addremake")
	public JSONObject addRemake(BugReplay play) {
		JSONObject json = new JSONObject();
		int a = bugserve.insertBugRemake(play);
		json.put("ok", a);
		return json;
	}

	@ResponseBody
	@RequestMapping("/deletemake")
	public JSONObject DeleteRemake(BugReplay play) {
		JSONObject json = new JSONObject();
		int a = bugserve.deleteReply(play);
		json.put("ok", a);
		return json;
	}

	// 描述、模块id、数据库用户、表名称、主键key、主键value
	@RequestMapping("/deleteBug")
	@ResponseBody
	public JSONObject deleteBug(Recycle recycle) {
		// 项目 模块 内容
		JSONObject json = new JSONObject();
		int res = recy.delData(recycle);
		json.put("res", res);
		return json;
	}

}
