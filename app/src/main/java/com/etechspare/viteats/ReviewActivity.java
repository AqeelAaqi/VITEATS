package com.etechspare.viteats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewActivity extends AppCompatActivity {

    RatingBar mRatingBar ;
    TextView mRatingScale ;
    EditText mFeedback ;
    Button mSendFeedback ;
    String hotel_id = "";

    DBHelper dbHelper =new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


        mRatingBar = findViewById(R.id.ratingBar);
        mRatingScale = findViewById(R.id.tvRatingScale);
        mFeedback = findViewById(R.id.etFeedback);
        mSendFeedback = findViewById(R.id.btnSubmit);

        hotel_id = getIntent().getStringExtra("hotel_id");

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Very bad");
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });

        mSendFeedback.setOnClickListener(view -> {
            if (mFeedback.getText().toString().isEmpty()) {
                Toast.makeText(ReviewActivity.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
            } else {
                dbHelper.insertReviewData("User", mRatingBar.getRating()+"", hotel_id,mFeedback.getText().toString());
                Toast.makeText(ReviewActivity.this, mRatingBar.getRating()+" Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();
                mFeedback.setText("");
                mRatingBar.setRating(0);
            }
        });
    }
}