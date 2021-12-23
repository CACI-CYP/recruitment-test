package uk.co.caci.cyp.mis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.caci.cyp.mis.entities.ExamResult;
import uk.co.caci.cyp.mis.entities.Grade;
import uk.co.caci.cyp.mis.entities.Student;
import uk.co.caci.cyp.mis.service.IStudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	 @Autowired
	 private IStudentService iStudent;

	    @GetMapping("/student")
	    public ResponseEntity<List<Student>> getAllStudents() {
	    	try {
	    	      List<Student> students = new ArrayList<Student>();

	    	      students= iStudent.getAllStudents();
	    	     
	    	      if (students.isEmpty()) {
	    	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	      }

	    	      return new ResponseEntity<>(students, HttpStatus.OK);
	    	    } catch (Exception e) {
	    	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    	    }
	    	
	    }

	    @GetMapping("/student/{id}")
	    public ResponseEntity <Student> getStudentById(@PathVariable(value = "id") Long id) {
	    	Optional<Student> studentData = iStudent.getStudentById(id);

	        if (studentData.isPresent()) {
	          return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
	        } else {
	          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @GetMapping("/exam-result")
	    public ResponseEntity<List<ExamResult>> getAllExamResults() {
	    	try {
	    	      List<ExamResult> results = new ArrayList<ExamResult>();

	    	      results= iStudent.getAllExamResults();
	    	     
	    	      if (results.isEmpty()) {
	    	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	      }

	    	      return new ResponseEntity<>(results, HttpStatus.OK);
	    	    } catch (Exception e) {
	    	    	e.printStackTrace();
	    	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    	    }
	    	
	    }
	    
	    @GetMapping("/student/{id}/exam-result")
	    public List<ExamResult> getStudentResultById(@PathVariable(value = "id") Long student_id) {
	    	return iStudent.getStudentExamResultById(student_id);
	    	
	    }
	    
	    @GetMapping("/exam-result/{id}/grade")
	    public Grade getExamResultGradeById(@PathVariable(value = "id") Long id) {
	    	return iStudent.getExamResultGradeById(id);
	    	
	    }

	    @PostMapping("/student")
	    public ResponseEntity<Student> createStudent(@RequestBody Student student) {	    	
	    	  try {
	    		  Student students = iStudent.createStudent(student);
	    		 return new ResponseEntity<>(students, HttpStatus.OK);
	    	    } catch (Exception e) {
	    	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    	    }
	    	
	    }
}
