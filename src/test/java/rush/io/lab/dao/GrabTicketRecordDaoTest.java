package rush.io.lab.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import rush.io.lab.entity.GrabTicketRecord;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author cang
 * @create_time 2017/1/5 18:36
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class GrabTicketRecordDaoTest {

    @Resource
    private GrabTicketRecordDao grabTicketRecordDao;

    @Test
    public void insertGrabTicketRecord() {
        long id = 1001;
        String userId = "test";
        GrabTicketRecord record = new GrabTicketRecord();
        record.setTicketId(id);
        record.setUserId(userId);
        record.setState(1);
        record = grabTicketRecordDao.save(record);
        assertThat("record id not null", record.getId(), notNullValue());
    }

    @Test
    public void findByTicketIdAndUserId() throws Exception {
        long id = 1001l;
        String userId = "test";
        GrabTicketRecord record = grabTicketRecordDao.findByTicketIdAndUserId(id, userId);
        assertThat("grabTicketRecord is not null", record, notNullValue());
    }
}