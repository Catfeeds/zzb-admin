package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.Orders;

/**
 * 订单类
 * 
 * @author kk
 *
 */
public interface IOrderService {
	int deleteByPrimaryKey(Integer id);

	int insert(Orders record);

	int insertSelective(Orders record);

	Orders selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Orders record);

	int updateByPrimaryKey(Orders record);

	Orders selectByOrdersUuid(String ordersUuid);

	List<Orders> selectByOrderStatus(int orderStatus);

	// Orders selectByUserUuid(String useruuid);
	List<Orders> selectByUserUuid(String useruuid);

	int selectCountByUserUuid(String userUuid);

	/**
	 * 根据uuidcha查询订单
	 * 
	 * @param useruuid
	 * @return
	 */
	List<Orders> selectByUserUuid1(String useruuid);

	List<Orders> selectByUserUuid2(String useruuid);

	List<Orders> selectByOwnerUuid(String useruuid);

	List<Orders> selectByOwnerUuid2(String string);

	List<Orders> selectByOwnerUuid3(String string);

	List<Map<String, Object>> selectByMapLimit(Map<String, Object> map);

	/**
	 * 根据筛选条件筛选数目
	 * 
	 * @param map
	 * @return
	 */
	int countselectByMapLimit(Map<String, Object> map);

	// Orders selectByUserUuidAndOrderUUid(String orderuuid);
	/**
	 * 根据订单号查找订单
	 * 
	 * @param orderNumber
	 * @return
	 */
	Orders selectByOrderNumber(String orderNumber);

	/**
	 * 根据uuid查询消费次数
	 * 
	 * @param userUuid
	 * @return
	 */
	int selectCountByConsume(String userUuid);

	/**
	 * 根据uuid查询消费金额
	 * 
	 * @param userUuid
	 * @return
	 */
	Float selectMoneyByConsume(String userUuid);

	/**
	 * 根据uuid查询确认接单数
	 * 
	 * @param userUuid
	 * @return
	 */
	int selectSureOrder(String userUuid);

	/**
	 * 根据uuid查询接单总数
	 * 
	 * @param userUuid
	 * @return
	 */
	int selectCount(String uuid);

	Float selectMoney();

	/**
	 * 通过订单号查找订单
	 * 
	 * @param ordernumber
	 * @return
	 */
	Orders selectByordernumber(java.lang.String ordernumber);

	/**
	 * 历史最高
	 * 
	 * @return
	 */
	Float selectHighMoney();

	/**
	 * 押金池总押金
	 * 
	 * @return
	 */
	Float selectPoolMoney();
}
