package uk.co.caci.cyp.mis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.co.caci.cyp.mis.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
