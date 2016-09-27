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
import android.text.InputType;
import android.view.View;

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

  private ImageButton addCurrentDateButton;
  private ImageButton addDateEntryButton;
  private ImageButton addDivButton;
  private ImageButton addStaticTextButton;
  private ImageButton addMultiChoiceButton;
  private ImageButton addSingleChoiceButton;
  private ImageButton addImageButton;
  private ImageButton addTextEntryButton;
  private ImageButton addTimeButton;

  private int id = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_creator);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    if(Preview.form == null){
      Preview.form = new Form();
      Preview.form.fields = new ArrayList<Field>();
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
            }
          }).show();
    }else if(view == addDivButton){
      Field divField = new Field();
      divField.id = getNewId();
      divField.type = Field.TYPE_DIVIDER;
      Preview.form.fields.add(divField);
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
            }
          }).show();
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
