package com.student.admin.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.student.admin.service.AdminService;
import com.student.admin.vo.AdminVO;
import com.student.student.service.StudentService;
import com.student.student.vo.GradeVO;
import com.student.student.vo.StudentVO;

@Controller("adminController")
public class AdminControllerImpl implements AdminController{
	@Autowired
	AdminVO adminVO;
	@Autowired
	AdminService adminService;
	@Autowired
	StudentService studentService;
	private static final String STUDENT_IMAGE_REPO = "c:\\student\\student_pic";

	@Override
	@RequestMapping(value="/admin/gradeList.do", method=RequestMethod.GET)
	public ModelAndView gradeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		List<AdminVO> gradeList= adminService.gradeList();
			mav.setViewName(viewName);
			mav.addObject("gradeList", gradeList);
			return mav;
	}

	
	@Override
	@RequestMapping(value="/admin/modGrade.do", method=RequestMethod.POST)
	public ModelAndView modGrade(GradeVO grade, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int kor = grade.getKor();
		int eng = grade.getEng();
		int math = grade.getMath();
		int history = grade.getHistory();
		int sum = kor + eng + math + history;
		int avg = sum / 4;
		grade.setSum(sum);
		grade.setAvg(avg);
		int result = studentService.modGrade(grade);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/admin/gradeList.do");
		return mav;
	}
	
	@RequestMapping(value= {"/admin/gradeForm.do", "/admin/studentForm.do"}, method=RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value="/admin/studentList.do", method=RequestMethod.GET)
	public ModelAndView studentList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		List<StudentVO> studentList = adminService.studentList();
		mav.setViewName(viewName);
		mav.addObject("studentList", studentList);
		return mav;
	}

	@Override
	@RequestMapping(value="/admin/addStudent.do", method=RequestMethod.POST)
	public ResponseEntity addStudent(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> studentMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			studentMap.put(name, value);
		}
		String id = multipartRequest.getParameter("id");
		String pic = upload(multipartRequest);
		studentMap.put("pic", pic);
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			int num = studentService.addStudent(studentMap);
			if(pic!=null&&pic.length()!=0) {
				File srcFile = new File(STUDENT_IMAGE_REPO + "\\" + "temp" + "\\" + pic);
				File destDir = new File(STUDENT_IMAGE_REPO + "\\" + id);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				message = "<script>";
				message += " alert('학생 등록이 완료되었습니다.');";
				message += " location.href='" + multipartRequest.getContextPath() + "/admin/studentList.do';";
				message += "</script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			}
		} catch(Exception e) {
			File srcFile = new File(STUDENT_IMAGE_REPO + "\\" + "temp" + "\\" + pic);
			srcFile.delete();
			message = "<script>";
			message += " alert('학생 등록에 실패했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/admin/studentForm.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception{
		String imageFileName = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			imageFileName = mFile.getOriginalFilename();
			File file = new File(STUDENT_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);
			if(mFile.getSize()!=0) {
				if(!file.exists()) {
					file.getParentFile().mkdirs();
					mFile.transferTo(new File(STUDENT_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName));
				}
			}
		}
		return imageFileName;
	}


	@Override
	@RequestMapping(value="/admin/modStudent.do", method=RequestMethod.POST)
	public ResponseEntity modStudent(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> studentMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			studentMap.put(name, value);
		}
		
		String id = multipartRequest.getParameter("id");
		String pic = upload(multipartRequest);
		studentMap.put("pic", pic);
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			studentService.modStudent(studentMap);
			if(pic!=null&&pic.length()!=0) {
				String originalFilename = (String) studentMap.get("originalFileName");
				File oldFile = new File(STUDENT_IMAGE_REPO + "\\" + id + "\\" + originalFilename);
				oldFile.delete();
				
				File srcFile = new File(STUDENT_IMAGE_REPO + "\\" + "temp" + "\\" + pic);
				File destDir = new File(STUDENT_IMAGE_REPO + "\\" + id);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				
			}
			message = "<script>";
			message += " alert('정보 수정이 완료됐습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/admin/studentList.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch(Exception e) {
			File srcFile = new File(STUDENT_IMAGE_REPO + "\\" + "temp" + "\\" + pic);
			srcFile.delete();
			message = "<script>";
			message += " alert('정보 수정을 실패했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/admin/studentList.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}


	@Override
	@RequestMapping(value="/admin/deleteStudent.do", method=RequestMethod.POST)
	public ResponseEntity deleteStudent(String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			adminService.deleteStudent(id);
			File destDir = new File(STUDENT_IMAGE_REPO + "\\" + id);
			FileUtils.deleteDirectory(destDir);
			
			message = "<script>";
			message += " alert('학생을 삭제했습니다.');";
			message += " location.href='" + request.getContextPath() + "/admin/studentList.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch(Exception e) {
			message = "<script>";
			message += " alert('삭제에 실패했습니다.');";
			message += " location.href='" + request.getContextPath() + "/admin/studentList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		
		return resEnt;
	}


	@Override
	@RequestMapping(value="/admin/addGrade.do", method=RequestMethod.POST)
	public ModelAndView addGrade(GradeVO grade, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		PrintWriter out = response.getWriter();
		int kor = grade.getKor();
		int eng = grade.getEng();
		int math = grade.getMath();
		int history = grade.getHistory();
		int sum = kor + eng + math + history;
		int avg = sum / 4;
		grade.setSum(sum);
		grade.setAvg(avg);
		int result = studentService.addGrade(grade);
		if(result == 1) {
			mav.setViewName("redirect:/admin/gradeList.do");
			return mav;
		} else {
			out.println("<script>alert('학생 등록에 실패했습니다.')</script>");
			mav.setViewName("redirect:/admin/gradeForm.do");
			return mav;
		}
	}

	
	
	
	
	
}
