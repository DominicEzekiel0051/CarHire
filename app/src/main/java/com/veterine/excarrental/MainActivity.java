package com.veterine.excarrental;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  private Toolbar toolbar;
  private Button button;
  private NavigationView navigationView;
  private DrawerLayout drawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);



  //initialize the toolbar
    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    //placing the navigation drawer button on the tool bar
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);


    drawerLayout = findViewById(R.id.drawer_layout);
    navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);






  }

  public void openLogInOptions(View view) {
    Intent intent = new Intent(this, LogInOptions.class);
    startActivity(intent);
  }

  public void openMakeReservation(View view) {
    Intent intent1 = new Intent(this, Login.class);
    startActivity(intent1);
  }

  public void openSignUpOptions(View view) {
    Intent intent3 = new Intent(this, SignUpOptions.class);
    startActivity(intent3);

  }



  public void openCustomerAccount(View view) {
    Intent intent5 = new Intent(this, CustomerAccount.class);
    startActivity(intent5);

  }

  public void openCustomerCarsList(View view) {
    Intent intent6 = new Intent(this, CustomerCarsList.class);
    startActivity(intent6);

  }

  public void openHomeCarsList(View view){
    Intent intent = new Intent(getApplicationContext(), HomeCarsList.class);
    startActivity(intent);

  }






  //handling the click events for the items on the navigation drawer
  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
     switch (item.getItemId()){

       case R.id.user_account:

         break;

     }

    drawerLayout.closeDrawer(GravityCompat.START);
     return true;
  }

  //Handling the click events of the items of the Navigation drawer
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()){

      case android.R.id.home:
      drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }

    return super.onOptionsItemSelected(item);
  }
  //adding menu on the appbar
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.app_bar_menu,menu);
    return true;
  }
}


