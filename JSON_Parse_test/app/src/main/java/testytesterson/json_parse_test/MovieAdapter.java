package testytesterson.json_parse_test;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.Collections;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<MovieData> data= Collections.emptyList();
    MovieData current;
    int currentPos=0;

    // create constructor to initilize context and data sent from MainActivity
    public MovieAdapter(Context context, List<MovieData> data){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_movie, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        String newMovieTi = current.MOVIE_TITLE;
        newMovieTi.replace("\u2019", "'");
        String newOvervi = current.MOVIE_TITLE;
        newOvervi.replace("\u2019", "'");

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder = (MyHolder) holder;
        MovieData current = data.get(position);
        myHolder.textMovieTitle.setText(newMovieTi);
        myHolder.textOverview.setText(newOvervi);
        myHolder.textOverview.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));

        // load image into imageview using glide
        String movieImage = current.IMAGE;
        movieImage = movieImage.replace("/", "");

        Glide.with(context).load("https://image.tmdb.org/t/p/w92" + movieImage)
                .placeholder(R.drawable.zero_logo)
                .error(R.drawable.zero_logo)
                .into(myHolder.mImage);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView textMovieTitle;
        ImageView mImage;
        TextView textSize;
        TextView textType;
        TextView textOverview;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textMovieTitle = (TextView) itemView.findViewById(R.id.textMovieTitle);
            mImage = (ImageView) itemView.findViewById(R.id.mImage);
            textOverview = (TextView) itemView.findViewById(R.id.textOverview);
        }

    }

}
