package com.bol.lookup.activities;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bol.lookup.R;
import com.bol.lookup.model.SendReview;
import com.bol.lookup.rest.ApiClientReviews;
import com.bol.lookup.rest.ApiInterfaceReviews;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {

    Toolbar toolbar;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private EditText inputName, inputEmail, inputTitle, inputReview;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutTitle, inputLayoutReview;
    private RatingBar rating;

    int ratingValue = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.form_elements_review);

        pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        editor = pref.edit();

        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        ImageView back = (ImageView) findViewById(R.id.toolbar_close);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/webfont.ttf");
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setTypeface(face);
        title.setTextSize(18);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button submit = (Button) findViewById(R.id.rg_send);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });


        TextView productName = (TextView) findViewById(R.id.productName);
        productName.setText(pref.getString("productName", ""));
        productName.setTypeface(null, Typeface.BOLD);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutReview = (TextInputLayout) findViewById(R.id.input_layout_review);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutTitle = (TextInputLayout) findViewById(R.id.input_layout_title);

        inputName = (EditText) findViewById(R.id.input_name);
        inputReview = (EditText) findViewById(R.id.input_review);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputTitle = (EditText) findViewById(R.id.input_title);

        rating = (RatingBar) findViewById(R.id.rating);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputReview.addTextChangedListener(new MyTextWatcher(inputReview));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputTitle.addTextChangedListener(new MyTextWatcher(inputTitle));


        addListenerOnRatingBar();

        final Drawable img = this.getResources().getDrawable(R.drawable.ic_action_close);

        inputName.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    inputName.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                    inputName.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {


                            if (inputName.getCompoundDrawables()[2] == null)
                                return false;

                            if (event.getAction() != MotionEvent.ACTION_UP)
                                return false;

                            if (event.getX() > inputName.getWidth() - inputName.getPaddingRight() - img.getIntrinsicWidth()) {
                                inputName.setText("");

                            }


                            return false;
                        }
                    });


                } else {

                    inputName.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                }
            }
        });

        inputReview.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    inputReview.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                    inputReview.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {


                            if (inputReview.getCompoundDrawables()[2] == null)
                                return false;

                            if (event.getAction() != MotionEvent.ACTION_UP)
                                return false;

                            if (event.getX() > inputReview.getWidth() - inputReview.getPaddingRight() - img.getIntrinsicWidth()) {
                                inputReview.setText("");

                            }


                            return false;
                        }
                    });


                } else {

                    inputReview.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                }
            }
        });

        inputEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    inputEmail.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                    inputEmail.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {


                            if (inputEmail.getCompoundDrawables()[2] == null)
                                return false;

                            if (event.getAction() != MotionEvent.ACTION_UP)
                                return false;

                            if (event.getX() > inputEmail.getWidth() - inputEmail.getPaddingRight() - img.getIntrinsicWidth()) {
                                inputEmail.setText("");

                            }


                            return false;
                        }
                    });


                } else {

                    inputEmail.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                }
            }
        });
        inputTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    inputTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                    inputTitle.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {


                            if (inputTitle.getCompoundDrawables()[2] == null)
                                return false;

                            if (event.getAction() != MotionEvent.ACTION_UP)
                                return false;

                            if (event.getX() > inputTitle.getWidth() - inputTitle.getPaddingRight() - img.getIntrinsicWidth()) {
                                inputTitle.setText("");

                            }


                            return false;
                        }
                    });


                } else {

                    inputTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                }
            }
        });


    }

    /**
     * Validating form
     */
    private void submitForm() {

        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validateTitle()) {
            return;
        }

        if (!validateReview()) {
            return;
        }

        if (ratingValue == 0) {
            Toast toast = Toast.makeText(this, "Please give a star rating", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }


        String name = inputName.getText().toString().trim();
        String title = inputTitle.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String reviewDesc = inputReview.getText().toString().trim();

        ApiInterfaceReviews apiService =
                ApiClientReviews.getClient().create(ApiInterfaceReviews.class);

        String productID = pref.getString("productId", "");


        SendReview review = new SendReview(productID, name, title, reviewDesc, ratingValue, email);
        Call<SendReview> call = apiService.createReview(review);
        call.enqueue(new Callback<SendReview>() {
            @Override
            public void onResponse(Call<SendReview> call, Response<SendReview> response) {
                int statusCode = response.code();

                System.out.println(statusCode);

                Toast toast = Toast.makeText(getApplicationContext(), "Review submitted successfully", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                finish();
            }

            @Override
            public void onFailure(Call<SendReview> call, Throwable t) {

                Toast toast = Toast.makeText(getApplicationContext(), "Error submitting review", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

    }


    @Override
    public void onBackPressed() {


        finish();


    }

    private void addListenerOnRatingBar() {

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                ratingValue = Math.round(rating);

            }
        });
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_review:
                    validateReview();
                    break;
                case R.id.input_title:
                    validateTitle();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;

            }
        }
    }


    private boolean validateName() {
        String name = inputName.getText().toString().trim();

        if (name.isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateTitle() {
        String title = inputTitle.getText().toString().trim();

        if (title.isEmpty()) {
            inputLayoutTitle.setError(getString(R.string.err_msg_title));
            requestFocus(inputTitle);
            return false;
        } else {
            inputLayoutTitle.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty()) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else if (!isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email_valid));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateReview() {
        String review = inputReview.getText().toString().trim();

        if (review.isEmpty()) {
            inputLayoutReview.setError(getString(R.string.err_msg_review));
            requestFocus(inputReview);
            return false;
        } else {
            inputLayoutReview.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


}
