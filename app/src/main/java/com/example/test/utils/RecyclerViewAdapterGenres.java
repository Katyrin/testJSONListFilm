package com.example.test.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterGenres extends RecyclerView.Adapter<RecyclerViewAdapterGenres.MyViewHolderGenres> {

    private Context mContext;
    private List<String> mData;
    List<CardView>cardViewList = new ArrayList<>();
    public String genre = null;
    private RecyclerViewAdapter rva;
    private final List<Film> allFilms;
    private List<Film> sortListGenres = new ArrayList<>();

    public List<Film> sortGenres(List<Film> listFilm,String nameGenre){
        sortListGenres.clear();
        for (int i =0;i<listFilm.size();i++){
            if (listFilm.get(i).getGenres().contains(nameGenre)){
                sortListGenres.add(listFilm.get(i));
            }
        }
        return sortListGenres;
    }


    public RecyclerViewAdapterGenres(Context mContext, List<String> mData,RecyclerViewAdapter rva,List<Film> allFilms) {
        this.mContext = mContext;
        this.mData = mData;
        this.rva = rva;
        this.allFilms = allFilms;
    }

    @NonNull
    @Override
    public MyViewHolderGenres onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_textbutton,parent,false);
        return new MyViewHolderGenres(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolderGenres holder, final int position) {

        cardViewList.add(holder.cardView);
        holder.textButton.setText(mData.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(CardView cardView : cardViewList){
                    cardView.setCardBackgroundColor(Color.WHITE);
                }
                //The selected card is set to colorSelected
                holder.cardView.setCardBackgroundColor(Color.YELLOW);

                genre = holder.textButton.getText().toString();

                List<Film> sf = sortGenres(allFilms,genre);
                rva.clear();
                rva.addFilms(sf);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolderGenres extends RecyclerView.ViewHolder{
        TextView textButton;
        CardView cardView;
        public MyViewHolderGenres(@NonNull View itemView) {
            super(itemView);
            textButton = (TextView) itemView.findViewById(R.id.textButton);
            cardView = (CardView)itemView.findViewById(R.id.card_view_id_gen);
        }
    }
}
