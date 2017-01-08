package rush.io.lab.dao;

import rush.io.lab.entity.Ticket;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author cang
 * @create_time 2017-01-05 16:59
 */
@Repository
public interface TicketDao extends
        JpaSpecificationExecutor<Ticket>,
        JpaRepository<Ticket, Long> {

    /**
     * 抢电影票
     *
     * @param ticketId
     * @param grabTime
     * @return
     */
    @Modifying
    @Query("update Ticket t set t.number = t.number - 1 where t.ticketId = ?1 and t.startTime <= ?2 and t.endTime >= ?2")
    @Version
    int grabTicket(long ticketId, Date grabTime);
}
