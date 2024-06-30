package io.github.haoyiwen.ctrip.home.header;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import io.github.haoyiwen.ctrip.R;
import io.github.haoyiwen.ctrip.util.PxTransform;

public class HomeHeaderFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_header_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("HomeHeaderFragment", "onViewCreated: ");
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.home_header_fragment), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            Log.d("HomeHeaderFragment", "onApplyWindowInsetsListener: " + systemBars);
            Log.d("HomeHeaderFragment", "onApplyWindowInsetsListener: " + PxTransform.pxToDp(systemBars.top, getContext()));
            return insets;
        });
    }
}
