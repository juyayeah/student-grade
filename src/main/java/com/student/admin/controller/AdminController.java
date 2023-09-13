package com.student.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.student.student.vo.GradeVO;

public interface AdminController {
	public ModelAndView gradeList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView studentList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addStudent(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	public ModelAndView modGrade(GradeVO grade, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity modStudent(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	public ResponseEntity deleteStudent(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addGrade(GradeVO grade, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
