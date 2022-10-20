package com.dlzx.common.core.web.domain;

/**
 * 类描述：ESB请求
 * @className ESBParams
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-02-02 15:20
 */
public class ESBParams<T> {

    /**
     * 请求头
     */
    private ESBHeader msgHeader;

    /**
     * body实体对象
     */
    private T msgBody;


    public ESBHeader getMsgHeader() {
        return msgHeader;
    }

    public void setMsgHeader(ESBHeader msgHeader) {
        this.msgHeader = msgHeader;
    }

    public T getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(T msgBody) {
        this.msgBody = msgBody;
    }

}
