package com.example.coronavirustracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class ParentActivity extends AppCompatActivity {
    public static final String TAG=ParentActivity.class.getSimpleName();

    private BottomSheetDialog mBottomSheetDialog;

    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    private RequestQueue mRequestQueue;
    private LoadingDialog mLoadingAlertDialog;
    private TextView mTotalCoronaCases, mTotalRecoveredCases, mTotalDeaths, mTotalClosedCases, mRecoveredClosed, mDeathsClosed, mTotalDeathsPercent, mTotalRecoveredPercent;
    private long totalCoronaCases, totalRecoveredCases, totalDeaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        mTotalCoronaCases = findViewById(R.id.total_cases_corona);
        mTotalRecoveredCases = findViewById(R.id.total_recovered_corona_case);
        mTotalDeaths = findViewById(R.id.total_deaths_corona_case);

        mTotalClosedCases = findViewById(R.id.total_outcomes);
        mRecoveredClosed = findViewById(R.id.total_recovered_cases_closed);
        mDeathsClosed = findViewById(R.id.total_deaths_closed);

        mTotalRecoveredPercent = findViewById(R.id.percentage_recover_discharge);
        mTotalDeathsPercent = findViewById(R.id.percentage_deaths);
        mLoadingAlertDialog=new LoadingDialog(this);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Covid-19 overall cases in India");


        mRequestQueue = Volley.newRequestQueue(this);

        //ads initialization
        initializeGoogleAdsSDKBanner();
        initializeInterstitialAdSDK();

        parseJsonUsingVolley();

        findViewById(R.id.navigation_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeBottomSheetDialog();
            }
        });
    }

    private void parseJsonUsingVolley() {
        String url = "https://api.covidindiatracker.com/total.json";
        mLoadingAlertDialog.startLoadingDialog();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    totalCoronaCases = response.getLong("confirmed");
                    totalRecoveredCases = response.getLong("recovered");
                    totalDeaths = response.getLong("deaths");

                    Log.d(TAG, "onResponse: "+response.toString());
                    Log.d(TAG, "\nonResponse: =================="+totalCoronaCases);
                    assignValues();
                } catch (JSONException e) {
                    e.printStackTrace();
                    mLoadingAlertDialog.dismissLoadingDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                mLoadingAlertDialog.dismissLoadingDialog();
            }
        });
        mRequestQueue.add(request);
    }

    private void assignValues() {
        mTotalCoronaCases.setText(String.valueOf(totalCoronaCases));
        mTotalRecoveredCases.setText(String.valueOf(totalRecoveredCases));
        mTotalDeaths.setText(String.valueOf(totalDeaths));

        mTotalClosedCases.setText(String.valueOf(totalRecoveredCases + totalDeaths));

        mRecoveredClosed.setText(String.valueOf(totalRecoveredCases));
        mDeathsClosed.setText(String.valueOf(totalDeaths));

        long totalDischarge = totalRecoveredCases + totalDeaths;
        if (totalDischarge != 0) {
            double percent=Math.round(((totalRecoveredCases*100.0f) / totalDischarge)*100.0)/100.0;
            double percentDec=Math.round(((totalDeaths*100.0f) / totalDischarge)*100.0)/100.0;

            String strRec = "( " +percent+ "% )";
            String strDed = "( " +percentDec+ "% )";
            mTotalRecoveredPercent.setText(strRec);
            mTotalDeathsPercent.setText(strDed);
        }

        mLoadingAlertDialog.dismissLoadingDialog();

    }

    private void initializeBottomSheetDialog() {


        View view = LayoutInflater.from(this).inflate(R.layout.categories, null);

        LinearLayout dayWise = view.findViewById(R.id.day_wise);
        LinearLayout stateWise = view.findViewById(R.id.state_wise);

        dayWise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ParentActivity.this,DayWiseActivity.class));
                mBottomSheetDialog.dismiss();
            }
        });

        stateWise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ParentActivity.this,MainActivity.class));
                mBottomSheetDialog.dismiss();
            }
        });

        mBottomSheetDialog = new BottomSheetDialog(this,R.style.BottomSheetDialogTheme);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.show();
    }

    private void initializeGoogleAdsSDKBanner() {

        mAdView = findViewById(R.id.adView_parent_activity);
        AdRequest.Builder builder = new AdRequest.Builder();


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView.loadAd(builder.build());

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                mAdView.loadAd(new AdRequest.Builder().build());
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

            }

        });
    }



    private void initializeInterstitialAdSDK() {
        mInterstitialAd = new InterstitialAd(this);

        mInterstitialAd.setAdUnitId(getString(R.string.interstitialAdUnitId));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finishAffinity();
                }
            });
        }else{
            super.onBackPressed();
            finishAffinity();
        }
    }
}