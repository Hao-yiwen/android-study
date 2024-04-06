package com.yiwen.recyclerviewtest.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiwen.recyclerviewtest.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToolBarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToolBarFragment extends Fragment {

    public ToolBarFragment() {
        // Required empty public constructor
    }

    public static ToolBarFragment newInstance(String param1) {
        ToolBarFragment fragment = new ToolBarFragment();
        Bundle args = new Bundle();
        args.putString("TITLE", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tool_bar, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar_fragment);

        if (getArguments() != null && getArguments().containsKey("TITLE")) {
            String title = getArguments().getString("TITLE");
            toolbar.setTitle(title);
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        return view;
    }
}