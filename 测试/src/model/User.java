package model;

public class User {
	private int id;
	private String uname;
	private int age;
	
	
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", age=" + age + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	

}
