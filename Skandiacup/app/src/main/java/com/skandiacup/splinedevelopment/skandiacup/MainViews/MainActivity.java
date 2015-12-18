package com.skandiacup.splinedevelopment.skandiacup.MainViews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.view.View;

import com.skandiacup.splinedevelopment.skandiacup.MainViews.Endplay.EndPlayClassesActivity;
import com.skandiacup.splinedevelopment.skandiacup.MainViews.Favorite.FavoritesActivity;
import com.skandiacup.splinedevelopment.skandiacup.MainViews.Field.FieldsActivity;
import com.skandiacup.splinedevelopment.skandiacup.MainViews.Instagram.SocialActivity;
import com.skandiacup.splinedevelopment.skandiacup.MainViews.NewsAndInfo.NewsActivity;
import com.skandiacup.splinedevelopment.skandiacup.MainViews.Tournament.TournamentActivity;
import com.skandiacup.splinedevelopment.skandiacup.R;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void changeToTournamentActivity(View view){
        Intent intent = new Intent(this, TournamentActivity.class);
        startActivity(intent);
    }

    public void changeToNewsActivity(View view){
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }

    public void changeToSocialActivity(View view){
        Intent intent = new Intent(this, SocialActivity.class);
        startActivity(intent);
    }

    public void changeToEndplayActivity(View view){
        Intent intent = new Intent(this, EndPlayClassesActivity.class);
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
