package studentManagement.config.persistant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import studentManagement.config.persistant.dto.StudentRequestDTO;

public interface StudentRepository extends JpaRepository<StudentRequestDTO,String> {
	@Query("select s from StudentRequestDTO s join s.courses c where s.studentId = ?1 or"
			+ " s.studentName = ?2 or c.courseName = ?3")
	public List<StudentRequestDTO> searchStudent(String studentId,String studentName,String courseName);
}
