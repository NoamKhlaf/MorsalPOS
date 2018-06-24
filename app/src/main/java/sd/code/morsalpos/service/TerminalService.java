package sd.code.morsalpos.service;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import sd.code.morsalpos.Type.PurchaseInfo;
import sd.code.morsalpos.Type.UserInfo;
import sd.code.morsalpos.common.CardInfo;
import sd.code.morsalpos.common.Config;
import sd.code.morsalpos.common.Security;


public class TerminalService {


    public String isAlive(String terminal_id) {

        String result = "";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        Date now = new Date();

        Map<String, String> postParam = new HashMap<>();
        postParam.put("terminalId", terminal_id);
        postParam.put("systemTraceAuditNumber", String.valueOf(Security.getSystemTraceAuditNumber()));
        postParam.put("tranDateTime", format.format(now));

        try {

            Disable.disableSslVerification();

            URL url = new URL(Config.API_URL + "isAlive/");
            HttpsURLConnection conn;
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("API-KEY", Config.API_KEY);
            conn.setConnectTimeout(20 * 1000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedReader reader;

            try {
                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(new JSONObject(postParam).toString());
                writer.flush();
                String line;

                if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK
                        || conn.getResponseCode() == HttpsURLConnection.HTTP_CREATED) {

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                while ((line = reader.readLine()) != null) {
                    result = result + line;
                }
                reader.close();
            } catch (Exception e) {
            }


            Log.e("isAlive result", result);

        } catch (Exception e) {
            Log.e("isAlive2", "Error in http connection login " + e.toString());
        }

        return result;
    }

    public String getWorkingKey(String terminalId) {

        String result = "";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        Date now = new Date();
        String uuid = UUID.randomUUID().toString();

        Map<String, String> postParam = new HashMap<>();
        postParam.put("systemTraceAuditNumber", String.valueOf(Security.getSystemTraceAuditNumber()));
        postParam.put("tranDateTime", format.format(now));
        postParam.put("UUID", uuid);
        postParam.put("terminalId", terminalId);


        try {

            Disable.disableSslVerification();

            URL url = new URL(Config.API_URL + "workingKey/");
            HttpsURLConnection conn;
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("API-KEY", Config.API_KEY);
            conn.setConnectTimeout(20 * 1000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedReader reader;

            try {
                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(new JSONObject(postParam).toString());
                writer.flush();
                String line;

                if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK
                        || conn.getResponseCode() == HttpsURLConnection.HTTP_CREATED) {

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                while ((line = reader.readLine()) != null) {
                    result = result + line;
                }
                reader.close();
            } catch (Exception ignored) {
            }


            Log.e("isAlive result", result);

        } catch (Exception e) {
            Log.e("isAlive2", "Error in http connection login " + e.toString());
        }

        return result;
    }

    public String purchase(PurchaseInfo purchaseInfo) {

        String result = "";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        Date now = new Date();
        String uuid = UUID.randomUUID().toString();

        Map<String, String> postParam = new HashMap<>();
        postParam.put("systemTraceAuditNumber", String.valueOf(Security.getSystemTraceAuditNumber()));
        postParam.put("tranDateTime", format.format(now));
        postParam.put("UUID", uuid);
        postParam.put("terminalId", purchaseInfo.getTerminalId());
        postParam.put("tranCurrencyCode", "SDG");
        postParam.put("PAN", purchaseInfo.getPAN());
        postParam.put("PIN", purchaseInfo.getPIN());

        postParam.put("tranAmount", purchaseInfo.getTranAmount());
        postParam.put("expDate", purchaseInfo.getExpDate());

        try {

            Disable.disableSslVerification();

            URL url = new URL(Config.API_URL + "transactions/purchase/");
            HttpsURLConnection conn;
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("API-KEY", Config.API_KEY);
            conn.setConnectTimeout(20 * 1000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedReader reader;

            try {
                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(new JSONObject(postParam).toString());
                writer.flush();
                String line;

                if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK
                        || conn.getResponseCode() == HttpsURLConnection.HTTP_CREATED) {

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                while ((line = reader.readLine()) != null) {
                    result = result + line;
                }
                reader.close();
            } catch (Exception e) {
            }


            Log.e("purchase result", result);

        } catch (Exception e) {
            Log.e("purchase", "Error in http connection login " + e.toString());
        }

        return result;
    }

    public String callUrl(String urlTrans, Map<String, String> params) {
        String TAG = "Call URL: "+urlTrans;
        StringBuilder result = new StringBuilder();
        try {
            Disable.disableSslVerification();
            URL url = new URL(urlTrans); // Config.API_URL+"transactions/purchase/"
            HttpsURLConnection conn;
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("API-KEY", Config.API_KEY);
            conn.setConnectTimeout(20 * 1000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            BufferedReader reader;
            try {
                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(new JSONObject(params).toString());
                writer.flush();
                String line;

                if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK || conn.getResponseCode() == HttpsURLConnection.HTTP_CREATED) {
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
                Log.d(TAG, "Response: "+result.toString());
            } catch (Exception e) {
                Log.e(TAG, "Exception, message: "+e.toString());
            }
            Log.e(TAG, result.toString());
        } catch (Exception e) {
            Log.e(TAG, "Error in http connection login " + e.toString());
        }
        return result.toString();
    }

    public String purchaseCashBack(PurchaseInfo purchaseInfo) {

        String result = "";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        Date now = new Date();

        Map<String, String> postParam = new HashMap<>();
        postParam.put("systemTraceAuditNumber", String.valueOf(Security.getSystemTraceAuditNumber()));
        postParam.put("tranDateTime", format.format(now));
//        postParam.put("UUID", uuid);
        postParam.put("terminalId", purchaseInfo.getTerminalId());
        postParam.put("tranCurrencyCode", "SDG");
        postParam.put("PAN", purchaseInfo.getPAN());
        postParam.put("PIN", purchaseInfo.getPIN());

        postParam.put("tranAmount", purchaseInfo.getTranAmount());
        postParam.put("expDate", purchaseInfo.getExpDate());

        try {

            Disable.disableSslVerification();

            URL url = new URL(Config.API_URL + "transactions/purchase/");
            HttpsURLConnection conn;
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("API-KEY", Config.API_KEY);
            conn.setConnectTimeout(20 * 1000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedReader reader;

            try {
                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(new JSONObject(postParam).toString());
                writer.flush();
                String line;

                if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK
                        || conn.getResponseCode() == HttpsURLConnection.HTTP_CREATED) {

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                while ((line = reader.readLine()) != null) {
                    result = result + line;
                }
                reader.close();
            } catch (Exception e) {
            }


            Log.e("purchase result", result);

        } catch (Exception e) {
            Log.e("purchase", "Error in http connection login " + e.toString());
        }

        return result;
    }


    public String checkBalance(String accessToken, String publicKey, CardInfo card) {


        String result = "";

        String uuid = UUID.randomUUID().toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        Date now = new Date();

        String IPIN = Security.getAuthorization(publicKey, card.getiPin(), uuid);

        Log.e("token", accessToken);
        Log.e("IPIN", IPIN);


        // INIT data to send by request
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("UUID", "" + uuid + ""));
        nameValuePairs.add(new BasicNameValuePair("tranDateTime", "" + format.format(now) + ""));
        nameValuePairs.add(new BasicNameValuePair("PAN", "" + card.getPan() + ""));
        nameValuePairs.add(new BasicNameValuePair("expDate", "" + card.getExpDate() + ""));
        nameValuePairs.add(new BasicNameValuePair("tranDateTime", "" + format.format(now) + ""));
        nameValuePairs.add(new BasicNameValuePair("IPIN", "" + IPIN + ""));

        Log.e("tranDateTime result", format.format(now));

        try {

            Disable.disableSSLCertificateChecking();
            // INIT my HTTP Client
            HttpClient httpclient = new DefaultHttpClient();

            // HTTP Post URL request
            HttpPost httppost = new HttpPost(Config.API_URL + "cards/balance/");

            // send nameValue Per data to PHP file by HTTP post
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            httppost.setHeader("API-KEY", "" + Config.API_KEY + "");
            httppost.setHeader("Authorization", "" + "access_token " + accessToken + "");

            // execute HTTP  request and get response
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            // convert it to input stream
            InputStream is = entity.getContent();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();

                result = sb.toString();
                Log.e("checkBalance result", result);

            } catch (Exception e) {
                Log.e("checkBalance", "Error converting result " + e.toString());
            }
        } catch (Exception e) {
            Log.e("checkBalance", "Error in http connection login " + e.toString());
        }


        return result;
    }


    public String payment(String accessToken, String publicKey, CardInfo card, String payeeId, String paymentInfo, Double tranAmount) {


        String result = "";

        String uuid = UUID.randomUUID().toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        Date now = new Date();

        String IPIN = Security.getAuthorization(publicKey, card.getiPin(), uuid);


        // INIT data to send by request
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("UUID", "" + uuid + ""));
        nameValuePairs.add(new BasicNameValuePair("tranDateTime", "" + format.format(now) + ""));
        nameValuePairs.add(new BasicNameValuePair("PAN", "" + card.getPan() + ""));
        nameValuePairs.add(new BasicNameValuePair("expDate", "" + card.getExpDate() + ""));
        nameValuePairs.add(new BasicNameValuePair("IPIN", "" + IPIN + ""));
        nameValuePairs.add(new BasicNameValuePair("payeeId", "" + payeeId + ""));
        nameValuePairs.add(new BasicNameValuePair("paymentInfo", "" + paymentInfo + ""));
        nameValuePairs.add(new BasicNameValuePair("tranAmount", "" + tranAmount + ""));
        nameValuePairs.add(new BasicNameValuePair("tranCurrency", "SDG"));


        try {
            Disable.disableSSLCertificateChecking();
            // INIT my HTTP Client
            HttpClient httpclient = new DefaultHttpClient();

            // HTTP Post URL request
            HttpPost httppost = new HttpPost(Config.API_URL + "transactions/payment/");

            // send nameValue Per data to PHP file by HTTP post
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            httppost.setHeader("API-KEY", "" + Config.API_KEY + "");
            httppost.setHeader("Authorization", "" + "access_token " + accessToken + "");

            // execute HTTP  request and get response
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            // convert it to input stream
            InputStream is = entity.getContent();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();

                result = sb.toString();
                Log.e("payment result", result);

            } catch (Exception e) {
                Log.e("payment", "Error converting result " + e.toString());
            }

        } catch (Exception e) {
            Log.e("payment", "Error in http connection login " + e.toString());
        }


        return result;
    }


    public String billInquiry(String accessToken, String publicKey, CardInfo card, String payeeId, String paymentInfo) {


        String result = "";

        String uuid = UUID.randomUUID().toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        Date now = new Date();

        String IPIN = Security.getAuthorization(publicKey, card.getiPin(), uuid);


        // INIT data to send by request
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("UUID", "" + uuid + ""));
        nameValuePairs.add(new BasicNameValuePair("tranDateTime", "" + format.format(now) + ""));
        nameValuePairs.add(new BasicNameValuePair("PAN", "" + card.getPan() + ""));
        nameValuePairs.add(new BasicNameValuePair("expDate", "" + card.getExpDate() + ""));
        nameValuePairs.add(new BasicNameValuePair("IPIN", "" + IPIN + ""));
        nameValuePairs.add(new BasicNameValuePair("payeeId", "" + payeeId + ""));
        nameValuePairs.add(new BasicNameValuePair("paymentInfo", "" + paymentInfo + ""));

        try {
            Disable.disableSSLCertificateChecking();
            // INIT my HTTP Client
            HttpClient httpclient = new DefaultHttpClient();

            // HTTP Post URL request
            HttpPost httppost = new HttpPost(Config.API_URL + "transactions/bill/");

            // send nameValue Per data to PHP file by HTTP post
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            httppost.setHeader("API-KEY", "" + Config.API_KEY + "");
            httppost.setHeader("Authorization", "" + "access_token " + accessToken + "");

            // execute HTTP  request and get response
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            // convert it to input stream
            InputStream is = entity.getContent();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();

                result = sb.toString();
                Log.e("billInquiry result", result);

            } catch (Exception e) {
                Log.e("billInquiry", "Error converting result " + e.toString());
            }

        } catch (Exception e) {
            Log.e("billInquiry", "Error in http connection login " + e.toString());
        }


