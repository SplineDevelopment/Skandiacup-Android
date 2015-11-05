package com.skandiacup.splinedevelopment.skandiacup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.domain.RSSObject;

public class NewsItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item);
        setupViewFromRSSObject();

    }

    void setupViewFromRSSObject(){
        RSSObject newsItem = (RSSObject)getIntent().getSerializableExtra("NewItem");
        TextView newsItemHeader = (TextView) findViewById(R.id.newsItemHeader);
        TextView newsItemPlainText = (TextView) findViewById(R.id.newsItemPlainText);

        newsItemHeader.setText(newsItem.getTitle());
        newsItemPlainText.setText(newsItem.getItemDescription());
        //newsItemPlainText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_item, menu);
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
}
