package com.udacity.popularmovies.movies.movie_trailers;

import android.content.Intent;
import android.net.Uri;
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
import com.udacity.popularmovies.movies.movie_trailers.list.MovieTrailersAdapter;
import com.udacity.popularmovies.movies.movie_trailers.list.MovieTrailersDiffUtil;
import com.udacity.popularmovies.movies.movie_trailers.networking.model.TrailerDto;
import com.udacity.popularmovies.movies.movie_trailers.networking.model.TrailersDto;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieTrailersActivity extends AppCompatActivity implements MovieTrailersAdapter.TrailerClickCallBack {

    private MovieTrailersAdapter movieTrailersAdapter;

    @BindView(R.id.movie_trailers_toolbar)
    Toolbar movieTrailersToolbar;

    @BindView(R.id.movie_trailers_rv)
    RecyclerView movieTrailersRv;

    @NonNull
    private final Observer<TrailersDto> movieTrailersObserver = trailers -> movieTrailersAdapter.submitList(trailers.getTrailers());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);
        ButterKnife.bind(this);

        movieTrailersToolbar.setTitle(getString(R.string.movie_trailers_title));

        setSupportActionBar(movieTrailersToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        movieTrailersToolbar.setNavigationOnClickListener(view -> onBackPressed());

        Integer movieId = getIntent().getIntExtra("movieId", 0);

        MovieExtrasViewModelFactory movieExtrasViewModelFactory = new MovieExtrasViewModelFactory(getApplication(), movieId);

        MovieTrailersViewModel movieTrailersViewModel = ViewModelProviders.of(this, movieExtrasViewModelFactory).get(MovieTrailersViewModel.class);


        if (NetworkUtils.isOnline(this)) {

            movieTrailersAdapter = new MovieTrailersAdapter(new MovieTrailersDiffUtil());
            movieTrailersAdapter.setTrailerClickCallback(this);
            movieTrailersRv.setAdapter(movieTrailersAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            movieTrailersRv.setLayoutManager(layoutManager);
            movieTrailersRv.addItemDecoration(new DividerItemDecoration(this,
                    DividerItemDecoration.VERTICAL));

            movieTrailersViewModel.getMovieTrailersObservable().observe(this, movieTrailersObserver);
        } else {

            Toast.makeText(this, getString(R.string.movies_network_offline_notification), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onTrailerClicked(TrailerDto trailer) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.movie_trailer_base_url) + trailer.getKey()));
        startActivity(intent);
    }
}
