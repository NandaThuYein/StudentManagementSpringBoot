package studentManagement.config.persistant.dto;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class CourseRequestDTO {
	
	@Id
	@Column(name = "course_id")
	private String courseId;
	@Column(name = "course_name")
	private String courseName;
	
	@ManyToMany(mappedBy = "courses")
	private Set<StudentRequestDTO> students = new HashSet<StudentRequestDTO>();
	
	public CourseRequestDTO() {
		
	}
	
	public CourseRequestDTO(String courseId, String courseName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Set<StudentRequestDTO> getStudents() {
		return students;
	}
	public void setStudents(Set<StudentRequestDTO> students) {
		this.students = students;
	}
	@Override
	public String toString() {
		return courseName;
	}
}
