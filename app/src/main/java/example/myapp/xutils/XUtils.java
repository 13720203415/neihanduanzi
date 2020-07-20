package example.myapp.xutils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class XUtils {
    public static void inject(Activity activity) {
        inject(new ViewFinder(activity), activity);
    }

    public static void inject(View view) {
        inject(new ViewFinder(view), view);
    }

    public static void inject(View view, Object object) {
        inject(new ViewFinder(view), object);
    }

    private static void inject(ViewFinder finder, Object object) {
        injectFiled(finder, object);
        injectEvent(finder, object);
    }

    //添加事件
    private static void injectEvent(ViewFinder finder, Object object) {
        //得到这个类
        Class<?> aClass = object.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            OnClick click = declaredMethod.getAnnotation(OnClick.class);
            if(click != null) {
                int[] viewIds = click.value();
                for (int viewId : viewIds) {
                    View view = finder.findViewById(viewId);
                    boolean isCheckNet = declaredMethod.getAnnotation(CheckNet.class) != null;
                    if(view != null) {
                        view.setOnClickListener(new DeclaredOnClickListener(declaredMethod, object, isCheckNet));
                    }
                }
            }
        }
    }

    //注入属性
    private static void injectFiled(ViewFinder finder, Object object) {
        //得到这个类
        Class<?> aClass = object.getClass();
        // 获取所有属性包括私有和共有
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            // 获取ViewById的里面的value值
            ViewById viewById = field.getAnnotation(ViewById.class);
            if(viewById != null) {
                //获取注解里面的id值。R.id.xxx
                int viewId = viewById.value();
                //用findViewById找到view
                View view = finder.findViewById(viewId);
                if(view != null) {
                    //能够注入所有的修饰符，包括private
                    field.setAccessible(true);
                    try {
                        // 动态的注入找到的View
                        field.set(object, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class DeclaredOnClickListener implements View.OnClickListener {
        private Method mMethod;
        private Object mObject;
        private boolean mIsCheckNet;

        public DeclaredOnClickListener(Method declaredMethod, Object object, boolean isCheckNet) {
            this.mMethod = declaredMethod;
            this.mObject = object;
            this.mIsCheckNet = isCheckNet;
        }

        @Override
        public void onClick(View view) {
            //是否需要检测网络
            if(mIsCheckNet) {
                if(!networkAvailable(view.getContext())) {
                    Toast.makeText(view.getContext(), "网络不可用", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            mMethod.setAccessible(true);
            try {
                mMethod.invoke(mObject, view);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    mMethod.invoke(mObject, null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * 判断当前网络是否可用
     */
    private static boolean networkAvailable(Context context) {
        // 得到连接管理器对象
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
