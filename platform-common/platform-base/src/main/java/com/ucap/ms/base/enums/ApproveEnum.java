package com.ucap.ms.base.enums;

/**
 * 项目登记审批相关枚举
 *
 * @author jjq
 * @date 202010805
 */
public class ApproveEnum {
    /**阶段类型**/
    public enum StageType {
        SOCIALINVEST("社会投资类"),
        GOVINVEST("政府投资类");

        private String name;

        StageType (String name) {
            this.name = name;
        }

        public String getName () {
            return this.name;
        }
    }
    /**步骤事项类型：申报事项还是并联推进事项**/
    public enum StepItemType {
        /**申报事项**/
        DECLARATION("申报事项"),
        /**并联推进事项**/
        PROPULSION("并联推进事项");

        private String name;

        StepItemType (String name) {
            this.name = name;
        }

        public String getName () {
            return this.name;
        }
    }
    /**项目状态**/
    public enum ProjectStatus {
        Receipt("收件"),
        CorrectionReceipt("补正收件"),
        DefectiveReceipt("容缺收件"),
        AuthCorrection("审批补正"),
        Correction("补正"),
        Defective("容缺"),
        Cancel("作废");

        private String name;

        ProjectStatus (String name) {
            this.name = name;
        }

        public String getName () {
            return this.name;
        }
    }
    /**申请联系信息类型：个人还是法人**/
    public enum ApplyType {
        Personal("个人申请"),
        LegalPerson("法人申请");

        private String name;

        ApplyType (String name) {
            this.name = name;
        }

        public String getName () {
            return this.name;
        }
    }

    /**申报事项状态**/
    public enum ApplyItemStatus {
        ToCorrect("待补正"),
        ToProcessed("待办理"),
        Handling("办理中"),
        Completed("已办结"),
        Returned("已退回");

        private String name;

        ApplyItemStatus (String name) {
            this.name = name;
        }

        public String getName () {
            return this.name;
        }
    }

    /**材料必要性**/
    public enum MaterialIsNeed{
        Necessary(1,"必要"),
        NonEssential(2,"非必要"),
        PostFilling(3,"容缺后补");


        private final int key;
        private final String name;

        MaterialIsNeed(int key, String name) {
            this.key = key;
            this.name = name;
        }

        public static MaterialIsNeed valueOfName(String name) {
            for (MaterialIsNeed value : MaterialIsNeed.values()) {
                if (value.getName().equals(name)) {
                    return value;
                }
            }
            return null;
        }

        public int getKey() {
            return key;
        }

        public String getName() {
            return name;
        }
    }
    /**材料类型**/
    public enum MaterialType{
        Original(1,"原件"),
        Copies(2,"复印件"),
        OriginalAndCopy(3,"原件和复印件");


        private final int key;
        private final String name;

        MaterialType(int key, String name) {
            this.key = key;
            this.name = name;
        }

        public static MaterialType valueOfName(String name) {
            for (MaterialType value : MaterialType.values()) {
                if (value.getName().equals(name)) {
                    return value;
                }
            }
            return null;
        }

        public int getKey() {
            return key;
        }

        public String getName() {
            return name;
        }
    }
    /**材料形式**/
    public enum MaterialForm{
        PaperQuality(1,"纸质"),
        Electronics(2,"电子"),
        PaperElectronic(3,"纸质、电子");


        private final int key;
        private final String name;

        MaterialForm(int key, String name) {
            this.key = key;
            this.name = name;
        }
        public static MaterialForm valueOfKey(String key) {
            for (MaterialForm value : MaterialForm.values()) {
                if (String.valueOf(value.getKey()).equals(key)) {
                    return value;
                }
            }
            return null;
        }

        public int getKey() {
            return key;
        }

        public String getName() {
            return name;
        }
    }
    /**办结时限单位**/
    public enum ItemLimitType{
        WorkDay(1,"工作日"),
        Day(2,"天"),
        Month(3,"月"),
        Year(4,"年");


        private final int key;
        private final String name;

        ItemLimitType(int key, String name) {
            this.key = key;
            this.name = name;
        }

        public static ItemLimitType valueOfName(String name) {
            for (ItemLimitType value : ItemLimitType.values()) {
                if (value.getName().equals(name)) {
                    return value;
                }
            }
            return null;
        }

        public int getKey() {
            return key;
        }

        public String getName() {
            return name;
        }
    }
    /**取件方式：邮政快递还是窗口取件**/
    public enum PickingMethod {
        PostExpress("邮政快递"),
        WindowPickup("窗口取件");

        private String name;

        PickingMethod (String name) {
            this.name = name;
        }

        public String getName () {
            return this.name;
        }
    }
    /**退件状态**/
    public enum RefundStatus {
        ToConfirmed("待确认"),
        Pending("待退件"),
        Returned("已退件");

        private String name;

        RefundStatus (String name) {
            this.name = name;
        }

        public String getName () {
            return this.name;
        }
    }
    /**出证状态**/
    public enum IssuedStatus {
        ToConfirmed("待确认"),
        Waiting("待发证"),
        Certified("已发证");

        private String name;

        IssuedStatus (String name) {
            this.name = name;
        }

        public String getName () {
            return this.name;
        }
    }
    /**上传附件位置**/
    public enum FileLocation {
        Register("登记"),
        Correction("补正"),
        Tolerance("容缺"),
        GiveEvidence("出证"),
        Certification("发证"),
        Refund("退件"),
        ApproveHandle("审批办理"),
        Other("其他");

        private String name;

        FileLocation (String name) {
            this.name = name;
        }

        public String getName () {
            return this.name;
        }
    }
    /**服务类型**/
    public enum SericeType{
        SericeType1(1,"自然人"),
        SericeType2(2,"企业法人"),
        SericeType3(3,"事业法人"),
        SericeType4(4,"社会组织法人"),
        SericeType5(5,"非法人企业"),
        SericeType6(6,"行政机关"),
        SericeType9(9,"其他组织");


        private final int key;
        private final String name;

        SericeType(int key, String name) {
            this.key = key;
            this.name = name;
        }

        public int getKey() {
            return key;
        }

        public String getName() {
            return name;
        }
    }
}
