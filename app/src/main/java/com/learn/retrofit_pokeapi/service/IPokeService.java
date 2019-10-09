package com.learn.retrofit_pokeapi.service;

import com.learn.retrofit_pokeapi.model.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPokeService {

    @GET("pokemon/{name}/")
    Call<Pokemon> getPokemonByName(@Path("name") String name);

    @GET("pokemon/{id}/")
    Call<Pokemon> getPokemonById(@Path("id") int id);
}
