package de.riemann.week2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView reView;
    RecyclerView.Adapter rvAdapter;
    RecyclerView.LayoutManager rvLayoutManager;
    static ArrayList<String> pokemonImage;
    static ArrayList<String> pokemonName;
    static ArrayList<String> pokemonWeight;
    JSONObject obj = null;
    JSONArray pokeArray = null;

    //ImageView imageViewTest = findViewById(R.id.imageViewTest);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);



        //init Json
        try {
            obj = new JSONObject(readJSONFromAsset());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            pokeArray = obj.getJSONArray("pokemon");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //init Arrays
        pokemonImage = new ArrayList<>();
        pokemonName = new ArrayList<>();
        pokemonWeight = new ArrayList<>();
        fillArrays(); //Fills the arrays with Data


        //init Adapter and Layout Manager
        rvAdapter = new PokemonAdapter();
        rvLayoutManager = new LinearLayoutManager(this);

        //Config the recView
        reView = (RecyclerView) findViewById(R.id.reView);
        reView.setLayoutManager(rvLayoutManager);
        reView.setAdapter(rvAdapter);

    }

    public  String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("poke.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void fillArrays(){
        for (int i = 0; i < pokeArray.length(); i++) {
            JSONObject pokeDetail = null;
            try {
                pokeDetail = pokeArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                pokemonImage.add(pokeDetail.getString("img"));
                pokemonName.add(pokeDetail.getString("name"));
                pokemonWeight.add(pokeDetail.getString("weight"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}
