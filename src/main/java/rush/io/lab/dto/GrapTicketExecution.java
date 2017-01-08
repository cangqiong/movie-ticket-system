package rush.io.lab.dto;

import rush.io.lab.entity.GrabTicketRecord;
import rush.io.lab.util.GrapTicketStateEnum;

/**
 * 封装秒杀执行后结果
 *
 * @author cang
 * @create_time 2017-01-02 14:29
 */
public class GrapTicketExecution {

    private long ticketId;

    // 秒杀执行结果状态
    private int state;

    // 状态表示
    private String stateInfo;

    // 秒杀成功对象
    private GrabTicketRecord grabTicketRecord;

    public GrapTicketExecution(long ticketId, GrapTicketStateEnum stateEnum, GrabTicketRecord grabTicketRecord) {
        this.ticketId = ticketId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.grabTicketRecord = grabTicketRecord;
    }

    public GrapTicketExecution(long ticketId, GrapTicketStateEnum stateEnum) {
        this.ticketId = ticketId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    @Override
    public String toString() {
        return "GrapTicketExecution{" +
                "ticketId=" + ticketId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", grabTicketRecord=" + grabTicketRecord +
                '}';
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public GrabTicketRecord getGrabTicketRecord() {
        return grabTicketRecord;
    }

    public void setGrabTicketRecord(GrabTicketRecord grabTicketRecord) {
        this.grabTicketRecord = grabTicketRecord;
    }
}

