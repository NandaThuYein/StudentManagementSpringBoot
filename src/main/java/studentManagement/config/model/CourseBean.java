package studentManagement.config.model;

import javax.validation.constraints.NotEmpty;

public class CourseBean {
		@NotEmpty(message = "Course Id must not be empty.")
		private String courseId;
		@NotEmpty(message = "Course Name must not be empty.")
		private String courseName;
		
		public CourseBean() {
			
		}
		
		public CourseBean(@NotEmpty(message = "Course Id must not be empty.") String courseId,
				@NotEmpty(message = "Course Name must not be empty.") String courseName) {
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
		
}
