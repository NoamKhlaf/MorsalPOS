package sd.code.morsalpos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sd.code.morsalpos.Type.ErrorDescription;

/**
 * Created by alaa on 12/29/2017.
 */

public class ErrorCodeDataSource {

      DatabaseHandler sqLiteOpenHelper ;

    public ErrorCodeDataSource(Context context){

        sqLiteOpenHelper = new DatabaseHandler(context);

    }

    public long addErrorDescription(ErrorDescription info) {


        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.ERROR_FIELD, info.getErrorField());
        values.put(DatabaseHandler.ERROR_CODE, info.getErrorCode());
        values.put(DatabaseHandler.ERROR_MSG_EN, info.getMsgEn());
        values.put(DatabaseHandler.ERROR_MSG_AR, info.getMsgAr());
        values.put(DatabaseHandler.ERROR_LABEL_EN, info.getLabelEn());
        values.put(DatabaseHandler.ERROR_LABEL_AR, info.getLabelAr());

        // Inserting Row
        long flag = db.insert(DatabaseHandler.TABLE_ERROR_DESCRIPTION, null, values);
        db.close(); // Closing database connection

        return  flag;
    }

    public void addErrorDescriptions() {

        List<ErrorDescription> errors = new ArrayList<>();

        errors.add(new ErrorDescription("mobile_number","required","This field is required.","هذا الحقل مطلوب","Mobile Number","رقم الهاتف"));
        errors.add(new ErrorDescription("mobile_number","blank","This field may not be blank.","هذا الحقل لا يجب ان يكون فارغ","Mobile Number","رقم الهاتف"));
        errors.add(new ErrorDescription("mobile_number","invalid","The mobile number should have 10 digits and start with 09 or 01","رقم الهاتف خطأ","Mobile Number","رقم الهاتف"));
        errors.add(new ErrorDescription("password","required","This field is required.","هذا الحقل مطلوب","Password","كلمة المرور"));
        errors.add(new ErrorDescription("password","blank","This field may not be blank.","هذا الحقل لا يجب ان يكون فارغ","Password","كلمة المرور"));
        errors.add(new ErrorDescription("non_field_errors","invalid_credentials","Unable to log in with provided credentials.","خطاء في بيانات الدخول"," الرجاء المحاولة مره اخرى"," "));
        errors.add(new ErrorDescription("non_field_errors","inactive_user","User account is not active.","هذا الحساب مغلق حاليا"," "," "));
        errors.add(new ErrorDescription("password1","required","This field is required.","هذا الحقل مطلوب","Password","كلمة المرور"));
        errors.add(new ErrorDescription("password1","blank","This field may not be blank.","هذا الحقل لا يجب ان يكون فارغ","Password","كلمة المرور"));
        errors.add(new ErrorDescription("password1","password_too_short","This password is too short. It must contain at least 8 characters.","كلمة المرور ضعيفة ","Password","كلمة المرور"));
        errors.add(new ErrorDescription("password1","password_no_upper","The password must include at least one uppercase letter","كلمة المرور ضعيفة ","Password","كلمة المرور"));
        errors.add(new ErrorDescription("password1","password_no_number","The password must include at least number","كلمة المرور ضعيفة ","Password","كلمة المرور"));
        errors.add(new ErrorDescription("password1","password_no_symbol","The password must include at least one symbol","كلمة المرور ضعيفة ","Password","كلمة المرور"));
        errors.add(new ErrorDescription("password1","password_entirely_numeric","This password is entirely numeric.","كلمة المرور ضعيفة ","Password","كلمة المرور"));
        errors.add(new ErrorDescription("password1","password_too_common","This password is too common.","كلمة المرور ضعيفة ","Password","كلمة المرور"));
        errors.add(new ErrorDescription("full_name","required","This field is required.","هذا الحقل مطلوب","Full Name","الاسم الكامل"));
        errors.add(new ErrorDescription("full_name","blank","This field may not be blank.","هذا الحقل لا يجب ان يكون فارغ","Full Name","الاسم الكامل"));
        errors.add(new ErrorDescription("full_name","max_length","Ensure this field has no more than 100 characters.","هذا الحقل لايزيد عن 100 حرف","Full Name","الاسم الكامل"));
        errors.add(new ErrorDescription(" email","invalid","Enter a valid email address.","ادخل بريد الكتروني صحيح","Email","البريد الاكتروني"));
        errors.add(new ErrorDescription("gender","invalid_choice","xxx is not a valid choice.","هذا الاختيار خاطئ","Gender","النوع"));
        errors.add(new ErrorDescription("date_of_birth","invalid","Date has wrong format. Use one of these formats instead: YYYY[- MM[-DD]]","تاريخ ميلاد خاطئ","Birth Date","تاريخ الميلاد"));
        errors.add(new ErrorDescription("address","max_length","Ensure this field has no more than 255 characters.","هذا الحقل لا يزيد عن 255 حرف","Address","العنوان"));
        errors.add(new ErrorDescription("state","max_length","Ensure this field has no more than 100 characters.","هذا الحقل لا يزيد عن 100 حرف","State","الولاية"));
        errors.add(new ErrorDescription("city","max_length","Ensure this field has no more than 100 characters.","هذا الحقل لا يزيد عن 100 حرف","City","المدينة"));
        errors.add(new ErrorDescription("pan","required","This field is required.","هذا الحقل مطلوب","Card No","رقم البطاقة"));
        errors.add(new ErrorDescription("pan","blank","This field may not be blank.","هذا الحقل لا يجب ان يكون فارغ","Card No","رقم البطاقة"));
        errors.add(new ErrorDescription("pan","card_not_found","Card is not found","البطاقة غير موجودة","Card No","رقم البطاقة"));
        errors.add(new ErrorDescription("pan","invalid","PAN should have at least 16 but not more than 19 digits","رقم البطاقة يجب ان يتكون  من 16 خانة او 19 خانة","Card No","رقم البطاقة"));
        errors.add(new ErrorDescription("PAN","required","This field is required.","هذا الحقل مطلوب","Card No","رقم البطاقة"));
        errors.add(new ErrorDescription("PAN","blank","This field may not be blank.","هذا الحقل لا يجب ان يكون فارغ","Card No","رقم البطاقة"));
        errors.add(new ErrorDescription("PAN","invalid","PAN should have at least 16 but not more than 19 digits","رقم البطاقة يجب ان يتكون  من 16 خانة او 19 خانة","Card No","رقم البطاقة"));
        errors.add(new ErrorDescription("PAN","card_not_found","Not a valid Morsal Card.","هذه البطاقة غير تابعة لمرسال","Card No","رقم البطاقة"));
        errors.add(new ErrorDescription("PAN","not_found","Not a valid Morsal Card.","هذه البطاقة غير تابعة لمرسال","Card No","رقم البطاقة"));
        errors.add(new ErrorDescription("PAN","lost_card","Morsal Card has been flagged as lost.","هذه البطاقة مسروقة","Card No","رقم البطاقة"));
        errors.add(new ErrorDescription("PAN","suspended_card","Morsal Card has been suspended.","هذه البطاقة معطلة","Card No","رقم البطاقة"));
        errors.add(new ErrorDescription("IPIN","required","This field is required.","هذا الحقل مطلوب","IPIN","الرقم السري"));
        errors.add(new ErrorDescription("IPIN","blank","This field may not be blank.","هذا الحقل لا يجب ان يكون فارغ","IPIN","الرقم السري"));
        errors.add(new ErrorDescription("IPIN","max_length","Ensure this field has no more than 4 characters.","هذا الحقل يجب ان لا يزيد عن 4 احرف","IPIN","الرقم السري"));
        errors.add(new ErrorDescription("IPIN","min_length","Ensure this field has at least 4 characters.","هذا الحقل يجب ان لا يقل عن 4","IPIN","الرقم السري"));
        errors.add(new ErrorDescription("newIPin","required","This field is required.","هذا الحقل مطلوب","New IPIN","الرقم السري الجديد"));
        errors.add(new ErrorDescription("newIPin","blank","This field may not be blank.","هذا الحقل لا يجب ان يكون فارغ","New IPIN","الرقم السري الجديد"));
        errors.add(new ErrorDescription("newIPin","max_length","Ensure this field has no more than 4 characters.","هذا الحقل يجب ان لا يزيد عن 4 احرف","New IPIN","الرقم السري الجديد"));
        errors.add(new ErrorDescription("newIPin","min_length","Ensure this field has at least 4 characters.","هذا الحقل يجب ان لا يقل عن 4","New IPIN","الرقم السري الجديد"));
        errors.add(new ErrorDescription("toCard","required","This field is required.","هذا الحقل مطلوب","To Card","البطاقة المرسل اليها"));
        errors.add(new ErrorDescription("toCard","blank","This field may not be blank.","هذا الحقل لا يجب ان يكون فارغ","To Card","البطاقة المرسل اليها"));
        errors.add(new ErrorDescription("toCard","invalid","PAN should have at least 16 but not more than 19 digits","رقم البطاقة يجب ان يتكون  من 16 خانة او 19 خانة","To Card","البطاقة المرسل اليها"));
        errors.add(new ErrorDescription("toCard","card_not_found","Not a valid Morsal Card.","هذه البطاقة غير تابعة لمرسال","To Card","البطاقة المرسل اليها"));
        errors.add(new ErrorDescription("toCard","lost_card","Morsal Card has been flagged as lost.","هذه البطاقة مسروقة","To Card","البطاقة المرسل اليها"));
        errors.add(new ErrorDescription("toCard","suspended_card","Morsal Card has been suspended.","هذه البطاقة معطلة","To Card","البطاقة المرسل اليها"));
        errors.add(new ErrorDescription("tranAmount","required","This field is required.","هذا الحقل مطلوب","Amount","المبلغ"));
        errors.add(new ErrorDescription("tranAmount","null","This field may not be null.","هذا الحقل لايمكن ان يكون فارغ","Amount","المبلغ"));
        errors.add(new ErrorDescription("tranAmount","invalid_amount","Amount should be multiple of 10.","المبلغ يجب ان يكون من مضاعفات العشرة","Amount","المبلغ"));
        errors.add(new ErrorDescription("tranAmount","blank","A valid number is required.","المبلغ غير صحيح","Amount","المبلغ"));
        errors.add(new ErrorDescription("tranAmount","invalid","A valid number is required.","المبلغ غير صحيح","Amount","المبلغ"));
        errors.add(new ErrorDescription("tranAmount","min_value","Ensure this value is greater than or equal to 0.01.","يجب ان يكون القيمة اكبر او تساوي 0.01","Amount","المبلغ"));
        errors.add(new ErrorDescription("tranAmount","max_digits","Ensure that there are no more than 10 digits in total.","يجب ان لا تزيد الخانات عن 10","Amount","المبلغ"));
        errors.add(new ErrorDescription("tranCurrency","null","This field may not be null.","هذا الحقل لايمكن ان يكون فارغ","Curruncy","العملة"));
        errors.add(new ErrorDescription("tranCurrency","blank","This field may not be blank.","هذا الحقل لايمكن ان يكون فارغ","Curruncy","العملة"));
        errors.add(new ErrorDescription("tranCurrency","invalid_choice","value is not a valid choice.","القيمة المختارة غير صحيحة","Curruncy","العملة"));
        errors.add(new ErrorDescription("serviceProviderId","null","This field may not be null.","هذا الحقل لايمكن ان يكون فارغ","Service Provider ID","رمز مقدم الخدمة"));
        errors.add(new ErrorDescription("serviceProviderId","blank","This field may not be blank.","هذا الحقل لايمكن ان يكون فارغ","Service Provider ID","رمز مقدم الخدمة"));
        errors.add(new ErrorDescription("serviceProviderId","required","This field is required.","هذا الحقل مطلوب","Service Provider ID","رمز مقدم الخدمة"));
        errors.add(new ErrorDescription("serviceProviderId","max_length","Ensure this field has no more than 10 characters.","هذا الحقل لايزيد عن 10 احرف","Service Provider ID","رمز مقدم الخدمة"));
        errors.add(new ErrorDescription("serviceProviderId","min_length","Ensure this field has at least 10 characters.","هذا الحقل لايقل عن 10 احرف","Service Provider ID","رمز مقدم الخدمة"));
        errors.add(new ErrorDescription("serviceInfo","blank","This field may not be blank.","هذا الحقل لايمكن ان يكون فارغ","Service Info","معلومات الخدمة"));
        errors.add(new ErrorDescription("serviceInfo","max_length","Ensure this field has no more than 100 characters.","هذا الحقل لايزيد عن 100 حرف","Service Info","معلومات الخدمة"));
        errors.add(new ErrorDescription("voucherNumber","null","This field may not be null.","هذا الحقل لايمكن ان يكون فارغ","Voucher Number","رقم القسيمة"));
        errors.add(new ErrorDescription("voucherNumber","blank","This field may not be blank.","هذا الحقل لايمكن ان يكون فارغ","Voucher Number","رقم القسيمة"));
        errors.add(new ErrorDescription("voucherNumber","invalid","Voucher Number should have at least 10 but not more than 20 digits.","رقم القسيمة لا يقل عن 10 و لا يزيد عن 20","Voucher Number","رقم القسيمة"));
        errors.add(new ErrorDescription("payeeId","null","This field may not be null.","هذا الحقل لايمكن ان يكون فارغ","Payee ID","رمز المدفوع له"));
        errors.add(new ErrorDescription("payeeId","blank","This field may not be blank.","هذا الحقل لايمكن ان يكون فارغ","Payee ID","رمز المدفوع له"));
        errors.add(new ErrorDescription("payeeId","invalid","This value does not match the required pattern.","القيمة لا تطابق الشكل المطلوب","Payee ID","رمز المدفوع له"));
        errors.add(new ErrorDescription("payeeId","max_length","Ensure this field has no more than 10 characters.","هذا الحقل لا يزيد عن 10 احرف","Payee ID","رمز المدفوع له"));
        errors.add(new ErrorDescription("payeeId","min_length","Ensure this field has at least 10 characters.","هذا الحقل لا يقل عن 10 احرف","Payee ID","رمز المدفوع له"));
        errors.add(new ErrorDescription("paymentInfo","blank","This field may not be blank.","هذا الحقل لايمكن ان يكون فارغ","Payment Info","معلومات الدفع"));
        errors.add(new ErrorDescription("paymentInfo","max_length","Ensure this field has no more than 999 characters.","هذا الحقل لايزيد عن 999 حرف","Payment Info","معلومات الدفع"));
        errors.add(new ErrorDescription("paymentInfo","min_length","Ensure this field has at least 1 characters.","هذا الحقل لايقل عن  حرف","Payment Info","معلومات الدفع"));
        errors.add(new ErrorDescription("tranAmount","max_whole_digits","Ensure that there are no more than 10 digits before the decimal point.","هذا الحقل يجب ان يكون اقل من 10 خانات قبل الفاصلة العشرية","Amount","المبلغ"));
        errors.add(new ErrorDescription("expDate","expired","Card has expired.","انتهت صلاحية البطاقة","Expiry Date","تاريخ الصلاحية"));

        // EBS errors

        errors.add(new ErrorDescription("EBS_error","response_code","response_message","response_message_AR","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","0","Approved","معاملة ناجحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","10","Should select account number","عليك اختيار رقم الحساب","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","11","Should change PVV","الرجاء تغير الرقم السري الأولي","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","13","Select Bill","اختر الفاتورة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","17","Personal information input error","خطأ في ادخال المعلومات الشخصية","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","20","Prepaid code not found","رقم الدفع المسبق غير موجود","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","21","Corresponding account exhausted","الحساب المقصود إستنفذ","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","22","Acquirer limit exceeded","لقد تجاوزت الحد المسموع به للسحب/للتحويل","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","40","Lost card","البطاقة مفقودة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","41","Stolen card","البطاقة مسروقة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","49","Ineligible vendor account","حساب مزود غير صالح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","50","Unauthorized usage","استخدام غير مسموح به/بطاقة غير منشطة/بطاقة مغلقة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","51","Expired card","البطاقة منتهية الصلاحية","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","52","Invalid card","البطاقة غير صحيحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","53","Invalid PIN","خطأ في الرقم السري","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","54","System error","خطأ في المصدر/ راجع خدمات العملاء","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","55","ineligible transaction","عملية غير صحيحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","56","Ineligible account","حساب غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","57","Transaction not supported","العملية غير مدعومة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","58","Restricted Card","بطاقة موقوفة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","59","Insufficient funds","الرصيد غير كافي","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","60","Uses limit exceeded","لقد تجاوزت الحد المسموع به لعدد الاستخدامات","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","61","Withdrawal limit would be exceeded","تجاوز المبلغ المسموح به للسحب","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","62","PIN tries limit was reached","تم إغلاق البطاقة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","63","Withdrawal limit already reached","لقد تجاوزت المبلغ المسموح به للسحب سلفا","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","64","Credit amount limit","لقد تجاوزت المبلغ المسموح به للأيداع","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","65","No statement information","لا توجد معلومات عن العمليات السابقة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","67","Invalid amount","مبلغ غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","68","External decline","معاملة فاشلة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","69","No sharing","مشاركة غير متاحة /معاملة غير صحيحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","71","Contact card issuer","اتصل بالجهة المصدرة للبطاقة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","72","Destination not available","مصدر البطاقة غير متاح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","73","Routing error","معاملة فاشلة / راجع خدمات العملاء","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","74","Format error","خطأ في بيانات المعاملة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","75","External decline special condition","معاملة فاشلة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","80","Bad CVV","خطأ في إصدار البطاقة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","81","Bad CVV2","خطأ في إصدار البطاقة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","82","Invalid transaction","عميلة غير صحيحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","83","PIN tries limit was exceeded","لقد تجاوزت عدد المحاولات الخاطئة لادخال الرقم السري PIN","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","84","Bad CAVV","خطأ في إصدار البطاقة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","85","Bad ARQC","خطأ في إصدار البطاقة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","86","Server is Up","الخادم بدأ في العمل","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","87","None","خطأ غير معروف","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","600","Invalid iPIN Block ","خطأ في التشفير","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","601","INVALID_APPLICATION_ID","رقم التطبيق للعملية غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","602","INVALID_UUID","رقم ال UUID للعملية غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","603","INVALID_TRAN_DATE_TIME","تاريخ العملية غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","604","INVALID_CARD_NO","رقم البطاقة غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","605","INVALID_EXP_DATE","تاريخ انتهاء الصلاحية للبطاقة غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","606","INVALID_ORIGINAL_TRAN_DATE_TIME","تاريخ العملية الأصلية غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","607","INVALID_ORIGINAL_TRAN_GUID","رقم ال UUID للعملية الأصلية غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","608","INVALID_AMOUNT","المبلغ غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","609","INVALID_TOCARD","رقم البطاقة المرسل لها غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","610","INVALID_TRANSACTION","عملية غير صحيحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","611","FORMATE_ERROR","خطأ في بيانات المعاملة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","612","INVALID_PAYEE_ID","رقم المدفوع له غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","613","DUPLICATE_TRANSACTION","عملية مكررة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","614","INVALID_PAYMENT_INFO","معلومات دفع غير صحيحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","615","CANNOT_BE_REVERSED","لا يمكن عكس او الغاء العملية","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","616","Invalid Account","حساب غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","617","INVALID MBR","رقم ال MBR في البطاقة غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","618","INVALID_ACCOUNT_TYPE","نوع الحساب غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","619","INVALID TRANSACTION CURRENCY","نوع العملة المستخدمة غير صحيحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","620","Invalid Authentication Type","نوع التحقق غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","621","Invalid Authentication","التحقق غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","622","Invalid Registration Type","نوع التسجيل غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","623","Invalid User Name","اسم المستخدم غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","624","Invalid Entity Id","رقم المعرف غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","625","Invalid Entity Type","نوع المعرف غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","626","Invalid Entity Group","مجموعة المعرف غير صحيحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","627","Invalid Phone No","رقم الهاتف غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","628","Invalid Email","البريد الالكتروني غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","629","Invalid Financial Institution Id","رقم المؤسسة المالية غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","630","Invalid User Password","كلمة مرور غير صحيحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","631","NOT_ENROLLED_BEFORE","غير مسجل من قبل","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","632","CANNOT_GENERATE_CARD","لا يمكن إنشاء بطاقة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","633","CUSTOMER_ALLREADY_EXSISTS","المستخدم موجود مسبقا","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","634","INVALID_CARD","بطاقة غير صحيحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","635","CANNOT_USE_CARD","لا يمكن استخدام البطاقة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","636","Invalid Security Question","سؤال تأمين غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","637","Invalid Security Question Answer","الاجابة عن سؤال التأمين غير صحيحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","638","No Security Info Found For The Customer","لا توجد معلومات تأمين عن العميل","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","639","Invalid Job","الوظيفة غير صحيحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","640","Invalid Date Of Birth","تاريخ الميلاد غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","641","Invalid Bank Account Number","رقم الحساب البنكي غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","642","Invalid Bank Account Type","نوع الحساب البنكي غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","643","Invalid Bank Branch Id","رقم فرع البنك غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","644","Invalid Bank Id","رقم البنك غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","646","Invalid Customer Id Number","رقم العميل غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","647","Invalid Customer Id Type","نوع رقم العميل غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","648","Invalid Admin User Name","اسم المستخدم المدير غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","649","Invalid Voucher Number","رقم الإيصال غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","651","Unauthorized Payee ID","رقم الجهة المدفوع لها غير مسموح به","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","652","Unauthorized Authentication Type","نوع التحقق غير مسموح به","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","653","Unauthorized Registration Type","نوع التسجيل غير مسموح به","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","655","Unauthorized Transaction","عملية غير مسموح بها","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","656","Unauthorized Card Prefix","الأرقام الابتدائية في رقم البطاقة غير مسموح بها","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","657","Unauthorized Amount","المبلغ غير مسموح به","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","658","Unauthorized Pan Category","فئة رقم البطاقة غير مسموح به","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","659","Unauthorized Financial Institution ID","رقم المؤسسة المالية غير مسموح به","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","660","Approved"," but notification was not sent to customer","تمت العملية بنجاح ، ولكن حدث فشل في ارسال رسالة تنبيه للعميل","Error"));
        errors.add(new ErrorDescription("EBS_error","661","Invalid Service Info","معلومات الخدمة غير صحيحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","662","Invalid Service Provider ID","رمز مقدم الخدمة غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","663","Unauthorized SERVICE PROVIDER ID","رمز مقدم الخدمة غير مسموح به","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","664","Wrong New Expiration Date","تاريخ الانتهاء الجديد غير صحيح","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","665","Registered Card Already Updated","البطاقة المسجلة محدثة مسبقاً","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","696","SYSTEM_ERROR","خطأ في النظام","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","706","Hermes not initialized","خطأ في النظام/راجع خدمات العملاء","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","999","TIME_OUT_RESPONSE_CODE","العملية استغرقت وقتا أكثر من السموح به","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","66","Statement not available","العمليات السابقة غير متاحة","Error","خطأ"));
        errors.add(new ErrorDescription("EBS_error","645","Invalid Full Name","الاسم الكامل غير صحيح","Error","خطأ"));

        for(ErrorDescription errorDescription : errors){

            addErrorDescription(errorDescription);

        }


    }

    public ErrorDescription getErrorDescription(String errorField,String errorCode) {
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHandler.TABLE_ERROR_DESCRIPTION, new String[] { DatabaseHandler.ERROR_ID,
                        DatabaseHandler.ERROR_FIELD, DatabaseHandler.ERROR_CODE , DatabaseHandler.ERROR_MSG_EN,DatabaseHandler.ERROR_MSG_AR,
                        DatabaseHandler.ERROR_LABEL_EN,DatabaseHandler.ERROR_LABEL_AR}, DatabaseHandler.ERROR_FIELD + "=? AND "+DatabaseHandler.ERROR_CODE +" = ?",
                new String[] { errorField ,errorCode }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        ErrorDescription errorDescription = new ErrorDescription(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        // return contact
        return errorDescription;
    }


    public ErrorDescription getErrorDescription(String errorField ) {
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + DatabaseHandler.TABLE_ERROR_DESCRIPTION +" WHERE  "+DatabaseHandler.ERROR_FIELD +" = '"+errorField + "'  " ;


        ErrorDescription errorDescription =null;
        Cursor cursor = db.rawQuery(selectQuery,  null);

        if (cursor != null && cursor.moveToFirst()) {

                     errorDescription = new ErrorDescription(0,
                    cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            cursor.close();
        }
        // return contact
        return errorDescription;
    }

    public List<ErrorDescription> getErrorDescriptions() {
        List<ErrorDescription> list = new ArrayList<ErrorDescription>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHandler.TABLE_ERROR_DESCRIPTION +"  " ;

        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,  null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ErrorDescription info = new ErrorDescription();
                info.setId(Integer.parseInt(cursor.getString(0)));
                info.setErrorField(cursor.getString(1));
                info.setMsgEn(cursor.getString(2));
                info.setMsgAr(cursor.getString(3));

                // Adding payee to list
                list.add(info);
            } while (cursor.moveToNext());
        }

        // return card list
        return list;
    }







}
