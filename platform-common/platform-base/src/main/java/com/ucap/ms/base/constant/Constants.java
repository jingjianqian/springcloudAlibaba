package com.ucap.ms.base.constant;


/**
 * 业务逻辑常量定义
 */
public class Constants {
    /** 起始页参数名 */
    public final static String PARAM_START = "start";
    /** 每页条数参数名 */
    public final static String PARAM_LIMIT = "limit";
    /** 分页：每页条数 */
    public final static String PAGE_SIZE_STR = "15";

    /** 消息服务-日志TAG标志 */
    public final static String TAG_LOG = "TAG_LOG";
    /************************************************************************/
    /** 系统状态码-操作失败状态 */
    public final static String SYS_FAILURE = "0";
    /** 系统状态码-操作成功状态 */
    public final static String SYS_SUCCESS = "1";

    /************************** web 用户登录信息 **********************************************/
    /** token名称 */
    public final static String TOKEN = "token";
    /** 获取用户id：token(request请求头获取token)+Constants.TOKEN_LOGIN_USERID_SUFFIX */
    public final static String TOKEN_LOGIN_USERID_SUFFIX = "_login_userid";
    /** 获取用户姓名：token(request请求头获取token)+Constants.TOKEN_LOGIN_USERNAME_SUFFIX */
    public final static String TOKEN_LOGIN_USERNAME_SUFFIX = "_login_username";
    /** 获取用户信息：token(request请求头获取token)+Constants.TOKEN_LOGIN_USER_SUFFIX */
    public final static String TOKEN_LOGIN_USER_SUFFIX = "_login_user";
    /** 获取用户所在大厅窗口信息：token(userCode+Constants.TOKEN_LOGIN_USER_DTCK_SUFFIX，格式：dtId|dtName|ckId|ckName */
    public final static String TOKEN_LOGIN_USER_DTCK_SUFFIX = "_login_user_dtck";

    /************************** 子系统名称，用于权限注解 **********************************************/
    public final static String DOMAIN_AUTH = "权限子系统";
    public final static String DOMAIN_AFFAIR = "事项子系统";
    public final static String DOMAIN_BUSINESS = "申办受理办结子系统";
    public final static String DOMAIN_BSDTGL = "办事大厅综合管理子系统";
    public final static String DOMAIN_YYGL = "预约管理子系统";
    public final static String DOMAIN_YJHGL = "云叫号管理子系统";
    public final static String DOMAIN_FLOW = "通用审批子系统";
    //TODO:更多子系统待增加，需与子系统管理中的系统名称一致

    /************************** 权限模块 *******************************/
    /** 类型:角色 */
    public final static String AUTH_TYPE_ROLE = "role";
    /** 类型:部门 */
    public final static String AUTH_TYPE_DEPT = "dept";
    /** 类型:区域 */
    public final static String AUTH_TYPE_AREA = "area";

    /************************** 系统固化的角色 *******************************/
    public final static String ROLE_SYSROLE = "sysrole";
    public final static String ROLE_AFFAIR_ADMIN = "affairadmin";
    public final static String ROLE_AFFAIR_TOWN_ADMIN = "affair-town-admin";
    /** 统一认证用户第一次同步进来后绑定的默认角色 */
    public final static String ROLE_KSRY = "role-ksry";

    /************************** 系统固化的部门编码 *******************************/
    /** 部门编码:市府部门 */
    public final static String DEPT_SFBM = "441900,DGS919,DGS920,DGS921,DGS922,DGS923";
    /** 部门编码:镇街园区 */
    public final static String DEPT_ZJYQ = "zjyq,DGSZJJCF,DGS924";
    /** 部门编码:系统维护 */
    public final static String DEPT_XTWH = "xtwh";

    /************************** 系统固化的机构类型 *******************************/
    /** 机构类型:部门 */
    public final static String DEPT_LX_BM = "1";
    /** 机构类型:区域 */
    public final static String DEPT_LX_ZZJG = "2";
    /** 机构类型:科室 */
    public final static String DEPT_LX_KS = "3";
    /** 机构类型/角色类型:分组 */
    public final static String DEPT_LX_FZ = "4";
    /** 角色类型:分组 */
    public final static String DEPT_LX_ROLE = "5";

