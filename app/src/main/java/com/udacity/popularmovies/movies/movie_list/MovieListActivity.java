package com.udacity.popularmovies.movies.movie_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.popularmovies.R;
import com.udacity.popularmovies.core.NetworkUtils;
import com.udacity.popularmovies.movies.movie_detail.MovieDetailActivity;
import com.udacity.popularmovies.movies.movie_list.list.MovieListAdapter;
import com.udacity.popularmovies.movies.movie_list.list.MovieListDiffUtil;
import com.udacity.popularmovies.movies.movie_list.networking.model.MoviesDto;
import com.udacity.popularmovies.movies.movie_list.networking.model.ResultDto;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends AppCompatActivity implements MovieListAdapter.MovieClickCallBack {

    private MovieListViewModel moviesViewModel;

    private MovieListAdapter moviesAdapter;

    @NonNull
    private final Observer<MoviesDto> movieListObserver = movies -> moviesAdapter.submitList(movies.getResults());

    @BindView(R.id.movies_toolbar)
    Toolbar moviesToolbar;

    @BindView(R.id.toolbar_sort_options)
    Spinner sortOptionsSpinner;

    @BindView(R.id.movies_rv)
    RecyclerView moviesRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);

        moviesToolbar.setTitle(R.string.app_name);

        moviesViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        if (NetworkUtils.isOnline(this)) {

            initSortSpinner();

            moviesAdapter = new MovieListAdapter(new MovieListDiffUtil());
            moviesAdapter.setMovieClickCallback(this);
            moviesRv.setAdapter(moviesAdapter);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
            moviesRv.setLayoutManager(layoutManager);

            moviesViewModel.getMoviesListObservable().observe(this, movieListObserver);
        } else {

            Toast.makeText(this, getString(R.string.movies_network_offline_notification), Toast.LENGTH_LONG).show();
        }
    }


    private void initSortSpinner() {

        final String POPULAR_SORT_TYPE = getString(R.string.movies_api_sort_type_popular);
        final String TOP_RATED_SORT_TYPE = getString(R.string.movies_api_sort_type_top_rated);

        ArrayAdapter<String> sortAdapter = new ArrayAdapter<>(MovieListActivity.this,
                R.layout.item_spinner,
                getResources().getStringArray(R.array.sort_options));
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortOptionsSpinner.setAdapter(sortAdapter);

        sortOptionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (NetworkUtils.isOnline(getApplicationContext())) {
                    if (sortOptionsSpinner.getSelectedItem().toString().equals(getString(R.string.movies_sort_spinner_option_popular))) {
                        moviesViewModel.sortMovies(POPULAR_SORT_TYPE);
                        Toast.makeText(getApplicationContext(), getString(R.string.movies_sort_toast_popular), Toast.LENGTH_SHORT).show();
                    } else {
                        moviesViewModel.sortMovies(TOP_RATED_SORT_TYPE);
                        Toast.makeText(getApplicationContext(), getString(R.string.movies_sort_toast_top_rated), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.movies_network_offline_notification), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onMovieClicked(ResultDto movie) {

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movie_detail", movie);
        startActivity(intent);
    }
}
