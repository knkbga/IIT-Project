package authentication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by kanak on 5/10/16.
 */
public class PagerAdapterAuthentication extends FragmentPagerAdapter {
    public PagerAdapterAuthentication(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f =null;
        switch (position)
        {
            case 0:
            {
                f = new FragmentOne();
            }
            break;
            case 1:
            {
                f = new FragmentTwo();
            }
            break;
            case 2:
            {
                f = new FragmentThree();
            }
            break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
