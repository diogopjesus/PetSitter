package com.example.petsitter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.petsitter.databinding.FragmentRequestsBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class RequestsFragment extends Fragment {


    // tab titles
    private String[] titles = new String[]{"Ongoing", "Upcoming", "Past"};
    FragmentRequestsBinding binding;

    FloatingActionButton mAddPetHosting, mAddPetSitting, mAddDogWalking;

    ExtendedFloatingActionButton mAddFab;

    TextView mAddPetHostingText, mAddPetSittingText, mAddDogWalkingText;

    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_requests, container, false);

        binding = FragmentRequestsBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        init();
        mAddFab = v.findViewById(R.id.add_fab);

        mAddDogWalking = v.findViewById(R.id.add_dog_walking_fab);
        mAddPetSitting = v.findViewById(R.id.add_petSitting_fab);
        mAddPetHosting = v.findViewById(R.id.add_petHosting_fab);

        mAddDogWalkingText = v.findViewById(R.id.add_dog_walking_text);
        mAddPetSittingText = v.findViewById(R.id.add_petSitting_text);
        mAddPetHostingText = v.findViewById(R.id.add_petHosting_text);

        mAddDogWalking.setVisibility(View.GONE);
        mAddPetSitting.setVisibility(View.GONE);
        mAddPetHosting.setVisibility(View.GONE);
        mAddDogWalkingText.setVisibility(View.GONE);
        mAddPetSittingText.setVisibility(View.GONE);
        mAddPetHostingText.setVisibility(View.GONE);

        isAllFabsVisible = false;

        mAddFab.shrink();

        mAddFab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {

                            mAddDogWalking.show();
                            mAddPetSitting.show();
                            mAddPetHosting.show();
                            mAddDogWalkingText.setVisibility(View.VISIBLE);
                            mAddPetSittingText.setVisibility(View.VISIBLE);
                            mAddPetHostingText.setVisibility(View.VISIBLE);

                            mAddFab.extend();

                            isAllFabsVisible = true;
                        } else {

                            mAddDogWalking.hide();
                            mAddPetSitting.hide();
                            mAddPetHosting.hide();
                            mAddDogWalkingText.setVisibility(View.GONE);
                            mAddPetSittingText.setVisibility(View.GONE);
                            mAddPetHostingText.setVisibility(View.GONE);

                            mAddFab.shrink();

                            isAllFabsVisible = false;
                        }
                    }
                });

        mAddDogWalking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), DogWalkingRequestActivity.class));
            }
        });

        mAddPetSitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), PetSittingRequestActivity.class));
            }
        });

        mAddPetHosting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), PetHostingRequestActivity.class));
            }
        });
        return v;
    }

    private void init() {
        // removing toolbar elevation
        MainActivity activity = (MainActivity)getActivity();

        binding.viewPager.setAdapter(new ViewPagerFragmentAdapter(activity));

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
                    return new OngoingFragment();
                case 1:
                    return new UpcomingFragment();
                case 2:
                    return new PastFragment();
            }
            return new OngoingFragment();
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}
