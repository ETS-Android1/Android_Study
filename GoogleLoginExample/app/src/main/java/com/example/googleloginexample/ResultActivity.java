package com.example.googleloginexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

//Display Login Information (User NickName & Profile Photo)
public class ResultActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    private TextView tv_result;
    private ImageView iv_profile;
    Button btn_signout;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        auth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
//        String nickName = intent.getStringExtra("nickName");
//        String photoUrl = intent.getStringExtra("photoUrl");

        tv_result = (TextView) findViewById(R.id.tv_result);
        iv_profile = (ImageView) findViewById(R.id.iv_pofile);
        btn_signout = (Button) findViewById(R.id.btn_signout);

        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
            }
        });

        tv_result.setText(auth.getCurrentUser().getDisplayName());
        Glide.with(this).load(Uri.parse(auth.getCurrentUser().getPhotoUrl().toString())).into(iv_profile);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if(firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        return;
    }
}