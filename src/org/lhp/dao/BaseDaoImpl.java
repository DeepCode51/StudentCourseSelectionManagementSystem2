package org.lhp.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/** 
 * TODO 数据层实现类
 */
public class BaseDaoImpl extends SqlSessionDaoSupport implements BaseDao {

	public <T> T getMapper(Class<T> clz) {

		return getSqlSession().getMapper(clz);
	}

	public Object selectOne(String statement, Object parameter) {

		return getSqlSession().selectOne(statement, parameter);
	}

	
	public List<?> selectList(String statement, Object parameter) {

		return getSqlSession().selectList(statement, parameter);
	}

	public List<?> selectList(String statement) {
		
		return getSqlSession().selectList(statement);
	}

	public <K, V> Map<K, V> selectMap(String statement, String parameter) {
		return selectMap(statement, parameter);
	}

	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String key) {

		return getSqlSession().selectMap(statement, parameter, key);
	}

	/*
	* (non-Javadoc)
	* 
	* @see com.lhp.dao.BaseDao#selectMap(java.lang.String,
	* java.lang.Object, java.lang.String)
	*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lhp.dao.BaseDao#insert(java.lang.String,
	 * java.lang.Object)
	 */
	public int insert(String statement, Object parameter) {

		return getSqlSession().insert(statement, parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lhp.dao.BaseDao#update(java.lang.String,
	 * java.lang.Object)
	 */
	public int update(String statement, Object parameter) {

		return getSqlSession().update(statement, parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lhp.dao.BaseDao#delete(java.lang.String,
	 * java.lang.Object)
	 */
	public int delete(String statement, Object parameter) {

		return getSqlSession().delete(statement, parameter);
	}

}
