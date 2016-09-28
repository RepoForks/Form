package io.fiskur.form.creator;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.afollestad.materialdialogs.MaterialDialog;
import io.fiskur.form.Field;
import io.fiskur.form.Form;
import io.fiskur.form.R;
import java.util.ArrayList;

public class CreatorActivity extends AppCompatActivity implements View.OnClickListener, OverviewFragment.OnListFragmentInteractionListener {

  private TabLayout tabs;
  private CreatorPagerAdapter creatorPagerAdapter;
  private ViewPager viewPager;

  private OverviewFragment overviewFragment = null;
  private PreviewFragment previewFragment = null;
  private JsonFragment jsonFragment = null;

  private ImageButton addCurrentDateButton;
  private ImageButton addDateEntryButton;
  private ImageButton addDivButton;
  private ImageButton addStaticTextButton;
  private ImageButton addMultiChoiceButton;
  private ImageButton addSingleChoiceButton;
  private ImageButton addImageButton;
  private ImageButton addTextEntryButton;
  private ImageButton addTimeButton;

  private FrameLayout bottomSheet;
  private BottomSheetBehavior behavior;

  private int id = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_creator);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    if(Preview.form == null){
      Preview.loadForm(this);
      if(Preview.form == null) {
        Preview.form = new Form();
        Preview.form.fields = new ArrayList<Field>();
      }
    }

    addCurrentDateButton = (ImageButton) findViewById(R.id.add_current_date_button);
    addDateEntryButton = (ImageButton) findViewById(R.id.add_date_button);
    addDivButton = (ImageButton) findViewById(R.id.add_div_button);
    addStaticTextButton = (ImageButton) findViewById(R.id.add_static_text_button);
    addMultiChoiceButton = (ImageButton) findViewById(R.id.add_multiple_choice_button);
    addSingleChoiceButton = (ImageButton) findViewById(R.id.add_single_choice_button);
    addImageButton = (ImageButton) findViewById(R.id.add_image_button);
    addTextEntryButton = (ImageButton) findViewById(R.id.add_text_entry_button);
    addTimeButton = (ImageButton) findViewById(R.id.add_time_button);

    addCurrentDateButton.setOnClickListener(this);
    addDateEntryButton.setOnClickListener(this);
    addDivButton.setOnClickListener(this);
    addStaticTextButton.setOnClickListener(this);
    addMultiChoiceButton.setOnClickListener(this);
    addSingleChoiceButton.setOnClickListener(this);
    addImageButton.setOnClickListener(this);
    addTextEntryButton.setOnClickListener(this);
    addTimeButton.setOnClickListener(this);

    bottomSheet = (FrameLayout) findViewById(R.id.bottom_sheet);
    behavior = BottomSheetBehavior.from(bottomSheet);

    creatorPagerAdapter = new CreatorPagerAdapter(getSupportFragmentManager());

    tabs = (TabLayout) findViewById(R.id.tabs);

    // Set up the ViewPager with the sections adapter.
    viewPager = (ViewPager) findViewById(R.id.view_pager);
    viewPager.setAdapter(creatorPagerAdapter);
    tabs.setupWithViewPager(viewPager);

    final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
      }
    });
    fab.setVisibility(View.GONE);

    viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageSelected(int position) {
        if(position == 1){
          ViewTools.show(fab, 400);
          jsonFragment.update();
        }else{
          ViewTools.hide(fab, 800);
        }
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_controls) {
      if(behavior.getState() == BottomSheetBehavior.STATE_HIDDEN){
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
      }else{
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
      }

      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    Preview.saveForm(this);
  }

  @Override
  public void onListFragmentInteraction(Field item) {

  }

  @Override public void onClick(View view) {
    if(view == addCurrentDateButton){
      new MaterialDialog.Builder(this)
          .title(R.string.add_dialog_title_current_date)
          .content(R.string.add_dialog_context_current_date)
          .input(R.string.add_dialog_hint_current_date, R.string.add_dialog_hint_current_date, new MaterialDialog.InputCallback() {
            @Override
            public void onInput(MaterialDialog dialog, CharSequence input) {
              Field currentDateField = new Field();
              currentDateField.id = getNewId();
              currentDateField.text = input.toString();
              currentDateField.type = Field.TYPE_CURRENT_DATE;
              Preview.form.fields.add(currentDateField);
              updateTabs();
            }
          }).show();
    }else if(view == addDateEntryButton){
      new MaterialDialog.Builder(this)
          .title(R.string.add_dialog_title_date)
          .content(R.string.add_dialog_context_date)
          .input(R.string.add_dialog_hint_date, R.string.add_dialog_hint_date, new MaterialDialog.InputCallback() {
            @Override
            public void onInput(MaterialDialog dialog, CharSequence input) {
              Field dateField = new Field();
              dateField.id = getNewId();
              dateField.text = input.toString();
              dateField.type = Field.TYPE_DATE;
              Preview.form.fields.add(dateField);
              updateTabs();
            }
          }).show();
    }else if(view == addDivButton){
      Field divField = new Field();
      divField.id = getNewId();
      divField.type = Field.TYPE_DIVIDER;
      Preview.form.fields.add(divField);
      updateTabs();
    }else if(view == addStaticTextButton){

    }else if(view == addMultiChoiceButton){

    }else if(view == addSingleChoiceButton){

    }else if(view == addImageButton){
      new MaterialDialog.Builder(this)
          .title(R.string.add_dialog_title_image)
          .content(R.string.add_dialog_context_image)
          .input(R.string.add_dialog_hint_image, R.string.add_dialog_hint_image, new MaterialDialog.InputCallback() {
            @Override
            public void onInput(MaterialDialog dialog, CharSequence input) {
              Field imageField = new Field();
              imageField.id = getNewId();
              imageField.text = input.toString();
              imageField.type = Field.TYPE_STATIC_IMAGE;
              Preview.form.fields.add(imageField);
              updateTabs();
            }
          }).show();
    }else if(view == addTextEntryButton){

    }else if(view == addTimeButton){
      new MaterialDialog.Builder(this)
          .title(R.string.add_dialog_title_time)
          .content(R.string.add_dialog_context_time)
          .input(R.string.add_dialog_hint_time, R.string.add_dialog_hint_time, new MaterialDialog.InputCallback() {
            @Override
            public void onInput(MaterialDialog dialog, CharSequence input) {
              Field timeField = new Field();
              timeField.id = getNewId();
              timeField.text = input.toString();
              timeField.type = Field.TYPE_TIME;
              Preview.form.fields.add(timeField);
              updateTabs();
            }
          }).show();
    }
  }

  private void updateTabs(){
    switch(viewPager.getCurrentItem()){
      case 0:
        overviewFragment.update();
        break;
      case 1:
        jsonFragment.update();
        break;
      case 2:
        previewFragment.update();
        break;
    }
  }

  private String getNewId(){
    id++;
    return String.valueOf(id);
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
          overviewFragment.update();
          return overviewFragment;
        case 1:
          if(jsonFragment == null){
            jsonFragment = new JsonFragment();
          }
          jsonFragment.update();
          return jsonFragment;
        case 2:
          if(previewFragment == null){
            previewFragment = new PreviewFragment();
          }
          previewFragment.update();
          return previewFragment;

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
