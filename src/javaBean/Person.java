package javaBean;

import java.io.Serializable;

public class Person implements Serializable, Comparable<Person>{
	private String id;    // 学号
	private String name;
	private String phone;
	private String qq;
	private String email;
	
	public Person() {}
	
	public Person(String id, String name, String phone, String qq, String email) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.qq = qq;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", phone=" + phone + ", qq=" + qq + ", email=" + email + "]";
	}

	@Override
	public int compareTo(Person o) {
		if(o!=null) {
			int i = (this.id).compareTo(o.getId());
			if(i>0) {
				return 1;
			}else if(i<0){
				return -1;
			}else {
				return 0;
			}
		}
		return 0;
	}
	
}
