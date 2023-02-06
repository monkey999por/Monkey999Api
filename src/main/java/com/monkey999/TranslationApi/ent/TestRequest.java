package com.monkey999.TranslationApi.ent;

public class TestRequest  extends  Certification{

    private String text;
    private String source;


    public TranslateReq translateReq;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    @Override
    public String toString() {
        return "text: " + this.getText() + Const.br +
                "source: "  + this.getSource()+ Const.br +
                "translateReq.source: "  + this.translateReq.source + Const.br +
                "translateReq.text: "  + this.translateReq.text + Const.br +
                "translateReq.target: "  + this.translateReq.target + Const.br +
                "super.apiKey: " + super.apiKey;
    }
}