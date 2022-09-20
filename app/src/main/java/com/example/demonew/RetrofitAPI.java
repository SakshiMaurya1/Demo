package com.example.demonew;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {
     @GET("random?tags=technology,famous-quotes")
     Call<ModalClass> getPosts();
}
