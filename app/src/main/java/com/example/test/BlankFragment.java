package com.example.test;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;

public class BlankFragment extends DialogFragment {
    public TextView name;
    public MaterialTextView localized_name;
    public TextView year;
    public TextView rating;
    public TextView description;
    public ImageView imageView;
    private String locName;
    private String filmName;
    private String yearStr;
    private String ratingStr;
    private String descriptionStr;
    private Uri imageUri;
    private ImageButton back;

    public void setLocName(String str){
        locName = str;
    }
    public void setFilmName(String str){
        filmName = str;
    }
    public void setYear(String str){
        yearStr = str;
    }
    public void setRating(String str){
        ratingStr = str;
    }
    public void setDescription(String str){
        descriptionStr = str;
    }
    public void setImageView(Uri uri){
        imageUri = uri;
    }

    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance() {
        BlankFragment fragment = new BlankFragment();
        return fragment;
    }

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.FullScreenDialogTheme);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        name = view.findViewById(R.id.name2);
        name.setText(filmName);
        localized_name = view.findViewById(R.id.localized_name2);
        localized_name.setText(locName);
        year = view.findViewById(R.id.year2);
        year.setText("Год: "+yearStr);
        rating = view.findViewById(R.id.rating);
        rating.setText("Рейтинг: "+ratingStr);
        description = view.findViewById(R.id.description);
        description.setText(descriptionStr);
        imageView = view.findViewById(R.id.image2);
        Picasso.with(this.getContext()).load(imageUri).into(imageView);
        back = view.findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        return view;

    }
}