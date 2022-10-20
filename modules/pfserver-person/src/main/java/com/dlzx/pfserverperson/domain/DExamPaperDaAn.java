package com.dlzx.pfserverperson.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 驾驶员-试卷问题对象 d_exam_paper_problem
 *
 * @author dlzx
 * @date 2020-11-19
 */
public class DExamPaperDaAn extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String paperId;

    /**
     * 题型 1单选题 2多选题 3判断题
     */
    @Excel(name = "题型 1单选题 2多选题 3判断题")
    private Long type;

    /**
     * 问题ID
     */
    @Excel(name = "问题ID")
    private String problemId;

    /**
     * 答案（多选逗号分割）
     */
    @Excel(name = "答案", readConverterExp = "多=选逗号分割")
    private String answer;


    public void setPaperId(String paperId)
    {
        this.paperId = paperId;
    }

    public String getPaperId()
    {
        return paperId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getType() {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("paperId", getPaperId())

                .append("problemId", getProblemId())

                .append("answer", getAnswer())
                .append("type", getType())

                .toString();
    }


}
