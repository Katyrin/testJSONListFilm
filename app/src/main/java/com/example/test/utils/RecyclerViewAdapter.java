package com.example.test.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.BlankFragment;
import com.example.test.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Film> mData;
    private BlankFragment blankFragment;

    public RecyclerViewAdapter(Context mContext, List<Film> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public void clear() {
        int size = mData.size();
        mData.clear();
        this.notifyItemRangeRemoved(0, size);
    }

    public void addFilms(List<Film> mData) {
        this.mData.addAll(mData);
        this.notifyItemRangeInserted(0, mData.size() - 1);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_film,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.localized_name.setText(mData.get(position).getLocalized_name());
        Picasso.with(mContext).load(mData.get(position).getImage_url()).into(holder.image);
        blankFragment = BlankFragment.newInstance();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blankFragment.show(((FragmentActivity)mContext).getSupportFragmentManager(),"tag");
                //FragmentTransaction ft = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
                //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                blankFragment.setLocName(mData.get(position).getLocalized_name());
                blankFragment.setFilmName(mData.get(position).getName());
                blankFragment.setYear(mData.get(position).getYear());
                blankFragment.setRating(mData.get(position).getRating());
                blankFragment.setDescription(mData.get(position).getDescription());
                blankFragment.setImageView(mData.get(position).getImage_url());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView localized_name;
        ImageView image;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            localized_name = (TextView)itemView.findViewById(R.id.localized_name);
            image = (ImageView)itemView.findViewById(R.id.image);
            cardView = (CardView)itemView.findViewById(R.id.card_view_id);

        }
    }
}
