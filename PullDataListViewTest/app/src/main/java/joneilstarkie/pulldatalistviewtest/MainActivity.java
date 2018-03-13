package joneilstarkie.pulldatalistviewtest;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    public static String MOVIE_ID = "";
    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;
    ArrayList<HashMap<String, String>> movieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);
        lv.setOnItemClickListener(this);

        new WebPull().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("Hello ListView", "You clicked item: " + id + " at position: " + position);
        Intent myIntent = new Intent(this, MovieActivity.class);
        MOVIE_ID = movieList.get((int) id).get("id");

        startActivity(myIntent);

    }


    private class WebPull extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            JSONParser sh = new JSONParser();
            // Making a request to url and getting response
            String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=3df5f7f0fa2c63de5a5f0687a402d65f&language=en-GB&page=1&region=GB";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray movies = jsonObj.getJSONArray("results");

                    // looping through All Contacts
                    for (int i = 0; i < movies.length(); i++) {
                        JSONObject c = movies.getJSONObject(i);
                        String id = c.getString("id");
                        String movie_title = c.getString("title");
                        String movie_overview = c.getString("overview");
                        //String movie_release = c.getString("release_date");
                        //String address = c.getString("address");
                        //String gender = c.getString("gender");

                        // tmp hash map for single movie
                        HashMap<String, String> movie = new HashMap<>();

                        // adding each child node to HashMap key => value
                        movie.put("id", id);
                        movie.put("title", movie_title);
                        movie.put("overview", movie_overview);
                        //movie.put("release_date", movie_release);

                        // adding movies to movies list
                        movieList.add(movie);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, movieList,
                    R.layout.list_item, new String[]{ "title","overview"},
                    new int[]{R.id.movie_title, R.id.movie_overview});

            lv.setAdapter(adapter);


        }
    }
}

