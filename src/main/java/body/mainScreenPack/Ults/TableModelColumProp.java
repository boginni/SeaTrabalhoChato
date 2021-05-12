package body.mainScreenPack.Ults;

public class TableModelColumProp {

    String var_newLine;
    String var_tab;
    String var_empty;

    boolean sbr_overrideNewLine = false,
            sbr_overrideTab = false,
            sbr_overrideEmpty = false,
            sbr_overrideUTF8 = false;

    int var_caseType;

    private static final int
            CASE_DEFAULT = 0,
            CASE_UPPER = 1,
            CASE_LOWER = 2;


    public void RezetValues(){
        var_newLine = "";
        var_tab = "";
        var_empty = "";
    }

    public String getValue(String originalValue){
        if(sbr_overrideNewLine){
            originalValue = originalValue.replaceAll("\n", var_newLine);
        }

        if(sbr_overrideTab){
            originalValue = originalValue.replaceAll("\t", var_tab);
        }

        if(sbr_overrideEmpty && originalValue.isEmpty()){
            originalValue = var_empty;
        }

        if(sbr_overrideUTF8){
            //originalValue =
        }

        switch (var_caseType){
            case CASE_DEFAULT:
                break;
            case CASE_UPPER:
                originalValue = originalValue.toUpperCase();
                break;
            case CASE_LOWER:
                originalValue = originalValue.toLowerCase();
                break;
        }

        return originalValue;
    }

    public String getVar_newLine() {
        return var_newLine;
    }

    public void setVar_newLine(String var_newLine) {
        this.var_newLine = var_newLine;
    }

    public String getVar_tab() {
        return var_tab;
    }

    public void setVar_tab(String var_tab) {
        this.var_tab = var_tab;
    }

    public String getVar_empty() {
        return var_empty;
    }

    public void setVar_empty(String var_empty) {
        this.var_empty = var_empty;
    }

    public boolean isSbr_overrideNewLine() {
        return sbr_overrideNewLine;
    }

    public void setSbr_overrideNewLine(boolean sbr_overrideNewLine) {
        this.sbr_overrideNewLine = sbr_overrideNewLine;
    }

    public boolean isSbr_overrideTab() {
        return sbr_overrideTab;
    }

    public void setSbr_overrideTab(boolean sbr_overrideTab) {
        this.sbr_overrideTab = sbr_overrideTab;
    }

    public boolean isSbr_overrideEmpty() {
        return sbr_overrideEmpty;
    }

    public void setSbr_overrideEmpty(boolean sbr_overrideEmpty) {
        this.sbr_overrideEmpty = sbr_overrideEmpty;
    }

    public boolean isSbr_overrideUTF8() {
        return sbr_overrideUTF8;
    }

    public void setSbr_overrideUTF8(boolean sbr_overrideUTF8) {
        this.sbr_overrideUTF8 = sbr_overrideUTF8;
    }

    public int getVar_caseType() {
        return var_caseType;
    }

    public void setVar_caseType(int var_caseType) {
        this.var_caseType = var_caseType;
    }
}
