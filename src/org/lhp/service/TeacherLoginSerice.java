/**
 * 
 */
package org.lhp.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lhp.bean.Notice;
import org.lhp.bean.RoleInfo;
import org.lhp.bean.Teacher;
import org.lhp.bean.TeacherUserRt;
import org.springframework.stereotype.Service;

/**
 * @author rcx
 * @date   2020年4月9日  下午8:49:44
 * @class  org.lhp.service.TeacherLogin
 * 
 * 
 */
@Service("teacherLoginSerice")
public class TeacherLoginSerice extends BaseService{
	
	public String login(String username,String password){
		Object one = dao.selectOne("org.lhp.mapper.TeacherLoginMapper.queryPassword", username);
		System.out.println(one);
		if(one!=null){
			if(one.equals(password)){
				return "true"; 
			}
		}
		return "false";
	}
	public int queryId(String username){
		Integer one = (Integer) dao.selectOne("org.lhp.mapper.TeacherLoginMapper.queryId", username);
		System.out.println(one);
		return one;
	}
	/**
	 * @param i
	 * TeacherLoginSerice.java
	 * 
	 */
	public String queryName(Integer i) {
		return (String) dao.selectOne("org.lhp.mapper.TeacherLoginMapper.queryName", i);
	}
	public RoleInfo queryRole(Integer tid){
		return (RoleInfo) dao.selectOne("org.lhp.mapper.TeacherLoginMapper.queryRole", tid);
	}
	/**
	 * @param cid
	 * TeacherLoginSerice.java
	 * 
	 */
	public TeacherUserRt queryTeachInfo(int Teaid) {
		return (TeacherUserRt) dao.selectOne("org.lhp.mapper.TeacherLoginMapper.queryTeachInfo", Teaid);
	}
	/**
	 * @param tu
	 * TeacherLoginSerice.java
	 * 
	 */
	public void TeachInfoModify(TeacherUserRt tu) {
		dao.update("org.lhp.mapper.TeacherLoginMapper.TeachInfoModify", tu);
	}
	/**
	 * @param passwordOld
	 * @param password
	 * @return
	 * TeacherLoginSerice.java
	 * 
	 */
	public String passwordModify(int tid,String passwordOld, String password) {
		String one = (String) dao.selectOne("org.lhp.mapper.TeacherLoginMapper.ByIdqueryPassword", tid);
		if(one.equals(passwordOld)){
			Map<String, Object> map=new HashMap<>();
			map.put("password", password);
			map.put("tid", tid);
			dao.update("org.lhp.mapper.TeacherLoginMapper.ModifyPassword", map);
			return "true";
		}else{
			return "false";
		}
	}
	/**
	 * @param teaid
	 * @return
	 * TeacherLoginSerice.java
	 * 
	 */
	public List<Notice> queryTeaNotice(Integer teaid) {
		return (List<Notice>) dao.selectList("lhpmapper.NoticeMapper.queryTeaNotice", teaid);
	}
}
