package com.jh.power;

public class AdmittanceExamModel {
    private String examNo;
    private String examText;
    private String examOption;
    private String examAnswer;

    public String getExamNo() {
        return examNo;
    }

    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }

    public String getExamText() {
        return examText;
    }

    public void setExamText(String examText) {
        this.examText = examText;
    }

    public String getExamOption() {
        return examOption;
    }

    public void setExamOption(String examOption) {
        this.examOption = examOption;
    }

    public String getExamAnswer() {
        return examAnswer;
    }

    public void setExamAnswer(String examAnswer) {
        this.examAnswer = examAnswer;
    }

    @Override
    public String toString() {
        return "AdmittanceExamModel{" +
                "examNo='" + examNo + '\'' +
                ", examText='" + examText + '\'' +
                ", examOption='" + examOption + '\'' +
                ", examAnswer='" + examAnswer + '\'' +
                '}';
    }
}
