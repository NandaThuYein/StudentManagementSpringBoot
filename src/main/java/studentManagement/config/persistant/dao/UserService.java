package studentManagement.config.persistant.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studentManagement.config.model.UserBean;
import studentManagement.config.persistant.dto.UserRequestDTO;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	public UserRequestDTO insertUser(UserRequestDTO bean) {
		return userRepo.save(bean);
	}
	
	public UserRequestDTO userLoginDao(UserBean bean) {
		return userRepo.userLogin(bean.getUserName(),bean.getUserPassword());
	}
	
	public List<UserRequestDTO> selectAllUser() {
		List<UserRequestDTO> list = userRepo.findAll();
		return list;
	}
	
	public List<UserRequestDTO> searchUser(UserBean bean){
		List<UserRequestDTO> list = userRepo.searchUserDao(bean.getUserId(),bean.getUserName());
		return list;
	}
	
	public UserRequestDTO searchUserOne(String userId) {		
		return userRepo.searchUserOneDao(userId);
	}
	
	public Optional<UserRequestDTO> checkUserOne(String userId) {		
		return userRepo.findById(userId);
	}
	
	public void updateUser(UserRequestDTO bean) {
		userRepo.save(bean);
	}
	
	public void deleteUser(String userId) {
		userRepo.deleteById(userId);
	}
	
}
