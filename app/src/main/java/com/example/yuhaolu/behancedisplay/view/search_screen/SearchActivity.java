package com.example.yuhaolu.behancedisplay.view.search_screen;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.toolbar.Searchbar;
import com.example.yuhaolu.behancedisplay.transition.FadeInTransition;
import com.example.yuhaolu.behancedisplay.transition.FadeOutTransition;
import com.example.yuhaolu.behancedisplay.transition.SimpleTransitionListener;

public class SearchActivity extends AppCompatActivity {

    private Searchbar searchbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchbar = findViewById(R.id.search_toolbar);
        setSupportActionBar(searchbar);

        if (isFirstTimeRunning(savedInstanceState)) {
            searchbar.hideContent();
            ViewTreeObserver viewTreeObserver = searchbar.getViewTreeObserver();
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @TargetApi(Build.VERSION_CODES.KITKAT)
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onGlobalLayout() {
                    searchbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    showSearch();
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                public void showSearch() {
                    TransitionManager.beginDelayedTransition(searchbar, FadeInTransition.createTransition());
                    searchbar.showContent();
                }
            });
        }


    }

    private boolean isFirstTimeRunning(Bundle savedInstanceState) {
        return savedInstanceState == null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void finish() {
        super.finish();
        hideKeyboard();
        exitTransitionWithAction(new Runnable() {
            @Override
            public void run() {
                SearchActivity.super.finish();
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_clear) {
            searchbar.clearText();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void exitTransitionWithAction(final Runnable endingAction) {
        Transition transition = FadeOutTransition.withAction(new SimpleTransitionListener() {
            @Override
            public void onTransitionEnd(Transition transition) {
                endingAction.run();
            }
        });
        TransitionManager.beginDelayedTransition(searchbar, transition);
        searchbar.hideContent();
    }
}
