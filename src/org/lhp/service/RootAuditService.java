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
 * @date   2020年4月9日  下午8:46:00
 * @class  org.lhp.service.RootAuditServince
 * 
 * 
 */
@Service("rootAuditService")
public class RootAuditService extends BaseService{

	/**
	 * 
	 * RootAuditService.java
	 * @return List<TeacherUserRt>
	 * @see 返回未审核教师账号信息
	 */
	public List<TeacherUserRt> queryNoAuditTeacherList() {
		return (List<TeacherUserRt>) dao.selectList("lhpmapper.RootAuditMapper.queryNoAuditTeacherList");
	}

	/**
	 * @param tid
	 * RootAuditService.java
	 * 
	 */
	public TeacherUserRt queryNoAuditTeacher(Integer tid) {
		return (TeacherUserRt) dao.selectOne("lhpmapper.RootAuditMapper.ByIdqueryTeachInfo", tid);
	}
	
	public List<RoleInfo> QueryAllRole() {
		return (List<RoleInfo>) dao.selectList("lhpmapper.RootRoleMapperEXT.QueryAllRole");
	}

	/**
	 * @param notice
	 * @return
	 * RootAuditService.java
	 * 
	 */
	public String addNoAutitTeachNotice(Notice notice) {
		dao.insert("lhpmapper.NoticeMapper.addNoAutitTeachNotice", notice);
		return "true";
	}


}
