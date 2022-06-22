package com.example.myyelp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Collections;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment listFragment = new ListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.list_container, listFragment).commit();

        searchView = findViewById(R.id.search_bar);
        spinner = findViewById(R.id.spinner);


        YelpAPI yelpAPI = new YelpClient().build();
        yelpAPI.getRestaurants("food", "montreal", 20).enqueue(new Callback<BodyResponse>() {
            @Override
            public void onResponse(Call<BodyResponse> call, Response<BodyResponse> response) {
                listFragment.updateList(response.body().mBusinesses);

            }
            @Override
            public void onFailure(Call<BodyResponse> call, Throwable t) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                yelpAPI.getRestaurants(query, "montreal", 20).enqueue(new Callback<BodyResponse>() {
                    @Override
                    public void onResponse(Call<BodyResponse> call, Response<BodyResponse> response) {
                        listFragment.updateList(response.body().mBusinesses);

                    }
                    @Override
                    public void onFailure(Call<BodyResponse> call, Throwable t) {

                    }
                });
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {


                    yelpAPI.getRestaurants("food", "montreal", 20).enqueue(new Callback<BodyResponse>() {
                        @Override
                        public void onResponse(Call<BodyResponse> call, Response<BodyResponse> response) {

                            listFragment.updateList(response.body().mBusinesses);

                        }

                        @Override
                        public void onFailure(Call<BodyResponse> call, Throwable t) {

                        }
                    });

                } else if (i == 1) {

                    ArrayList<YelpResponse> temp = new ArrayList<>();
                    temp.addAll(listFragment.getYelpResponses());
                    Collections.sort(temp, Adapter.RatingComparator);
                    listFragment.updateList(temp);

                } else if (i == 2) {
                    ArrayList<YelpResponse> temp = new ArrayList<>();
                    ArrayList<YelpResponse> temp2 = new ArrayList<>();
                    ArrayList<YelpResponse> temp3 = new ArrayList<>();
                    for (int position = 0; position < listFragment.getYelpResponses().size(); ++position){
                        if (listFragment.getYelpResponses().get(position).mPrice == null){
                            temp.add( listFragment.getYelpResponses().get(position));
                        }
                    }
                    for (int position = 0; position < listFragment.getYelpResponses().size(); ++position){
                        if (listFragment.getYelpResponses().get(position).mPrice != null){
                            temp2.add( listFragment.getYelpResponses().get(position));
                        }
                    }

                    Collections.sort(temp2, Adapter.PriceComparator);
                    temp3.addAll(temp);
                    temp3.addAll(temp2);

                    listFragment.updateList(temp3);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}