package crm09.services;

import java.util.List;

import crm09.entity.Projects;
import crm09.repository.ProjectsRepository;

public class ProjectServices {
	private ProjectsRepository projectsRepository = new ProjectsRepository();

//lay danh sach project
	public List<Projects> findAll() {
		return projectsRepository.findAll();
	}

// delete
	public int delete(int id) {
		return projectsRepository.delete(id);
	}

//update
	public int update(Projects project) {
		return projectsRepository.update(project);
	}
}
