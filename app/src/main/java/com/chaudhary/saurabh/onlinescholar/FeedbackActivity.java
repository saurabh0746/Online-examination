package com.chaudhary.saurabh.onlinescholar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbackActivity extends AppCompatActivity {

    @BindView(R.id.feedback)
    EditText feedbackET;
    @BindView(R.id.suggestion)
    EditText suggestionET;
    @BindView(R.id.rating)
    RatingBar ratingBar;
    @BindView(R.id.rating_tv)
    TextView ratingTV;
    @BindView(R.id.feedback_submit_btn)
    Button submit_feedback;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String feedbackString;
    String suggestionString;
    String ratedValue;
    int submitCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.feedback_title);

        submit_feedback.setEnabled(false);
        submit_feedback.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        feedbackET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                feedbackString = feedbackET.getText().toString().trim();
                if (feedbackString.length() > 0) {
                    submit_feedback.setEnabled(true);
                    submit_feedback.setBackgroundColor(getResources().getColor(R.color.colorBlue900));
                } else {
                    submit_feedback.setEnabled(false);
                    submit_feedback.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratedValue = String.valueOf(rating);
                String ratingString = getString(R.string.rating_tv) + ratedValue +
                        "/" + ratingBar.getNumStars();
                ratingTV.setText(ratingString);
            }
        });
        submit_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suggestionString = suggestionET.getText().toString().trim();
                String feedbackDb = "T:" + UserDetailsActivity.teamNo + " F:" + feedbackString +
                        " S: " + suggestionString + " R:" + ratedValue;
                if (submitCount < 2) {
                    UserDetailsActivity.mDatabaseReferenceFeedback.push().setValue(feedbackDb);
                    submitCount++;
                    Toast.makeText(FeedbackActivity.this, getString(R.string.feedback_submit_success),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FeedbackActivity.this, getString(R.string.feedback_submit_failed),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
