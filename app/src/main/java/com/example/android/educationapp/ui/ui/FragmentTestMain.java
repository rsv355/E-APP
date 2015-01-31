package com.example.android.educationapp.ui.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.educationapp.R;
import com.filippudak.ProgressPieView.ProgressPieView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTestMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTestMain extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public static ProgressPieView mProgressPieView;
    public static Button btn;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTestMain.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTestMain newInstance(String param1, String param2) {
        FragmentTestMain fragment = new FragmentTestMain();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentTestMain() {
        // Required empty public constructor
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
        View rootView = inflater.inflate(R.layout.fragment_test_main, container, false);

        btn = (Button)rootView.findViewById(R.id.btn);
        mProgressPieView = (ProgressPieView)rootView. findViewById(R.id.progressPieView);


        mProgressPieView.setOnProgressListener(new ProgressPieView.OnProgressListener() {
            @Override
            public void onProgressChanged(int progress, int max) {
                int counter=0; // for 10seconds
                if(progress%30==0){
                    mProgressPieView.setText(""+progress/30+"s");
                }


                if (!mProgressPieView.isTextShowing()) {
                    mProgressPieView.setShowText(true);
                    mProgressPieView.setShowImage(false);
                }
            }

            @Override
            public void onProgressCompleted() {
                if (!mProgressPieView.isImageShowing()) {
                    mProgressPieView.setShowImage(true);
                }
                mProgressPieView.setShowText(true);
                mProgressPieView.setText("Time's Up!!!");
                // mProgressPieView.setImageResource(R.drawable.ic_action_accept);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressPieView.setProgress(0);
                mProgressPieView.setMax(300); // 30=1s
                mProgressPieView.animateProgressFill();
            }
        });


       return rootView;
    }


}
