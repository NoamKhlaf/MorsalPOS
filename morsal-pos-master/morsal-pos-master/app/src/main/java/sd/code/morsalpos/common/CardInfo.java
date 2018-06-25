package sd.code.morsalpos.common;

/**
 * Created by alaa on 12/4/2017.
 */

public class CardInfo {

    public CardInfo() {
    }

    public CardInfo(String name, String pan, String expDate, int defaultFlag) {
        this.name = name;
        this.pan = pan;
        this.expDate = expDate;
        this.defaultFlag = defaultFlag ;
    }

    public CardInfo(int id, String name, String pan, String expDate, int defaultFlag) {
        this.id = id ;
        this.name = name;
        this.pan = pan;
        this.expDate = expDate;
        this.defaultFlag = defaultFlag;

    }

    int id ,defaultFlag;
    String name,pan,expDate ;
    String iPin;

    public String getiPin() {
        return iPin;
    }

    public void setiPin(String iPin) {
        this.iPin = iPin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getExpDate() {
        return expDate;
    }

    public int getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(int defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }


}
