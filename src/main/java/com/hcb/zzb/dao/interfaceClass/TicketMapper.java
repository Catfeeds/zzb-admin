package com.hcb.zzb.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.Ticket;

public interface TicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    Ticket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);
    
    List<Ticket> selectAllTickeByStstus(Map<String, Object> map);
    
    int selectAllTickeByStstusCount(Map<String, Object> map);
    
    Ticket selectByTicketUuid(String ticketUuid);
    
    List<Ticket> selectTicketsLimit(Map<String, Object> map);
    
    int countSelectTickets(Map<String, Object> map);
}