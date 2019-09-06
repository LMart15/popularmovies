package com.udacity.popularmovies.movies.movie_reviews;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.udacity.popularmovies.movies.movie_reviews.networking.MovieReviewsNetworkClient;
import com.udacity.popularmovies.movies.movie_reviews.networking.model.ReviewsDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class MovieReviewsViewModel extends ViewModel {

    @NonNull
    private final MovieReviewsNetworkClient movieReviewsNetworkClient;

    @NonNull
    private final MutableLiveData<ReviewsDto> movieReviewsObservable = new MutableLiveData<>();


    public MovieReviewsViewModel(Application application, int movieId) {

        movieReviewsNetworkClient = new MovieReviewsNetworkClient(application.getApplicationContext());

        loadMovieReviewsObservable(movieId);
    }

    private void loadMovieReviewsObservable(Integer movieId) {

        movieReviewsNetworkClient.getReviews(movieId, new Callback<ReviewsDto>() {
            @Override
            public void onResponse(Call<ReviewsDto> call, Response<ReviewsDto> response) {
                movieReviewsObservable.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ReviewsDto> call, Throwable t) {
                Timber.d(t);
            }
        });
    }

    LiveData<ReviewsDto> getMovieReviewsObservable() {

        return movieReviewsObservable;
    }

}
