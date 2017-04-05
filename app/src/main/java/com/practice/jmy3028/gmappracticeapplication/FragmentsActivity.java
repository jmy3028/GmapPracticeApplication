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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        weatherFragment = new WeatherFragment();
        listFragment = new ListFragment();

        viewPager = (ViewPager) findViewById(R.id.frgment_view_pager);

        //메인엑티비티에서 data값을 전달 받음.
        intent = getIntent();
        WeatherMain result = (WeatherMain) intent.getSerializableExtra("Result1");
        Example result2 = (Example) intent.getSerializableExtra("Result2");

        weatherFragment = WeatherFragment.newInstance(result);
        listFragment = ListFragment.newInstance(result2);
        FragmentsActivity.FragmentsPagerAdapter pagerAdapter = new FragmentsActivity.FragmentsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);



    }

    //PagerView를 활용해서 두개의 fragment 를 분할 해서 표현함.
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

}
