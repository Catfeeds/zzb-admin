package com.hcb.zzb.service;


import java.util.List;

import com.hcb.zzb.dto.Orders;


public interface IOrderService {
	int deleteByPrimaryKey(Integer id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

	Orders selectByOrdersUuid(String ordersUuid);

	List<Orders> selectByOrderStatus(int orderStatus);

	//Orders selectByUserUuid(String useruuid);
	List<Orders> selectByUserUuid(String useruuid);
	int selectCountByUserUuid(String userUuid);

	List<Orders> selectByUserUuid1(String useruuid);

	List<Orders> selectByUserUuid2(String useruuid);

	List<Orders> selectByOwnerUuid(String useruuid);

	List<Orders> selectByOwnerUuid2(String string);

	List<Orders> selectByOwnerUuid3(String string);

	//Orders selectByUserUuidAndOrderUUid(String orderuuid);
}
