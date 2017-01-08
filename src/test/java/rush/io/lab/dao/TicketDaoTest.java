package rush.io.lab.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import rush.io.lab.entity.Ticket;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

/**
 * @author cang
 * @create_time 2017/1/5 17:15
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class TicketDaoTest {

    @Resource
    private TicketDao ticketDao;

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Ticket ticket = ticketDao.findOne(id);
        ticket.getCreateTime();
        ticket.getStartTime();
        assertThat("record id not null", ticket, notNullValue());
    }

    @Test
    public void queryAll() throws Exception {
        List<Ticket> tickets = ticketDao.findAll();
        assertEquals(4, tickets.size());
    }

    @Test
    public void grabTicket() throws Exception {
        Date date = new Date();
        int updateCount = ticketDao.grabTicket(1002l, date);
        assertEquals(1, updateCount);
    }


}