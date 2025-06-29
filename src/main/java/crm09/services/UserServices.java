package crm09.services;

import java.util.List;

import crm09.entity.Roles;
import crm09.repository.RolesRepository;
import crm09.repository.UserRepository;

public class UserServices {

	private RolesRepository rolesRepository = new RolesRepository();
	private UserRepository userRepository = new UserRepository();
	
	public List<Roles> getAllRole(){
		return rolesRepository.findAll();
	}
	public boolean insertuser(String email, String password, int roleID, String fullname, String phone) {
		return userRepository.save(email, password, roleID, fullname, phone)>0;
	}
}
