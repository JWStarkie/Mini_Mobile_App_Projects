    package joneilstarkie.recyclerviertest;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.util.Log;

    /**
     * Created by Owner on 13/03/2018.
     */

    public class UserDBHelper extends SQLiteOpenHelper {

        public static final String DB_NAME = "miniproject_test";
        public static final int DB_VERSION = 1;

        public static final String CREATE_QUERY = "CREATE TABLE " + ManualContact.UserEntry.TABLE_NAME + "( " + ManualContact.UserEntry.USER_ID + " integer," + ManualContact.UserEntry.USERNAME + " text," + ManualContact.UserEntry.FIRST_NAME + " text," + ManualContact.UserEntry.LAST_INITIAL + " text," + ManualContact.UserEntry.EMAIL + " text," + ManualContact.UserEntry.LOGGED_IN + " text);";

        private static final String DROP_QUERY = "DROP TABLE IF EXISTS " + ManualContact.UserEntry.TABLE_NAME + ";";

        public UserDBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_QUERY);
            Log.d("Database Operations","Table created...");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_QUERY);
            Log.d("Database Operations","Database updated...");
        }

        public void putInformation(int user_id, String username, String first_name, String last_initial, String email, boolean logged_in, SQLiteDatabase db)
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ManualContact.UserEntry.USER_ID,user_id);
            contentValues.put(ManualContact.UserEntry.USERNAME,username);
            contentValues.put(ManualContact.UserEntry.FIRST_NAME,first_name);
            contentValues.put(ManualContact.UserEntry.LAST_INITIAL,last_initial);
            contentValues.put(ManualContact.UserEntry.EMAIL,email);
            contentValues.put(ManualContact.UserEntry.LOGGED_IN,logged_in);
            long l = db.insert(ManualContact.UserEntry.TABLE_NAME,null,contentValues);
            Log.d("Database Operations","One row inserted...");
        }

    public Cursor getInformation(SQLiteDatabase db)
    {
        String[] projection = {ManualContact.UserEntry.USER_ID,ManualContact.UserEntry.USERNAME,ManualContact.UserEntry.FIRST_NAME,ManualContact.UserEntry.LAST_INITIAL,ManualContact.UserEntry.EMAIL,ManualContact.UserEntry.LOGGED_IN};
        Cursor cursor = db.query(ManualContact.UserEntry.TABLE_NAME,projection,null,null,null,null,null);
        return cursor;
    }

    }
