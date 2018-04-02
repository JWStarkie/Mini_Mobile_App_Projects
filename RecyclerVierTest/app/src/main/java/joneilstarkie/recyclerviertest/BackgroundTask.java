package joneilstarkie.recyclerviertest;

import android.app.ProgressDialog;
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

    ProgressDialog progressDialog;
    Context ctx;
    String JSON_url = "http://192.168.64.2/php_files/get_movie_info.php";

    public BackgroundTask(Context ctx)
    {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute(){
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Please Wait ...");
        progressDialog.setMessage("Download in Progress ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //super.onPreExecute();
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
                Thread.sleep(500);
            }
            httpURLConnection.disconnect();
            String json_data = stringBuilder.toString().trim();
            JSONObject jsonObject = new JSONObject(json_data);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            MovieDBHelper mDBHelper = new MovieDBHelper(ctx);
            SQLiteDatabase db = mDBHelper.getWritableDatabase();


            int count = 0;
            while (count < jsonArray.length())
            {
                JSONObject jo = jsonArray.getJSONObject(count);
                count++;
                mDBHelper.putInformation(jo.getInt("movie_id"),jo.getString("movie_title"),jo.getString("overview"),jo.getString("release_date"),jo.getString("image"), db);
            }
            mDBHelper.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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
        progressDialog.dismiss();
    }
}
