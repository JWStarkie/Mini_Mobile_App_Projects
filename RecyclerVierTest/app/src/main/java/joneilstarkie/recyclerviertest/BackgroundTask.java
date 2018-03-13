package joneilstarkie.recyclerviertest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Owner on 12/03/2018.
 */

public class BackgroundTask extends AsyncTask<Void, Void, Void> {

    Context ctx;
    String JSON_url = "http://localhost:8080/php_files/get_movie_info.php";

    public BackgroundTask(Context ctx)
    {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(JSON_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            httpURLConnection.disconnect();
            String json_data = stringBuilder.toString().trim();
            JSONObject jsonObject = new JSONObject(json_data);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            UserDBHelper uDBHelper = new UserDBHelper(ctx);
            SQLiteDatabase db = uDBHelper.getWritableDatabase();


            int count = 0;
            while (count < jsonArray.length())
            {
                JSONObject jo = jsonArray.getJSONObject(count);
                count++;
                uDBHelper.putInformation(jo.getInt("user_id"),jo.getString("username"),jo.getString("first_name"),jo.getString("last_initial"),jo.getString("email"),jo.getBoolean("logged_in"), db);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
