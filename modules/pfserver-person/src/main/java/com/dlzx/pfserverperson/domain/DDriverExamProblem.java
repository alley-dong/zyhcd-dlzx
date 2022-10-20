package com.dlzx.pfserverperson.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 驾驶员-司机试卷问题对象 d_driver_exam_problem
 * 
 * @author yangluhe
 * @date 2020-11-18
 */
public class DDriverExamProblem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 司机ID */
    @Excel(name = "司机ID")
    private String driverId;

    /** 司机考试ID */
    @Excel(name = "司机考试ID")
    private String examId;

    /** 问题ID */
    @Excel(name = "问题ID")
    private String problemId;

    /** 题目 */
    @Excel(name = "题目")
    private String name;

    /** 题型 1单选题 2多选题 3判断题 */
    @Excel(name = "题型 1单选题 2多选题 3判断题")
    private Long type;

    /** 选项A */
    @Excel(name = "选项A")
    private String optionA;

    /** 选项B */
    @Excel(name = "选项B")
    private String optionB;

    /** 选项C */
    @Excel(name = "选项C")
    private String optionC;

    /** 选项D */
    @Excel(name = "选项D")
    private String optionD;

    /** 回答答案（多选逗号分割） */
    @Excel(name = "回答答案", readConverterExp = "多=选逗号分割")
    private String answer;

    /** 得分分数 */
    @Excel(name = "得分分数")
    private Long score;

    private String trueAnswer;

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDriverId(String driverId) 
    {
        this.driverId = driverId;
    }

    public String getDriverId() 
    {
        return driverId;
    }
    public void setExamId(String examId) 
    {
        this.examId = examId;
    }

    public String getExamId() 
    {
        return examId;
    }
    public void setProblemId(String problemId) 
    {
        this.problemId = problemId;
    }

    public String getProblemId() 
    {
        return problemId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setOptionA(String optionA) 
    {
        this.optionA = optionA;
    }

    public String getOptionA() 
    {
        return optionA;
    }
    public void setOptionB(String optionB) 
    {
        this.optionB = optionB;
    }

    public String getOptionB() 
    {
        return optionB;
    }
    public void setOptionC(String optionC) 
    {
        this.optionC = optionC;
    }

    public String getOptionC() 
    {
        return optionC;
    }
    public void setOptionD(String optionD) 
    {
        this.optionD = optionD;
    }

    public String getOptionD() 
    {
        return optionD;
    }
    public void setAnswer(String answer) 
    {
        this.answer = answer;
    }

    public String getAnswer() 
    {
        return answer;
    }
    public void setScore(Long score) 
    {
        this.score = score;
    }

    public Long getScore() 
    {
        return score;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("driverId", getDriverId())
            .append("examId", getExamId())
            .append("problemId", getProblemId())
            .append("name", getName())
            .append("type", getType())
            .append("optionA", getOptionA())
            .append("optionB", getOptionB())
            .append("optionC", getOptionC())
            .append("optionD", getOptionD())
            .append("answer", getAnswer())
            .append("score", getScore())
                .append("trueAnswer",getTrueAnswer())
            .toString();
    }
}
