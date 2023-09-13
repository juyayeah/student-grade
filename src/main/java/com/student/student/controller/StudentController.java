package com.student.student.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.student.student.vo.GradeVO;

public interface StudentController {
	public ModelAndView login(@RequestParam Map<String, String> loginMap, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addStudent(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	public ResponseEntity modStudent(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	public ModelAndView gradeList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView addMember(@ModelAttribute("grade") GradeVO grade, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modGrade(@ModelAttribute("grade") GradeVO grade, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
