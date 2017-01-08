package rush.io.lab.util;

/**
 * 使用枚举标签常量字段
 */
public enum GrapTicketStateEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复异常"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3, "数据篡改");

    private int state;

    private String stateInfo;

    GrapTicketStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static GrapTicketStateEnum stateOf(int index) {
        for (GrapTicketStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }


}
