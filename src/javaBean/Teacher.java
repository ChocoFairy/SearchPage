package javaBean;

import java.io.Serializable;

public class Teacher implements Serializable{
	private int id;
	private String name;
	private String phone;
	private int votes;
	
	public Teacher() {}

	public Teacher(int id, String name, String phone, int votes) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.votes = votes;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", phone=" + phone + ", votes=" + votes + "]";
	}
	
}
