package crm09.services;

import java.util.List;

import crm09.entity.Tasks;
import crm09.repository.TasksRepository;

public class TaskServices {
  private TasksRepository tasksRepository = new TasksRepository();
  //get all list
	public List<Tasks> findAll() {
		return tasksRepository.findAll();
	}
	//detele
	public int delete(int id) {
		return tasksRepository.delete(id);
	}
    //update 
	public int update(Tasks task) {
		return tasksRepository.update(task);
	}
	// save
	public int save(Tasks task) {
		return tasksRepository.save(task);
	}
}
