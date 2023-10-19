package studentManagement.config.persistant.dto;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class StudentRequestDTO {
	
	@Id
	@Column(name = "student_id")
	private String studentId;
	@Column(name = "student_name")
	private String studentName;
	@Column(name = "student_dob")
	private String studentDob;
	@Column(name = "student_gender")
	private String studentGender;
	@Column(name = "student_phone")
	private String studentPhone;
	@Column(name = "student_education")
	private String studentEducation;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "students_courses", joinColumns = { @JoinColumn(name = "student_id") },
				inverseJoinColumns = { @JoinColumn(name = "course_id") })
	private Set<CourseRequestDTO> courses = new HashSet<CourseRequestDTO>();
	
	public StudentRequestDTO() {
		
	}
	
	public StudentRequestDTO(String studentId, String studentName, String studentDob, String studentGender,
			String studentPhone, String studentEducation, Set<CourseRequestDTO> courses) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentDob = studentDob;
		this.studentGender = studentGender;
		this.studentPhone = studentPhone;
		this.studentEducation = studentEducation;
		this.courses = courses;
	}

	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentDob() {
		return studentDob;
	}
	public void setStudentDob(String studentDob) {
		this.studentDob = studentDob;
	}
	public String getStudentGender() {
		return studentGender;
	}
	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}
	public String getStudentPhone() {
		return studentPhone;
	}
	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}
	public String getStudentEducation() {
		return studentEducation;
	}
	public void setStudentEducation(String studentEducation) {
		this.studentEducation = studentEducation;
	}
	public Set<CourseRequestDTO> getCourses() {
		return courses;
	}
	public void setCourses(Set<CourseRequestDTO> courses) {
		this.courses = courses;
	}
	
}
