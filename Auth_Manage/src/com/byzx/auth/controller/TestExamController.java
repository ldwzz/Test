package com.byzx.auth.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.byzx.auth.bean.BugInfo;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.Recycle;
import com.byzx.auth.bean.TestExam;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.service.BugService;
import com.byzx.auth.service.RecycleService;
import com.byzx.auth.service.TestExamService;
import com.byzx.auth.utils.ImgUtil;
import com.byzx.auth.utils.PageBean;

@Controller
@RequestMapping("/test")
public class TestExamController {
	@Autowired
	TestExamService testuse;
	@Autowired
	BugService bug;
	@Autowired
	RecycleService recy;

	@RequestMapping("/testlist")
	public ModelAndView testList(HttpServletRequest request, TestExam exam) {
		ModelAndView view = new ModelAndView("testExam");
		PageBean list = testuse.findTestExam(request, exam);
		List<ProjectInfo> proj = bug.findProject(request);
		List<UserInfo> user = testuse.findTestMember();
		// view.addObject("testList", list);
		view.addObject("proId", proj);
		view.addObject("user", user);
		view.addObject("page", list);
		return view;
	}

	@RequestMapping("/insertlist")
	public String insertList(TestExam exam) {
		int a = testuse.insertExam(exam);
		return "redirect:testlist.action";
	}

	@RequestMapping("/updatexam")
	public String updateExam(TestExam exam, MultipartFile pictureFileUpdate,
			HttpServletRequest request) throws IllegalStateException,
			IOException {
		String imgPath = ImgUtil.loadImg(request, pictureFileUpdate);
		if (imgPath != null) {
			// 把图片存储路径保存到数据库
			exam.setFileUrl(imgPath);
		}
		int a = testuse.updateExam(exam);
		return "redirect:testlist.action";
	}

	@ResponseBody
	@RequestMapping("/updateAssign")
	public JSONObject updateAssign(TestExam exam) {
		if (exam.getExecutor() != null && exam.getExecutor() != "") {
			exam.setTestState("1");
		} else {
			exam.setTestState("0");
		}
		JSONObject json = new JSONObject();
		int a = testuse.updateExamAssign(exam);
		json.put("ok", a);
		return json;
	}

	// 描述、模块id、数据库用户、表名称、主键key、主键value
	@RequestMapping("/deleteTest")
	@ResponseBody
	public JSONObject deleteTest(Recycle recycle) {
		// 项目 模块 内容
		JSONObject json = new JSONObject();
		int res = recy.delData(recycle);
		json.put("res", res);
		return json;
	}

}
