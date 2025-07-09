package crm09.services;

import java.util.List;

import crm09.entity.Roles;
import crm09.repository.RolesRepository;

public class RoleServices {
    private RolesRepository rolesRepository = new RolesRepository();
    // get list
    public List<Roles> findAll() {
       return rolesRepository.findAll();
    }
    // get role by id
    public Roles getRoleById (int id) {
    	return rolesRepository.getRoleById(id);
    }
    //delete
    public int delete(Roles role) {
    	 return rolesRepository.delete(role); 
     }
    //update 
    public int update (Roles role) {
    	return rolesRepository.update(role);
    }
    //set active
    public int setActive() {
    	return rolesRepository.setActive();
    }
    
    //save
    public int save(Roles role) {
    	return rolesRepository.save(role);
    }
}