    /************************** 系统固化的消息类型 *******************************/
    /**消息服务-认证组名*/
    public final static String  MQ_AUTH_GROUP_NAME="MQ_AUTH_GROUP";
    /**消息服务-认证主题*/
    public final static String  MQ_AUTH_TOPIC_NAME="MQ_AUTH_TOPIC";
    /**消息服务-信息资源库消费用AUTH_USER主题*/
    public final static String  MQ_TOPIC_IRR_AUTH_USER="MQ_TOPIC_IRR_AUTH_USER";
    /**消息服务-信息资源库消费用AUTH_GROUP主题*/
    public final static String  MQ_TOPIC_IRR_AUTH_GROUP="MQ_TOPIC_IRR_AUTH_GROUP";

    /**消息服务-用户同步   删除用户   TAG标志*/
    public final static String  MQ_OPT_DEL_USER="MQ_DEL_USER";
    /**消息服务-用户同步   新增用户   TAG标志*/
    public final static String  MQ_OPT_ADD_USER="MQ_ADD_USER";
    /**消息服务-用户同步   修改用户   TAG标志*/
    public final static String  MQ_OPT_UPD_USER="MQ_UPD_USER";

    /**消息服务-组同步   删除组   TAG标志*/
    public final static String  MQ_OPT_DEL_GROUP="MQ_DEL_GROUP";
    /**消息服务-组同步   新增组   TAG标志*/
    public final static String  MQ_OPT_ADD_GROUP="MQ_ADD_GROUP";
    /**消息服务-组同步   修改组   TAG标志*/
    public final static String  MQ_OPT_UPD_GROUP="MQ_UPD_GROUP";
    /**消息服务-组同步   保存组（新增或修改）   TAG标志*/
    public final static String  MQ_OPT_SAVE_GROUP="MQ_SAVE_GROUP";

    /**消息服务-待办组名*/
    public final static String  MQ_TASK_GROUP_NAME="MQ_TASK_GROUP";
    /**消息服务-待办主题*/
    public final static String  MQ_TASK_TOPIC_NAME="MQ_TASK_TOPIC";

    /**消息服务-删除待办 */
    public final static String  MQ_OPT_DEL_TASK="MQ_DEL_TASK";
    /**消息服务-新增待办   */
    public final static String  MQ_OPT_ADD_TASK="MQ_ADD_TASK";
    /**消息服务-修改待办*/
    public final static String  MQ_OPT_UPD_TASK="MQ_UPD_TASK";

    /**消息服务-事项模块组*/
    public final static String  MQ_AFFAIR_GROUP_NAME="MQ_AFFAIR_GROUP";
    /**消息服务-实施清单版本发布同步主题 */
    public final static String  MQ_AFFAIR_MODIFY_DELEGATE="MQ_AFFAIR_MODIFY_DELEGATE";
    /**消息服务-用户同步   新增用户   TAG标志*/
    public final static String  MQ_OPT_MODIFY_AFFAIR_ITEM="MQ_MODIFY_AFFAIR";

    /**消息服务-受理登记模块组*/
    public final static String MQ_APPLY_GROUP_NAME="MQ_APPLY_GROUP";
    /**消息服务-根据办事过程处理工单*/
    public final static String MQ_WORKITEM_UPDATE_STATUS="MQ_WORKITEM_UPDATE_STATUS";
    /**消息服务-办事过程数据*/
    public final static String MQ_WORKITEM_EXGDBS_PROCESS="MQ_WORKITEM_EXGDBS_PROCESS";


    /************************** 系统固化的提示信息 *******************************/
    /** fegin快速失败提示 */
    public final static String FEIGN_FALLBACK_DEF_MSG = "系统繁忙，请稍候再试";

    /************************** 系统固化的参数名称 *******************************/
    /** 列表标识参数·查看全部数据 */
    public final static String AUTH_LIST_ALL_DATA = "authListAllData";
    /** 列表标识参数·查看部门数据 */
    public final static String AUTH_LIST_DEPT_ID = "authListDeptId";
    /** API过滤传入参数·数据开始时间 */
    public final static String API_DATA_BEGIN_TIME = "apiDataBeginTime";
    /** API过滤传入参数·数据结束时间 */
    public final static String API_DATA_END_TIME = "apiDataEndTime";
    /** API过滤传入参数·所属部门 */
    public final static String API_DEPT_ID = "apiDeptId";


    /** 数据库数据状态-删除状态 */
    public final static String STATUS_DEL = "0";
    /** 数据库数据状态-正常状态 */
    public final static String STATUS_NORMAL = "1";
}
