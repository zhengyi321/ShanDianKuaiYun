package com.zhyan.shandiankuaiyuanwidgetlib.Utils;

/**
 * Created by az on 2017/5/8.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Utility to request and check System permissions for apps targeting Android M
 * (API >= 23).
 */
public class PermissionUtil {

    private static final String TAG = "EasyPermissions";

    public interface PermissionCallback extends
            ActivityCompat.OnRequestPermissionsResultCallback {

        void onPermissionsGranted(int requestCode, List<String> perms);

        void onPermissionsDenied(int requestCode, List<String> perms);

    }

    public interface Permissions{
        String READ_CALL_LOG=VERSION.SDK_INT>=16?permission.READ_CALL_LOG:"android.permission.READ_CALL_LOG";
    }

    /**
     * 权限请求code ，由此处定义基本常用的
     * @author G410
     *
     */
    public interface RequestCode{
        //Group-Phone
        int READ_PHONE_STATE=1;
        int CALL_PHONE = 2;

        int READ_CALL_LOG = 3;

        int SEND_SMS = 5;
        int READ_SMS = 6;

        //Group-contact

        int READ_CONTACTS=10;

        //定位
        int ACCESS_FINE_LOCATION=15;

        //sdk
        int WRITE_EXTERNAL_STORAGE=20;

        //录音
        int RECORD_AUDIO=21;

        //拍照
        int CAMERA_CAPTURE=25;

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface AfterPermissionGranted {
        int value();
    }

    public static boolean checkPermission(@Nullable Context context,
                                          @NonNull String... permissions) {
        return hasPermissions(context, permissions);
    }

    /**
     * 检查网络权限
     *
     * @param context
     * @return
     */
    public static boolean checkInternetPermission(@Nullable Context context) {
        return checkPermission(context, permission.INTERNET);
    }

    /**
     * 发送短信权限
     *
     * @param context
     * @return
     */
    public static boolean checkSendSMSPermission(@Nullable Context context) {
        return checkPermission(context, permission.SEND_SMS);
    }

    /**
     * 拨打电话的权限
     *
     * @param context
     * @return
     */
    public static boolean checkCallPhonePermission(@Nullable Context context) {
        return checkPermission(context, permission.CALL_PHONE);
    }

    /**
     * Check if the calling context has a set of permissions. 必须同组，不同组方法失效
     *
     * @param context
     *            the calling context.
     * @param permissions
     *            one ore more permissions, such as
     *            {@code android.Manifest.permission.CAMERA}.
     * @return true if all permissions are already granted, false if at least
     *         one permission is not yet granted.
     */
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context == null) {
            return false;
        }

