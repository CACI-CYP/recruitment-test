package uk.co.caci.cyp.mis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uk.co.caci.cyp.mis.entities.ExamResult;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {

	@Query(value="select * from exam_result er where er.student_id =:student_id", nativeQuery=true)
	List<ExamResult> findByStudentId(@Param("student_id") Long student_id);
	
	@Query(value="select result from exam_result er where er.id =:id", nativeQuery=true)
	List<Object> getExamResultById(@Param("id") Long id);
}
