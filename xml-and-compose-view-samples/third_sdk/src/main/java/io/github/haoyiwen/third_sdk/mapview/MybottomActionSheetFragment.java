package io.github.haoyiwen.third_sdk.mapview;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import io.github.haoyiwen.third_sdk.R;

public class MybottomActionSheetFragment extends BottomSheetDialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int themeResId = getContext().getResources().getIdentifier("AppTheme", "style", getContext().getPackageName());
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), themeResId);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        return localInflater.inflate(R.layout.bottom_sheet_layout, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            View bottomSheet = dialog.findViewById(R.id.bottom_sheet_layout);
            bottomSheet.getLayoutParams().height = (int)getResources().getDimension(R.dimen.bottom_sheet_height);
            bottomSheet.findViewById(R.id.btn_close_bottom_sheet).setOnClickListener(v -> {
                dismiss();
            });
        }
    }
}
