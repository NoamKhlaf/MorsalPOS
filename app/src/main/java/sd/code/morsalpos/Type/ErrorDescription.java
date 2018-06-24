package sd.code.morsalpos.Type;

/**
 * Created by alaa on 2/18/2018.
 */

public class ErrorDescription {

    int id;
    String errorField, errorCode, msgEn, msgAr, labelEn, labelAr;

    public ErrorDescription() {
    }

    public ErrorDescription(String errorField, String errorCode, String msgEn, String msgAr, String labelEn, String labelAr) {
        this.errorField = errorField;
        this.errorCode = errorCode;
        this.msgEn = msgEn;
        this.msgAr = msgAr;
        this.labelEn = labelEn;
        this.labelAr = labelAr;
    }

    public ErrorDescription(int id, String errorField, String errorCode, String msgEn, String msgAr, String labelEn, String labelAr) {
        this.id = id;
        this.errorField = errorField;
        this.errorCode = errorCode;
        this.msgEn = msgEn;
        this.msgAr = msgAr;
        this.labelEn = labelEn;
        this.labelAr = labelAr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getErrorField() {
        return errorField;
    }

    public void setErrorField(String errorField) {
        this.errorField = errorField;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsgEn() {
        return msgEn;
    }

    public void setMsgEn(String msgEn) {
        this.msgEn = msgEn;
    }

    public String getMsgAr() {
        return msgAr;
    }

    public void setMsgAr(String msgAr) {
        this.msgAr = msgAr;
    }

    public String getLabelEn() {
        return labelEn;
    }

    public void setLabelEn(String labelEn) {
        this.labelEn = labelEn;
    }

    public String getLabelAr() {
        return labelAr;
    }

    public void setLabelAr(String labelAr) {
        this.labelAr = labelAr;
    }
}
