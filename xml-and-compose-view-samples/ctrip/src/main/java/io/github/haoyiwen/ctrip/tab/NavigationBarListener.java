package io.github.haoyiwen.ctrip.tab;

import android.app.Activity;
import android.content.Context;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;

import io.github.haoyiwen.ctrip.R;
import io.github.haoyiwen.ctrip.community.CommunityFragment;
import io.github.haoyiwen.ctrip.home.HomeFragment;
import io.github.haoyiwen.ctrip.message.MessageFragment;
import io.github.haoyiwen.ctrip.schedule.ScheduleFragment;
import io.github.haoyiwen.ctrip.user.UserFragment;

public class NavigationBarListener implements NavigationBarView.OnItemSelectedListener {
    private Context context;

    public NavigationBarListener(Context context) {
        this.context = context;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.nav_home) {
            selectedFragment = new HomeFragment();
        } else if (itemId == R.id.nav_user) {
            selectedFragment = new UserFragment();
        } else if(itemId == R.id.nav_community){
            selectedFragment = new CommunityFragment();
        } else if(itemId == R.id.nav_message){
            selectedFragment = new MessageFragment();
        } else if(itemId == R.id.nav_schedule){
            selectedFragment = new ScheduleFragment();
        }
        if (selectedFragment != null) {
            FragmentTransaction transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, selectedFragment);
            transaction.commit();
        }
        return true;
    }
}
