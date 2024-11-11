/**
 * 
 */
package org.lhp.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lhp.bean.Notice;
import org.lhp.bean.Student;
import org.lhp.bean.StudentUserRt;
import org.springframework.stereotype.Service;

/**
 * @author rcx
 * @date   2020年4月9日  下午8:49:44
 * @class  org.lhp.service.TeacherLogin
 * 
 * 
 */
@Service("studentLoginSerice")
public class StudentLoginSerice extends BaseService{
	
	public String login(String username,String password){
		Object one = dao.selectOne("lhpmapper.StudentLoginMapper.queryPassword", username);
		System.out.println(one);
		if(one!=null){
			if(one.equals(password)){
				return "true"; 
			}
		}
		return "false";
	}

	/**
	 * @param username
	 * @return
	 * StudentLoginSerice.java
	 * 
	 */
	public Integer queryId(String username) {
		// TODO Auto-generated method stub
		return (Integer) dao.selectOne("lhpmapper.StudentLoginMapper.queryId", username);
	}

	/**
	 * 
	 * StudentLoginSerice.java
	 * 
	 */
	public String queryName(int id) {
		// TODO Auto-generated method stub
		return (String) dao.selectOne("lhpmapper.StudentLoginMapper.queryName", id);
	}
	
	public StudentUserRt queryStudentInfo(int sid){
		return (StudentUserRt) dao.selectOne("lhpmapper.StudentLoginMapper.queryStudentInfo",sid);
	}

	/**
	 * @param sur
	 * StudentLoginSerice.java
	 * 
	 */
	public void stuModifyInfo(StudentUserRt sur) {
		int i = dao.update("lhpmapper.StudentLoginMapper.stuModifyInfo", sur);
		System.out.println(i);
	}

	/**
	 * @param stuid
	 * @param passwordOld
	 * @param password
	 * @return
	 * StudentLoginSerice.java
	 * 
	 */
	public String passwordModify(Integer stuid, String passwordOld, String password) {
		Object one = dao.selectOne("lhpmapper.StudentLoginMapper.ByidqueryPassword", stuid);
		if(one.equals(passwordOld)){
			Map map=new HashMap<>();
			map.put("stuid", stuid);
			map.put("password", password);
			
			dao.update("lhpmapper.StudentLoginMapper.ModifyStuPassword", map);
			
			return "true";
		}else{
			return "false";
		}
	}

	/**
	 * @param stuid
	 * @param password
	 * StudentLoginSerice.java
	 * 
	 */
	public String ByIdQueryStudentPassword(Integer stuid, String password) {
		String queryPassword = (String) dao.selectOne("lhpmapper.StudentLoginMapper.ByIdQueryStudentPassword", stuid);
		System.out.println(queryPassword);
		if(queryPassword.equals(password)){
			return "true";
		}else{
			return "false";
		}
	}

	/**
	 * @param teaid
	 * @return
	 * StudentLoginSerice.java
	 * 
	 */
	public List<Notice> queryStuNotice(Integer stuid) {
		return (List<Notice>) dao.selectList("lhpmapper.NoticeMapper.queryStuNotice", stuid);
	}
}
