package com.hcb.zzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.OrdersMapper;
import com.hcb.zzb.dto.Orders;
import com.hcb.zzb.service.IOrderService;
@Service("orderService")
public class OrderServiceImpl implements IOrderService {
@Autowired
private OrdersMapper ordersMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return ordersMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Orders record) {
		// TODO Auto-generated method stub
		return ordersMapper.insert(record);
	}

	@Override
	public int insertSelective(Orders record) {
		// TODO Auto-generated method stub
		return ordersMapper.insertSelective(record);
	}

	@Override
	public Orders selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Orders record) {
		// TODO Auto-generated method stub
		return ordersMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Orders record) {
		// TODO Auto-generated method stub
		return ordersMapper.updateByPrimaryKey(record);
	}

	@Override
	public Orders selectByOrdersUuid(String ordersUuid) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByOrdersUuid(ordersUuid);
	}

	@Override
	public List<Orders> selectByOrderStatus(int orderStatus) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByOrderStatus(orderStatus);
	}

	/*@Override
	public Orders selectByUserUuid(String useruuid) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByUserUuid(useruuid);
	}*/

	@Override
	public int selectCountByUserUuid(String userUuid) {
		// TODO Auto-generated method stub
		return ordersMapper.selectCountByUserUuid(userUuid);
	}

	@Override
	public List<Orders> selectByUserUuid(String useruuid) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByUserUuid(useruuid);
	}

	@Override
	public List<Orders> selectByUserUuid1(String useruuid) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByUserUuid1(useruuid);
	}

	@Override
	public List<Orders> selectByUserUuid2(String useruuid) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByUserUuid2(useruuid);
	}

	@Override
	public List<Orders> selectByOwnerUuid(String useruuid) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByOwnerUuid(useruuid);
	}

	@Override
	public List<Orders> selectByOwnerUuid2(String useruuid) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByOwnerUuid2(useruuid);
	}

	@Override
	public List<Orders> selectByOwnerUuid3(String useruuid) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByOwnerUuid3(useruuid);
	}

	@Override
	public List<Map<String, Object>> selectByMapLimit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByMapLimit(map);
	}

	@Override
	public int countselectByMapLimit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ordersMapper.countselectByMapLimit(map);
	}

	@Override
	public Orders selectByOrderNumber(String orderNumber) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByOrderNumber(orderNumber);
	}

	/*@Override
	public Orders selectByUserUuidAndOrderUUid(String orderuuid) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByUserUuidAndOrderUUid(orderuuid);
	}*/

}
