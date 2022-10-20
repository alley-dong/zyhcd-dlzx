package com.dlzx.common.core.web.page;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 表格分页数据对象
 * 
 * @author dlzx
 */
public class TableDataInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private List<?> rows;

    /** 消息状态码 */
    private int code;

    /** 消息内容 */
    private String msg;


    private BigDecimal fuelPriceTotal;
    private BigDecimal ballastQuotaPriceTotal;
    private BigDecimal pourQuotaPriceTotal;

    public BigDecimal getFuelPriceTotal() {
        return fuelPriceTotal;
    }

    public void setFuelPriceTotal(BigDecimal fuelPriceTotal) {
        this.fuelPriceTotal = fuelPriceTotal;
    }

    public BigDecimal getBallastQuotaPriceTotal() {
        return ballastQuotaPriceTotal;
    }

    public void setBallastQuotaPriceTotal(BigDecimal ballastQuotaPriceTotal) {
        this.ballastQuotaPriceTotal = ballastQuotaPriceTotal;
    }

    public BigDecimal getPourQuotaPriceTotal() {
        return pourQuotaPriceTotal;
    }

    public void setPourQuotaPriceTotal(BigDecimal pourQuotaPriceTotal) {
        this.pourQuotaPriceTotal = pourQuotaPriceTotal;
    }

    /**
     * 表格数据对象
     */
    public TableDataInfo()
    {
    }

    /**
     * 分页
     * 
     * @param list 列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<?> list, int total)
    {
        this.rows = list;
        this.total = total;
    }

    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public List<?> getRows()
    {
        return rows;
    }

    public void setRows(List<?> rows)
    {
        this.rows = rows;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}