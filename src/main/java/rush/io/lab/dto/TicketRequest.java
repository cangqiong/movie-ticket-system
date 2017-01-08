package rush.io.lab.dto;

import java.io.Serializable;

/**
 * 抢票请求封装对象
 *
 * @author cang
 * @create_time 2017-01-08 10:24
 */
public class TicketRequest implements Serializable {

    // 用户id
    private String userId;

    // 订单id
    private long ticketId;

    // 订单数量
    private int number;

    // 请求时间
    private long time;

    public TicketRequest(String userId, long ticketId, int number) {
        this.userId = userId;
        this.ticketId = ticketId;
        this.number = number;
    }

    public TicketRequest(String userId, long ticketId, int number, long time) {
        this.userId = userId;
        this.ticketId = ticketId;
        this.number = number;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketRequest)) return false;

        TicketRequest that = (TicketRequest) o;

        if (getTicketId() != that.getTicketId()) return false;
        if (getNumber() != that.getNumber()) return false;
        if (getTime() != that.getTime()) return false;
        return getUserId() != null ? getUserId().equals(that.getUserId()) : that.getUserId() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (int) (getTicketId() ^ (getTicketId() >>> 32));
        result = 31 * result + getNumber();
        result = 31 * result + (int) (getTime() ^ (getTime() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TicketRequest{" +
                "userId='" + userId + '\'' +
                ", ticketId=" + ticketId +
                ", number=" + number +
                ", time=" + time +
                '}';
    }
}
