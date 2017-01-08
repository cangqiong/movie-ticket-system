package rush.io.lab.service;

import rush.io.lab.dto.Exposer;
import rush.io.lab.dto.GrapTicketExecution;
import rush.io.lab.entity.Ticket;
import rush.io.lab.exception.RepeatGrabTicketException;
import rush.io.lab.exception.TicketException;
import rush.io.lab.exception.TicketSoldOutException;

import java.util.List;

/**
 * 抢票服务类
 *
 * @author cang
 * @create_time 2017-01-05 23:07
 */
public interface TicketService {

    /**
     * 查询所有电影票
     *
     * @return
     */
    List<Ticket> getTicketList();

    /**
     * 查询单张电影票信息
     *
     * @param tickedId
     * @return
     */
    Ticket getTicketById(long tickedId);


    /**
     * 获取抢票接口
     *
     * @param tickedId
     * @return
     */
    Exposer exportGrabTicketUrl(long tickedId);

    /**
     * 抢电影票
     *
     * @param ticketId
     * @param userId
     * @param md5
     * @return
     * @throws TicketException
     * @throws RepeatGrabTicketException
     * @throws TicketSoldOutException
     */
    GrapTicketExecution executeGrabTicket(long ticketId, String userId, String md5) throws TicketException, RepeatGrabTicketException, TicketSoldOutException;


}
