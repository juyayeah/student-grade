package com.student.student.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.student.student.service.StudentService;
import com.student.student.vo.GradeVO;
import com.student.student.vo.StudentVO;

@Controller("studentController")
public class StudentControllerImpl implements StudentController{
	private static final String STUDENT_IMAGE_REPO = "c:\\student\\student_pic";
	@Autowired
	StudentVO studentVO;
	@Autowired
	GradeVO gradeVO;
	@Autowired
	StudentService studentService;
	
	@RequestMapping(value= {"/main.do", "/"}, method= {RequestMethod.GET, RequestMethod.POST})
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@RequestMapping(value= {"/student/loginForm.do", "/student/studentForm.do", "/student/gradeForm.do"}, method=RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value="/student/login.do", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam Map<String, String> loginMap, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView();
		HttpSession session =request.getSession();
		studentVO = studentService.login(loginMap);
		if(studentVO.getId().equals("admin")) {
			session.setAttribute("isLogOn", true);
			session.setAttribute("student", studentVO);
			session.setAttribute("side_menu", "admin_mode");
			mav.setViewName("redirect:/admin/gradeList.do");
		} else if(studentVO!=null && studentVO.getId()!=null) {
			session.setAttribute("isLogOn", true);
			session.setAttribute("student", studentVO);
			mav.setViewName("redirect:/student/gradeList.do");
		} else {
			String message="로그인실패";
			mav.addObject("message", message);
			mav.setViewName("/student/loginForm");
		}
		
		return mav;
	}

	@Override
	@RequestMapping(value="/student/addStudent.do", method= {RequestMethod.GET, RequestMethod.POST})
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
				message += " alert('회원가입이 완료되었습니다.');";
				message += " location.href='" + multipartRequest.getContextPath() + "/main.do';";
				message += "</script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			}
		} catch(Exception e) {
			File srcFile = new File(STUDENT_IMAGE_REPO + "\\" + "temp" + "\\" + pic);
			srcFile.delete();
			message = "<script>";
			message += " alert('회원가입에 실패했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/main.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	@Override
	@RequestMapping(value="/student/gradeList.do", method=RequestMethod.GET)
	public ModelAndView gradeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		HttpSession session =request.getSession();
		studentVO = (StudentVO) session.getAttribute("student");
		String id = studentVO.getId();
		gradeVO = studentService.gradeList(id);
		if(gradeVO == null) {
			mav.setViewName("redirect:/student/gradeForm.do");
			return mav;
		} else {
			mav.setViewName(viewName);
			mav.addObject("grade", gradeVO);
			return mav;
		}
		
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
	@RequestMapping(value="/logout.do" ,method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session=request.getSession();
		session.setAttribute("isLogOn", false);
		session.removeAttribute("student");
		session.removeAttribute("side_menu");
		mav.setViewName("redirect:/main.do");
		return mav;
	}

	@Override
	@RequestMapping(value="/student/studentDetail.do", method=RequestMethod.GET)
	public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		HttpSession session = request.getSession();
		studentVO = (StudentVO) session.getAttribute("student");
		String id = studentVO.getId();
		studentVO = studentService.detail(id);
		session.removeAttribute("student");
		session.setAttribute("student", studentVO);
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value="/student/modStudent.do", method=RequestMethod.POST)
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
			message += " location.href='" + multipartRequest.getContextPath() + "/student/studentDetail.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch(Exception e) {
			File srcFile = new File(STUDENT_IMAGE_REPO + "\\" + "temp" + "\\" + pic);
			srcFile.delete();
			message = "<script>";
			message += " alert('정보 수정을 실패했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/student/studentDetail.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	@Override
	@RequestMapping(value="/student/addGrade.do", method=RequestMethod.POST)
	public ModelAndView addMember(GradeVO grade, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session =request.getSession();
		ModelAndView mav = new ModelAndView();
		studentVO = (StudentVO) session.getAttribute("student");
		String id = studentVO.getId();
		grade.setId(id);
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
			mav.setViewName("redirect:/student/gradeList.do");
			return mav;
		} else {
			mav.setViewName("redirect:/student/gradeForm.do");
			return mav;
		}
		
	}

	@Override
	@RequestMapping(value="/student/modGrade.do", method=RequestMethod.POST)
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
		mav.setViewName("/student/gradeList.do");
		return mav;
	}

	
	
}
