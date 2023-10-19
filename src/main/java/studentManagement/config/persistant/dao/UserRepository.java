package studentManagement.config.persistant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import studentManagement.config.persistant.dto.*;

public interface UserRepository extends JpaRepository<UserRequestDTO,String> {
		
	@Query("select u from UserRequestDTO u where u.userName = ?1 and u.userPassword = ?2")
	public UserRequestDTO userLogin(String userName,String userPass);
	
	@Query("select u from UserRequestDTO u where u.userId = ?1 or u.userName = ?2")
	public List<UserRequestDTO> searchUserDao(String userId,String userName);
	
	@Query("select u from UserRequestDTO u where u.userId = ?1")
	public UserRequestDTO searchUserOneDao(String userId);
}
