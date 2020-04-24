package com.arcsoft.arcfacedemo.utils;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class LayoutUtil extends AppCompatActivity {

        /**
         * 软盘弹出，面板上移
         * @param root 要上移的根布局layout对象
         * @param scrollToView 事件监听对象
         */
        public static void controlKeyboardLayout(final View root, final View scrollToView) {

            root.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            Rect rect = new Rect();
                            root.getWindowVisibleDisplayFrame(rect);
                            int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
                            if (rootInvisibleHeight > 300) {
                                int[] location = new int[2];
                                scrollToView.getLocationInWindow(location);
                                int scrollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
                                root.scrollTo(0, scrollHeight);
                            } else {
                                root.scrollTo(0, 0);
                            }
                        }
                    });
        }

        /**
         * 透明式状态栏
         */
        public static void setNullStatusBar(Window window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        }

        /**
         * 白色状态栏
         */
        public static void setWhiteStatusBar(Window window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        }
}

