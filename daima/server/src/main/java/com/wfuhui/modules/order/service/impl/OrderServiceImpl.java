package com.wfuhui.modules.order.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wfuhui.common.utils.NumberUtil;
import com.wfuhui.modules.seat.dao.SeatDao;
import com.wfuhui.modules.seat.dao.SessionsDao;
import com.wfuhui.modules.seat.entity.SeatEntity;
import com.wfuhui.modules.seat.entity.SessionsEntity;
import com.wfuhui.modules.member.dao.MemberDao;
import com.wfuhui.modules.member.service.MemberService;
import com.wfuhui.modules.order.dao.OrderDao;
import com.wfuhui.modules.order.dao.OrderRoomDao;
import com.wfuhui.modules.order.dao.OrderSeatDao;
import com.wfuhui.modules.order.entity.OrderEntity;
import com.wfuhui.modules.order.entity.OrderRoomEntity;
import com.wfuhui.modules.order.entity.OrderSeatEntity;
import com.wfuhui.modules.order.service.OrderService;


@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderRoomDao orderRoomDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private OrderSeatDao orderSeatDao;
	@Autowired
	private SeatDao seatDao;
	@Autowired
	private SessionsDao sessionsDao;
	
	@Override
	public OrderEntity queryObject(Integer id){
		OrderEntity order = orderDao.queryObject(id);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", order.getId());
		List<OrderRoomEntity> orderRoomList = orderRoomDao.queryList(params);
		order.setOrderRoomList(orderRoomList);
		order.setMember(memberDao.queryObject(order.getMemberId()));
		order.setOrderSeatList(orderSeatDao.queryList(params));
		return order;
	}
	
	@Override
	public List<OrderEntity> queryList(Map<String, Object> map){
		List<OrderEntity> orderList = orderDao.queryList(map);
		for (OrderEntity order : orderList) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("orderId", order.getId());
			List<OrderRoomEntity> orderRoomList = orderRoomDao.queryList(params);
			order.setOrderRoomList(orderRoomList);
			order.setOrderSeatList(orderSeatDao.queryList(params));
		}
		return orderList;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderEntity order){
		orderDao.save(order);
	}
	
	@Override
	public void update(OrderEntity order){
		orderDao.update(order);
	}
	
	@Override
	public void delete(Integer orderId){
		orderDao.delete(orderId);
	}
	
	@Override
	public void deleteBatch(Integer[] orderIds){
		orderDao.deleteBatch(orderIds);
	}

	@Override
	@Transactional
	public void createOrder(OrderEntity order) {
		order.setOrderNumber(NumberUtil.getOrderNumber());
		orderDao.save(order);
		List<OrderRoomEntity> orderRoomList = order.getOrderRoomList();
		if(orderRoomList != null) {
			for (OrderRoomEntity orderRoomEntity : orderRoomList) {
				orderRoomEntity.setOrderId(order.getId());
				orderRoomDao.save(orderRoomEntity);
			}
		}
		
		List<OrderSeatEntity> orderSeatList = order.getOrderSeatList();
		if(orderSeatList != null) {
			for (OrderSeatEntity orderSeat : orderSeatList) {
				orderSeat.setOrderId(order.getId());
				orderSeatDao.save(orderSeat);
				SessionsEntity sessions = new SessionsEntity();
				sessions.setStatus(1);
				sessions.setId(orderSeat.getSessionsId());
				sessionsDao.update(sessions);
			}
		}
	}

	@Override
	public void updateByOrderNumber(OrderEntity order) {
		orderDao.updateByOrderNumber(order);
	}

	@Override
	public List<Map<String, String>> queryOrderCount() {
		return orderDao.queryOrderCount();
	}

	@Override
	public void confirm(OrderEntity order) {
		orderDao.update(order);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", order.getId());
		List<OrderSeatEntity> orderSeatList = orderSeatDao.queryList(params);
		for (OrderSeatEntity orderSeat : orderSeatList) {
			SeatEntity seat = new SeatEntity();
			seat.setId(orderSeat.getSeatId());
			seat.setStatus(0);
			seatDao.update(seat);
		}
	}

	@Override
	@Transactional
	public void pay(Integer id, BigDecimal amount, Long memberId) {
		memberDao.delBanlance(memberId, amount);
		OrderEntity order = new OrderEntity();
    	order.setOrderStatus(2);
    	order.setId(id);
    	orderDao.update(order);
	}
	
}
