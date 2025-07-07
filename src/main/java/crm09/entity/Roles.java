package crm09.entity;

public class Roles {
	
	private int id;
	private String name;
	private String description;
	private boolean isActive;
	
	
	 
	public Roles() {
		super();
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Roles(int id, String name, String description, boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.isActive = isActive;
	}

	public Roles(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
