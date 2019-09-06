package com.udacity.popularmovies.movies.movie_list.list;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.R;
import com.udacity.popularmovies.movies.movie_list.networking.model.MovieDto;

import butterknife.BindView;
import butterknife.ButterKnife;

class MovieListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.movie_img)
    ImageView movieImage;

    private final MovieListAdapter.MovieClickCallBack movieClickCallBack;
    private final String baseImageUrl;

    MovieListViewHolder(@NonNull View itemView, @Nullable MovieListAdapter.MovieClickCallBack movieClickCallBack) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        this.movieClickCallBack = movieClickCallBack;

        baseImageUrl = itemView.getContext().getString(R.string.movie_base_image_url);
    }

    void bind(MovieDto movie) {

        final String posterPath = movie.getPosterPath();
        final String url = baseImageUrl + posterPath;

        Picasso.get()
                .load(url)
                .into(movieImage);

        itemView.setOnClickListener(itemView -> movieClickCallBack.onMovieClicked(movie));
    }

}
