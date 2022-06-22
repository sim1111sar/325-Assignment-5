package com.example.myyelp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class ListFragment extends Fragment {

    private ArrayList<YelpResponse> yelpResponses;
    private Adapter adapter;
    private ListView listView;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        context =  inflater.getContext();
        listView = view.findViewById(R.id.list_view);
        yelpResponses = new ArrayList<>();
        adapter = new Adapter(context, R.layout.fragment_list_customize, yelpResponses);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int position = i;

                new AlertDialog.Builder(context)
                        .setTitle("Add to Favorite?")
                        .setMessage("Do you want to add this item to favorite?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                yelpResponses.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
        return view;
    }

    public void updateList (ArrayList<YelpResponse> data){
        yelpResponses.clear();
        yelpResponses.addAll(data);
        adapter.notifyDataSetChanged();
    }

    public ArrayList<YelpResponse> getYelpResponses() {
        return yelpResponses;
    }

    public void setYelpResponses(ArrayList<YelpResponse> yelpResponses) {
        this.yelpResponses = yelpResponses;
    }



}