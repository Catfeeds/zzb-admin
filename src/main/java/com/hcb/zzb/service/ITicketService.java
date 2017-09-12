package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.Ticket;
/**
 * 罚单类
 * @author kk
 *
 */
public interface ITicketService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(Ticket record);

	public int insertSelective(Ticket record);

	public Ticket selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(Ticket record);

	public int updateByPrimaryKey(Ticket record); 
	/**
     * 根据筛选条件查询列表
     * @param map
     * @return
     */
	public List<Ticket> selectAllTickeByStstus(Map<String, Object> map);
	/**
     * 根据筛选条件查询数目
     * @param map
     * @return
     */
	public int selectAllTickeByStstusCount(Map<String, Object> map);
	//
	/**
	 * 根据uuid查询罚单
	 * @param ticketUuid
	 * @return
	 */
	public Ticket selectByTicketUuid(String ticketUuid);
	/**
     * 根据筛选条件查询列表
     * @param map
     * @return
     */
	List<Map<String, Object>> selectTicketsLimit(Map<String, Object> map);
    /**
     * 根据筛选条件查询数目
     * @param map
     * @return
     */
    int countSelectTickets(Map<String, Object> map);
    //根据uuid查询罚单数目
    /**
     * 根据uuid查询罚单
     * @param userUuid
     * @return
     */
    int countUserTicket(String userUuid);
}
