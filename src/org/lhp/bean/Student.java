/**
 * 
 */
package org.lhp.bean;

/**
 * @author rcx
 * @date   2020年4月9日  下午9:52:16
 * @class  org.lhp.bean.Student
 * 
 * 
 */
public class Student {
	private int id;
	private String name;
	private String sex;
	private String stuclass;
	private String idCard;
	private String phoneNum;
	private String provinces;
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
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getProvinces() {
		return provinces;
	}
	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}
	public String getStuclass() {
		return stuclass;
	}
	public void setStuclass(String stuclass) {
		this.stuclass = stuclass;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", stuclass=" + stuclass + ", idCard=" + idCard
				+ ", phoneNum=" + phoneNum + ", provinces=" + provinces + "]";
	}

}
