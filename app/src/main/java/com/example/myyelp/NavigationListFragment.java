package com.example.myyelp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.ListView;
import java.util.ArrayList;

public class NavigationListFragment extends Fragment {

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

        return view;
    }

    public void updateNavigationList (ArrayList<YelpResponse> data){
        yelpResponses.clear();
        yelpResponses.addAll(data);
        adapter.notifyDataSetChanged();
    }
}