/**
 * 
 */
package org.lhp.service;

import org.lhp.bean.Notice;
import org.lhp.bean.Student;
import org.lhp.bean.StudentUser;
import org.springframework.stereotype.Service;

/**
 * @author rcx
 * @date   2020年4月9日  下午10:02:25
 * @class  org.lhp.service.StudentRegisterService
 * 
 * 
 */
@Service("studentRegisterService")
public class StudentRegisterService extends BaseService{
	
	public String stuUsernameCheck(String username){
		int one = (int) dao.selectOne("lhpmapper.StudentLoginMapper.stuUsernameCheck", username);
		if(one>0){
			return "true";
		}else{
			return "false";
		}
	}
	
	public void stuRegister(Student student,StudentUser user){
		
		int i = dao.insert("lhpmapper.StudentLoginMapper.addStudentInfo", student);
		user.setInfoid(student.getId());
		int j = dao.insert("lhpmapper.StudentLoginMapper.addStudentUser", user);
		
		System.out.println(i+" "+j);
	}

}
