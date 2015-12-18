package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class EditTextBackEvent extends EditText {

    private EditTextImeBackListener mOnImeBack;

    public EditTextBackEvent(Context context) {
        super(context);
    }

    public EditTextBackEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextBackEvent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, @NonNull KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if (mOnImeBack != null) mOnImeBack.onImeBack(this, this.getText().toString());
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    public void setOnEditTextImeBackListener(EditTextImeBackListener listener) {
        mOnImeBack = listener;
    }

}

interface EditTextImeBackListener {
    void onImeBack(EditTextBackEvent ctrl, String text);
}