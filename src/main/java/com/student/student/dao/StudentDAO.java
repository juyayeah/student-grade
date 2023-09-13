package com.student.student.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.student.student.vo.GradeVO;
import com.student.student.vo.StudentVO;


@Mapper
@Repository("studentDAO")
public interface StudentDAO {
	public int insertStudent(Map studentMap) throws DataAccessException;
	public StudentVO loginById(Map studentMap) throws DataAccessException;
	public GradeVO gradeList(String id) throws DataAccessException;
	public void updateStudent(Map studentMap) throws DataAccessException;
	public int insertGrade(GradeVO gradeVO) throws DataAccessException;
	public String gradeRank(String id) throws DataAccessException;
	public int updateGrade(GradeVO gradeVO) throws DataAccessException;
	public StudentVO selectById(String id) throws DataAccessException;
}
