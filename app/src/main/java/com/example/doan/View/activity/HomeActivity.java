package com.example.doan.View.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.doan.R;
import com.example.doan.View.fragment.BagFragment;
import com.example.doan.View.fragment.FavFragment;
import com.example.doan.View.fragment.HomeFragment;
import com.example.doan.View.fragment.ProfileFragment;
import com.example.doan.View.fragment.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavMenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, new HomeFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeMenu: {
                HomeFragment fragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, fragment, fragment.getClass().getSimpleName())
                        .commit();
                return true;
            }
            case R.id.shopMenu: {
                ShopFragment fragment = new ShopFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, fragment, fragment.getClass().getSimpleName())
                        .commit();
                return true;
            }
            case R.id.bagMenu: {
                BagFragment fragment = new BagFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, fragment, fragment.getClass().getSimpleName())
                        .commit();
                return true;
            }
            case R.id.favMenu: {
                FavFragment fragment = new FavFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, fragment, fragment.getClass().getSimpleName())
                        .commit();
                return true;
            }
            case R.id.profileMenu: {
                ProfileFragment fragment = new ProfileFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, fragment, fragment.getClass().getSimpleName())
                        .commit();
                return true;
            }
        }
        return false;
    }
}
