package com.udacity.popularmovies.movies.movie_detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.R;
import com.udacity.popularmovies.core.NetworkUtils;
import com.udacity.popularmovies.movies.movie_list.networking.model.MovieDto;
import com.udacity.popularmovies.movies.movie_reviews.MovieReviewsActivity;
import com.udacity.popularmovies.movies.movie_trailers.MovieTrailersActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.movie_detail_toolbar)
    Toolbar movieDetailToolbar;

    @BindView(R.id.movie_detail_favorite_cb)
    CheckBox movieDetailFavoriteButton;

    @BindView(R.id.movie_detail_img)
    ImageView movieDetailThumbnail;

    @BindView(R.id.movie_detail_release_date)
    TextView movieDetailReleaseDate;

    @BindView(R.id.movie_detail_overview)
    TextView movieDetailOverview;

    @BindView(R.id.movie_detail_rating)
    RatingBar movieDetailRating;

    private MovieDto movieDetail;

    private MovieDetailViewModel movieDetailViewModel;

    @NonNull
    private final Observer<Boolean> isFavoriteMovieObserver = this::updateFavMovieButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        setSupportActionBar(movieDetailToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        movieDetailToolbar.setNavigationOnClickListener(view -> onBackPressed());

        movieDetail = (MovieDto) getIntent().getSerializableExtra("movie_detail");

        movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);

        movieDetailViewModel.isFavoriteMovie(movieDetail.getId().toString());

        movieDetailViewModel.getIsFavoriteMovie().observe(this, isFavoriteMovieObserver);

        renderMovieDetail(movieDetail);

    }

    private void renderMovieDetail(MovieDto movie) {

        final String baseImageUrl = getString(R.string.movie_base_image_url);
        final String posterPath = movie.getPosterPath();
        final String url = baseImageUrl + posterPath;

        Picasso.get()
                .load(url)
                .into(movieDetailThumbnail);

        Objects.requireNonNull(getSupportActionBar()).setTitle(movie.getTitle());
        movieDetailReleaseDate.setText(movie.getReleaseDate());
        movieDetailRating.setRating(movie.getVoteAverage().floatValue() / 2);
        movieDetailOverview.setText(movie.getOverview());

    }

    private void updateFavMovieButton(Boolean isFavorite) {
        movieDetailFavoriteButton.setChecked(isFavorite);
    }

    @OnClick(R.id.movie_trailers_ll)
    void navigateToTrailers() {
        if (NetworkUtils.isOnline(this)) {
            Intent navTrailers = new Intent(this, MovieTrailersActivity.class);
            navTrailers.putExtra("movieId", movieDetail.getId());
            startActivity(navTrailers);
        } else {
            Toast.makeText(this, getString(R.string.movies_network_offline_notification), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.movie_reviews_ll)
    void navigateToReviews() {
        if (NetworkUtils.isOnline(this)) {
            Intent navReviews = new Intent(this, MovieReviewsActivity.class);
            navReviews.putExtra("movieId", movieDetail.getId());
            startActivity(navReviews);
        } else {
            Toast.makeText(this, getString(R.string.movies_network_offline_notification), Toast.LENGTH_SHORT).show();
        }
    }

    @OnCheckedChanged(R.id.movie_detail_favorite_cb)
    void setFavMovie(final boolean isChecked) {
        if (isChecked) {
            movieDetailViewModel.setFavoriteMovie(movieDetail);
        } else {
            movieDetailViewModel.removeMovieFromFavorites(movieDetail);
        }
    }

}
