package com.student.student.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.student.student.vo.GradeVO;
import com.student.student.vo.StudentVO;

public interface StudentService {
	public int addStudent(Map studentMap) throws DataAccessException;
	public StudentVO login(Map loginMap) throws DataAccessException;
	public void modStudent(Map studentMap) throws DataAccessException;
	public GradeVO gradeList(String id) throws DataAccessException;
	public int addGrade(GradeVO grade) throws DataAccessException;
	public int modGrade(GradeVO grade) throws DataAccessException;
	public StudentVO detail(String id) throws DataAccessException;
}
