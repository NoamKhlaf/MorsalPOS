package sd.code.morsalpos.common;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mustafa on 18/05/2018.
 */

public class Common {
    public static String formatDate(String sourceDateStr, String currentFormat, String targerFormat) {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat(currentFormat);
        Date sourceDate = new Date(System.currentTimeMillis());
        try {
            sourceDate = dateFormat.parse(sourceDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat dateFormatx = new SimpleDateFormat(targerFormat);
        return dateFormatx.format(sourceDate);
    }

    public static Map<String, String> formatAdditionalData(String additionalData){
        Map<String, String> map = new HashMap<>();
        String[] array = additionalData.split(";");
        for (String anArray : array) {
            String[] innerArray = anArray.split("=");
            map.put(innerArray[0], innerArray[1]);
        }
        return map;
    }

    public static String tokenizeExpDate(String data){
        String expiryDateX = "" ;
        try {
            int index = data.indexOf("^", 25)+1;
            expiryDateX = data.substring(index, index+4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expiryDateX;
    }
    public static String tokenizeCardHolderName(String data){
        String name = "" ;
        try {
            int index = data.indexOf("^")+1;
            name = data.substring(index, data.indexOf("^", index));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

}
