package com.example.googleloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

//Display Login Information (User NickName & Profile Photo)
public class ResultActivity extends AppCompatActivity {

    private TextView tv_result;
    private ImageView iv_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        String photoUrl = intent.getStringExtra("photoUrl");

        tv_result = (TextView) findViewById(R.id.tv_result);
        iv_profile = (ImageView) findViewById(R.id.iv_pofile);

        tv_result.setText(nickName);
        Glide.with(this).load(photoUrl).into(iv_profile);   //Display Profile Photo

    }
}