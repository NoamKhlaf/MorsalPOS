package sd.code.morsalpos.common;

/**
 * Created by alaa on 12/4/2017.
 */

public class TransactionInfo {

    String pan,date,transName,amount ;

    public TransactionInfo(String pan, String date, String transName, String amount) {
        this.pan = pan;
        this.date = date;
        this.transName = transName;
        this.amount = amount;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
