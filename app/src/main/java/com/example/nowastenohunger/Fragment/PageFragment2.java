package com.example.nowastenohunger.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nowastenohunger.R;

public class PageFragment2 extends Fragment
{
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        ViewGroup rooView = (ViewGroup)inflater.inflate(R.layout.slide_2, container, false);

        return rooView;
    }
}
