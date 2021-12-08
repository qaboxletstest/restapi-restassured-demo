package models;

import com.google.gson.annotations.Expose;

public class Member {
	
//	private transient int id;
	
	@Expose(serialize = false, deserialize = true) 
	private int id;
	
	@Expose
	private String name;
	
	@Expose
	private String gender;	
	
	public Member() {};
	
	public Member(String name, String gender) {
		super();
		this.name = name;
		this.gender = gender;
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
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", gender=" + gender + "]";
	}
	
	
	

}
