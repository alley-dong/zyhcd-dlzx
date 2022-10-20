package com.dlzx.pfserverperson.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 驾驶员-题库对象 d_exam_problem
 * 
 * @author dlzx
 * @date 2020-11-18
 */
public class DExamProblem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 题目 */
    @Excel(name = "题目")
    private String name;

    /** 题型 1单选题 2多选题 3判断题 */
    @Excel(name = "题型(单选题:1/多选题:2/判断题:3)")
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

    /** 答案（多选逗号分割） */
    @Excel(name = "答案(判断题:正确:A,错误:B)")
    private String answer;

    /** 分数 */
    @Excel(name = "分数")
    private int score;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;



    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    @NotBlank(message = "题目不能为空")
    @Size(min = 0, max = 100, message = "题目长度不能超过100个字符")
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

    @NotBlank(message = "选项A不能为空")
    @Size(min = 0, max = 50, message = "选项A长度不能超过50个字符")
    public String getOptionA() 
    {
        return optionA;
    }
    public void setOptionB(String optionB) 
    {
        this.optionB = optionB;
    }
    @NotBlank(message = "选项B不能为空")
    @Size(min = 0, max = 50, message = "选项B长度不能超过50个字符")
    public String getOptionB() 
    {
        return optionB;
    }
    public void setOptionC(String optionC) 
    {
        this.optionC = optionC;
    }
    @NotBlank(message = "选项C不能为空")
    @Size(min = 0, max = 50, message = "选项C长度不能超过50个字符")
    public String getOptionC() 
    {
        return optionC;
    }
    public void setOptionD(String optionD) 
    {
        this.optionD = optionD;
    }
    @NotBlank(message = "选项D不能为空")
    @Size(min = 0, max = 50, message = "选项D长度不能超过50个字符")
    public String getOptionD() 
    {
        return optionD;
    }
    public void setAnswer(String answer) 
    {
        this.answer = answer;
    }
    @NotBlank(message = "答案不能为空")
    public String getAnswer() 
    {
        return answer;
    }
    public void setScore(int score)
    {
        this.score = score;
    }

    public int getScore()
    {
        return score;
    }
    public void setDeleteFlag(Long deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() 
    {
        return deleteFlag;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("optionA", getOptionA())
            .append("optionB", getOptionB())
            .append("optionC", getOptionC())
            .append("optionD", getOptionD())
            .append("answer", getAnswer())
            .append("score", getScore())
            .append("deleteFlag", getDeleteFlag())

            .append("createTime", getCreateTime())

            .append("updateTime", getUpdateTime())
            .toString();
    }
    public boolean equals() {
        String str = new StringBuilder()
                .append(getId()!=null?getId():"")
                .append(getName()!=null?getName():"")
                .append(getType()!=null?getType():"")
                .append(getOptionA()!=null?getOptionA():"")
                .append(getOptionB()!=null?getOptionB():"")
                .append(getOptionC()!=null?getOptionC():"")
                .append(getOptionD()!=null?getOptionD():"")
                .append(getAnswer()!=null?getAnswer():"")

                .append(getDeleteFlag()!=null?getDeleteFlag():"")

                .append(getCreateTime()!=null?getCreateTime():"")

                .append(getUpdateTime()!=null?getUpdateTime():"")

                .toString();
        boolean flag = ("".equals(str.trim())||str.trim().length()==0);
//        System.out.println("数据长度:"+str+""+flag);
        return flag;


    }
}
