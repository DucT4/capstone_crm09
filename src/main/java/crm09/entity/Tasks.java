package crm09.entity;

import java.sql.Date;

public class Tasks {
 private int id;
  private String name;
  private String status;
  private Date beginTask;
  private Date endTask;
  private User user;
  private Projects project;
public Tasks(int id, String name, String status, Date beginTask, Date endTask, User user, Projects project) {
	super();
	this.id = id;
	this.name = name;
	this.status = status;
	this.beginTask = beginTask;
	this.endTask = endTask;
	this.user = user;
	this.project = project;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Date getBeginTask() {
	return beginTask;
}
public void setBeginTask(Date beginTask) {
	this.beginTask = beginTask;
}
public Date getEndTask() {
	return endTask;
}
public void setEndTask(Date endTask) {
	this.endTask = endTask;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Projects getProject() {
	return project;
}
public void setProject(Projects project) {
	this.project = project;
}
  
  
}
