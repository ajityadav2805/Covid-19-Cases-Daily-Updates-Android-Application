package com.example.coronavirustracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter mAdapter;
    private ArrayList<ItemClass> mList;
    private RequestQueue mRequestQueue;
    private LoadingDialog mLoadingAlertDialog;

    private AdView mAdView;

    private Map<String, Object> mStateMapping;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_container);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mLoadingAlertDialog = new LoadingDialog(this);

        mRequestQueue = Volley.newRequestQueue(this);

        initializeGoogleAdsSDKBanner();

        Objects.requireNonNull(getSupportActionBar()).setTitle("Current Covid-19 status in India");

        init__stateMapping();

        parseJson();




    }

    public void init__stateMapping() {
        mStateMapping = new HashMap<>();

        mStateMapping.put("Maharashtra", "Maharashtra");
        mStateMapping.put("Andhra Pradesh", "Andhra Pradesh");
        mStateMapping.put("Tamil Nadu", "Tamil Nadu");
        mStateMapping.put("Karnataka", "Karnataka");
        mStateMapping.put("Uttar Pradesh", "Uttar Pradesh");
        mStateMapping.put("Delhi", "Delhi");
        mStateMapping.put("West Bengal", "West Bengal");
        mStateMapping.put("Bihar", "Bihar");
        mStateMapping.put("Telangana", "Telengana");
        mStateMapping.put("Assam", "Assam");
        mStateMapping.put("Odisha", "Odisha");
        mStateMapping.put("Gujarat", "Gujarat");
        mStateMapping.put("Rajasthan", "Rajasthan");
        mStateMapping.put("Kerala", "Kerala");
        mStateMapping.put("Haryana", "Haryana");
        mStateMapping.put("Madhya Pradesh", "Madhya Pradesh");
        mStateMapping.put("Punjab", "Punjab");
        mStateMapping.put("Jharkhand", "Jharkhand");
        mStateMapping.put("Chhattisgarh", "Chhattisgarh");
        mStateMapping.put("Jammu and Kashmir", "Jammu and Kashmir");
        mStateMapping.put("Uttarakhand", "Uttarakhand");
        mStateMapping.put("Goa", "Goa");
        mStateMapping.put("Puducherry", "Puducherry");
        mStateMapping.put("Tripura", "Tripura");
        mStateMapping.put("Himachal Pradesh", "Himachal Pradesh");
        mStateMapping.put("Manipur", "Manipur");
        mStateMapping.put("Chandigarh", "Chandigarh");
        mStateMapping.put("Arunachal Pradesh", "Arunachal Pradesh");
        mStateMapping.put("Nagaland", "Nagaland");
        mStateMapping.put("Andaman and Nicobar Islands", "Andaman and Nicobar Islands");
        mStateMapping.put("Ladakh", "Ladakh");
        mStateMapping.put("Sikkim", "Sikkim");
        mStateMapping.put("Daman and Diu", "Dadra and Nagar Haveli and Daman and Diu");
        mStateMapping.put("Mizoram", "Mizoram");
        mStateMapping.put("Lakshadweep", "Lakshadweep");
    }

    private void parseJson() {
        mLoadingAlertDialog.startLoadingDialog();
        String url = "https://api.covidindiatracker.com/state_data.json";
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);
                        long activeCase = object.getLong("active");
                        long recoveredCase = object.getLong("recovered");
                        long confirmedCases = object.getLong("confirmed");
                        long deathsCases = object.getLong("deaths");
                        String state = object.getString("state");
                        mList.add(new ItemClass(activeCase, recoveredCase, confirmedCases, deathsCases, state));
                    }

                    mAdapter = new ItemAdapter(MainActivity.this, mList);
                    recyclerView.setAdapter(mAdapter);

                    mAdapter.setOnItemClick(new ItemAdapter.OnItemClick() {
                        @Override
                        public void onClick(int position) {
                            Intent intent = new Intent(MainActivity.this, StateWiseActivity.class);
                            if (mList != null && mList.get(position) != null) {
                                String state = (String) mStateMapping.get(mList.get(position).getState());
                                intent.putExtra("State", state);
                                startActivity(intent);
                            }
                        }
                    });

                    mLoadingAlertDialog.dismissLoadingDialog();

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

    private void initializeGoogleAdsSDKBanner() {

        mAdView = findViewById(R.id.adView_main_activity);
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