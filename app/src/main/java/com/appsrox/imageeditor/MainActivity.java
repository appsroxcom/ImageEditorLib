package com.appsrox.imageeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.burhanrashid52.photoeditor.EditImageActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_PICK_IMAGE = 101;
    private static final int REQUEST_EDIT_IMAGE = 102;

    private Button mButton;
    private ImageView mImageView;
    private Uri mImageURI;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PICK_IMAGE) {
            Intent editIntent = new Intent(this, EditImageActivity.class);
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    editIntent.setDataAndType(data.getData(), "image/*");
                }
            }
            startActivityForResult(editIntent, REQUEST_EDIT_IMAGE);
        }
        if (requestCode == REQUEST_EDIT_IMAGE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    mImageURI = data.getData();
                    mImageView.setImageURI(mImageURI);
                    mButton.setText("Edit");
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.image);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (mImageURI != null) {
            startActivityForResult(new Intent(this, EditImageActivity.class).setDataAndType(mImageURI, "image/*"), REQUEST_EDIT_IMAGE);

        } else {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_PICK_IMAGE);
            } else {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_PICK_IMAGE);
            }
        }
    }
}
