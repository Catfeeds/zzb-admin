package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.Ticket;

public interface ITicketService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(Ticket record);

	public int insertSelective(Ticket record);

	public Ticket selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(Ticket record);

	public int updateByPrimaryKey(Ticket record); 
	
	public List<Ticket> selectAllTickeByStstus(Map<String, Object> map);
	
	public int selectAllTickeByStstusCount(Map<String, Object> map);
	
	public Ticket selectByTicketUuid(String ticketUuid);
	
	List<Map<String, Object>> selectTicketsLimit(Map<String, Object> map);
    
    int countSelectTickets(Map<String, Object> map);
}
