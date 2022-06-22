package com.example.myyelp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.Comparator;

public class Adapter extends ArrayAdapter <YelpResponse> {

    private Context mContext;
    private int mResource;
    private ArrayList<YelpResponse> mResponses;

    public Adapter(@NonNull Context context, int resource, @NonNull ArrayList<YelpResponse> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mResponses = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);
        YelpResponse response = mResponses.get(position);

        ImageView restaurant_image_list = convertView.findViewById(R.id.restaurant_image_list);
        TextView restaurant_name_list = convertView.findViewById(R.id.restaurant_name_list);
        RatingBar rating_bar_list = convertView.findViewById(R.id.rating_bar_list);
        TextView price_list = convertView.findViewById(R.id.price_list);
        TextView category_type_list = convertView.findViewById(R.id.category_type_list);
        TextView phone_number_list = convertView.findViewById(R.id.phone_number_list);
        TextView address_list = convertView.findViewById(R.id.address_list);

        Glide.with(mContext).load(response.mImage).into(restaurant_image_list);
        restaurant_name_list.setText(position + 1 + ". " + response.mName);
        rating_bar_list.setRating(response.mRating);
        price_list.setText(response.mPrice);
        category_type_list.setText(response.mCategories.get(0).mTitle);
        phone_number_list.setText(response.mPhone);
        address_list.setText(response.mLocation.mAddress + ", " +response.mLocation.mCity +", " + response.mLocation.mState);

        return convertView;
    }

    public static  Comparator<YelpResponse> RatingComparator = new Comparator<YelpResponse>() {
        @Override
        public int compare(YelpResponse t1, YelpResponse t2) {
            return Double.compare(t2.mRating, t1.mRating);
        }
    };

    public static Comparator<YelpResponse> PriceComparator = new Comparator<YelpResponse>() {
        @Override
        public int compare(YelpResponse t1, YelpResponse t2) {

            return Integer.compare(t1.mPrice.length(), t2.mPrice.length());
        }
    };
}
