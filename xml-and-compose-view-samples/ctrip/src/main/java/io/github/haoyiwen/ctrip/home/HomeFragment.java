package io.github.haoyiwen.ctrip.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.github.haoyiwen.ctrip.R;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FrameLayout header = view.findViewById(R.id.home_header);
        getChildFragmentManager().beginTransaction().replace(header.getId(), new HomeHeaderFragment()).commit();
        FrameLayout position = view.findViewById(R.id.home_position);
        getChildFragmentManager().beginTransaction().replace(position.getId(), new HomePositionFragment()).commit();
    }
}
