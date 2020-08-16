package com.example.test;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.utils.Film;
import com.example.test.utils.RecyclerViewAdapter;
import com.example.test.utils.RecyclerViewAdapterGenres;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static com.example.test.utils.NetworkUtils.generateURL;
import static com.example.test.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {
    TreeMap<String, Film> listFilm;
    public Context context = this;
    public static final String MY_URL = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/films.json";

    class QueryTask extends AsyncTask<URL,Void,String>{
        @Override
        protected String doInBackground(URL... urls) {
            String films=null;
            try {
                films = getResponseFromURL(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return films;
        }
        @Override
        protected void onPostExecute(String films){
            String id;
            String localized_name;
            String name;
            String year;
            String rating;
            Uri image_url;
            String description;
            List<String> genres;
            HashSet<String> setGeners = new HashSet<>();
            listFilm = new TreeMap<String,Film>();

            try {
                JSONObject jsonFilms = new JSONObject(films);
                JSONArray jsonArray = jsonFilms.getJSONArray("films");

                for (int i=0;i<jsonArray.length();i++){
                    JSONObject filmInfo = jsonArray.getJSONObject(i);
                    id = filmInfo.getString("id");
                    localized_name = filmInfo.getString("localized_name");
                    name = filmInfo.getString("name");
                    year = filmInfo.getString("year");
                    rating = filmInfo.getString("rating");
                    image_url = Uri.parse(filmInfo.getString("image_url"));
                    description = filmInfo.getString("description");
                    genres = new ArrayList<>();
                    JSONArray genresArray = filmInfo.getJSONArray("genres");
                    for (int j=0;j<genresArray.length();j++){
                        genres.add(genresArray.get(j).toString());
                    }
                    listFilm.put(localized_name,new Film(id,localized_name,name,year,rating,image_url,description,genres));

                    setGeners.addAll(genres);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Получение контейнера ArrayList из значений карты для сортировки по алфавиту
            final List<Film> lf = new ArrayList<>();
            Set set = listFilm.entrySet();
            Iterator it = set.iterator();
            while (it.hasNext()){
                Map.Entry me = (Map.Entry)it.next();
                lf.add((Film) me.getValue());
            }
            List<Film> lf2 = new ArrayList<>(lf);

            //Передача контейнера фильмов в адаптер
            RecyclerView myrv = (RecyclerView)findViewById(R.id.recyclerview_id);
            RecyclerViewAdapter myrvAdapter = new RecyclerViewAdapter(context,lf);
            myrv.setLayoutManager(new GridLayoutManager(context,2));
            myrv.setAdapter(myrvAdapter);

            List<String> genresList = new ArrayList<>(setGeners);
            RecyclerView myrvg = (RecyclerView)findViewById(R.id.recyclerview_category_id);
            RecyclerViewAdapterGenres myrvgAdapter =
                    new RecyclerViewAdapterGenres(context, genresList,myrvAdapter,lf2);
            myrvg.setLayoutManager(new GridLayoutManager(context,3));
            myrvg.setAdapter(myrvgAdapter);
            myrvg.suppressLayout(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new QueryTask().execute(generateURL(MY_URL));
    }
}
