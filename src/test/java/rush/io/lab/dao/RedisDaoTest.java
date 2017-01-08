package rush.io.lab.dao;

import org.junit.Test;

/**
 * @author cang
 * @create_time 2017/1/7 20:43
 * @description
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class RedisDaoTest {

//    @Resource
//    private TicketDao ticketDao;
//
//    @Resource
//    private RedisDao redisDao;
//
//    private long tickedId = 1001;


//    @Test
//    public void getTicket() throws Exception {
//        Ticket ticket = ticketDao.findOne(tickedId);
//        System.out.println("从数据库中获取的消息= " + ticket);
////        Gson gson = new Gson();
////        String json = gson.toJson(ticket);
////        System.out.println("对象转换成的json字符串" + json);
////        Ticket jsonObj = gson.fromJson(json,Ticket.class);
////        System.out.println("反序列化得到的对象" + jsonObj);
//
//        byte[] bytes = SerializeUtil.serialize(ticket);
//        Ticket newTicket = (Ticket) SerializeUtil.unserialize(bytes);
//        System.out.println("从序列化的结果:" + newTicket);
//        System.out.println(newTicket.getName());
//        System.out.println(newTicket.getTicketId());
//
////        redisDao.putTicket(ticket);
////        ticket = redisDao.getTicket(tickedId);
////        System.out.println(ticket.getName());
////        System.out.println(ticket);
//
//    }

    @Test
    public void  testSubstr(){
        String pre = "dfdf:";
        String s1 = pre+-1;
        String s2 = pre+37;
        String s3 = pre+56;
        System.out.println(s1.indexOf(pre));
        System.out.println(s1.replace(pre,""));
        System.out.println(Integer.parseInt("3 "));
        String res1 = s1.substring(s1.indexOf(pre));
        System.out.println(Integer.parseInt(res1));
        String res2 = s2.substring(s2.indexOf(pre));
        System.out.println(Integer.parseInt(res2));
        String res3 = s3.substring(s3.indexOf(pre));
        System.out.println(Integer.parseInt(res3));
    }

}