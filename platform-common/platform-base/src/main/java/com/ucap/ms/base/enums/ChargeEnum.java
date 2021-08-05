package com.ucap.ms.base.enums;

/**
 * 收费缓存登记管理相关枚举
 *
 * @author fjy
 * @date 2019/11/20
 */
public class ChargeEnum {
    /**办件类型**/
    public enum HandleType {
        IMMEDIATELY("即办件"),
        PROMISE("承诺件");

        private String name;

        HandleType (String name) {
            this.name = name;
        }

        public String getName () {
            return this.name;
        }
    }
    /**事项类型：**/
    public enum ItemType {
        /**许可**/
        PERMIT("政府许可"),
        RENDER("政府给予"),
        CONFIRM("政府确认"),
        REWARD("政府奖励"),
        ADJUDICATION("政府裁决"),
        OTHER("其他行政权力"),
        SERVICE("公共服务");

        private String name;

        ItemType (String name) {
            this.name = name;
        }

        public String getName () {
            return this.name;
        }
    }
    /**项目事项状态**/
    public enum ChargeStatus {
        RELEASE("已发布"),
        UNRELEASED("未发布");

        private String name;

        ChargeStatus (String name) {
            this.name = name;
        }

        public String getName () {
            return this.name;
        }
    }
    /**计费方式**/
    public enum FeeType {
        FIXED("固定额度"),
        SECTION("区间额度"),
        CALCULATION("计算额度");

        private String name;

        FeeType (String name) {
            this.name = name;
        }

        public String getName () {
            return this.name;
        }
    }

    /**收费记录状态**/
    public enum RecordStatus {
        UNCHARGE("待收费"),
        CHARGE("已收费"),
        RETURN("已退费");

        private String name;

        RecordStatus (String name) {
            this.name = name;
        }

        public String getName () {
            return this.name;
        }
    }

}
