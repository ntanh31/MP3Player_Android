package org.o7planning.mp3player.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerPlaylistnhac extends FragmentPagerAdapter {

    public final ArrayList<Fragment> array_fragment = new ArrayList<>();

    public ViewPagerPlaylistnhac(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return array_fragment.get(position);
    }

    @Override
    public int getCount() {
        return array_fragment.size();
    }

    public void AddFragment(Fragment fragment){
        array_fragment.add(fragment);
    }
}
