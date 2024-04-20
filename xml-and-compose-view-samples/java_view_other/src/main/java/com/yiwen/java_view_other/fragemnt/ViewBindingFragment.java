package com.yiwen.java_view_other.fragemnt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiwen.java_view_other.R;
import com.yiwen.java_view_other.databinding.FragmentViewBindingBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewBindingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewBindingFragment extends Fragment implements View.OnClickListener {

    private FragmentViewBindingBinding binding;

    public ViewBindingFragment() {
        // Required empty public constructor
    }


    public static ViewBindingFragment newInstance() {
        ViewBindingFragment fragment = new ViewBindingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentViewBindingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.linearLayout.setGravity(17);
        ViewGroup.LayoutParams params = binding.linearLayout.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        binding.linearLayout.setLayoutParams(params);
        binding.button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        binding.text.setText("fragemnt viewbinding测试");
    }
}