package com.choo.application.memo.Util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.choo.application.memo.Common.Defines;
import com.choo.application.memo.MemoApplication;

/**
 * Created by Bridge on 2018-06-15.
 */
public class SharedPreferenceUtil {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private SharedPreferenceUtil() {
        /*
          getSharedPreference("name",0); : 쉐어프리퍼런스 저장 폴더에 name.xml 이름의 파일로 저장이 된다. 다른 이름도 얼마든지 가능
          getSharedPreference(0); : 현재 액티비티의 이름으로 xml 파일이 저장
          PreferenceManager.getDefaultSharedPreference(this); : 환경설정에 저장된 값으로 쉐어프리퍼런스를 가져오는 방법
         */

        preferences = PreferenceManager.getDefaultSharedPreferences(MemoApplication.getInstance());
//        preferences = context.getSharedPreferences(MemoApplication.userId, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    private static SharedPreferenceUtil instance;
    public static SharedPreferenceUtil getInstance() {
        if (instance == null)
            instance = new SharedPreferenceUtil();

        return instance;
    }



    // 맨 처음 1회에 한해 memo 라는 폴더 이름을 sqlite에 저장하기 위한 set/get/clear
    public void setFirstFolderName(String memoName) {
        editor.putString(Defines.FIRST_FOLDER_NAME, memoName);
        editor.commit();
    }
    public String getFirstFolderName() {
        return preferences.getString(Defines.FIRST_FOLDER_NAME, "");
    }
    public void clearFirstFolderName() {
        editor.remove(Defines.FIRST_FOLDER_NAME);
        editor.apply();
    }

    // SplashActivity 에서 더이상 보지 않기 클릭 시에 대한 set/get/clear
    public void setCheckSplash(boolean checkSplash) {
        editor.putBoolean(Defines.CHECK_SPLASH, checkSplash);
        editor.commit();
    }
    public boolean getCheckSplash() {
        return preferences.getBoolean(Defines.CHECK_SPLASH, false);
    }
    public void clearCheckSplash() {
        editor.remove(Defines.CHECK_SPLASH);
        editor.apply();
    }

    // 선택한 폴더 ID에 대한 set/get/clear
    public void setFolderNameId(int folderNameId) {
        editor.putInt(Defines.SELECTED_FOLDER_NAME_ID, folderNameId);
        editor.commit();
    }
    public int getFolderNameId() {
        return preferences.getInt(Defines.SELECTED_FOLDER_NAME_ID, -1);
    }
    public void clearFolderNameId() {
        editor.remove(Defines.SELECTED_FOLDER_NAME_ID);
        editor.apply();
    }

    // network status 에 대한 set/get/clear
    public void setNetworkStatus(String networkStatus) {
        editor.putString(Defines.NETWORK_STATUS, networkStatus);
        editor.commit();
    }
    public String getNetworkStatus() {
        return preferences.getString(Defines.NETWORK_STATUS, "");
    }
    public void clearNetworkStatus() {
        editor.remove(Defines.NETWORK_STATUS);
        editor.apply();
    }

    // Market Version 에 대한 set/get/clear
    public void setMarketVersion(String marketVersion) {
        editor.putString(Defines.MARKET_VERSION, marketVersion);
        editor.commit();
    }
    public String getMarketVersion() {
        return preferences.getString(Defines.MARKET_VERSION, "");
    }
    public void clearMarketVersion() {
        editor.remove(Defines.MARKET_VERSION);
        editor.apply();
    }
}