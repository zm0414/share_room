package com.wfuhui.modules.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.wfuhui.modules.order.entity.OrderEntity;

/**
 * 订单
 * 
 * @author evanluns
 * @email 1197827306@qq.com
 */
public interface OrderService {
	
	OrderEntity queryObject(Integer id);
	
	List<OrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderEntity order);
	
	void update(OrderEntity order);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void createOrder(OrderEntity orderEntity);

	void updateByOrderNumber(OrderEntity order);

	List<Map<String, String>> queryOrderCount();

	void confirm(OrderEntity order);

	void pay(Integer id, BigDecimal amount, Long memberId);
}
