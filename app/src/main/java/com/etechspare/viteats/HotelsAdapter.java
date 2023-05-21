package com.etechspare.viteats;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.MyViewHolder> {

    private List<Restaurant> peacePoints;
    private Activity context;
    private DBHelper dbHelper;

    public HotelsAdapter(List<Restaurant> peacePoints, Activity context, DBHelper dbHelper) {
        this.peacePoints = peacePoints;
        this.context = context;
        this.dbHelper = dbHelper;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_design_resturant, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        Restaurant restaurant = peacePoints.get(position);
        try {
            if (restaurant != null) {
                holder.tv_title.setText(restaurant.restaurantName);
                holder.tvAddress.setText(restaurant.address.street+ "  -  "+ restaurant.address.city);

                ArrayList<ReviewModel> reviewList = dbHelper.getReviewsData(restaurant.id);

                double avg = 0.0;
                for (ReviewModel reviewModel:reviewList) {
                    avg+= Double.parseDouble(reviewModel.review);
                }
                avg = avg/reviewList.size();
                holder.tvReviews.setText(new DecimalFormat("##.##").format(avg)+" / 5");
                holder.ratingBar.setRating(Float.parseFloat(String.valueOf(avg)));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String type = SharedPrefHelper.readString(context,"userType");
        if(type.equals("1")){
            holder.root.setOnClickListener(view -> {
                Intent intent=new Intent(context, ReviewActivity.class);
                intent.putExtra("hotel_id",restaurant.id);
                context.startActivity(intent);
            });
        }

    }
    @Override
    public int getItemCount() {
        return peacePoints.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout root;
        TextView tv_title, tvAddress, tvReviews;
        AppCompatRatingBar ratingBar;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tvReviews = itemView.findViewById(R.id.tv_reviews);
            root = itemView.findViewById(R.id.root);
            tvAddress = itemView.findViewById(R.id.tv_address);
            ratingBar = itemView.findViewById(R.id.tv_stars);
        }
    }

}
