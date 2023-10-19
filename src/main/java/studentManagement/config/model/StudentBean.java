package studentManagement.config.model;

import java.util.*;

import javax.validation.constraints.NotEmpty;

import studentManagement.config.persistant.dto.CourseRequestDTO;

public class StudentBean {
		@NotEmpty(message = "Student Id must not be empty.")
		private String studentId;
		@NotEmpty(message = "Student Name must not be empty.")
		private String studentName;
		@NotEmpty(message = "Student Dob must not be empty.")
		private String studentDob;
		@NotEmpty(message = "Student Gender must not be empty.")
		private String studentGender;
		@NotEmpty(message = "Phone No must not be empty.")
		private String studentPhone;
		@NotEmpty(message = "Education must not be empty.")
		private String studentEducation;
		@NotEmpty(message = "Course must not be empty.")
		private Set<CourseRequestDTO> courses = new HashSet<CourseRequestDTO>();
		
		public StudentBean() {
			
		}
		
		public StudentBean(@NotEmpty(message = "Student Id must not be empty.") String studentId,
				@NotEmpty(message = "Student Name must not be empty.") String studentName,
				@NotEmpty(message = "Student Dob must not be empty.") String studentDob,
				@NotEmpty(message = "Student Gender must not be empty.") String studentGender,
				@NotEmpty(message = "Phone No must not be empty.") String studentPhone,
				@NotEmpty(message = "Education must not be empty.") String studentEducation,
				@NotEmpty(message = "Course must not be empty.") Set<CourseRequestDTO> courses) {
			super();
			this.studentId = studentId;
			this.studentName = studentName;
			this.studentDob = studentDob;
			this.studentGender = studentGender;
			this.studentPhone = studentPhone;
			this.studentEducation = studentEducation;
			this.courses = courses;
		}


		public Set<CourseRequestDTO> getCourses() {
			return courses;
		}
		public void setCourses(Set<CourseRequestDTO> courses) {
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
}
