package org.lhp.bean;


/**
 * 
 * @author rcx
 * @date   2020年4月9日  下午10:52:39
 * @class  org.lhp.bean.TeacherUser
 * 
 *
 */
public class TeacherUser {
	private int id;
	private String username;
	private String password;
	private int role;
	private int infoid;
	
	@Override
	public String toString() {
		return "TeacherUser [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", infoid=" + infoid + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getInfoid() {
		return infoid;
	}
	public void setInfoid(int infoid) {
		this.infoid = infoid;
	}
	
}
