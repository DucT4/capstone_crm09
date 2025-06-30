package crm09.services;

import java.util.List;

import crm09.entity.Roles;
import crm09.entity.User;
import crm09.repository.RolesRepository;
import crm09.repository.UserRepository;

public class UserServices {

	private RolesRepository rolesRepository = new RolesRepository();
	private UserRepository userRepository = new UserRepository();
	//get all role feature
	public List<Roles> getAllRole(){
		return rolesRepository.findAll();
	}
	// insert user feature
	public boolean insertuser(User user) {
		return userRepository.save(user)>0;
	}
	// login feature
	public User  checkLogin (String email, String password) {
		return userRepository.checkLogin(email, password);
	}
	public int delete (User user) {
		return userRepository.delete(user);
	}
    //get all user feature
	public List<User> getAll() {
		return userRepository.getAll();
	}
}
