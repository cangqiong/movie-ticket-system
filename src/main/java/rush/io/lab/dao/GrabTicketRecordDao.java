package rush.io.lab.dao;

import rush.io.lab.entity.GrabTicketRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * @author cang
 * @create_time 2017-01-05 16:59
 */
@Repository
public interface GrabTicketRecordDao extends
        JpaSpecificationExecutor<GrabTicketRecord>,
        JpaRepository<GrabTicketRecord, Long> {


    /**
     * 根据电影票id与用户id查询抢票记录
     *
     * @param ticketId
     * @param userId
     * @return
     */
    GrabTicketRecord findByTicketIdAndUserId(long ticketId, String userId);

}
