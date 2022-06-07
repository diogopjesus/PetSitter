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
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

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
        TabLayout tabLayout = root.findViewById(R.id.tab_layout);
        ViewPager2 viewPager2 = root.findViewById(R.id.view_pager);
        ViewPagerFragmentAdapter adapter= new ViewPagerFragmentAdapter((MainActivity) getActivity());
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout,viewPager2, (tab, position) ->
                tab.setText(titles[position])).attach();

        mAddFab = root.findViewById(R.id.add_fab);

        mAddDogWalking = root.findViewById(R.id.add_dog_walking_fab);
        mAddPetSitting = root.findViewById(R.id.add_petSitting_fab);
        mAddPetHosting = root.findViewById(R.id.add_petHosting_fab);

        mAddDogWalkingText = root.findViewById(R.id.add_dog_walking_text);
        mAddPetSittingText = root.findViewById(R.id.add_petSitting_text);
        mAddPetHostingText = root.findViewById(R.id.add_petHosting_text);

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
