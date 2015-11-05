package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Removing our actionbar (OK to do it here?
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //actionBar.hide();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch(position){
                case 0:
                    return new HomeActivityFragment();
                case 1:
                    return new TournamentActivityFragment();
                case 2:
                    return new FieldActivityFragment();
                case 3:
                    return new EndplayActivityFragment();
                case 4:
                    return new FavoritesActivityFragment();
                default:
                    return new HomeActivityFragment();
            }
        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "Tournament";
                case 2:
                    return "Field";
                case 3:
                    return "Endplay";
                case 4:
                    return "Favorites";
            }
            return null;
        }
    }

//    public void changeToNewsActivity(){
//        Intent intent = new Intent(this, NewsActivity.class);
//        startActivity(intent);
//    }

    public void changeToTournamentActivity(View view){
        Intent intent = new Intent(this, TournamentActivity.class);
        startActivity(intent);
    }

    public void changeToNewsActivity(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void changeToSocialActivity(View view){
        Intent intent = new Intent(this, SocialActivity.class);
        startActivity(intent);
    }

    public void changeToEndplayActivity(View view){
        Intent intent = new Intent(this, EndplayActivity.class);
        startActivity(intent);
    }

    public void changeToFavoritesActivity(View view){
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }

    public void changeToFieldsActivity(View view){
        Intent intent = new Intent(this, FieldsActivity.class);
        startActivity(intent);
    }
}
