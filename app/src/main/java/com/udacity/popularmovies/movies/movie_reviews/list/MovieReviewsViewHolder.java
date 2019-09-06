package com.udacity.popularmovies.movies.movie_reviews.list;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.popularmovies.R;
import com.udacity.popularmovies.movies.movie_reviews.networking.model.ReviewDto;

import butterknife.BindView;
import butterknife.ButterKnife;

class MovieReviewsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.movie_review_author_tv)
    TextView movieReviewAuthorTv;

    @BindView(R.id.movie_review_content_tv)
    TextView movieReviewContentTv;


    MovieReviewsViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    void bind(ReviewDto review) {

        movieReviewContentTv.setText(review.getContent());
        movieReviewAuthorTv.setText(review.getAuthor());

    }

}
