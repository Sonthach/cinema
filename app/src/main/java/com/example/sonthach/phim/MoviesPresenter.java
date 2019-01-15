package com.example.sonthach.phim;

import com.example.sonthach.phim.Load.Filmss;
import com.example.sonthach.phim.model.response.Films;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesPresenter {
    /* MoviesView view ;
    public MoviesPresenter(MoviesView view) {
        this.view= view;
    }
    void getData() {
        Call<Filmss> call = APIUtils.getAPIService().getAllMovie();
        call.enqueue(new Callback<Films>(){

            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                view.hideLoading();
                Films bd= response.body();
                if (response.isSuccessful())
                {
                    view.onGetResult(bd);
                }
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
} */
}
