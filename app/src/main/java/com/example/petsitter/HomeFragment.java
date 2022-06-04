package com.example.petsitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.petsitter.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    private TabLayout _homeTabLayout;
    private ViewPager _homeViewPager;

    // tab titles
    private String[] titles = new String[]{"Ongoing", "Upcoming", "Past"};
    FragmentHomeBinding binding;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FloatingActionButton mAddPetHosting, mAddPetSitting, mAddDogWalking;

    ExtendedFloatingActionButton mAddFab;

    TextView mAddPetHostingText, mAddPetSittingText, mAddDogWalkingText;

    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_home, container, false);

        binding = FragmentHomeBinding.inflate(getLayoutInflater());
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
                startActivity(new Intent(view.getContext(),DogWalkingRequest.class));
            }
        });

        mAddPetSitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),PetSittingRequest.class));
            }
        });

        mAddPetHosting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),PetHostingRequest.class));
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
