package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 驾驶员-司机试卷问题对象 d_driver_exam_problem
 * 
 * @author yangluhe
 * @date 2020-11-18
 */
public class DProblem extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    private List<DDriverExamProblem> list;
    private Long scoreDan;
    private Long scoreDuo;
    private Long scorePan;

    private Long scoreHe;

    private Long status;
//    public void setList(List<DDriverExamProblem> list){ this.list =list;}
//
//    public List<DDriverExamProblem> getList() {return list;}

    public void setScoreDan(Long scoreDan)
    {
        this.scoreDan = scoreDan;
    }

    public Long getScoreDan()
    {
        return scoreDan;
    }
    public void setScoreDuo(Long scoreDuo)
    {
        this.scoreDuo = scoreDuo;
    }

    public Long getScoreDuo()
    {
        return scoreDuo;
    }
    public void setScorePan(Long scorePan)
    {
        this.scorePan = scorePan;
    }

    public Long getScorePan()
    {
        return scorePan;
    }

    public void setScoreHe(Long scoreHe)
    {
        this.scoreHe = scoreHe;
    }

    public Long getScoreHe()
    {
        return scoreHe;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("list", getList())
                .append("scoreDan", getScoreDan())
                .append("scoreDuo", getScoreDuo())
                .append("scorePan", getScorePan())
                .append("scoreHe", getScoreHe())
                .append("status", getStatus())
            .toString();
    }

    public List<DDriverExamProblem> getList() {
        return list;
    }

    public void setList(List<DDriverExamProblem> list) {
        this.list = list;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
