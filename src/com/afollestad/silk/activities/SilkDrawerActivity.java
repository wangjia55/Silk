package com.afollestad.silk.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

/**
 * An activity that makes interacting with a DrawerLayout quick and easy. All that you have to do is create a layout
 * modelled off the layouts used on the Android Developer website for drawer layouts.
 *
 * @author Aidan Follestad (afollestad)
 */
public abstract class SilkDrawerActivity extends Activity {

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;

    /**
     * Gets the drawer indicator drawable resource that will be displayed next to the the home up button in the activity. This is usually
     * an icon consisting of 3 vertically orientated lines.
     */
    public abstract int getDrawerIndicatorRes();

    /**
     * Gets the shadow drawable resource that will be used for the drawer.
     */
    public abstract int getDrawerShadowRes();

    /**
     * Gets the layout used for the activity.
     */
    public abstract int getLayout();

    /**
     * Gets the drawer layout, you should return the View from the layout.
     */
    public abstract DrawerLayout getDrawerLayout();

    /**
     * Gets the action bar title displayed when the drawer is open.
     */
    public abstract int getOpenedTextRes();


    private void setupDrawer() {
        mTitle = getTitle();
        DrawerLayout mDrawerLayout = getDrawerLayout();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getDrawerIndicatorRes(), getOpenedTextRes(), getOpenedTextRes()) {
            @Override
            public void onDrawerClosed(View drawerView) {
                getActionBar().setTitle(mTitle);
            }
        };
        mDrawerLayout.setDrawerShadow(getDrawerShadowRes(), Gravity.START);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setupDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return mDrawerToggle.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
