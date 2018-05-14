package com.example.yuhaolu.behancedisplay.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuhaolu.behancedisplay.R;

public class Searchbar extends TransformingToolbar {

    private EditText editText;

    public Searchbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(context.getResources().getColor(android.R.color.white));
        setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflate(getContext(), R.layout.merge_search, this);
        editText = findViewById(R.id.toolbar_search_editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Toast.makeText(getContext(), "Search info", Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void showContent() {
        super.showContent();
        editText.requestFocus();
    }

    public void clearText() {
        editText.setText(null);
    }

}
