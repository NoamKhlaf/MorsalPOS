package sd.code.morsalpos.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import sd.code.morsalpos.common.CardInfo;
import sd.code.morsalpos.common.ConfigBean;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 22;

	// Database Name
	private static final String DATABASE_NAME = "morsal";

	// Cards table name
	private static final String TABLE_CARDS = "cards";
	public static final String TABLE_PAYEE = "payee";
	public static final String TABLE_STUDENT_COURSE = "student_course";
	public static final String TABLE_FORM_KIND= "form_kind";
	public static final String TABLE_ERROR_DESCRIPTION= "error_description";
	public static final String TABLE_CONFIG = "config";

	// Cards Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_CARD_NO = "phone_number";
	private static final String KEY_EX_DATE = "expiry_date";
	private static final String KEY_DEFAULT = "default_flag";

	// Cards Table Columns names
	public static final String PAYEE_ID = "id";
	public static final String PAYEE_NUMBER = "payee_number";
	public static final String PAYEE_NAME = "payee_name";
	public static final String PAYEE_NAME_AR = "payee_name_ar";
	public static final String PAYEE_TYPE = "payee_type";

	public static final String ERROR_ID = "id";
	public static final String ERROR_FIELD = "error_field";
	public static final String ERROR_CODE = "error_code";
	public static final String ERROR_MSG_EN = "msg_en";
	public static final String ERROR_MSG_AR= "msg_ar";
	public static final String ERROR_LABEL_EN = "label_en";
	public static final String ERROR_LABEL_AR = "label_ar";


	public static final String INFO_ID = "id";
	public static final String INFO_TITLE = "title";
	public static final String INFO_TITLE_EN = "title_en";

	public static final String CONFIG_ID = "id";
	public static final String CONFIG_PARAM = "param";
	public static final String CONFIG_VAL = "val";
	public static final String CONFIG_DESCRIPTION = "description";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_CARDS_TABLE = "CREATE TABLE " + TABLE_CARDS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_CARD_NO + " TEXT NOT NULL UNIQUE," + KEY_EX_DATE + " TEXT NOT NULL,"  + KEY_DEFAULT + " INTEGER NOT NULL" + ")";

		String CREATE_PAYEE_TABLE = "CREATE TABLE " + TABLE_PAYEE + "("
				+ PAYEE_ID + " INTEGER PRIMARY KEY," + PAYEE_NUMBER + " TEXT NOT NULL UNIQUE,"
				+ PAYEE_NAME + " TEXT NOT NULL,"+ PAYEE_NAME_AR + " TEXT NOT NULL," + PAYEE_TYPE + " INTEGER NOT NULL" + ")";


		String CREATE_STUDENT_COURSE_TABLE = "CREATE TABLE " + TABLE_STUDENT_COURSE + "("
				+ INFO_ID + " INTEGER PRIMARY KEY," + INFO_TITLE + " TEXT NOT NULL UNIQUE ," + INFO_TITLE_EN + " TEXT NOT NULL UNIQUE )";

		String CREATE_FORM_KIND_TABLE = "CREATE TABLE " + TABLE_FORM_KIND + "("
				+ INFO_ID + " INTEGER PRIMARY KEY," + INFO_TITLE + " TEXT NOT NULL UNIQUE ," + INFO_TITLE_EN + " TEXT NOT NULL UNIQUE )";

		String CREATE_ERROR_DESCRIPTION_TABLE = "CREATE TABLE " + TABLE_ERROR_DESCRIPTION + "("
				+ ERROR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + ERROR_FIELD + " TEXT NOT NULL  ," + ERROR_CODE + " TEXT NOT NULL  ," + ERROR_MSG_EN + " TEXT NOT NULL  " +
				"," + ERROR_MSG_AR + " TEXT NOT NULL  ," + ERROR_LABEL_EN + " TEXT NOT NULL  ," + ERROR_LABEL_AR + " TEXT NOT NULL  )";
		String CREATE_CONFIG_TABLE = "CREATE TABLE " + TABLE_CONFIG + "("
				+ CONFIG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CONFIG_PARAM + " TEXT NOT NULL UNIQUE ," + CONFIG_VAL + " TEXT NOT NULL ," + CONFIG_DESCRIPTION + " TEXT NOT NULL )";



		db.execSQL(CREATE_CARDS_TABLE);
		db.execSQL(CREATE_PAYEE_TABLE);
		db.execSQL(CREATE_STUDENT_COURSE_TABLE);
		db.execSQL(CREATE_FORM_KIND_TABLE);
		db.execSQL(CREATE_ERROR_DESCRIPTION_TABLE);
		db.execSQL(CREATE_CONFIG_TABLE);

	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYEE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT_COURSE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORM_KIND);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ERROR_DESCRIPTION);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONFIG);
		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new card
	public long addCard(CardInfo card) {

		if(card.getDefaultFlag() == 1){
			int rF = removeDefault(card);
			Log.d("dddddd: ", String.valueOf(rF));
		}
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, card.getName()); //
		values.put(KEY_CARD_NO, card.getPan()); //
		values.put(KEY_EX_DATE, card.getExpDate()); //
		values.put(KEY_DEFAULT, card.getDefaultFlag()); //

		// Inserting Row
		long flag = db.insert(TABLE_CARDS, null, values);
		db.close(); // Closing database connection

		return  flag;
	}

	// Getting single card
	public CardInfo getCard(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CARDS, new String[] { KEY_ID,
						KEY_NAME, KEY_CARD_NO , KEY_EX_DATE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		CardInfo card = new CardInfo(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
		// return contact
		return card;
	}

	// Getting All card
	public List<CardInfo> getAllCards() {
		List<CardInfo> cardList = new ArrayList<CardInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CARDS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				CardInfo card = new CardInfo();
				card.setId(Integer.parseInt(cursor.getString(0)));
				card.setName(cursor.getString(1));
				card.setPan(cursor.getString(2));
				card.setExpDate(cursor.getString(3));
				card.setDefaultFlag(Integer.parseInt(cursor.getString(4)));
				// Adding card to list
				cardList.add(card);
			} while (cursor.moveToNext());
		}

		// return card list
		return cardList;
	}

	// Updating single card
	public int updateCard(CardInfo card) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, card.getName());
		values.put(KEY_CARD_NO, card.getPan());
		values.put(KEY_EX_DATE, card.getExpDate());

		// updating row
		return db.update(TABLE_CARDS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(card.getId()) });
	}

	public int removeDefault(CardInfo card) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_DEFAULT, 0);
		int result = db.update(TABLE_CARDS, values,null,
				null);
		db.close();
		// updating row
		return result;
	}

	// Deleting single card
	public void deleteCard(CardInfo card) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CARDS, KEY_ID + " = ?",
				new String[] { String.valueOf(card.getId()) });
		db.close();
	}


	// Getting cards Count
	public int getCardsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CARDS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
	public long addConfig(ConfigBean configBean) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CONFIG_PARAM, configBean.getParam()); //
		values.put(CONFIG_VAL, configBean.getVal()); //
		values.put(CONFIG_DESCRIPTION, configBean.getDescription()); //
		// Inserting Row
		long flag = db.insert(TABLE_CONFIG, null, values);
		db.close(); // Closing database connection
		return  flag;
	}
	public long updateConfig(ConfigBean configBean) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CONFIG_PARAM, configBean.getParam()); //
		values.put(CONFIG_VAL, configBean.getVal()); //
		values.put(CONFIG_DESCRIPTION, configBean.getDescription()); //
		// Inserting Row
		long flag = db.update(TABLE_CONFIG, values,CONFIG_ID+"=?",new String[] { String.valueOf(configBean.getId()) });
		db.close(); // Closing database connection
		return  flag;
	}
	public ConfigBean getConfigByParam(String param) {
		SQLiteDatabase db = this.getReadableDatabase();
		ConfigBean configBean = new ConfigBean();
		Cursor cursor = db.query(TABLE_CONFIG, new String[] { CONFIG_ID,
						CONFIG_PARAM, CONFIG_VAL , CONFIG_DESCRIPTION }, CONFIG_PARAM + "=?",
				new String[] { param }, null, null, null, null);
		if (cursor != null) {
			if(cursor.moveToFirst()) {
				configBean.setId(cursor.getInt(0));
				configBean.setParam(cursor.getString(1));
				configBean.setVal(cursor.getString(2));
				configBean.setDescription(cursor.getString(3));
			}
		}

		return configBean;
	}
	public boolean isConfigExist(String param) {
		boolean res=false;
		SQLiteDatabase db = this.getReadableDatabase();
		ConfigBean configBean = new ConfigBean();
		Cursor cursor = db.query(TABLE_CONFIG, new String[] { CONFIG_ID,
						CONFIG_PARAM, CONFIG_VAL , CONFIG_DESCRIPTION }, CONFIG_PARAM + "=?",
				new String[] { param }, null, null, null, null);
		if (cursor != null) {
			if(cursor.moveToFirst()) {
				res=true;
			}
		}

		return res;
	}


}