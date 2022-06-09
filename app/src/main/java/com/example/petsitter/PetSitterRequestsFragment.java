package com.example.petsitter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PetSitterRequestsFragment extends Fragment {

    private String[] titles = new String[]{"Booked", "Applied", "Past"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_pet_sitter_requests, container, false);
        TabLayout tabLayout = root.findViewById(R.id.tab_layout);
        ViewPager2 viewPager2 = root.findViewById(R.id.view_pager);
        ViewPagerFragmentAdapter adapter= new ViewPagerFragmentAdapter((PetSitterMainActivity) getActivity());
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout,viewPager2, (tab, position) ->
                tab.setText(titles[position])).attach();

        return root;
    }

    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new PetSitterBookedFragment();
                case 1:
                    return new PetSitterAppliedFragment();
                case 2:
                    return new PetSitterPastFragment();
            }
            return new PetSitterAppliedFragment();
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}