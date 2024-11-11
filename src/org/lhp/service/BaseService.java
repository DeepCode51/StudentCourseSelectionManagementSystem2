package org.lhp.service;

import javax.annotation.Resource;

import org.apache.commons.logging.LogFactory;
import org.lhp.dao.BaseDaoImpl;

//这个方法就是为了获取dao
public abstract class BaseService {
	protected static final org.apache.commons.logging.Log log = LogFactory.getLog(BaseService.class);

	protected BaseDaoImpl dao;
	
	@Resource(name = "baseDao")
	
	public void setDao(BaseDaoImpl dao) {

		this.dao = dao;
	}

}
