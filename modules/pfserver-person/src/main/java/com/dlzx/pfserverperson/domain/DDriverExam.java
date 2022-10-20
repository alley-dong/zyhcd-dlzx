package com.dlzx.pfserverperson.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 驾驶员-司机考试对象 d_driver_exam
 * 
 * @author yangluhe
 * @date 2020-11-18
 */
public class DDriverExam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 试卷ID */
    @Excel(name = "试卷ID")
    private String paperId;

    /** 试卷名称 */
    @Excel(name = "试卷名称")
    private String paperName;

    /** 司机ID */
    @Excel(name = "司机ID")
    private String driverId;

    /** 状态 1已答 2未答 */
    @Excel(name = "状态 1已答 2未答")
    private Long status;

    /** 单选题得分 */
    @Excel(name = "单选题得分")
    private Long radioScore;

    /** 多选题得分 */
    @Excel(name = "多选题得分")
    private Long checkboxScore;

    /** 判断题得分 */
    @Excel(name = "判断题得分")
    private Long judgmentScore;

    /** 总分 */
    @Excel(name = "总分")
    private Long totalScore;



    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setPaperId(String paperId) 
    {
        this.paperId = paperId;
    }

    public String getPaperId() 
    {
        return paperId;
    }
    public void setPaperName(String paperName) 
    {
        this.paperName = paperName;
    }

    public String getPaperName() 
    {
        return paperName;
    }
    public void setDriverId(String driverId) 
    {
        this.driverId = driverId;
    }

    public String getDriverId() 
    {
        return driverId;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setRadioScore(Long radioScore)
    {
        this.radioScore = radioScore;
    }

    public Long getRadioScore()
    {
        return radioScore;
    }
    public void setCheckboxScore(Long checkboxScore)
    {
        this.checkboxScore = checkboxScore;
    }

    public Long getCheckboxScore()
    {
        return checkboxScore;
    }
    public void setJudgmentScore(Long judgmentScore)
    {
        this.judgmentScore = judgmentScore;
    }

    public Long getJudgmentScore()
    {
        return judgmentScore;
    }
    public void setTotalScore(Long totalScore)
    {
        this.totalScore = totalScore;
    }

    public Long getTotalScore()
    {
        return totalScore;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("paperId", getPaperId())
            .append("paperName", getPaperName())
            .append("driverId", getDriverId())
            .append("status", getStatus())
            .append("radioScore", getRadioScore())
            .append("checkboxScore", getCheckboxScore())
            .append("judgmentScore", getJudgmentScore())
            .append("totalScore", getTotalScore())

            .append("createTime", getCreateTime())

            .append("updateTime", getUpdateTime())
            .toString();
    }
}
