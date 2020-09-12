package com.example.coronavirustracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DayWiseActivity extends AppCompatActivity {

    private static final String TAG = DayWiseActivity.class.getSimpleName();
    private StateClassAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<StateClass> mList;

    private AdView mAdView;

    private List<StateClass> mTempList;
    private RequestQueue mRequestQueue;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_wise);

        mRecyclerView = findViewById(R.id.recycler_view_container_day_wise);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mTempList = new ArrayList<>();
        mLoadingDialog = new LoadingDialog(this);

        mRequestQueue = Volley.newRequestQueue(this);

        initializeGoogleAdsSDKBanner();

        Objects.requireNonNull(getSupportActionBar()).setTitle("Daily Cases In India");


        parseJson();

    }


    private void parseJson() {

        mLoadingDialog.startLoadingDialog();


        String url = "https://api.rootnet.in/covid19-in/stats/history";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray data = response.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {

                        JSONObject dayWise = data.getJSONObject(i);

                        String currentDate = dayWise.getString("day");

                        JSONObject Cases = dayWise.getJSONObject("summary");


                        long totalCases = Cases.getLong("total");
                        long confirmedCasesIndian = Cases.getLong("confirmedCasesIndian");
                        long confirmedCasesForeign = Cases.getLong("confirmedCasesForeign");
                        long discharged = Cases.getLong("discharged");
                        long deaths = Cases.getLong("deaths");

                        if (mList.size() == 0) {
                            mList.add(new StateClass(totalCases, confirmedCasesIndian, confirmedCasesForeign
                                    , deaths, discharged, null, currentDate));
                        } else {
                            Log.d(TAG, "onResponse: ================" + totalCases + "======" + (totalCases - mTempList.get(mTempList.size() - 1).getTotalCases()) + " Date: " + currentDate);

                            mList.add(new StateClass(totalCases - (mTempList.get(mTempList.size() - 1).getTotalCases()) >= 0 ? totalCases - (mTempList.get(mTempList.size() - 1).getTotalCases()) : 0
                                    , confirmedCasesIndian - mTempList.get(mTempList.size() - 1).getConfirmedIndian() >= 0 ? confirmedCasesIndian - mTempList.get(mTempList.size() - 1).getConfirmedIndian() : 0
                                    , confirmedCasesForeign - mTempList.get(mTempList.size() - 1).getConfirmedForeign() >= 0 ? confirmedCasesForeign - mTempList.get(mTempList.size() - 1).getConfirmedForeign() : 0
                                    , deaths - mTempList.get(mTempList.size() - 1).getDeaths() >= 0 ? deaths - mTempList.get(mTempList.size() - 1).getDeaths() : 0
                                    , discharged - mTempList.get(mTempList.size() - 1).getDischarge() >= 0 ? discharged - mTempList.get(mTempList.size() - 1).getDischarge() : 0
                                    , null, currentDate));
                        }
                        mTempList.add(new StateClass(totalCases, confirmedCasesIndian, confirmedCasesForeign
                                , deaths, discharged, null, currentDate));

                    }


                    Collections.reverse(mList);

                    mAdapter = new StateClassAdapter(DayWiseActivity.this, mList);
                    mRecyclerView.setAdapter(mAdapter);

                    mLoadingDialog.dismissLoadingDialog();

                } catch (JSONException e) {
                    e.printStackTrace();
                    mLoadingDialog.dismissLoadingDialog();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                mLoadingDialog.dismissLoadingDialog();
            }
        });

        mRequestQueue.add(request);

    }


    private void initializeGoogleAdsSDKBanner() {

        mAdView = findViewById(R.id.adView_day_activity);
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
}