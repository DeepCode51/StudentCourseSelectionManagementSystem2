/**
 * 
 */
package org.lhp.service;
import java.util.List;

import org.lhp.bean.Notice;
import org.lhp.bean.RoleInfo;
import org.lhp.bean.TeacherUser;
import org.springframework.stereotype.Service;

/**
 * @author rcx
 * @date   2020年4月9日  下午8:49:44
 * @class  org.lhp.service.TeacherLogin
 * 
 * 
 */
@Service("rootLoginSerice")
public class RootLoginSerice extends BaseService{

	public String login(String username,String password){
		
		TeacherUser tu = (TeacherUser) dao.selectOne("lhpmapper.rootLoginMapper.queryTeaUser", username);
		if(tu!=null){			
			if(tu.getPassword().equals(password)){
				return "true";
			}
		}
		return "false"; 
	}

	/**
	 * @param username
	 * @return
	 * RootLoginSerice.java
	 * 
	 */
	public RoleInfo queryTeachIsRoot(String username) {
		
		return (RoleInfo) dao.selectOne("lhpmapper.rootLoginMapper.queryTeachIsRoot", username);
	}

	/**
	 * @return
	 * RootLoginSerice.java
	 * 
	 */
	public List<Notice> queryRootNotice() {
		// TODO Auto-generated method stub
		return (List<Notice>) dao.selectList("lhpmapper.NoticeMapper.queryRootNotice");
	}
}
