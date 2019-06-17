package com.udacity.popularmovies.movies.movie_detail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.R;
import com.udacity.popularmovies.movies.movie_list.networking.model.ResultDto;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.movie_detail_toolbar)
    Toolbar movieDetailToolbar;

    @BindView(R.id.movie_detail_img)
    ImageView movieDetailThumbnail;

    @BindView(R.id.movie_detail_release_date)
    TextView movieDetailReleaseDate;

    @BindView(R.id.movie_detail_overview)
    TextView movieDetailOverview;

    @BindView(R.id.movie_detail_rating)
    RatingBar movieDetailRating;

    ResultDto movieDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        setSupportActionBar(movieDetailToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        movieDetailToolbar.setNavigationOnClickListener(view -> onBackPressed());

        movieDetail = (ResultDto) getIntent().getSerializableExtra("movie_detail");
        renderMovieDetail(movieDetail);
    }

    private void renderMovieDetail(ResultDto movieDetail) {

        final String baseImageUrl = getString(R.string.base_image_url);
        final String posterPath = movieDetail.getPosterPath();
        final String url = baseImageUrl + posterPath;

        Picasso.get()
                .load(url)
                .into(movieDetailThumbnail);

        Objects.requireNonNull(getSupportActionBar()).setTitle(movieDetail.getTitle());
        movieDetailReleaseDate.setText(movieDetail.getReleaseDate());
        movieDetailRating.setRating(movieDetail.getVoteAverage().floatValue()/2);
        movieDetailOverview.setText(movieDetail.getOverview());

    }

}
