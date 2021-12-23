package uk.co.caci.cyp.mis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.caci.cyp.mis.entities.ExamResult;
import uk.co.caci.cyp.mis.entities.Grade;
import uk.co.caci.cyp.mis.entities.Student;
import uk.co.caci.cyp.mis.repository.ExamResultRepository;
import uk.co.caci.cyp.mis.repository.StudentRepository;

@Service
public class StudentServiceImpl implements IStudentService {
	
	 @Autowired
	 private StudentRepository studentRepository;
	 
	 @Autowired
	 private ExamResultRepository examResultRepository;

	@Override
	public List<Student> getAllStudents() {
		 List<Student> students = new ArrayList<Student>();
		  studentRepository.findAll().forEach(students::add);
 	     
		  return students;
	}

	@Override
	public Optional<Student> getStudentById(long id) {
		Optional<Student> studentData = studentRepository.findById(id);
		return studentData;
	}

	@Override
	public Student createStudent(Student student) {
		 return studentRepository.save(student);
	      
	}

	@Override
	public List<ExamResult> getAllExamResults() {
		 List<ExamResult> results = new ArrayList<ExamResult>();
		 results=examResultRepository.findAll();
		 // examResultRepository.findAll().forEach(results::add);
		  return results;
	}

	@Override
	public List<ExamResult> getStudentExamResultById(long student_id) {
		return examResultRepository.findByStudentId(student_id);
	}

	@Override
	public Grade getExamResultGradeById(long id) {
		Grade grade=new Grade();
		List<Object> resultData = examResultRepository.getExamResultById(id);
		
		if(!resultData.isEmpty()) {
			if(null==resultData.get(0)) {
				grade.setGrade("U");
			}else {
			int r=(Integer) resultData.get(0);
			 if(r<60){
				grade.setGrade("F");
			}else if(r>=60 && r<80) {
				grade.setGrade("B");
			}else if(r>=80){
				grade.setGrade("A");
			}
			}
		}else {
			grade.setGrade("No result found");
		}
		
		return grade;
	}
}
