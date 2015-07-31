package com.sothree.slidinguppanel.demo;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.FloatingActionButtonLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState;

import java.util.Arrays;
import java.util.List;

public class DemoActivity extends ActionBarActivity {
    private static final String TAG = "DemoActivity";

    private SlidingUpPanelLayout mLayout;
    private FloatingActionButtonLayout mFabLayout;
    private View mFAB;
    //private LinearLayout mTitleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mFabLayout = (FloatingActionButtonLayout) findViewById(R.id.fab_layout);
        mFAB = findViewById(R.id.fab);
        //mTitleLayout = (LinearLayout) findViewById(R.id.titlebar);
        //final ArgbEvaluator colorEvaluator = new ArgbEvaluator();
        mViewPager.setAdapter(new ScreenSlidePagerAdapter(getSupportFragmentManager(), mLayout));
        mLayout.setPanelSlideListener(new PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
                //mTitleLayout.setBackgroundColor((int) colorEvaluator.evaluate(slideOffset, Color.WHITE, Color.parseColor("#ffff9431")));
            }

            @Override
            public void onPanelExpanded(View panel) {
                Log.i(TAG, "onPanelExpanded");

            }

            @Override
            public void onPanelCollapsed(View panel) {
                Log.i(TAG, "onPanelCollapsed");

            }

            @Override
            public void onPanelAnchored(View panel) {
                Log.i(TAG, "onPanelAnchored");
            }

            @Override
            public void onPanelHidden(View panel) {
                Log.i(TAG, "onPanelHidden");
            }

            @Override
            public void onPanelHiddenExecuted(View panel, Interpolator interpolator, int duration) {
                Log.i(TAG, "onPanelHiddenExecuted");
            }

            @Override
            public void onPanelShownExecuted(View panel, Interpolator interpolator, int duration) {
                Log.i(TAG, "onPanelShownExecuted");
            }

            @Override
            public void onPanelExpandedStateY(View panel, boolean reached) {
                Log.i(TAG, "onPanelExpandedStateY" + (reached ? "reached" : "left"));
            }

            @Override
            public void onPanelCollapsedStateY(View panel, boolean reached) {
                Log.i(TAG, "onPanelCollapsedStateY" + (reached ? "reached" : "left"));
                LinearLayout titleBar = (LinearLayout) findViewById(R.id.titlebar);
                if (reached) {
                    //titleBar.setBackgroundColor(Color.WHITE);
                } else {
                    //titleBar.setBackgroundColor(Color.parseColor("#ffff9431"));
                }
            }

            @Override
            public void onPanelLayout(View panel, SlidingUpPanelLayout.PanelState state) {
                LinearLayout titleBar = (LinearLayout) findViewById(R.id.titlebar);
                if (state == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    //titleBar.setBackgroundColor(Color.WHITE);
                } else if (state == SlidingUpPanelLayout.PanelState.EXPANDED || state == SlidingUpPanelLayout.PanelState.ANCHORED) {
                    //titleBar.setBackgroundColor(Color.parseColor("#ffff9431"));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.demo, menu);
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (mLayout != null) {
            if (mLayout.getPanelState() == PanelState.HIDDEN) {
                item.setTitle(R.string.action_show);
            } else {
                item.setTitle(R.string.action_hide);
            }
        }
        MenuItem item_attach = menu.findItem(R.id.action_toggle_attach);
        if (mFabLayout != null) {
            if (mFabLayout.isFloatingActionButtonAttached()) {
                item_attach.setTitle(R.string.action_detach);
            } else {
                item_attach.setTitle(R.string.action_attach);
            }
        }
        MenuItem item_visibility = menu.findItem(R.id.action_toggle_visibility);
        if (mFAB != null) {
            if (mFAB.getVisibility() == View.VISIBLE) {
                item_visibility.setTitle(R.string.action_gone);
            } else {
                item_visibility.setTitle(R.string.action_visible);
            }
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_toggle: {
                if (mLayout != null) {
                    if (mLayout.getPanelState() != PanelState.HIDDEN) {
                        mLayout.setPanelState(PanelState.HIDDEN);
                        item.setTitle(R.string.action_show);
                    } else {
                        mLayout.setPanelState(PanelState.COLLAPSED);
                        item.setTitle(R.string.action_hide);
                    }
                }
                return true;
            }
            case R.id.action_toggle_attach: {
                if (mFabLayout != null) {
                    if (!mFabLayout.isFloatingActionButtonAttached()) {
                        mFabLayout.setFloatingActionButtonAttached(true);
                        item.setTitle(R.string.action_detach);
                    } else {
                        mFabLayout.setFloatingActionButtonAttached(false);
                        item.setTitle(R.string.action_attach);
                    }
                }
                return true;
            }
            case R.id.action_toggle_visibility: {
                if (mFAB != null) {
                    if (mFAB.getVisibility() != View.VISIBLE) {
                        mFAB.setVisibility(View.VISIBLE);
                        item.setTitle(R.string.action_gone);
                    } else {
                        mFAB.setVisibility(View.GONE);
                        item.setTitle(R.string.action_visible);
                    }
                }
                return true;
            }
            case R.id.action_anchor: {
                if (mLayout != null) {
                    if (mLayout.getAnchorPoint() == 1.0f) {
                        mLayout.setAnchorPoint(0.7f);
                        mLayout.setPanelState(PanelState.ANCHORED);
                        item.setTitle(R.string.action_anchor_disable);
                    } else {
                        mLayout.setAnchorPoint(1.0f);
                        mLayout.setPanelState(PanelState.COLLAPSED);
                        item.setTitle(R.string.action_anchor_enable);
                    }
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == PanelState.EXPANDED || mLayout.getPanelState() == PanelState.ANCHORED)) {
            mLayout.setPanelState(PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private SlidingUpPanelLayout mSlidingUpPanelLayout;

        public ScreenSlidePagerAdapter(FragmentManager fm, SlidingUpPanelLayout supl) {
            super(fm);
            mSlidingUpPanelLayout = supl;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object item) {
            super.setPrimaryItem(container, position, item);
            if (item instanceof Fragment) {
                View primaryView = ((Fragment) item).getView();
                if (primaryView != null) {
                    mSlidingUpPanelLayout.setScrollableView(primaryView.findViewById(R.id.scrollableView));
                    mSlidingUpPanelLayout.setDragView(primaryView.findViewById(R.id.dragView));
                }
            }
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ScrollViewFragment();
                case 1:
                    return new ListViewFragment();
                case 2:
                    return new RecyclerViewFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
