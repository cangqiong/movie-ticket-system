package rush.io.lab.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import rush.io.lab.dto.Exposer;
import rush.io.lab.dto.GrapTicketExecution;
import rush.io.lab.entity.Ticket;
import rush.io.lab.exception.RepeatGrabTicketException;
import rush.io.lab.exception.TicketSoldOutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author cang
 * @create_time 2017/1/5 23:51
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml",
        "classpath:spring/spring-mail.xml"})
public class TicketServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TicketService ticketService;

    @Test
    public void getTicketList() throws Exception {
        List<Ticket> list = ticketService.getTicketList();
        assertEquals(4, list.size());
    }

    @Test
    public void getTicketById() throws Exception {
        long id = 1000;
        Ticket ticket = ticketService.getTicketById(id);
        assertNotNull(ticket);
    }

    @Test
    public void exportGrabTicketUrl() throws Exception {
        long id = 1000;
        Exposer exposer = ticketService.exportGrabTicketUrl(id);
        System.out.println(exposer);
    }

    @Test
    public void executeGrabTicket() throws Exception {
        long id = 1001;
        String userId = "test";
        Exposer exposer = ticketService.exportGrabTicketUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            GrapTicketExecution execution = null;
            try {
                execution = ticketService.executeGrabTicket(id, userId, exposer.getMd5());
            } catch (RepeatGrabTicketException e) {
                logger.error(e.getMessage());
            } catch (TicketSoldOutException e) {
                logger.error(e.getMessage());
            }
            logger.info("result={}", execution);
        } else {
            // 秒杀未开始
            logger.error("exposer={}", exposer);
        }
    }

}