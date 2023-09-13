package com.student.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.student.admin.vo.AdminVO;
import com.student.student.vo.StudentVO;

public interface AdminService {
	public List<AdminVO> gradeList() throws DataAccessException;
	public List<StudentVO> studentList() throws DataAccessException;
	public void modStudent(Map studentMap) throws DataAccessException;
	public void deleteStudent(String id) throws DataAccessException;
}
