package crm09.entity;

public class User {
	private int id;
	private String email;
	private Roles roles;
	private String phone;
	 private String password;
	 private String fullName;
	 
	 
	public User() {
		super();
	}

	public User(String email, Roles roles, String phone, String password, String fullName) {
		super();
		this.email = email;
		this.roles = roles;
		this.phone = phone;
		this.password = password;
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", roles=" + roles + ", phone=" + phone + ", password="
				+ password + ", fullName=" + fullName + "]";
	}

}
