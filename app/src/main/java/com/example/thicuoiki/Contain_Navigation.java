package com.example.thicuoiki;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Contain_Navigation extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contain_navigation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Gọi trang cá nhân

        ImageView imgvPersional = findViewById(R.id.img_vw_persional);

        imgvPersional.setOnClickListener(v -> {
            Intent intent = new Intent(Contain_Navigation.this, Activity_Personal.class);
            startActivity(intent);
        });

        // Gọi trang thong bao

        ImageView imgvNotice = findViewById(R.id.img_vw_notice);

        imgvNotice.setOnClickListener(v -> {
            Intent intent = new Intent(Contain_Navigation.this, Notice.class);
            startActivity(intent);
        });

        ImageView heartIcon = findViewById(R.id.ivHeart);
        heartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupGuide(v); // Hiển thị PopupWindow
            }
        });
    }
    private void showPopupGuide(View anchorView) {
        // Inflate layout của popup
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_guide, null);

        // Tạo PopupWindow
        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);

        // Hiển thị popup ngay bên dưới icon (đè lên giao diện)
        popupWindow.showAsDropDown(anchorView, 0, -20); // Điều chỉnh vị trí hiển thị với offset (x, y)
    }
}