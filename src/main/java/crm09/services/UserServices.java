package crm09.services;

import java.util.List;

import crm09.entity.Roles;
import crm09.entity.User;
import crm09.repository.RolesRepository;
import crm09.repository.UserRepository;

public class UserServices {

	private RolesRepository rolesRepository = new RolesRepository();
	private UserRepository userRepository = new UserRepository();
	
	public List<Roles> getAllRole(){
		return rolesRepository.findAll();
	}
	public boolean insertuser(User user) {
		return userRepository.save(user)>0;
	}
	public User  checkLogin (String email, String password) {
		return userRepository.checkLogin(email, password);
	}
}
