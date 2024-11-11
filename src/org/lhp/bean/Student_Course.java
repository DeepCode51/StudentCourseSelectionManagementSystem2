/**
 * 
 */
package org.lhp.bean;

/**
 * @author rcx
 * @date   2020年4月9日  下午12:25:50
 * @class  org.lhp.bean.Student_Course
 * 
 * 
 */
public class Student_Course {
	private StudentUserRt userRt;
	private CourseRt courseRt;
	private int Score;
	public StudentUserRt getUserRt() {
		return userRt;
	}
	public void setUserRt(StudentUserRt userRt) {
		this.userRt = userRt;
	}
	public CourseRt getCourseRt() {
		return courseRt;
	}
	public void setCourseRt(CourseRt courseRt) {
		this.courseRt = courseRt;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	
}
