package com.wfuhui.modules.member.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.wfuhui.modules.member.entity.MemberEntity;

/**
 * 会员
 * 
 * @author evanluns
 * @email 1197827306@qq.com
 */
public interface MemberService {
	
	MemberEntity queryObject(Long id);
	
	List<MemberEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MemberEntity member);
	
	void update(MemberEntity member);
	
	void delete(Long id);
	
	void deleteBatch(Integer[] ids);

	MemberEntity queryByOpenid(String openid);

	MemberEntity queryByLoginName(String loginName);

	void recharge(Long userId, BigDecimal amount);

	void withdraw(Long userId, BigDecimal amount);
}
