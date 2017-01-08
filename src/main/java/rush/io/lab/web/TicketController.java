package rush.io.lab.web;

import rush.io.lab.dao.cache.RedisDao;
import rush.io.lab.dto.Exposer;
import rush.io.lab.dto.Result;
import rush.io.lab.dto.TicketRequest;
import rush.io.lab.entity.Ticket;
import rush.io.lab.service.impl.TicketSendMessage;
import rush.io.lab.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 抢票控制器
 *
 * @author cang
 * @create_time 2017-01-02 19:05
 */
@Controller
@RequestMapping("/ticket")
public class TicketController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TicketService ticketService;

    @Resource
    private TicketSendMessage ticketSendMessage;

    @Resource
    private RedisDao redisDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Ticket> list = ticketService.getTicketList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{ticketId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("ticketId") Long ticketId, Model model) {
        if (ticketId == null) {
            return "redirect:/ticket/list";
        }
        Ticket ticket = ticketService.getTicketById(ticketId);
        model.addAttribute("ticket", ticket);
        return "detail";
    }

    @RequestMapping(value = "/{ticketId}/exposer", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result<Exposer> exposer(@PathVariable("ticketId") Long ticketId) {
        Result<Exposer> result;
        try {
            Exposer exposer = ticketService.exportGrabTicketUrl(ticketId);
            result = new Result<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new Result<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{ticketId}/{md5}/execution", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result<Boolean> execute(@PathVariable("ticketId") Long ticketId, @PathVariable("md5") String md5,
                                   @CookieValue(value = "userId", required = true) String userId) {
        if (userId == null) {
            return new Result<Boolean>(false, "未注册");
        }
        Date now = new Date();
        TicketRequest request = new TicketRequest(userId, ticketId, 1, now.getTime());
        // 加入请求队列
        ticketSendMessage.sendMessage("ticket", request);
        // 异步返回结果
        return new Result<Boolean>(true, true);
    }

    @RequestMapping(value = "/{ticketId}/polling", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result<Integer> polling(@PathVariable("ticketId") Long ticketId,
                                   @CookieValue(value = "userId", required = true) String userId) {
        if (userId == null) {
            return new Result<Integer>(false, -1);
        }
        int status = redisDao.getGrapTicketStatus(userId, ticketId);
        // 异步返回结果
        return new Result<Integer>(true, status);
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public Result<Long> time() {
        Date now = new Date();
        return new Result<Long>(true, now.getTime());
    }

}
