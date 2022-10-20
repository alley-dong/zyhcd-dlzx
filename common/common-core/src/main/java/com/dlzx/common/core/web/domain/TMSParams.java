package com.dlzx.common.core.web.domain;

/**
 * 类描述：TEM请求
 * @className TMSParams
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-02-02 15:20
 */
public class TMSParams {

    /**
     * 请求头
     */
    private ESBHeader msgHeader;

    private TMSMsgBody msgBody;


    public ESBHeader getMsgHeader() {
        return msgHeader;
    }

    public void setMsgHeader(ESBHeader msgHeader) {
        this.msgHeader = msgHeader;
    }

    public TMSMsgBody getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(TMSMsgBody msgBody) {
        this.msgBody = msgBody;
    }


    private String receiver;
    private String sender;
    private String resultCode;
    private String count;
    private String comment;
    private String resultMessage;
    private String resultType;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
