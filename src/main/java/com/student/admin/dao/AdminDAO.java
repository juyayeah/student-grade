package com.student.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.student.admin.vo.AdminVO;
import com.student.student.vo.StudentVO;

@Mapper
@Repository("adminDAO")
public interface AdminDAO {
	public List<AdminVO> gradeList() throws DataAccessException;
	public List<StudentVO> studentList() throws DataAccessException;
	public void updateStudent(Map studentMap) throws DataAccessException;
	public void deleteStudent(String id) throws DataAccessException;
}
