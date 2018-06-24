package sd.code.morsalpos.common;


public class Config {

    public static String API_URL = "https://212.0.129.118/terminal_api/";

    public static String API_KEY = "5d6f54d4-3af4-4ffc-b78d-c2f1ca7827d9";
    public static String API_EXTRA_URL = "https://212.0.129.118/terminal_api/";

    public static String TMK = "ABCDEF0123456789";

    public static final String URL_CASH_BACK = Config.API_URL + "transactions/purchaseWithCashBack/";
    public static final String URL_BALANCE = Config.API_URL + "transactions/balance/";
    public static final String URL_CHANGE_PIN = Config.API_URL + "transactions/changePin/";
    public static final String URL_TOP_UP = Config.API_URL + "transactions/topUp/";
    public static final String URL_TRANSFARE_CARD = Config.API_URL + "transactions/cardTransfer/";
    public static final String URL_TRANSFARE_ACCOUNT = Config.API_URL + "transactions/accountTransfer/";

    public static final String URL_BILL_PAYMENT = Config.API_URL + "transactions/billPayment/";
    public static final String URL_BILL_INQUIRY = Config.API_URL + "transactions/billInquiry/";

    public static final String URL_REFUND = Config.API_URL + "transactions/refund/";
    public static final String URL_REVERSE = Config.API_URL + "transactions/reverse/";
    public static final String URL_MINI_STATMENT = Config.API_URL + "transactions/miniStatement/";

    public static final String URL_GENERATE_VOUCHER = Config.API_URL + "transactions/generateVoucher/";
    public static final String URL_CASH_OUT_VOUCHER = Config.API_URL + "transactions/cashOutVoucher/";

    public static final String DEFAULT_NAME = "";
}
