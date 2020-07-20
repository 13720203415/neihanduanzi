package example.myapp.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

class DialogViewHelper {
    private View mContentView = null;
    private SparseArray<WeakReference<View>> mViews;
    public DialogViewHelper(Context mContext, int viewLayoutId) {
        this();
        mContentView = LayoutInflater.from(mContext).inflate(viewLayoutId, null);
    }

    public DialogViewHelper() {
        mViews = new SparseArray<>();
    }

    public void setContentView(View view) {
        this.mContentView = view;
    }

    public View getContentView() {
        return mContentView;
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if(view != null) {
            view.setOnClickListener(listener);
        }
    }

    public void setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        if(view != null) {
            view.setText(text);
        }
    }

    private <T extends View> T getView(int viewId) {
        WeakReference<View> weakReference = mViews.get(viewId);
        View view = null;
        if (weakReference != null) {
            view = weakReference.get();
        }
        if(view == null) {
            view = mContentView.findViewById(viewId);
            mViews.put(viewId, new WeakReference<View>(view));
        }
        return (T) view;
    }
}
