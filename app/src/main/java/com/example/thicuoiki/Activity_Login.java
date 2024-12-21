package com.example.thicuoiki;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONObject;

import java.util.Arrays;

public class Activity_Login extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK_ACCOUNT = 1001;
    private EditText emailEditText;
    private static final String EXTRA_NAME = "EXTRA_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);



    }




}