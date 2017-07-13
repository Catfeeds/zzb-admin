package com.hcb.zzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.TicketMapper;
import com.hcb.zzb.dto.Ticket;
import com.hcb.zzb.service.ITicketService;
@Service("ticketService")
public class TicketServiceImpl implements ITicketService{
	@Autowired
	TicketMapper ticketMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return ticketMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Ticket record) {
		// TODO Auto-generated method stub
		return ticketMapper.insert(record);
	}

	@Override
	public int insertSelective(Ticket record) {
		// TODO Auto-generated method stub
		return ticketMapper.insertSelective(record);
	}

	@Override
	public Ticket selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return ticketMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Ticket record) {
		// TODO Auto-generated method stub
		return ticketMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Ticket record) {
		// TODO Auto-generated method stub
		return ticketMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Ticket> selectAllTickeByStstus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ticketMapper.selectAllTickeByStstus(map);
	}

	@Override
	public int selectAllTickeByStstusCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ticketMapper.selectAllTickeByStstusCount(map);
	}

	@Override
	public Ticket selectByTicketUuid(String ticketUuid) {
		// TODO Auto-generated method stub
		return ticketMapper.selectByTicketUuid(ticketUuid);
	}
	
}
