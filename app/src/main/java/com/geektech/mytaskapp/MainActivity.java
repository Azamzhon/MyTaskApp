package com.geektech.mytaskapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.geektech.mytaskapp.ui.interfaces.ImageListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements ImageListener {

    NavController navController;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_task, R.id.navigation_dashboard, R.id.boardFragment, R.id.navigation_profile )
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//       boolean isShown = new Prefs(this).isShown();
//        if (!isShown)
//            navController.navigate(R.id.boardFragment);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            navController.navigate(R.id.phoneFragment);
        }

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.navigation_dashboard ||destination.getId() == R.id.navigation_task){
                    navView.setVisibility(View.VISIBLE);
                } else {navView.setVisibility(View.GONE);}
                if (destination.getId() == R.id.navigation_dashboard ||destination.getId() == R.id.navigation_chat
                        ||destination.getId() == R.id.navigation_task ||destination.getId() == R.id.formFragment ) {
                    getSupportActionBar().show();;
                } else {
                    getSupportActionBar().hide();;
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,appBarConfiguration)  || super.onSupportNavigateUp();
    }

    @Override
    public void setImage(ImageView imageView) {
    }
}