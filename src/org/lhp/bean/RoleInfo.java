/**
 * 
 */
package org.lhp.bean;

/**
 * @author rcx
 * @date   2020年4月9日  上午11:59:20
 * @class  org.lhp.bean.RoleInfo
 * 
 * 
 */
public class RoleInfo {
	private int roleid;
	private String rolename;
	private String roleinfo;
	private String fn1;
	private String fn2;
	private String fn3;
	private String fn4;
	private String fn5;
	private String fn6;
	
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRoleinfo() {
		return roleinfo;
	}
	public void setRoleinfo(String roleinfo) {
		this.roleinfo = roleinfo;
	}
	public String getFn1() {
		return fn1;
	}
	public void setFn1(String fn1) {
		this.fn1 = fn1;
	}
	public String getFn2() {
		return fn2;
	}
	public void setFn2(String fn2) {
		this.fn2 = fn2;
	}
	public String getFn3() {
		return fn3;
	}
	public void setFn3(String fn3) {
		this.fn3 = fn3;
	}
	public String getFn4() {
		return fn4;
	}
	public void setFn4(String fn4) {
		this.fn4 = fn4;
	}
	public String getFn5() {
		return fn5;
	}
	public void setFn5(String fn5) {
		this.fn5 = fn5;
	}
	public String getFn6() {
		return fn6;
	}
	public void setFn6(String fn6) {
		this.fn6 = fn6;
	}
	@Override
	public String toString() {
		return "RoleInfo [roleid=" + roleid + ", rolename=" + rolename + ", roleinfo=" + roleinfo + ", fn1=" + fn1
				+ ", fn2=" + fn2 + ", fn3=" + fn3 + ", fn4=" + fn4 + ", fn5=" + fn5 + ", fn6=" + fn6 + "]";
	}
	
}
