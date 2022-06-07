package com.example.petsitter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petsitter.databinding.FragmentRequestsBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class PetSitterRequestsFragment extends Fragment {

    private String[] titles = new String[]{"Applied", "Upcoming", "Past"};
    FragmentRequestsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_pet_sitter_requests, container, false);
        binding = FragmentRequestsBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();

        init();
        return v;
    }

    private void init() {
        // removing toolbar elevation
        PetSitterMainActivity activity = (PetSitterMainActivity) getActivity();
        binding.viewPager.setAdapter(new PetSitterRequestsFragment.ViewPagerFragmentAdapter(activity));

        // attaching tab mediator
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> tab.setText(titles[position])).attach();
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
                    return new PetSitterAppliedFragment();
                case 1:
                    return new PetSitterUpcomingFragment();
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