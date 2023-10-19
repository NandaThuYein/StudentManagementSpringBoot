package studentManagement.config.persistant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import studentManagement.config.persistant.dto.CourseRequestDTO;

public interface CourseRepository extends JpaRepository<CourseRequestDTO,String> {

}
