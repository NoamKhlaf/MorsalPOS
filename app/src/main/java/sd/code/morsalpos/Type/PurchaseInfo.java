package sd.code.morsalpos.Type;

/**
 * Created by alaa on 5/7/2018.
 */

public class PurchaseInfo {

    String PAN, PIN, tranAmount, tranCurrencyCode, terminalId,expDate ;

    public PurchaseInfo() {
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(String tranAmount) {
        this.tranAmount = tranAmount;
    }

    public String getTranCurrencyCode() {
        return tranCurrencyCode;
    }

    public void setTranCurrencyCode(String tranCurrencyCode) {
        this.tranCurrencyCode = tranCurrencyCode;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
}
