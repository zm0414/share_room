package com.wfuhui.modules.seat.service;

import java.util.List;
import java.util.Map;

import com.wfuhui.modules.seat.entity.CategoryEntity;

/**
 * 分类
 * 
 * @author evanluns
 * @email 1197827306@qq.com
 * @date 2017-07-14 13:43:12
 */
public interface CategoryService {
	
	CategoryEntity queryObject(Integer id);
	
	List<CategoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CategoryEntity category);
	
	void update(CategoryEntity category);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

}
