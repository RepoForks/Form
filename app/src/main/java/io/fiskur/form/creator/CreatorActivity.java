package io.fiskur.form.creator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.fiskur.form.Field;
import io.fiskur.form.Form;
import io.fiskur.form.FormApi;
import io.fiskur.form.R;

public class CreatorActivity extends AppCompatActivity implements OverviewFragment.OnListFragmentInteractionListener {

  private TabLayout tabs;
  private CreatorPagerAdapter creatorPagerAdapter;
  private ViewPager viewPager;

  private OverviewFragment overviewFragment = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_creator);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    if(Preview.form == null){
      Preview.form = new Form();
    }



    creatorPagerAdapter = new CreatorPagerAdapter(getSupportFragmentManager());

    tabs = (TabLayout) findViewById(R.id.tabs);

    // Set up the ViewPager with the sections adapter.
    viewPager = (ViewPager) findViewById(R.id.container);
    viewPager.setAdapter(creatorPagerAdapter);
    tabs.setupWithViewPager(viewPager);


    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
      }
    });
  }

  @Override
  public void onListFragmentInteraction(Field item) {

  }

  public class CreatorPagerAdapter extends FragmentPagerAdapter {

    public CreatorPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      switch(position){
        case 0:
          if(overviewFragment == null){
            overviewFragment = OverviewFragment.newInstance();
          }
          overviewFragment.update(Preview.form);
          return overviewFragment;
        default:
          return TabActivity.PlaceholderFragment.newInstance(position + 1);
      }
    }

    @Override
    public int getCount() {
      // Show 3 total pages.
      return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      switch (position) {
        case 0:
          return "Overview";
        case 1:
          return "JSON";
        case 2:
          return "Preview";
      }
      return null;
    }
  }

}
