package com.example.yuhaolu.behancedisplay.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.discover_popular_creatives.DiscoverCreativesFragment;
import com.example.yuhaolu.behancedisplay.view.discover_popular_fields.DiscoverFieldsFragment;

public class DiscoverFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        tabLayout = view.findViewById(R.id.view_pager_tab);
        viewPager = view.findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(R.string.view_pager_follower);
        tabLayout.getTabAt(1).setText(R.string.view_pager_fields);
        return view;
    }

    public static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private static final int ITEMS_NUM = 2;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0) {
                return DiscoverCreativesFragment.newInstance();
            } else {
                return DiscoverFieldsFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return ITEMS_NUM;
        }
    }

}
