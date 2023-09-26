package com.voice.changer.speechshift.langClass;

public class LanguageModel {
    private String code;
    private boolean isCheck;
    private String name;
    private String strLang;
    private int flags;

    public LanguageModel(String str, String str2, String strLang, int flags) {
        this.name = str;
        this.flags = flags;
        this.strLang = strLang;
        this.code = str2;
    }

    public String getStrLang() {
        return strLang;
    }

    public void setStrLang(String strLang) {
        this.strLang = strLang;
    }

    public boolean isCheck() {
        return this.isCheck;
    }

    public void setCheck(boolean z) {
        this.isCheck = z;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }
}
