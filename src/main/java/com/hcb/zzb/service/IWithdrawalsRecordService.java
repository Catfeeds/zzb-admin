package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.WithdrawalsRecord;

/**
 * 提现记录
 * 
 * @author kk
 *
 */
public interface IWithdrawalsRecordService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(WithdrawalsRecord record);

	public int insertSelective(WithdrawalsRecord record);

	public WithdrawalsRecord selectByPrimaryKey(Integer id);

	public List<WithdrawalsRecord> selectByUserUuid(Map<String, Object> map);

	public int selectByUserUuidCount(String userUuid);

	public int updateByPrimaryKeySelective(WithdrawalsRecord record);

	public int updateByPrimaryKey(WithdrawalsRecord record);

	/**
	 * 提现记录列表
	 * 
	 * @param map
	 * @return
	 */
	List<WithdrawalsRecord> selectByMapLimit(Map<String, Object> map);

	int countSelectByMapLimit(Map<String, Object> map);

	/**
	 * 已提现金额
	 * 
	 * @param userUuid
	 * @return
	 */
	public Float selectAlreadyMoney(String userUuid);

	/**
	 * 今日结算支出
	 * 
	 * @param tmap1
	 * @return
	 */
	public List<WithdrawalsRecord> selectByAgree(Map<String, Object> tmap1);
}
