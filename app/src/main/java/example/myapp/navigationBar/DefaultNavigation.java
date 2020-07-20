package example.myapp.navigationBar;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import example.myapp.R;

public class DefaultNavigation extends AbsNavigationBar<DefaultNavigation.Builder.DefaultNavigationParams>{

    public DefaultNavigation(Builder.DefaultNavigationParams mParams) {
        super(mParams);
    }

    @Override
    public int bindLayoutId() {
        return R.layout.title_bar;
    }

    @Override
    public void applyView() {
        setText(R.id.title, getParams().mTitle);
        setText(R.id.right_text, getParams().mRightText);

        setOnClickListener(R.id.right_text, getParams().mRightClickListener);
        setOnClickListener(R.id.message, getParams().mLeftClickListener);
    }



    public static class Builder extends AbsNavigationBar.Builder {
        public DefaultNavigationParams mParams;

        public Builder(Context context, ViewGroup viewGroup) {
            super(context, viewGroup);
            mParams = new DefaultNavigationParams(context, viewGroup);
        }

        public Builder(Context context) {
            super(context, null);
            mParams = new DefaultNavigationParams(context, null);
        }

        public Builder setTitle(String title) {
            mParams.mTitle = title;
            return this;
        }

        public Builder setRightText(String rightText) {
            mParams.mRightText = rightText;
            return this;
        }

        /**
         * 设置右边的点击事件
         */
        public Builder setRightClickListener(View.OnClickListener rightListener) {
            mParams.mRightClickListener = rightListener;
            return this;
        }

        /**
         * 设置左边的点击事件
         */
        public Builder setLeftClickListener(View.OnClickListener rightListener) {
            mParams.mLeftClickListener = rightListener;
            return this;
        }

        /**
         * 设置右边的图片
         */
        public Builder setRightIcon(int rightRes) {

            return this;
        }
        @Override
        public DefaultNavigation builder() {
            DefaultNavigation defaultNavigation = new DefaultNavigation(mParams);
            return defaultNavigation;
        }

        public static class DefaultNavigationParams extends AbsNavigationBar.Builder.AbsNavigationParams{
            public String mTitle;
            public String mRightText;
            public View.OnClickListener mRightClickListener;
            public View.OnClickListener mLeftClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Activity) mContext).finish();
                }
            };

            public DefaultNavigationParams(Context context, ViewGroup viewGroup) {
                super(context, viewGroup);
            }
        }
    }
}
