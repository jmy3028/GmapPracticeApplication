package com.practice.jmy3028.gmappracticeapplication;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.practice.jmy3028.gmappracticeapplication.api.GetApi;
import com.practice.jmy3028.gmappracticeapplication.api.RetrofitUtil;
import com.practice.jmy3028.gmappracticeapplication.fragments.ListFragment;
import com.practice.jmy3028.gmappracticeapplication.fragments.WeatherFragment;
import com.practice.jmy3028.gmappracticeapplication.model.WeatherMain;
import com.practice.jmy3028.gmappracticeapplication.model2.Example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentsActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private WeatherFragment weatherFragment;
    private ListFragment listFragment;
    private Intent intent;
    private GetApi mGetApi;
    private List<WeatherFragment> mData;


    String BASE_APPID = "1de08f4347f09c9540f906a810f95b03";
    private GetApi mGetApi2;
    private double mLat;
    private double mLon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        weatherFragment = new WeatherFragment();
        listFragment = new ListFragment();

        viewPager = (ViewPager) findViewById(R.id.frgment_view_pager);





        intent = getIntent();
        mLat = intent.getExtras().getDouble("lat");
        mLon = intent.getExtras().getDouble("lon");
//        mlatlon(mLat,mLon);

        mGetApi = new RetrofitUtil().getUserApi();
        Call<WeatherMain> call = mGetApi.latlon(BASE_APPID,
                mLat, mLon);

        call.enqueue(new Callback<WeatherMain>() {

            @Override
            public void onResponse(Call<WeatherMain> call, Response<WeatherMain> response) {
                final WeatherMain result = response.body();
                weatherFragment = WeatherFragment.newInstance(result);

            }

            @Override
            public void onFailure(Call<WeatherMain> call, Throwable throwable) {
            }
        });

//                mGetApi2 = new RetrofitUtil().getUserApi();
        Call<Example> call2 = mGetApi.latlon2(BASE_APPID,
                mLat, mLon);

        call2.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call,
                                   Response<Example> response) {
                final Example result2 = response.body();
                listFragment = ListFragment.newInstance(result2);
                FragmentsPagerAdapter pagerAdapter = new FragmentsPagerAdapter(getSupportFragmentManager());
                viewPager.setAdapter(pagerAdapter);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(FragmentsActivity.this, "실패", Toast.LENGTH_SHORT).show();
            }

        });


    }

    private class FragmentsPagerAdapter extends FragmentPagerAdapter {

        public FragmentsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return weatherFragment;
                case 1:
                    return listFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

    }

    public void mlatlon(double mLat, double mLon){




    }
}
