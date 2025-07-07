package crm09.entity;

import java.sql.Date;

public class Projects {
	private int id;
	private String name;
	private Date beginDay;
	private Date endDate;
	private String status;
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
	public Date getBeginDay() {
		return beginDay;
	}
	public void setBeginDay(Date beginDay) {
		this.beginDay = beginDay;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
