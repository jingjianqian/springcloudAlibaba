package com.ucap.ms.approve.api.payload;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 接口access_token请求响应数据
 * @author loncx
 * @date 2019/11/23
 */
public class RequestTokenPayload {


    /**
     * controls : []
     * custom : {"access_token":"cc687abc823460a906053efc00f46364","refresh_token":"57f2149b7360debe5d907cfb67075a38","jsessionid":"","expires_in":"1800"}
     * status : {"code":"1"}
     */

    @JSONField(name = "custom")
    private CustomBean custom;
    @JSONField(name = "status")
    private StatusBean status;
    @JSONField(name = "controls")
    private List<?> controls;

    public CustomBean getCustom() {
        return custom;
    }

    public void setCustom(CustomBean custom) {
        this.custom = custom;
    }

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public List<?> getControls() {
        return controls;
    }

    public void setControls(List<?> controls) {
        this.controls = controls;
    }

    public static class CustomBean {
        /**
         * access_token : cc687abc823460a906053efc00f46364
         * refresh_token : 57f2149b7360debe5d907cfb67075a38
         * jsessionid :
         * expires_in : 1800
         */

        @JSONField(name = "access_token")
        private String accessToken;
        @JSONField(name = "refresh_token")
        private String refreshToken;
        @JSONField(name = "jsessionid")
        private String jsessionid;
        @JSONField(name = "expires_in")
        private String expiresIn;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public String getJsessionid() {
            return jsessionid;
        }

        public void setJsessionid(String jsessionid) {
            this.jsessionid = jsessionid;
        }

        public String getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(String expiresIn) {
            this.expiresIn = expiresIn;
        }
    }

    public static class StatusBean {
        /**
         * code : 1
         */

        @JSONField(name = "code")
        private String code;

        @JSONField(name = "text")
        private String text;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public boolean isOk() {
        return "1".equals(status.getCode());
    }

    @Override
    public String toString() {
        return "RequestTokenPayload{" +
                "custom=" + custom +
                ", status=" + status +
                ", controls=" + controls +
                '}';
    }
}
