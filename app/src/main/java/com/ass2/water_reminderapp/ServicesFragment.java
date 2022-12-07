package com.ass2.water_reminderapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesFragment extends Fragment {

    ImageView notifyimage, sideeffectsimage, benefitsimage, graphimage, progressimage, servingsimage, bedtimemodeimage, historyimage;
    TextView notifytext, sideeffectstext, benefitstext, graphtext, progresstext, servingstext, bedtimemodetext, historytext;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ServicesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesFragment newInstance(String param1, String param2) {
        ServicesFragment fragment = new ServicesFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_services, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        notifyimage = view.findViewById(R.id.notifyimage);
        notifytext = view.findViewById(R.id.notifytext);
        sideeffectsimage = view.findViewById(R.id.sideeffectsimage);
        sideeffectstext = view.findViewById(R.id.sideeffectstext);
        benefitsimage = view.findViewById(R.id.benefitsimage);
        benefitstext = view.findViewById(R.id.benefitstext);
        graphimage = view.findViewById(R.id.graphimage);
        graphtext = view.findViewById(R.id.graphtext);
        progressimage = view.findViewById(R.id.progressimage);
        progresstext = view.findViewById(R.id.progresstext);
        servingsimage = view.findViewById(R.id.servingsimage);
        servingstext = view.findViewById(R.id.servingstext);
        bedtimemodeimage = view.findViewById(R.id.bedtimemodeimage);
        bedtimemodetext = view.findViewById(R.id.bedtimemodetext);
        historyimage = view.findViewById(R.id.historyimage);
        historytext = view.findViewById(R.id.historytext);



        notifyimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Notify.class));
            }
        });
        notifytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Notify.class));
            }
        });

        sideeffectsimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SideEffects.class));
            }
        });
        sideeffectstext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SideEffects.class));
            }
        });

        benefitsimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Benefits.class));
            }
        });
        benefitstext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Benefits.class));
            }
        });

        graphimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Graph.class));
            }
        });
        graphtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Graph.class));
            }
        });

        progressimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Progress.class));
            }
        });
        progresstext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Progress.class));
            }
        });

        servingsimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Servings.class));
            }
        });
        servingstext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Servings.class));
            }
        });

        bedtimemodeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BedTimeMode.class));
            }
        });
        bedtimemodetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BedTimeMode.class));
            }
        });

        historyimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), History.class));
            }
        });
        historytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), History.class));
            }
        });
    }
}