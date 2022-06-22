package com.example.myyelp;

import androidx.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YelpClient {
    public YelpAPI build() {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        return chain.proceed(chain
                                .request()
                                .newBuilder()
                                .addHeader("Authorization",
                                        "Bearer QAeTbhjhfux-0_yvNY5QM8SV34uKss3mmqYlmyf62Kp77Cj0EHj3FqFV4LxffIPbKzAyQN3aCCcL74JN51pYeM8_OA5xGeGu6eRRGpRMlw3ZPcqr0H5nvkXuSlWiYnYx")
                                .build());
                    }
                }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.yelp.com/v3/businesses/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        return retrofit.create(YelpAPI.class);
    }

}
