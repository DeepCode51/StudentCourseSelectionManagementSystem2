package org.lhp.bean;


/**
 * 
 * @author rcx
 * @date   2020年4月9日  下午7:32:59
 * @class  org.lhp.bean.TeacherUserRt
 * 
 *
 */
public class TeacherUserRt {
	private int id;
	private String username;
	private String password;
	private int role;
	private Teacher teachinfo;
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
	public Teacher getTeachinfo() {
		return teachinfo;
	}
	public void setTeachinfo(Teacher teachinfo) {
		this.teachinfo = teachinfo;
	}
	
}
