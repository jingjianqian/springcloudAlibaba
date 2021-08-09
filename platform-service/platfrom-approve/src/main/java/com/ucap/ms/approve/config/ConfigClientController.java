package com.ucap.ms.approve.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RefreshScope
public class ConfigClientController implements Serializable {
    /**
     * from nacos config
     */
    //参数
    @Value("${config.token.grant_type}")
    private String GRANT_TYPE;
    @Value("${config.token.grant_type_value}")
    private String GRANT_TYPE_VALUE;
    @Value("${config.token.client_id}")
    private String CLIENT_ID;
    @Value("${config.token.client_id_value}")
    private String CLIENT_ID_VALUE;
    @Value("${config.token.client_secret}")
    private String CLIENT_SECRET;
    @Value("${config.token.client_secret_value}")
    private String CLIENT_SECRET_VALUE;
    @Value("${config.token.access_token}")
    private String ACCESS_TOKEN;
    @Value("${config.token.key_audit_item_api_token}")
    private String KEY_AUDIT_ITEM_API_TOKEN;

    //接口url
    @Value("${config.urls.get_token_url}")
    private String GET_TOKEN_URL;
    @Value("${config.urls.get_deptAudiItem_url}")
    private String GET_DEPTAUDITITEM_URL;

    //参数名
    @Value("${config.params.dept_code}")
    private String DEPT_CODE;
    @Value("${config.params.time_stamp}")
    private String TIME_STAMP;



    public String getKEY_AUDIT_ITEM_API_TOKEN() {
        return KEY_AUDIT_ITEM_API_TOKEN;
    }

    public void setKEY_AUDIT_ITEM_API_TOKEN(String KEY_AUDIT_ITEM_API_TOKEN) {
        this.KEY_AUDIT_ITEM_API_TOKEN = KEY_AUDIT_ITEM_API_TOKEN;
    }

    public String getGRANT_TYPE() {
        return GRANT_TYPE;
    }

    public void setGRANT_TYPE(String GRANT_TYPE) {
        this.GRANT_TYPE = GRANT_TYPE;
    }

    public String getGRANT_TYPE_VALUE() {
        return GRANT_TYPE_VALUE;
    }

    public void setGRANT_TYPE_VALUE(String GRANT_TYPE_VALUE) {
        this.GRANT_TYPE_VALUE = GRANT_TYPE_VALUE;
    }

    public String getCLIENT_ID() {
        return CLIENT_ID;
    }

    public void setCLIENT_ID(String CLIENT_ID) {
        this.CLIENT_ID = CLIENT_ID;
    }

    public String getCLIENT_ID_VALUE() {
        return CLIENT_ID_VALUE;
    }

    public void setCLIENT_ID_VALUE(String CLIENT_ID_VALUE) {
        this.CLIENT_ID_VALUE = CLIENT_ID_VALUE;
    }

    public String getCLIENT_SECRET() {
        return CLIENT_SECRET;
    }

    public void setCLIENT_SECRET(String CLIENT_SECRET) {
        this.CLIENT_SECRET = CLIENT_SECRET;
    }

    public String getCLIENT_SECRET_VALUE() {
        return CLIENT_SECRET_VALUE;
    }

    public void setCLIENT_SECRET_VALUE(String CLIENT_SECRET_VALUE) {
        this.CLIENT_SECRET_VALUE = CLIENT_SECRET_VALUE;
    }

    public String getACCESS_TOKEN() {
        return ACCESS_TOKEN;
    }

    public void setACCESS_TOKEN(String ACCESS_TOKEN) {
        this.ACCESS_TOKEN = ACCESS_TOKEN;
    }

    public String getGET_TOKEN_URL() {
        return GET_TOKEN_URL;
    }

    public void setGET_TOKEN_URL(String GET_TOKEN_URL) {
        this.GET_TOKEN_URL = GET_TOKEN_URL;
    }

    public String getGET_DEPTAUDITITEM_URL() {
        return GET_DEPTAUDITITEM_URL;
    }

    public void setGET_DEPTAUDITITEM_URL(String GET_DEPTAUDITITEM_URL) {
        this.GET_DEPTAUDITITEM_URL = GET_DEPTAUDITITEM_URL;
    }

    public String getDEPT_CODE() {
        return DEPT_CODE;
    }

    public void setDEPT_CODE(String DEPT_CODE) {
        this.DEPT_CODE = DEPT_CODE;
    }

    @Override
    public String toString() {
        return "ConfigClientController{" +
                "GRANT_TYPE='" + GRANT_TYPE + '\'' +
                ", GRANT_TYPE_VALUE='" + GRANT_TYPE_VALUE + '\'' +
                ", CLIENT_ID='" + CLIENT_ID + '\'' +
                ", CLIENT_ID_VALUE='" + CLIENT_ID_VALUE + '\'' +
                ", CLIENT_SECRET='" + CLIENT_SECRET + '\'' +
                ", CLIENT_SECRET_VALUE='" + CLIENT_SECRET_VALUE + '\'' +
                ", ACCESS_TOKEN='" + ACCESS_TOKEN + '\'' +
                ", GET_TOKEN_URL='" + GET_TOKEN_URL + '\'' +
                ", GET_DEPTAUDITITEM_URL='" + GET_DEPTAUDITITEM_URL + '\'' +
                '}';
    }

    @GetMapping("/getAllConfig/test")
    public String getAllConfigTempTest() {
        return this.toString();
    }
}
