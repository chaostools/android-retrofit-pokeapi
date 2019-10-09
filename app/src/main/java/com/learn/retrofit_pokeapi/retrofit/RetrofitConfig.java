package com.learn.retrofit_pokeapi.retrofit;

import com.learn.retrofit_pokeapi.service.IPokeService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;
    private String baseURL = "https://pokeapi.co/api/v2/";


    public RetrofitConfig(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public IPokeService getPokeService(){
        return this.retrofit.create(IPokeService.class);
    }

}
