package com.choo.application.memo;

import android.app.Activity;
import android.content.pm.PackageManager;

import com.choo.application.memo.Common.Defines;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class VersionChecker {

    private static VersionChecker instance;
    public static VersionChecker getInstance() {
        if (instance == null)
            instance = new VersionChecker();

        return instance;
    }


    // 마켓에서 앱 버전 가져오기
    public String getMarketVersion() {
        String url = Defines.MARKET_APP_ADDRESS;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements currentVersionDiv = doc.select(".BgcNfc");
            Elements currentVersion = doc.select("div.hAyfc div span.htlgb");
            for (int i = 0; i < currentVersionDiv.size(); i++) {
                if (currentVersionDiv.get(i).text().equals("Current Version")) {
                    return currentVersion.get(i).text();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 안드로이드 스튜디오에서 작성한 버전 가져오기
    public String getAppVersion(Activity context) {
        String device_version = "";
        try {
            device_version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return device_version;
    }
}
