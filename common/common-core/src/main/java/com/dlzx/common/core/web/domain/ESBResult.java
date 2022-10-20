package com.dlzx.common.core.web.domain;

import com.dlzx.common.core.constant.HttpStatus;
import com.dlzx.common.core.utils.StringUtils;

import java.util.HashMap;

/**
 * 类描述：ESB响应
 * @className ESBResult
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-02-02 15:20
 */
public class ESBResult extends HashMap<String, Object>{

    private static final long serialVersionUID = 1L;

    /**
     * 响应Header
     */
    public static final String msgHeader = "msgHeader";

    /**
     * 消息编号
     */
    public static final String msgMessageId = "messageId";
    /**
     * 接口编号
     */
    public static final String msgInterfaceId = "interfaceId";
    /**
     * 事务号
     */
    public static final String msgTransId = "transId";
    /**
     * 返回结果类型
     */
    public static final String msgResultType = "resultType";
    /**
     * 异常码
     */
    public static final String msgResultCode = "resultCode";
    /**
     * 异常信息
     */
    public static final String msgResultMessage = "resultMessage";
    /**
     * 返回数据条数
     */
    public static final String msgCount = "count";
    /**
     * 备注
     */
    public static final String msgComment = "comment";

    /**
     * 数据对象
     */
    public static final String msgBody = "msgBody";


    /**
     * 初始化一个新创建的 ESBResult 对象，使其表示一个空消息。
     */
    public ESBResult() {

    }


    /**
     * 初始化一个新创建的 ESBResult 对象
     *
     */
    public ESBResult(String messageId, String interfaceId,String transId, String resultType, String resultCode
            ,String resultMessage ,String count,String comment,Object data)
    {
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put(msgMessageId,messageId);
        resultMap.put(msgInterfaceId,interfaceId);
        resultMap.put(msgTransId,transId);
        resultMap.put(msgResultType,resultType);
        resultMap.put(msgResultCode,resultCode);
        resultMap.put(msgResultMessage,resultMessage);
        resultMap.put(msgCount,count);
        resultMap.put(msgComment,comment);
        super.put(msgHeader,resultMap);
        if (StringUtils.isNotNull(data))
        {
            super.put(msgBody, data);
        }
    }

    /**
     * 初始化一个新创建的 ESBResult 对象
     *
     */
    public ESBResult(String resultType,Object data)
    {
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put(msgMessageId,"");
        resultMap.put(msgInterfaceId,"");
        resultMap.put(msgTransId,"");
        resultMap.put(msgResultType,resultType);
        resultMap.put(msgResultCode,"");
        resultMap.put(msgResultMessage,"");
        resultMap.put(msgCount,"");
        resultMap.put(msgComment,"");
        super.put(msgHeader,resultMap);
        if (StringUtils.isNotNull(data))
        {
            super.put(msgBody, data);
            super.put("code",200);
        }
    }

    /**
     * 初始化一个新创建的 ESBResult 对象
     *
     */
    public ESBResult(String resultType,String resultCode,String resultMessage)
    {
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put(msgMessageId,"");
        resultMap.put(msgInterfaceId,"");
        resultMap.put(msgTransId,"");
        resultMap.put(msgResultType,resultType);
        resultMap.put(msgResultCode,resultCode);
        resultMap.put(msgResultMessage,resultMessage);
        resultMap.put(msgCount,"");
        resultMap.put(msgComment,"");
        super.put(msgHeader,resultMap);
        super.put(msgBody,null);
    }


    /**
     * 返回成功消息
     * @return 成功消息
     */
    public static ESBResult success(String messageId, String interfaceId,String transId, String resultCode
            ,String resultMessage ,String count,String comment,Object data)
    {
        return new ESBResult(messageId, interfaceId,transId,HttpStatus.ESB_SUCCESS,resultCode,resultMessage,count, comment,data);
    }

    /**
     * 返回成功消息
     * @return 成功消息
     */
    public static ESBResult success()
    {
        return new ESBResult(HttpStatus.ESB_SUCCESS,"操作成功");
    }

    /**
     * 返回成功消息
     * @return 成功消息
     */
    public static ESBResult success(Object data)
    {
        return new ESBResult(HttpStatus.ESB_SUCCESS,data);
    }


    /**
     * 返回失败消息
     * @return 失败消息
     */
    public static ESBResult error(String resultMessage)
    {
        return new ESBResult(HttpStatus.ESB_ERROR,HttpStatus.ESB_RES_ERROR,resultMessage);
    }

    /**
     * 返回错误消息
     * @return 警告消息
     */
    public static ESBResult error(String messageId, String interfaceId,String transId, String resultCode
            ,String resultMessage ,String count,String comment,Object data)
    {
        return new ESBResult(messageId, interfaceId,transId,HttpStatus.ESB_ERROR,resultCode,resultMessage,count, comment,data);
    }

}
