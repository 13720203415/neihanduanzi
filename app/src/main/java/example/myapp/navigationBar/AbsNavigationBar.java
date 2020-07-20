package example.myapp.navigationBar;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public abstract class AbsNavigationBar<P extends AbsNavigationBar.Builder.AbsNavigationParams> implements INavigationBar{
    private static final String THIS_FILE = "AbsNavigationBar";
    private P mParams;
    private View mNavigationView;

    public AbsNavigationBar(P params) {
        this.mParams = params;
        createAndBindView();
    }

    private void createAndBindView() {
        if(mParams.mParent == null){
            // 获取activity的根布局，View源码
            ViewGroup activityRoot = (ViewGroup) ((Activity)(mParams.mContext))
                    .findViewById(android.R.id.content);
            mParams.mParent = (ViewGroup) activityRoot.getChildAt(0);
            Log.e("TAG",mParams.mParent+"");
        }

        // 处理Activity的源码，后面再去看

        if(mParams.mParent == null){
            return;
        }
        mNavigationView = LayoutInflater.from(mParams.mContext).inflate(bindLayoutId(), mParams.mParent, false);
        mParams.mParent.addView(mNavigationView, 0);
        applyView();
    }

    protected P getParams() {
        return mParams;
    }

    protected void setText(int viewId, String text) {
        Log.d(THIS_FILE, "setText, viewId:" + viewId + ", text:" + text);
        TextView tv = findViewById(viewId);
        if (!TextUtils.isEmpty(text)) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
        }
    }

    protected void setOnClickListener(int viewId, View.OnClickListener listener) {
        findViewById(viewId).setOnClickListener(listener);
    }

    protected <T extends View> T findViewById(int viewId) {
        return mNavigationView.findViewById(viewId);
    }

    public abstract static class Builder {
        private Builder mBuilder;

        public Builder(Context context, ViewGroup viewGroup) {

        }

        public abstract AbsNavigationBar builder();

        public static class AbsNavigationParams{
            public Context mContext;
            public ViewGroup mParent;
            public AbsNavigationParams(Context context, ViewGroup viewGroup) {
                mContext = context;
                mParent = viewGroup;
            }
        }
    }
}
