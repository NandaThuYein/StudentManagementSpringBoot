package studentManagement.config.persistant.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studentManagement.config.persistant.dto.StudentRequestDTO;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepo;
	
	public void insertStudent(StudentRequestDTO dto) {
		studentRepo.save(dto);
	}
	
	public List<StudentRequestDTO> selectAllStudent() {
		List<StudentRequestDTO> list = studentRepo.findAll();
		List<StudentRequestDTO> list2 = studentRepo.searchStudent("S001", "", "");
		System.out.println(list2);
		return list;
	}
	
	public StudentRequestDTO selectOneStudent(String studentId) {
		return studentRepo.findById(studentId).get();
	}
	
	public Optional<StudentRequestDTO> checkOneStudent(String studentId) {
		return studentRepo.findById(studentId);
	}
	
	public void updateStudent(StudentRequestDTO dto) {
		studentRepo.save(dto);
	}
	
	public void deleteStudent(String studentId) {
		studentRepo.deleteById(studentId);
	}
}
