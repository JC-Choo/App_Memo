package com.choo.application.memo;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;

import com.choo.application.memo.Common.Defines;
import com.choo.application.memo.Common.Dlog;
import com.choo.application.memo.Util.SharedPreferenceUtil;
import com.choo.application.memo.Util.sqlite.SQLiteUtil;

import java.util.Locale;

/**
 * Created by Bridge on 2018-06-15.
 */

public class MemoApplication extends Application {
    private static MemoApplication instance;
    public static MemoApplication getInstance() {
        if (instance == null)
            instance = new MemoApplication();

        return instance;
    }

    public static boolean DEBUG;

    @Override
    public void onCreate() {    // 앱 생성될 때 호출됩니다. 모든 상태변수와 리소스 초기화 로직을 이곳에서 관리합니다.
        super.onCreate();

        instance = this;

        setInitView();

        this.DEBUG = isDebuggable(this);
    }

    @Override
    public void onLowMemory() { // 시스템의 리소스가 부족할 때 발생
        super.onLowMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {   // 컴포넌트가 실행되는 동안 기기의 설정 정보가 변경될 때 시스템에서 호출
        super.onConfigurationChanged(newConfig);
    }

    private void setInitView() {
        if( !SharedPreferenceUtil.getInstance().getFirstFolderName().equals(getString(R.string.memo)) ) {
            SharedPreferenceUtil.getInstance().setFirstFolderName(getString(R.string.memo));
            SQLiteUtil.getInstance().setInitView(getApplicationContext(), Defines.FOLDER);
            SQLiteUtil.getInstance().insertFolder(getString(R.string.memo));
        }

//        Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
    }

    // 현재 디버그모드여부를 리턴
    private boolean isDebuggable(Context context) {
        boolean debuggable = false;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(context.getPackageName(), 0);
            debuggable = (0 != (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {
            /* debuggable variable will remain false */
        }

        return debuggable;
    }

    public void getSystemLanguage() {
        Locale systemLocale = getApplicationContext().getResources().getConfiguration().locale;
        String strDisplayCountry = systemLocale.getDisplayCountry(); // 대한민국
        String strCountry = systemLocale.getCountry(); // KR
        String strLanguage = systemLocale.getLanguage(); // ko

        Dlog.i("strDisplayCountry : " + strDisplayCountry);
        Dlog.i("strCountry : " + strCountry);
        Dlog.i("strLanguage : " + strLanguage);
    }
}