        boolean hasAllPermissions = true;
        if (permissions.length == 1) {
            hasAllPermissions &= hasPermission(context, permissions[0]);
        } else {
            for (String perm : permissions) {
                hasAllPermissions &= hasPermission(context, perm);
            }
        }
        return hasAllPermissions;
    }

    private static boolean hasPermission(@Nullable Context context,
                                         @NonNull String permission) {
        return context != null
                && (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED);
    }

    /**
     * Request a set of permissions, showing rationale if the system requests
     * it.
     *
     * @param object
     *            Activity or Fragment requesting permissions. Should implement
     *            {@link android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback}
     *            or
     *
     * @param rationale
     *            a message explaining why the application needs this set of
     *            permissions, will be displayed if the user rejects the request
     *            the first time.
     * @param requestCode
     *            request code to track this request, must be < 256.
     * @param perms
     *            a set of permissions to be requested.
     */
    public static void requestPermissions(final Object object,
                                          String rationale, final int requestCode, final String... perms) {
        requestPermissions(object, false, rationale, android.R.string.ok,
                android.R.string.cancel, requestCode, perms);
    }

    /**
     * 请求一个不需要权限解释框的授权请求
     *
     * @param object
     * @param requestCode
     * @param perms
     */
    public static void requestPermissions(final Object object,
                                          final int requestCode, final String... perms) {
        requestPermissions(object, true, null, 0, 0, requestCode, perms);
    }

    /**
     * 版本低于23则检查权限后获取一个grantResults[]数组后发布出去onRequestPermissionsResult()
     *
     * @param object
     * @param requestCode
     * @param permissions
     */
    private static void doPermissionWorkBeforeAndroidM(final Object object,
                                                       final int requestCode, final String... permissions) {

        int[] grantResults = new int[permissions.length];

        Activity activity = getActivity(object);
        for (int i = 0; i < permissions.length; i++) {
            grantResults[i] = hasPermission(activity, permissions[i]) ? 0 : 1;
        }

        onRequestPermissionsResult(requestCode, permissions, grantResults,
                object);
    }

    /**
     * Request a set of permissions, showing rationale if the system requests
     * it.
     *
     * @param object
     *            Activity or Fragment requesting permissions. Should implement
     *            {@link android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback}
     *            or
     *
     * @param rationale
     *            a message explaining why the application needs this set of
     *            permissions, will be displayed if the user rejects the request
     *            the first time.
     * @param positiveButton
     *            custom text for positive button
     * @param negativeButton
     *            custom text for negative button
     * @param requestCode
     *            request code to track this request, must be < 256.
     * @param perms
     *            a set of permissions to be requested.
     */
    public static void requestPermissions(final Object object,
                                          final boolean skipShouldShowRationale, String rationale,
                                          @StringRes int positiveButton, @StringRes int negativeButton,
                                          final int requestCode, final String... perms) {
        if (object == null) {
            return;
        }

        // 如果版本低于23则直接发布结果
        if (Build.VERSION.SDK_INT < 23) {
            Log.e(TAG,
                    "Build.VERSION.SDK_INT <= 23 execute onRequestPermissionsResult(object,requestCode("
                            + requestCode
                            + "),permissions("
                            + perms.length
                            + "))函数");
            doPermissionWorkBeforeAndroidM(object, requestCode, perms);
        } else {
            checkCallingObjectSuitability(object);
            final PermissionCallback callbacks = (PermissionCallback) object;

            if (!skipShouldShowRationale) {
                boolean shouldShowRationale = false;
                for (String perm : perms) {
                    shouldShowRationale = shouldShowRationale
                            || shouldShowRequestPermissionRationale(object,
                            perm);
                }

                if (shouldShowRationale) {
                    Activity activity = getActivity(object);
                    if (null == activity) {
                        return;
                    }
                    AlertDialog dialog = new AlertDialog.Builder(activity)
                            .setMessage(rationale)
                            .setPositiveButton(positiveButton,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                            executePermissionsRequest(object,
                                                    perms, requestCode);
                                        }
                                    })
                            .setNegativeButton(negativeButton,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                            // act as if the permissions were
                                            // denied
                                            callbacks.onPermissionsDenied(
                                                    requestCode,
                                                    Arrays.asList(perms));
                                        }
                                    }).create();
                    dialog.show();
                } else {
                    executePermissionsRequest(object, perms, requestCode);
                }
            } else {
                executePermissionsRequest(object, perms, requestCode);
            }
        }
    }

    /**
     * Handle the result of a permission request, should be called from the
     * calling Activity's
     * {@link android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback#onRequestPermissionsResult(int, String[], int[])}
     * method.
     * <p>
     * If any permissions were granted or denied, the Activity will receive the
     * appropriate callbacks through {@link PermissionCallback} and methods
     * annotated with {@link AfterPermissionGranted} will be run if appropriate.
     *
     * @param requestCode
     *            requestCode argument to permission result callback.
     * @param permissions
     *            permissions argument to permission result callback.
     * @param grantResults
     *            grantResults argument to permission result callback.
     * @param object
     *            the calling Activity or Fragment.
     * @throws IllegalArgumentException
     *             if the calling Activity does not implement
     *             {@link PermissionCallback}.
     */
    public static void onRequestPermissionsResult(int requestCode,
                                                  String[] permissions, int[] grantResults, Object object) {

        checkCallingObjectSuitability(object);
        PermissionCallback callbacks = (PermissionCallback) object;

        // Make a collection of granted and denied permissions from the request.
        ArrayList<String> granted = new ArrayList<String>();
        ArrayList<String> denied = new ArrayList<String>();
        for (int i = 0; i < permissions.length; i++) {
            String perm = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granted.add(perm);
            } else {
                denied.add(perm);
            }
        }

        // Report granted permissions, if any.
        if (!granted.isEmpty()) {
            // Notify callbacks
            callbacks.onPermissionsGranted(requestCode, granted);
        }

        // Report denied permissions, if any.
        if (!denied.isEmpty()) {
            callbacks.onPermissionsDenied(requestCode, denied);
        }

        // 注解方法在所有权限授权之后才会执行
        if (!granted.isEmpty() && denied.isEmpty()) {
            runAnnotatedMethods(object, requestCode);
        }
    }

    /**
     * If user denied permissions with the flag NEVER ASK AGAIN, open a dialog
     * explaining the permissions rationale again and directing the user to the
     * app settings.
     *
     * NOTE: use of this method is optional, should be called from
     * {@link PermissionCallback#onPermissionsDenied(int, List)}
     *
     * @param object
     *            the calling Activity or Fragment.
     * @param deniedPerms
     *            the set of denied permissions.
     * @return {@code true} if user denied at least one permission with the flag
     *         NEVER ASK AGAIN.
     */
    public static boolean checkDeniedPermissionsNeverAskAgain(Object object,
                                                              String rationale, @StringRes int positiveButton,
                                                              @StringRes int negativeButton, List<String> deniedPerms) {
        boolean shouldShowRationale;
        for (String perm : deniedPerms) {
            shouldShowRationale = shouldShowRequestPermissionRationale(object,
                    perm);
            if (!shouldShowRationale) {
                final Activity activity = getActivity(object);
                if (null == activity) {
                    return true;
                }

                AlertDialog dialog = new AlertDialog.Builder(activity)
                        .setMessage(rationale)
                        .setPositiveButton(positiveButton,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        Intent intent = new Intent(
                                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package",
                                                activity.getPackageName(), null);
                                        intent.setData(uri);
                                        activity.startActivity(intent);
                                    }
                                }).setNegativeButton(negativeButton, null)
                        .create();
                dialog.show();

                return true;
            }
        }

        return false;
    }

    @TargetApi(23)
    private static boolean shouldShowRequestPermissionRationale(Object object,
                                                                String perm) {
        if (object instanceof Activity) {
            return ActivityCompat.shouldShowRequestPermissionRationale(
                    (Activity) object, perm);
        } else if (object instanceof Fragment) {
            return ((Fragment) object)
                    .shouldShowRequestPermissionRationale(perm);
        } else if (object instanceof android.app.Fragment) {
            return ((android.app.Fragment) object)
                    .shouldShowRequestPermissionRationale(perm);
        } else {
            return false;
        }
    }

    @TargetApi(23)
    private static void executePermissionsRequest(Object object,
                                                  String[] perms, int requestCode) {
        checkCallingObjectSuitability(object);

        if (object instanceof Activity) {
            ActivityCompat.requestPermissions((Activity) object, perms,
                    requestCode);
        } else if (object instanceof Fragment) {
            ((Fragment) object).requestPermissions(perms, requestCode);
        } else if (object instanceof android.app.Fragment) {
            ((android.app.Fragment) object).requestPermissions(perms,
                    requestCode);
        }
    }

    @TargetApi(11)
    private static Activity getActivity(Object object) {
        if (object instanceof Activity) {
            return ((Activity) object);
        } else if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        } else if (object instanceof android.app.Fragment) {
            return ((android.app.Fragment) object).getActivity();
        } else {
            return null;
        }
    }

    /**
     * 运行注解方法
     *
     * @param object
     * @param requestCode
     */
    private static void runAnnotatedMethods(Object object, int requestCode) {
        Class<?> clazz = object.getClass();
        if (isUsingAndroidAnnotations(object)) {
            clazz = clazz.getSuperclass();
        }
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(AfterPermissionGranted.class)) {
                // Check for annotated methods with matching request code.
                AfterPermissionGranted ann = method
                        .getAnnotation(AfterPermissionGranted.class);
                if (ann.value() == requestCode) {
                    // Method must be void so that we can invoke it
                    if (method.getParameterTypes().length > 0) {
                        throw new RuntimeException(
                                "Cannot execute non-void method "
                                        + method.getName());
                    }

                    try {
                        // Make method accessible if private
                        if (!method.isAccessible()) {
                            method.setAccessible(true);
                        }
                        method.invoke(object);
                    } catch (IllegalAccessException e) {
                        Log.e(TAG, "runDefaultMethod:IllegalAccessException", e);
                    } catch (InvocationTargetException e) {
                        Log.e(TAG,
                                "runDefaultMethod:InvocationTargetException", e);
                    }
                }
            }
        }
    }

    /**
     * Make sure Object is an Activity or Fragment
     *
     * @param object
     */
    private static void checkCallingObjectSuitability(Object object) {
        boolean isActivity = object instanceof Activity;
        boolean isSupportFragment = object instanceof Fragment;
        boolean isAppFragment = object instanceof android.app.Fragment;
        boolean isMinSdkM = Build.VERSION.SDK_INT >= 23;

        if (!(isSupportFragment || isActivity || (isAppFragment && isMinSdkM))) {
            if (isAppFragment) {
                throw new IllegalArgumentException(
                        "Target SDK needs to be greater than 23 if caller is android.app.Fragment");
            } else {
                throw new IllegalArgumentException(
                        "Caller must be an Activity or a Fragment.");
            }
        }

        // Make sure Object implements callbacks
        if (!(object instanceof PermissionCallback)) {
            throw new IllegalArgumentException(
                    "Caller must implement PermissionCallbacks.");
        }
    }

    /**
     * 是否使用AndroidAnnotations
     *
     * @param object
     * @return
     */
    private static boolean isUsingAndroidAnnotations(Object object) {
        if (!object.getClass().getSimpleName().endsWith("_")) {
            return false;
        }

        try {
            Class<?> clazz = Class
                    .forName("org.androidannotations.api.view.HasViews");
            return clazz.isInstance(object);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @SuppressLint("NewApi")
    public static Boolean checkCanDrawOverlays(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(context)) {
                return false;
            }
        }
        return true;
    }

    @SuppressLint("NewApi")
    public static void requestCanDrawOverlays(Context context) {
        if (!checkCanDrawOverlays(context)) {
            Intent intent = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }

}
