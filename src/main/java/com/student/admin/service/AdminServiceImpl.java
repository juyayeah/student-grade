package com.student.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.student.admin.dao.AdminDAO;
import com.student.admin.vo.AdminVO;
import com.student.student.vo.StudentVO;

@Service("adminService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminServiceImpl implements AdminService{
	@Autowired
	AdminVO adminVO;
	@Autowired
	AdminDAO adminDAO;
	

	@Override
	public List<AdminVO> gradeList() throws DataAccessException {
		return adminDAO.gradeList();
	}


	@Override
	public List<StudentVO> studentList() throws DataAccessException {

		return adminDAO.studentList();
	}


	@Override
	public void modStudent(Map studentMap) throws DataAccessException {
		adminDAO.updateStudent(studentMap);
		
	}


	@Override
	public void deleteStudent(String id) throws DataAccessException {
		adminDAO.deleteStudent(id);
		
	}
	
	
	
}
