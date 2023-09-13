package com.student.student.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.student.student.dao.StudentDAO;
import com.student.student.vo.GradeVO;
import com.student.student.vo.StudentVO;

@Service("studentService")
@Transactional(propagation=Propagation.REQUIRED)
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentDAO studentDAO;
	@Autowired
	GradeVO gradeVO;
	
	@Override
	public int addStudent(Map studentMap) throws DataAccessException {
		return studentDAO.insertStudent(studentMap);
	}

	@Override
	public StudentVO login(Map loginMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return studentDAO.loginById(loginMap);
	}

	@Override
	public void modStudent(Map studentMap) throws DataAccessException {
		studentDAO.updateStudent(studentMap);
		
	}

	@Override
	public GradeVO gradeList(String id) throws DataAccessException {
		gradeVO = studentDAO.gradeList(id);
		String srank = studentDAO.gradeRank(id);
		if(srank != null && srank != "") {
			int rank = Integer.parseInt(srank);
			gradeVO.setRank(rank);
			return gradeVO;
		}
		
		return gradeVO;
	}

	@Override
	public int addGrade(GradeVO grade) throws DataAccessException {
		// TODO Auto-generated method stub
		return studentDAO.insertGrade(grade);
	}

	@Override
	public int modGrade(GradeVO grade) throws DataAccessException {
		// TODO Auto-generated method stub
		return studentDAO.updateGrade(grade);
	}

	@Override
	public StudentVO detail(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		return studentDAO.selectById(id);
	}
	
	
	
}
