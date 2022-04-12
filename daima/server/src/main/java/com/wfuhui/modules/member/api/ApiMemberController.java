package com.wfuhui.modules.member.api;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.common.annotation.Login;
import com.wfuhui.common.utils.R;
import com.wfuhui.modules.member.entity.MemberEntity;
import com.wfuhui.modules.member.service.MemberService;

/**
 * 用户接口
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api/member")
public class ApiMemberController {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 用户详情
	 * @param userId
	 * @return
	 */
    @GetMapping("info")
    public R info(@RequestAttribute("userId") Long userId){
    	MemberEntity member = memberService.queryObject(userId);
        return R.ok().put("member", member);
    }
	
	/**
	 * 更新用户
	 * @param member
	 * @return
	 */
	@RequestMapping("/update")
	public R update(@RequestBody MemberEntity member){
		memberService.update(member);
		return R.ok();
	}
	
	/**
	 * 充值
	 * @return
	 */
	@RequestMapping("/recharge")
	public R recharge(@RequestAttribute("userId") Long userId, BigDecimal amount) {
		memberService.recharge(userId, amount);
		return R.ok();
	}
	
	/**
	 * 提现
	 * @return
	 */
	@RequestMapping("/withdraw")
	public R withdraw(@RequestAttribute("userId") Long userId, BigDecimal amount) {
		memberService.withdraw(userId, amount);
		return R.ok();
	}
}
