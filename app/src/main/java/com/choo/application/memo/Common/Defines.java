package com.choo.application.memo.Common;

/**
 * Created by Bridge on 2018-05-06.
 */

public class Defines {
    public final static int CODE_0 = 0;
    public final static int CODE_1 = 1;
    public final static int CODE_2 = 2;
    public final static int CODE_401 = 401;     // login fail
    public final static int CODE_1000 = 1000;   // success

    public final static int COUNT_DOWN_INTERVAL = 1000;

    public final static String MARKET_APP_ADDRESS = "https://play.google.com/store/apps/details?id=com.choo.application.memo";

    // BUNDLE KEY
    public final static String PAGE_NUMBER = "PAGE_NUMBER";
    public final static String POSITION = "POSITION";
    public final static String MEMO_TIME = "MEMO_TIME";
    public final static String MEMO_CONTENT = "MEMO_CONTENT";
    public final static String MEMO_IMAGE_PATH = "MEMO_IMAGE_PATH";

    // DATABASE NAME
    public final static String DATABASE_NAME = "MEMO_APPLICATION.db";
    // TABLE NAME
    public final static String FOLDER = "FOLDER";
    public final static String MEMO = "MEMO";
    // INDEX NAME
    public final static String ID = "id";
    public final static String FOLDER_NAME = "folder_name";
    public final static String FOLDER_NAME_ID = "folder_name_id";
    public final static String TIME = "time";
    public final static String CONTENT = "content";
    public final static String IMAGE_PATH = "image_path";

    // Shared Preference KEY
    public final static String FIRST_FOLDER_NAME = "FIRST_FOLDER_NAME";
    public final static String SELECTED_FOLDER_NAME_ID = "SELECTED_FOLDER_NAME_ID";
    public final static String NETWORK_STATUS = "NETWORK_STATUS";
    public final static String MARKET_VERSION = "MARKET_VERSION";
    public final static String CHECK_SPLASH = "CHECK_SPLASH";

    // INTENT KEY
    public final static String SELECTED_FOLDER_NAME = "SELECTED_FOLDER_NAME";
    public final static String MAIN_TO_MEMO = "MAIN_TO_MEMO";
    public final static String THE_NUMBER_OF_MEMO = "THE_NUMBER_OF_MEMO";

    // INTENT VALUE
    public final static String WRITE = "WRITE";
    public final static String READ = "READ";


    public final static String TYPE_MOBILE = "MOBILE_DATA_ENABLED";
    public final static String TYPE_WIFI = "WIFI_ENABLED";
    public final static String TYPE_NOT_CONNECTED = "NOT_CONNECTED_TO_INTERNET";
}
