package sd.code.morsalpos.Type;

/**
 * Created by alaa on 12/29/2017.
 */

public class PayeeInfo {


    int id;
    String payeeNumber,payeeName, payeeNameAr;
    int payeeType;

    public PayeeInfo(int id, String payeeNumber, String payeeName,String payeeNameAr, int payeeType) {
        this.id = id;
        this.payeeNumber = payeeNumber;
        this.payeeName = payeeName;
        this.payeeNameAr = payeeNameAr;
        this.payeeType = payeeType;
    }

    public PayeeInfo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayeeNumber() {
        return payeeNumber;
    }

    public void setPayeeNumber(String payeeNumber) {
        this.payeeNumber = payeeNumber;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public int getPayeeType() {
        return payeeType;
    }

    public void setPayeeType(int payeeType) {
        this.payeeType = payeeType;
    }

    public String getPayeeNameAr() {
        return payeeNameAr;
    }

    public void setPayeeNameAr(String payeeNameAr) {
        this.payeeNameAr = payeeNameAr;
    }
}
