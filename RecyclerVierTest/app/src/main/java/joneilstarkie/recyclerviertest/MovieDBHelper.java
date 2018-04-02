package joneilstarkie.recyclerviertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Owner on 29/03/2018.
 */

public class MovieDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "miniproject_test";
    public static final int DB_VERSION = 1;

    public static final String CREATE_QUERY = "CREATE TABLE " + MovieDetails.MovieEntry.TABLE_NAME + "( " + MovieDetails.MovieEntry.MOVIE_ID + " integer," + MovieDetails.MovieEntry.MOVIE_TITLE + " text," + MovieDetails.MovieEntry.OVERVIEW + " text," + MovieDetails.MovieEntry.RELEASE_DATE + " text," + MovieDetails.MovieEntry.IMAGE + " text);";

    private static final String DROP_QUERY = "DROP TABLE IF EXISTS " + MovieDetails.MovieEntry.TABLE_NAME + ";";

    public MovieDBHelper(Context context) {
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

    public void putInformation(int movie_id, String movie_title, String overview, String release_date, String image, SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieDetails.MovieEntry.MOVIE_ID,movie_id);
        contentValues.put(MovieDetails.MovieEntry.MOVIE_TITLE,movie_title);
        contentValues.put(MovieDetails.MovieEntry.OVERVIEW,overview);
        contentValues.put(MovieDetails.MovieEntry.RELEASE_DATE,release_date);
        contentValues.put(MovieDetails.MovieEntry.IMAGE,image);
        long l = db.insert(MovieDetails.MovieEntry.TABLE_NAME,null,contentValues);
        Log.d("Database Operations","One row inserted...");
    }

    public Cursor getInformation(SQLiteDatabase db)
    {
        String[] projection = {MovieDetails.MovieEntry.MOVIE_ID, MovieDetails.MovieEntry.MOVIE_TITLE, MovieDetails.MovieEntry.OVERVIEW, MovieDetails.MovieEntry.RELEASE_DATE, MovieDetails.MovieEntry.IMAGE};
        Cursor cursor = db.query(MovieDetails.MovieEntry.TABLE_NAME,projection,null,null,null,null,null);
        return cursor;
    }

}
