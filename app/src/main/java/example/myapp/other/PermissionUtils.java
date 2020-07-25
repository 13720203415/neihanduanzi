package example.myapp.other;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class PermissionUtils {
    private static final String THIS_FILE = "PermissionUtils";
    public static int REQUEST_CODE = 101;
    public static String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
}
