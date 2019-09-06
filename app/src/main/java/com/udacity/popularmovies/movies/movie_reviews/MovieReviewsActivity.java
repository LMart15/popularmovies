package com.udacity.popularmovies.movies.movie_reviews;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.popularmovies.R;
import com.udacity.popularmovies.core.NetworkUtils;
import com.udacity.popularmovies.movies.MovieExtrasViewModelFactory;
import com.udacity.popularmovies.movies.movie_reviews.list.MovieReviewsAdapter;
import com.udacity.popularmovies.movies.movie_reviews.list.MovieReviewsDiffUtil;
import com.udacity.popularmovies.movies.movie_reviews.networking.model.ReviewsDto;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieReviewsActivity extends AppCompatActivity {

    private MovieReviewsAdapter movieReviewsAdapter;

    @BindView(R.id.movie_reviews_toolbar)
    Toolbar movieReviewsToolbar;

    @BindView(R.id.movie_reviews_rv)
    RecyclerView movieReviewsRv;

    @NonNull
    private final Observer<ReviewsDto> movieReviewsObserver = reviews -> movieReviewsAdapter.submitList(reviews.getReviewDtos());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_reviews);
        ButterKnife.bind(this);

        movieReviewsToolbar.setTitle(getString(R.string.movie_reviews_title));

        setSupportActionBar(movieReviewsToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        movieReviewsToolbar.setNavigationOnClickListener(view -> onBackPressed());

        Integer movieId = getIntent().getIntExtra("movieId", 0);

        MovieExtrasViewModelFactory movieExtrasViewModelFactory = new MovieExtrasViewModelFactory(getApplication(), movieId);

        MovieReviewsViewModel movieReviewsViewModel = ViewModelProviders.of(this, movieExtrasViewModelFactory).get(MovieReviewsViewModel.class);


        if (NetworkUtils.isOnline(this)) {

            movieReviewsAdapter = new MovieReviewsAdapter(new MovieReviewsDiffUtil());
            movieReviewsRv.setAdapter(movieReviewsAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            movieReviewsRv.setLayoutManager(layoutManager);
            movieReviewsRv.addItemDecoration(new DividerItemDecoration(this,
                    DividerItemDecoration.VERTICAL));

            movieReviewsViewModel.getMovieReviewsObservable().observe(this, movieReviewsObserver);
        } else {

            Toast.makeText(this, getString(R.string.movies_network_offline_notification), Toast.LENGTH_LONG).show();
        }
    }

}
