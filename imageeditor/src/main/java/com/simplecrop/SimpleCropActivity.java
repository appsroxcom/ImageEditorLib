package com.simplecrop;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import image.editor.android.R;

public class SimpleCropActivity extends AppCompatActivity {
  private static final String TAG = SimpleCropActivity.class.getSimpleName();

  public static Intent createIntent(Activity activity) {
    return new Intent(activity, SimpleCropActivity.class);
  }

  public void makeFullScreen() {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
  }

  // Lifecycle Method ////////////////////////////////////////////////////////////////////////////

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    makeFullScreen();
    setContentView(R.layout.activity_simple_crop);

    if(savedInstanceState == null){
      getSupportFragmentManager().beginTransaction().add(R.id.container, SimpleCropFragment.newInstance(getIntent() != null ? getIntent().getData() : null)).commit();
    }

    // apply custom font
    //FontUtils.setFont(findViewById(R.id.root_layout));

    initToolbar();
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }

  private void initToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    //FontUtils.setTitle(actionBar, "Rx Sample");
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeButtonEnabled(true);
  }

  public void startResultActivity(Uri uri) {
    if (isFinishing()) return;
    // Start ResultActivity
    //startActivity(ResultActivity.createIntent(this, uri));
  }
}
