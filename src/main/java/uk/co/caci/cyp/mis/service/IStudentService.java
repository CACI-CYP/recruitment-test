package uk.co.caci.cyp.mis.service;

import java.util.List;
import java.util.Optional;

import uk.co.caci.cyp.mis.entities.ExamResult;
import uk.co.caci.cyp.mis.entities.Grade;
import uk.co.caci.cyp.mis.entities.Student;

public interface IStudentService {

	List<Student> getAllStudents();
	Optional<Student> getStudentById(long id);
	Student createStudent(Student student);
	List<ExamResult> getAllExamResults();
	List<ExamResult> getStudentExamResultById(long id);
	Grade getExamResultGradeById(long id);
}
