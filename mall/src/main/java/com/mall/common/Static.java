package com.mall.common;

/**
 * 静态配置类
 * @author Jianshu
 * @time 20201030
 */
public class Static {
    public static final String CURRENT_USER = "currentUser";
    public static final String FORGET_TOKEN = "forgetToken";

    public interface ExpiredType{
        long ONE_MONTH =  1000L * 60L * 60L * 24L * 30L;
        long ONE_DAY =  1000L * 60L * 60L * 24L;
        long ONE_HOUR =  1000L * 60L * 60L;
        long ONE_MINUTE =  1000L * 60L;
    }

    public interface AdvertPosition{
        int INDEX_BANNER = 1;
    }

    public interface ValidStatus{
        int VALID = 1;
        int INVALID = 0;
    }

    public interface Cache{
        /**
         * 所有品类
         */
        String ALL_CATEGORY = "allCategory";
    }

    public interface Role {
        /**
         * 管理员
         */
        int ROLE_ADMIN = 1;
        /**
         * 普通用户
         */
        int ROLE_CUSTOMER = 0;
    }

    /**
     * 订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
     */
    public enum OrderStatus {
        CANCEL(0, "已取消"),
        UNPAY(10, "未付款"),
        PAID(20, "已付款"),
        PREPARE(30, "已备货"),
        SHIPED(40, "已发货"),
        SUCCESS(50, "交易成功"),
        CLOSE(60, "交易关闭");

        OrderStatus(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private int code;
        private String desc;

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static OrderStatus codeOf(int code) {
            for (OrderStatus orderStatusEnum : values()) {
                if (orderStatusEnum.getCode() == code) {
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("无对应的枚举");
        }

    }
}
