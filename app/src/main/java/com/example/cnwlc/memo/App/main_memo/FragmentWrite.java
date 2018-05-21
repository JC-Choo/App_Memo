package com.example.cnwlc.memo.App.main_memo;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cnwlc.memo.Common.Defines;
import com.example.cnwlc.memo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bridge on 2018-05-21.
 */

public class FragmentWrite extends Fragment {
    @BindView(R.id.write_edit_text_content)
    EditText editTextContent;
    @BindView(R.id.write_image_view_1)
    ImageView imageView1;
    @BindView(R.id.write_image_view_2)
    ImageView imageView2;
    @BindView(R.id.write_image_view_3)
    ImageView imageView3;

    private Bitmap photo;
    private String selectedImagePath;

    public static FragmentWrite newInstance() {
        FragmentWrite fragment = new FragmentWrite();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_write, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @OnClick({R.id.writeA_floating_action_menu_item_camera, R.id.writeA_floating_action_menu_item_gallery, R.id.writeA_floating_action_menu_item_draw})
    public void onClickMethod(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.writeA_floating_action_menu_item_camera:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);    // 안드로이드 카메라 가이드
                intent.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
                try {
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, Defines.CODE_0);
                } catch (ActivityNotFoundException e) {
                    // Do nothing for now
                }
                break;
            case R.id.writeA_floating_action_menu_item_gallery:
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                //사진을 여러개 선택할수 있도록 한다
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), Defines.CODE_1);
                break;
            case R.id.writeA_floating_action_menu_item_draw:
//                intent = new Intent(this, DrawActivity.class);
//                startActivityForResult(intent, Defines.CODE_2);
                break;
        }
    }

    // 사진찍고 저장 및 불러오기, 갤러리에서 사진 불러오기 정의, bit맵 저장
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == Defines.CODE_0) {
                if (data != null) {
                    photo = (Bitmap) data.getExtras().get("data");
                    if (photo != null) {
                        imageView1.setImageBitmap(photo);

                        Uri cameraUri = data.getData();
                        selectedImagePath = getPath(cameraUri);
                    }
                }
            } else if (requestCode == Defines.CODE_1) {
                //기존 이미지 지우기
                imageView1.setImageResource(0);
                imageView2.setImageResource(0);
                imageView3.setImageResource(0);

                //ClipData 또는 Uri를 가져온다
                Uri uri = data.getData();
                ClipData clipData = data.getClipData();

                //이미지 URI 를 이용하여 이미지뷰에 순서대로 세팅한다.
                if (clipData != null) {
                    for (int i = 0; i < 3; i++) {
                        if (i < clipData.getItemCount()) {
                            Uri urione = clipData.getItemAt(i).getUri();
                            switch (i) {
                                case 0:
                                    imageView1.setImageURI(urione);
                                    break;
                                case 1:
                                    imageView2.setImageURI(urione);
                                    break;
                                case 2:
                                    imageView3.setImageURI(urione);
                                    break;
                            }
                        }
                    }
                } else if (uri != null) {
                    imageView1.setImageURI(uri);
                }
            } else if (requestCode == Defines.CODE_2) {
//                selectedImagePath = data.getStringExtra("saveUri");
//
//                photo = BitmapFactory.decodeFile(selectedImagePath);
//                imgview.setImageBitmap(photo);
//                imgview.startAnimation(animSlide_Down);
            }
        }
    }


    // 사진의 URI 경로를 받는 메소드
    public String getPath(Uri uri) {
        // uri가 null일경우 null반환
        if (uri == null) {
            return null;
        }
        // 미디어스토어에서 유저가 선택한 사진의 URI를 받아온다.
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // URI경로를 반환한다.
        return uri.getPath();
    }
}
