package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.FinanceRecord;

/**
 * 收支明细
 * 
 * @author kk
 *
 */
public interface IFinanceRecordService {
	int deleteByPrimaryKey(Integer id);

	int insert(FinanceRecord record);

	int insertSelective(FinanceRecord record);

	FinanceRecord selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(FinanceRecord record);

	int updateByPrimaryKey(FinanceRecord record);

	/**
	 * 根据筛选条件查询收支明细列表
	 * 
	 * @param map
	 * @return
	 */
	List<FinanceRecord> selectAllByUserUuid(Map<String, Object> map);

	/**
	 * 根据筛选条件查询收支明细数目
	 * 
	 * @param map
	 * @return
	 */
	int selectCountByUserUuid(String userUuid);

	/**
	 * 今日收入和支出
	 * 
	 * @param map
	 * @return
	 */
	List<FinanceRecord> selectIncomeAndExpenditureByToday(Map<String, Object> map);

	/**
	 * 根据uuid查询收支明细
	 * @param uuid
	 * @return
	 */
	FinanceRecord selectByUuid(String uuid);
	/**
	 * 根据查询条件查询列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectByMapLimit(Map<String, Object> map);
	/**
	 * 根据查询条件查询数目
	 * @param map
	 * @return
	 */
	int countSelectByMapLimit(Map<String, Object> map);

	List<FinanceRecord> selectByRecordType(Map<String, Object> map);

	int countSelectByRecordType(Map<String, Object> map);

	List<FinanceRecord> selectOutcomeAndExpenditureByToday(Map<String, Object> tmap1);

	Float selectMoney(String orderUuid);
}
