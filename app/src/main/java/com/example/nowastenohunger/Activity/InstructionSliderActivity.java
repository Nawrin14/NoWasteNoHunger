package com.example.nowastenohunger.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.nowastenohunger.Adapter.SlidePagerAdapter;
import com.example.nowastenohunger.Fragment.PageFragment;
import com.example.nowastenohunger.Fragment.PageFragment2;
import com.example.nowastenohunger.Fragment.PageFragment3;
import com.example.nowastenohunger.R;

import java.util.ArrayList;
import java.util.List;

public class InstructionSliderActivity extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_slider);

        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragment());
        list.add(new PageFragment2());
        list.add(new PageFragment3());

        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);

        pager.setAdapter(pagerAdapter);
    }
}