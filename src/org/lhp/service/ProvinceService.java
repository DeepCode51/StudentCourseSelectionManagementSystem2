/**
 * 
 */
package org.lhp.service;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author rcx
 * @date   2020年4月9日  上午10:25:53
 * @class  org.lhp.service.ProvinceServinces
 * 
 * 
 */
@Service("provinceService")
public class ProvinceService extends BaseService{
	
	public List<?> queryPrivates(){
		List<?> list =dao.selectList("lhpmapper.ProvincesMapperEXT.queryProvinces");
		return list;
	}

	public List<?> queryCity(String provinte){
		List<?> list = dao.selectList("lhpmapper.ProvincesMapperEXT.queryCity",provinte);
		return list;
	}
	public List<?> QueryArea(String provinte){
		List<?> list = dao.selectList("lhpmapper.ProvincesMapperEXT.QueryArea",provinte);
		return list;
	}
	
}
