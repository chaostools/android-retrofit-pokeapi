package com.learn.retrofit_pokeapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.learn.retrofit_pokeapi.R;
import com.learn.retrofit_pokeapi.model.Pokemon;
import com.learn.retrofit_pokeapi.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button buttonSearch;
    EditText etSearch;
    TextView tvPokemonName, tvPokemonId, tvPokemonHeight;
    RetrofitConfig rfConfig;
    InputMethodManager inputManager;

    Call<Pokemon> request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rfConfig = new RetrofitConfig();
        inputManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);


        etSearch = findViewById(R.id.editText_search);
        buttonSearch = findViewById(R.id.button_search);
        tvPokemonName = findViewById(R.id.tv_pokename);
        tvPokemonId = findViewById(R.id.tv_pokeid);
        tvPokemonHeight = findViewById(R.id.tv_pokeheight);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Request by Name
                request = rfConfig.getPokeService().getPokemonByName(etSearch.getText().toString());
                //Request by Id
//                request = rfConfig.getPokeService().getPokemonById(
////                        Integer.parseInt(etSearch.getText().toString()));

                request.enqueue(new Callback<Pokemon>() {
                    @Override
                    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                        Pokemon pokemon = response.body();
                        tvPokemonName.setText(pokemon.getName());
                        tvPokemonId.setText(Integer.toString(pokemon.getId()));
                        tvPokemonHeight.setText(Float.toString(pokemon.getHeight()));
                        etSearch.clearFocus();
                    }

                    @Override
                    public void onFailure(Call<Pokemon> call, Throwable t) {
                        Log.e("REQUEST ERROR", "Fail to find Pokemon. " + t.getMessage());
                        Toast.makeText(MainActivity.this, "Fail to find Pokemon.", Toast.LENGTH_LONG).show();
                        etSearch.setText("");
                    }
                });
                inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
    }


}
