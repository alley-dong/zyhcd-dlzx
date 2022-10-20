package com.dlzx.common.core.web.domain;

/**
 * 类描述：ESB请求头
 * @className ESBHeader
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-02-02 15:20
 */
public class ESBHeader {

    /**
     * 消息编号
     */
    private String messageID;
    /**
     * 接口编号
     */
    private String interfaceID;
    /**
     * 事务号
     */
    private String transID;
    /**
     * 发送方标示
     */
    private String sender;
    /**
     * 接收方标示
     */
    private String receiver;
    /**
     * 数据条数
     */
    private String count;
    /**
     * 备注
     */
    private String comment;

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getInterfaceID() {
        return interfaceID;
    }

    public void setInterfaceID(String interfaceID) {
        this.interfaceID = interfaceID;
    }

    public String getTransID() {
        return transID;
    }

    public void setTransID(String transID) {
        this.transID = transID;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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
}
