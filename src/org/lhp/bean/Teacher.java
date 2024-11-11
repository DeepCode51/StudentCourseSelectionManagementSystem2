package org.lhp.bean;


/**
 * 
 * @author rcx
 * @date   2020年4月9日  下午10:52:08
 * @class  org.lhp.bean.Teacher
 * 
 *
 */
public class Teacher {
	private int id;
	private String name;
	private String sex;
	private String idcard;
	private String phonenumber;
	private String provinces;//籍贯
	private String eduction;//学历
	private String school;//学校
	private String major;//专业
	
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getProvinces() {
		return provinces;
	}
	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}
	public String getEduction() {
		return eduction;
	}
	public void setEduction(String eduction) {
		this.eduction = eduction;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", sex=" + sex + ", idcard=" + idcard + ", phonenumber="
				+ phonenumber + ", provinces=" + provinces + ", eduction=" + eduction + ", school=" + school
				+ ", major=" + major + "]";
	}
	
}
