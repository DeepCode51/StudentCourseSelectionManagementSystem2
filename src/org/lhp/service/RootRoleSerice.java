/**
 * 
 */
package org.lhp.service;

import java.util.List;
import java.util.Map;

import org.lhp.bean.Notice;
import org.lhp.bean.RoleInfo;
import org.lhp.bean.TeacherUserRt;
import org.springframework.stereotype.Service;

/**
 * @author rcx
 * @date   2020年4月9日  下午12:27:08
 * @class  org.lhp.service.RootRoleSerice
 * 
 * 
 */
@Service("rootRoleSerice")
public class RootRoleSerice extends BaseService{

	/**
	 * @param role
	 * RootRoleSerice.java
	 * 
	 */
	public String AddRole(RoleInfo role) {
		dao.insert("lhpmapper.RootRoleMapperEXT.AddRole", role);
		return "true";
	}


	public List<RoleInfo> QueryAllRole() {
		return (List<RoleInfo>) dao.selectList("lhpmapper.RootRoleMapperEXT.QueryAllRole");
	}
	public RoleInfo ByIdQueryOneRole(int rid){
		return (RoleInfo) dao.selectOne("lhpmapper.RootRoleMapperEXT.ByIdQueryOneRole", rid);
	}

	/**
	 * @param role
	 * @return
	 * RootRoleSerice.java
	 * 
	 */
	public String RoleModify(RoleInfo role) {
		dao.update("lhpmapper.RootRoleMapperEXT.RoleModify", role);
		return "true";
	}
	
	public String ModifyTeacherRole(Map<String, Object> map) {
		dao.update("lhpmapper.RootRoleMapperEXT.ModifyTeacherRole", map);
		return "true";
	}



	/**
	 * @return
	 * RootRoleSerice.java
	 * 查询所以通过审核的教师
	 */
	public List<TeacherUserRt> queryAllTeach() {
		
		return (List<TeacherUserRt>) dao.selectList("lhpmapper.RootRoleMapperEXT.queryAllTeach");
	}


	/**
	 * @param notice
	 * RootRoleSerice.java
	 * 
	 */
	public void addModifyTeacherRoleNotice(Notice notice) {
		dao.insert("lhpmapper.NoticeMapper.addNoAutitTeachNotice", notice);
	}


	/**
	 * @param notice
	 * RootRoleSerice.java
	 * 
	 */
	public void AddRootNotice(Notice notice) {
		dao.insert("lhpmapper.NoticeMapper.addRoot_Notice", notice);
		
	}
	
}
