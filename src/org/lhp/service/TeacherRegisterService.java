package org.lhp.service;

import org.lhp.bean.Notice;
import org.lhp.bean.Teacher;
import org.lhp.bean.TeacherUser;
import org.springframework.stereotype.Service;

@Service("teacherRegisterService")
public class TeacherRegisterService extends BaseService{
	
	public String usernameCheck(String username){
		int one = (int) dao.selectOne("org.lhp.mapper.TeacherLoginMapper.queryUsernameExits", username);
		if(one>0){			
			return "true";
		}else{			
			return "false";
		}
	}
	public String usernameRegister(Teacher teacher,TeacherUser user){
		dao.insert("org.lhp.mapper.TeacherLoginMapper.teacherInfo", teacher);
		user.setInfoid(teacher.getId());
		dao.insert("org.lhp.mapper.TeacherLoginMapper.teacherRegister", user);
		return "true";
	}
	/**
	 * @param notice
	 * TeacherRegisterService.java
	 * 
	 */
	public void AddRootNotice(Notice notice) {
		dao.insert("lhpmapper.NoticeMapper.addRoot_Notice", notice);
	}
}