        return result;
    }


    public String transfer(String accessToken, String publicKey, CardInfo card, String toCard, Double tranAmount) {


        String result = "";

        String uuid = UUID.randomUUID().toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        Date now = new Date();

        String IPIN = Security.getAuthorization(publicKey, card.getiPin(), uuid);


        // INIT data to send by request
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("UUID", "" + uuid + ""));
        nameValuePairs.add(new BasicNameValuePair("tranDateTime", "" + format.format(now) + ""));
        nameValuePairs.add(new BasicNameValuePair("PAN", "" + card.getPan() + ""));
        nameValuePairs.add(new BasicNameValuePair("expDate", "" + card.getExpDate() + ""));
        nameValuePairs.add(new BasicNameValuePair("IPIN", "" + IPIN + ""));
        nameValuePairs.add(new BasicNameValuePair("toCard", "" + toCard + ""));
        nameValuePairs.add(new BasicNameValuePair("tranAmount", "" + tranAmount + ""));
        nameValuePairs.add(new BasicNameValuePair("tranCurrency", "SDG"));


        try {
            Disable.disableSSLCertificateChecking();
            // INIT my HTTP Client
            HttpClient httpclient = new DefaultHttpClient();

            // HTTP Post URL request
            HttpPost httppost = new HttpPost(Config.API_URL + "transactions/transfer/");

            // send nameValue Per data to PHP file by HTTP post
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            httppost.setHeader("API-KEY", "" + Config.API_KEY + "");
            httppost.setHeader("Authorization", "" + "access_token " + accessToken + "");

            // execute HTTP  request and get response
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            // convert it to input stream
            InputStream is = entity.getContent();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();

                result = sb.toString();
                Log.e("payment result", result);

            } catch (Exception e) {
                Log.e("payment", "Error converting result " + e.toString());
            }

        } catch (Exception e) {
            Log.e("payment", "Error in http connection login " + e.toString());
        }


        return result;
    }


    public String changeIPIN(String accessToken, String publicKey, CardInfo card, String newPin) {


        String result = "";

        String uuid = UUID.randomUUID().toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        Date now = new Date();

        String IPIN = Security.getAuthorization(publicKey, card.getiPin(), uuid);
        newPin = Security.getAuthorization(publicKey, newPin, uuid);


        // INIT data to send by request
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("UUID", "" + uuid + ""));
        nameValuePairs.add(new BasicNameValuePair("tranDateTime", "" + format.format(now) + ""));
        nameValuePairs.add(new BasicNameValuePair("PAN", "" + card.getPan() + ""));
        nameValuePairs.add(new BasicNameValuePair("expDate", "" + card.getExpDate() + ""));
        nameValuePairs.add(new BasicNameValuePair("IPIN", "" + IPIN + ""));
        nameValuePairs.add(new BasicNameValuePair("newIPIN", "" + newPin + ""));

        try {
            Disable.disableSSLCertificateChecking();
            // INIT my HTTP Client
            HttpClient httpclient = new DefaultHttpClient();

            // HTTP Post URL request
            HttpPost httppost = new HttpPost(Config.API_URL + "cards/changeIPin/");

            // send nameValue Per data to PHP file by HTTP post
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            httppost.setHeader("API-KEY", "" + Config.API_KEY + "");
            httppost.setHeader("Authorization", "" + "access_token " + accessToken + "");

            // execute HTTP  request and get response
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            // convert it to input stream
            InputStream is = entity.getContent();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();

                result = sb.toString();
                Log.e("changeIPIN result", result);

            } catch (Exception e) {
                Log.e("changeIPIN", "Error converting result " + e.toString());
            }

        } catch (Exception e) {
            Log.e("changeIPIN", "Error in http connection login " + e.toString());
        }


        return result;
    }

    public boolean reportCardAsLost(String accessToken, String pan) {


        boolean result = false;


        // INIT data to send by request
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("pan", "" + pan + ""));


        try {
            Disable.disableSSLCertificateChecking();
            // INIT my HTTP Client
            HttpClient httpclient = new DefaultHttpClient();

            // HTTP Post URL request
            HttpPost httppost = new HttpPost(Config.API_URL + "cards/report_as_lost/");

            // send nameValue Per data to PHP file by HTTP post
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            httppost.setHeader("API-KEY", "" + Config.API_KEY + "");
            httppost.setHeader("Authorization", "" + "access_token " + accessToken + "");

            // execute HTTP  request and get response
            HttpResponse response = httpclient.execute(httppost);

            Log.e("reportCardAsLost result", String.valueOf(response.getStatusLine().getStatusCode()));


            if (response.getStatusLine().getStatusCode() == HttpsURLConnection.HTTP_OK) {

                result = true;

            }

        } catch (Exception e) {
            Log.e("reportCardAsLost", "Error in http connection login " + e.toString());
        }


        return result;
    }


    public String generateVoucher(String accessToken, String publicKey, CardInfo card, String voucherNumber, Double tranAmount) {


        String result = "";

        String uuid = UUID.randomUUID().toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        Date now = new Date();

        String IPIN = Security.getAuthorization(publicKey, card.getiPin(), uuid);


        // INIT data to send by request
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("UUID", "" + uuid + ""));
        nameValuePairs.add(new BasicNameValuePair("tranDateTime", "" + format.format(now) + ""));
        nameValuePairs.add(new BasicNameValuePair("PAN", "" + card.getPan() + ""));
        nameValuePairs.add(new BasicNameValuePair("expDate", "" + card.getExpDate() + ""));
        nameValuePairs.add(new BasicNameValuePair("IPIN", "" + IPIN + ""));
        nameValuePairs.add(new BasicNameValuePair("voucherNumber", "" + voucherNumber + ""));
        nameValuePairs.add(new BasicNameValuePair("tranAmount", "" + tranAmount + ""));
        nameValuePairs.add(new BasicNameValuePair("tranCurrency", "SDG"));


        try {
            Disable.disableSSLCertificateChecking();
            // INIT my HTTP Client
            HttpClient httpclient = new DefaultHttpClient();

            // HTTP Post URL request
            HttpPost httppost = new HttpPost(Config.API_URL + "transactions/generateVoucher/");

            // send nameValue Per data to PHP file by HTTP post
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            httppost.setHeader("API-KEY", "" + Config.API_KEY + "");
            httppost.setHeader("Authorization", "" + "access_token " + accessToken + "");

            // execute HTTP  request and get response
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            // convert it to input stream
            InputStream is = entity.getContent();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();

                result = sb.toString();
                Log.e("generateVoucher result", result);

            } catch (Exception e) {
                Log.e("generateVoucher", "Error converting result " + e.toString());
            }

        } catch (Exception e) {
            Log.e("generateVoucher", "Error in http connection login " + e.toString());
        }


        return result;
    }


}
