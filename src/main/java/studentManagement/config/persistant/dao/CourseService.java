package studentManagement.config.persistant.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studentManagement.config.persistant.dto.CourseRequestDTO;

@Service
public class CourseService {
	
	@Autowired
	CourseRepository courseRepo;
	
	public CourseRequestDTO insertCourse(CourseRequestDTO dto) {
		return courseRepo.save(dto);
	}
	
	public List<CourseRequestDTO> selectAllCourse() {
		List<CourseRequestDTO> list = courseRepo.findAll();
		return list;
	}
}
