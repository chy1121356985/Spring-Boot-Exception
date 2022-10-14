package com.renderg.pojo;


/**
 * 测试枚举
 *
 * @author chy
 * @Date:Created in 9/27/22
 */
public enum TestEnums {

    /**
     * Unknown
     */
    UNKNOWN(0, "Unknown"),
    /**
     * Rendering
     */
    RENDERING(1, "Rendering"),
    /**
     * Idle
     */
    IDLE(2, "Idle"),
    /**
     * Offline
     */
    OFFLINE(3, "Offline"),
    /**
     * Stalled
     */
    STALLED(4, "Stalled"),
    /**
     * StartingJob
     */
    STARTING_JOB(8, "StartingJob");

    private Integer code;

    private String desc;

    TestEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static TestEnums typeOf(Integer code) {
        for (TestEnums value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    public static String showDesc(Integer code) {
        for (TestEnums value : values()) {
            if (value.getCode().equals(code)) {
                return value.getDesc();
            }
        }
        return "未知";
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static void main(String[] args) {
        // 测试获取枚举变量
        TestEnums testEnums = TestEnums.typeOf(1);
        if (testEnums != null) {
            System.out.println(testEnums.desc);
            System.out.println(testEnums.getDesc());
        }
        // 测试获取枚举描述
        String desc = TestEnums.showDesc(0);
        System.out.println(desc);

        TestEnums.showDesc(1);

        testEnums.getDesc();
        TestEnums.showDesc(2);

        TestEnums.showDesc(3);

    }


}
