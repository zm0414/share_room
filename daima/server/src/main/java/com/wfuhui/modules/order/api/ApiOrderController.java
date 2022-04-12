package com.wfuhui.modules.order.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.common.annotation.Login;
import com.wfuhui.common.utils.R;
import com.wfuhui.modules.order.entity.OrderRoomEntity;
import com.wfuhui.modules.seat.entity.RoomEntity;
import com.wfuhui.modules.seat.service.RoomService;
import com.wfuhui.modules.member.entity.MemberEntity;
import com.wfuhui.modules.member.service.MemberService;
import com.wfuhui.modules.order.entity.OrderEntity;
import com.wfuhui.modules.order.service.OrderService;

/**
 * 订单接口
 * @author 2803180149
 *
 */
@RestController
@RequestMapping("/api/order")
public class ApiOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private MemberService memberService;
	
	//查询列表
	@Login
    @GetMapping("list")
    public R getOrderList(@RequestAttribute Long userId, Integer status){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("orderStatus", status);
    	params.put("memberId", userId);
    	List<OrderEntity> orderList = orderService.queryList(params);
        return R.ok().put("orderList", orderList);
    }
    
	//下单
    @Login
    @PostMapping("create")
    public R createOrder(@RequestAttribute("userId") Long userId, @RequestBody OrderEntity order){
    	order.setMemberId(userId);
    	order.setCreateTime(new Date());
    	order.setOrderStatus(1);
    	orderService.createOrder(order);
    	return R.ok().put("id", order.getId());
    }
    
    //详情
    @Login
    @GetMapping("detail")
    public R detail(Integer id) {
    	OrderEntity order = orderService.queryObject(id);
    	return R.ok().put("order", order);
    }
    
    //取消
    @Login
    @GetMapping("cancel")
    public R cancel(Integer id) {
    	OrderEntity order = new OrderEntity();
    	order.setOrderStatus(0);
    	order.setId(id);
    	orderService.update(order);
    	return R.ok();
    }
	
    //支付
    @Login
	@GetMapping("pay")
    public R pay(@RequestAttribute("userId") Long userId, Integer id) {
    	MemberEntity member = memberService.queryObject(userId);
    	OrderEntity order = orderService.queryObject(id);
    	if(member.getBalance().compareTo(order.getTotalAmount()) < 0) {
    		return R.error("余额不足，请到充值");
    	}
    	orderService.pay(id, order.getTotalAmount(), userId);
    	return R.ok();
    }
    
    //完成
    @Login
	@GetMapping("confirm")
    public R complet(Integer id) {
    	OrderEntity order = new OrderEntity();
    	order.setOrderStatus(3);
    	order.setId(id);
    	orderService.confirm(order);
    	return R.ok();
    }
        
}
