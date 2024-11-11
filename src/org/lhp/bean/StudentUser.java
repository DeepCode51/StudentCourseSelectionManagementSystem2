/**
 * 
 */
package org.lhp.bean;

/**
 * @author rcx
 * @date   2020年4月9日  下午9:54:15
 * @class  org.lhp.bean.StudentUser
 * 
 * 
 */
public class StudentUser {
	private int id;
	private String username;
	private String password;
	private int check;
	private int infoid;
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
	public int getInfoid() {
		return infoid;
	}
	public void setInfoid(int infoid) {
		this.infoid = infoid;
	}
	@Override
	public String toString() {
		return "StudentUser [id=" + id + ", username=" + username + ", password=" + password + ", check=" + check
				+ ", infoid=" + infoid + "]";
	}

	
}
