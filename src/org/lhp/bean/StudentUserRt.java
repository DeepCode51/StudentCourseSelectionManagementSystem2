/**
 * 
 */
package org.lhp.bean;

/**
 * 
 * @author rcx
 * @date   2020年4月9日  上午10:22:39
 * @class  org.lhp.bean.StudentUserRt
 * 
 *
 */
public class StudentUserRt {
	private int id;
	private String username;
	private String password;
	private int check;
	private Student student;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	
}
