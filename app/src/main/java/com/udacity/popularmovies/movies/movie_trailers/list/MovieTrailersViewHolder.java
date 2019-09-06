package com.udacity.popularmovies.movies.movie_trailers.list;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.popularmovies.R;
import com.udacity.popularmovies.movies.movie_trailers.networking.model.TrailerDto;

import butterknife.BindView;
import butterknife.ButterKnife;

class MovieTrailersViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.movie_trailer_name_tv)
    TextView movieTrailerNameTv;

    private final MovieTrailersAdapter.TrailerClickCallBack trailerClickCallBack;

    MovieTrailersViewHolder(@NonNull View itemView, @Nullable MovieTrailersAdapter.TrailerClickCallBack trailerClickCallBack) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        this.trailerClickCallBack = trailerClickCallBack;

    }

    void bind(TrailerDto trailer) {

        movieTrailerNameTv.setText(trailer.getName());

        itemView.setOnClickListener(itemView -> trailerClickCallBack.onTrailerClicked(trailer));
    }

}
