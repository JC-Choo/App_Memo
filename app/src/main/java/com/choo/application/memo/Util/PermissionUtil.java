package com.choo.application.memo.Util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.choo.application.memo.MemoApplication;
import com.choo.application.memo.R;

import java.util.ArrayList;
import java.util.List;

//import com.example.cnwlc.app_memo.InviteItem;
//import com.example.cnwlc.app_memo.InviteRecyclerAdapter;

<<<<<<< HEAD:app/src/main/java/com/example/cnwlc/memo/Util/PermissionUtil.java

/**
 * Created by Bridge on 2018-05-06.
 */
=======
/**
* Created by Bridge on 2018-06-01.
*/
>>>>>>> android:app/src/main/java/com/choo/application/memo/Util/PermissionUtil.java

public class PermissionUtil {

    private static PermissionUtil instance;
    public static PermissionUtil getInstance() {
        if (instance == null)
            instance = new PermissionUtil();

        return instance;
    }

    private Activity context;
    private RecyclerView recyclerView;
    public void setInitValue(Activity context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    private DialogUtil dialogUtil;
    public void showPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionReadContactsResult = context.checkSelfPermission(Manifest.permission.CAMERA);
            if (permissionReadContactsResult == PackageManager.PERMISSION_DENIED) {
                /* 사용자가 한번이라도 거부한 적이 있는지 조사해 있다면 true 반환 */
                if (context.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    dialogUtil = DialogUtil.getDialog(context, MemoApplication.getInstance().getString(R.string.request_permission_title),
                            MemoApplication.getInstance().getString(R.string.request_permission_content), yes, intentFundA);
                    dialogUtil.show();
                } else {    /* 최초로 권한을 요청할 때 */
                    context.requestPermissions(new String[]{Manifest.permission.CAMERA}, 1000);
                }
//                if (context.shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)
//                        && context.shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS)
//                        && context.shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {
//                    dialogUtil = DialogUtil.getDialog(context, "", "내용", yes, intentFundA);
//                    dialogUtil.show();
//                } else {    /* 최초로 권한을 요청할 때 */
//                    context.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS}, 1000);
//                }
            } else {      /* 권한이 있을 때 */
//                getContacts();
            }
        } else {
//            getContacts();
        }
    }



//    private List<InviteItem> inviteItems = new ArrayList<>();
    private List<String> arrayListContactsName = new ArrayList<>();
    private List<String> arrayListContactsPhone = new ArrayList<>();

    private void getContacts() {
        Cursor cursor = context.managedQuery(ContactsContract.Contacts.CONTENT_URI, new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_ID
        }, null, null, ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");

        if (!cursor.moveToNext()) {
            intentFundActivity();
            return;
        }

        while (cursor.moveToNext()) {
            try {
                String v_id = cursor.getString(0);
                String v_display_name = cursor.getString(1);
                String v_phone = contactsPhone(v_id);

                arrayListContactsName.add(v_display_name);
                arrayListContactsPhone.add(v_phone);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        cursor.close();
    }
    private void intentFundActivity() {
        dialogUtil = DialogUtil.getDialog(context, "제목", "내용", intentFundA);
        dialogUtil.show();
    }

    // 기계에서 핸드폰 번호 가져오기
    private String contactsPhone(String p_id) {
        String reuslt = null;

        if (p_id == null || p_id.trim().equals("")) {
            return reuslt;
        }

        Cursor cursor = context.managedQuery(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + p_id,
                null, null);
        while (cursor.moveToNext()) {
            try {
                reuslt = cursor.getString(0);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        cursor.close();
        return reuslt;
    }

    private View.OnClickListener yes = new View.OnClickListener() {
        public void onClick(View v) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS}, 1000);
            }

            dialogUtil.dismiss();
        }
    };

    private View.OnClickListener intentFundA = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();
            dialogUtil.dismiss();
        }
    };
}
