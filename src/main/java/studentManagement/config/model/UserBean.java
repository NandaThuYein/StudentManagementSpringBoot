package studentManagement.config.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserBean {
	@NotEmpty(message = "User Id must not be empty.")
	private String userId;
	@NotEmpty(message = "User Name must not be empty.")
	private String userName;
	@NotEmpty(message = "Email must not be empty.")
	@Email(message = "Email should be valid.")
	private String userEmail;
	@Size(min = 4,max = 6,message = "Password must be between 4 and 6.")
	private String userPassword;
	@Size(min = 4,max = 6,message = "Confirm Password must be between 4 and 6.")
	private String confPassword;
	@NotEmpty(message = "User Role must not be empty.")
	private String userRole;
	
	
	public UserBean() {
		
	}
	public UserBean(@NotEmpty(message = "User Id must not be empty.") String userId,
			@NotEmpty(message = "User Name must not be empty.") String userName,
			@NotEmpty(message = "Email must not be empty.") @Email(message = "Email should be valid.") String userEmail,
			@Size(min = 4, max = 6, message = "Password must be between 4 and 6.") String userPassword,
			@Size(min = 4, max = 6, message = "Confirm Password must be between 4 and 6.") String confPassword,
			@NotEmpty(message = "User Role must not be empty.") String userRole) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.confPassword = confPassword;
		this.userRole = userRole;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getConfPassword() {
		return confPassword;
	}
	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}
	
}
