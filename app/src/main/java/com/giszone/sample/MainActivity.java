package com.giszone.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.giszone.popdialog.PopDialog;


/**
 * This class shows how to use PopDialog
 * <p/>
 * Created by giszone on 15/4/15.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout rlBackground;
    private Button btnTopLeft;
    private Button btnTopRight;
    private Button btnMiddleTop;
    private Button btnMiddleLeft;
    private Button btnMiddleRight;
    private Button btnMiddleBottom;
    private Button btnBottomLeft;
    private Button btnBottomRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniComponent();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void iniComponent() {
        rlBackground = findViewById(R.id.rlBackground);
        btnTopLeft = findViewById(R.id.btnTopLeft);
        btnTopRight = findViewById(R.id.btnTopRight);
        btnMiddleTop = findViewById(R.id.btnMiddleTop);
        btnMiddleLeft = findViewById(R.id.btnMiddleLeft);
        btnMiddleRight = findViewById(R.id.btnMiddleRight);
        btnMiddleBottom = findViewById(R.id.btnMiddleBottom);
        btnBottomLeft = findViewById(R.id.btnBottomLeft);
        btnBottomRight = findViewById(R.id.btnBottomRight);

        btnTopLeft.setOnClickListener(this);
        btnTopRight.setOnClickListener(this);
        btnMiddleTop.setOnClickListener(this);
        btnMiddleLeft.setOnClickListener(this);
        btnMiddleRight.setOnClickListener(this);
        btnMiddleBottom.setOnClickListener(this);
        btnBottomLeft.setOnClickListener(this);
        btnBottomRight.setOnClickListener(this);

        rlBackground.setOnTouchListener((v, event) -> {
            int[] location = new int[2];
            location[0] = (int) event.getX();
            location[1] = (int) event.getY();
            location[1] = location[1] + getActionBarHeight() + getStatusBarHeight();
            Toast.makeText(MainActivity.this, "x:" + location[0] + " y:" + location[1], Toast.LENGTH_SHORT).show();

//            View popView = MainActivity.this.getLayoutInflater().inflate(R.layout.layout_tip_list_view, null);
//            ListView listView = popView.findViewById(R.id.lvList);
//            List<String> items = new ArrayList<>();
//            for (int i = 0; i < 20; i++) {
//                items.add("" + i);
//            }
//            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, items);
//            listView.setAdapter(itemsAdapter);
//                        .setLayout(popView)

            new PopDialog(MainActivity.this)
                    .setLayoutResourceId(R.layout.layout_tip_content_horizontal) // 设置自定义气泡布局
                    .setOnViewItemClickListener((clickView, dialog) -> { // 气泡子控件点击事件
                        dialog.dismiss();
                        switch (clickView.getId()) {
                            case R.id.btn_got_it:
                                Toast.makeText(this, "R.id.btn_got_it", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.tv_content:
                                Toast.makeText(this, "R.id.tv_content", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }, R.id.btn_got_it, R.id.tv_content)
                    .setBackgroundColor(getResources().getColor(R.color.background_color_black)) // 气泡背景颜色
                    .setOutsideColor(getResources().getColor(R.color.outside_color_gray)) // 气泡外区域背景色
                    .setTouchOutsideDismiss(false) // 点击气泡外区域不消失
                    .setLocation(location) // 气泡锚点
                    .setGravity(PopDialog.GRAVITY_TOP) // 气泡在锚点上方
                    .setMatchParent(false) // 非全屏宽度
                    .setMarginLeftAndRight(24, 24)
                    .show();

            return false;
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTopLeft:
                View view = this.getLayoutInflater().inflate(R.layout.layout_tip_content_horizontal, null);
                new PopDialog(MainActivity.this)
//                        .setLayoutResourceId(R.layout.layout_tip_content_horizontal)//layout resource id
                        .setLayout(view)
                        .setBackgroundColor(MainActivity.this.getResources().getColor(R.color.background_color_black))
//                        .setLocation(new location[])//point in screen
                        .setLocationByAttachedView(btnTopLeft)
                        .setGravity(PopDialog.GRAVITY_BOTTOM)
                        .setAnimationTranslationShow(PopDialog.DIRECTION_X, 1000, -600, 100, -50, 50, 0)
                        .setAnimationAlphaShow(1000, 0.3f, 1.0f)
                        .setAnimationTranslationDismiss(PopDialog.DIRECTION_X, 500, -50, 800)
                        .setAnimationAlphaDismiss(500, 1.0f, 0.0f)
                        .setTouchOutsideDismiss(true)
                        .setMatchParent(true)
                        .setMarginLeftAndRight(24, 24)
                        .setOutsideColor(MainActivity.this.getResources().getColor(R.color.outside_color_trans))
                        .show();
                break;

            case R.id.btnTopRight:
                new PopDialog(MainActivity.this)
                        .setLayoutResourceId(R.layout.layout_tip_content_horizontal)
                        .setOnViewItemClickListener((clickView, dialog) -> { // 气泡子控件点击事件
                            dialog.dismiss();
                            switch (clickView.getId()) {
                                case R.id.btn_got_it:
                                    Toast.makeText(this, "R.id.btn_got_it", Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.tv_content:
                                    Toast.makeText(this, "R.id.tv_content", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }, R.id.btn_got_it, R.id.tv_content)
                        .setGravity(PopDialog.GRAVITY_BOTTOM)
                        .setBackgroundColor(MainActivity.this.getResources().getColor(R.color.background_color_black))
                        .setLocationByAttachedView(btnTopRight)
                        .setAnimationTranslationShow(PopDialog.DIRECTION_X, 350, 400, 0)
                        .setAnimationTranslationDismiss(PopDialog.DIRECTION_X, 350, 0, 400)
                        .setTouchOutsideDismiss(false)
                        .setMatchParent(false)
                        .setMarginLeftAndRight(24, 24)
                        .setOutsideColor(MainActivity.this.getResources().getColor(R.color.outside_color_gray))
                        .setOnPopDialogDismissed(() -> Toast.makeText(MainActivity.this, "dismiss", Toast.LENGTH_SHORT).show())
                        .setOnPopDialogShow(() -> Toast.makeText(MainActivity.this, "show", Toast.LENGTH_SHORT).show())
                        .show();
                break;
            case R.id.btnMiddleTop:
                new PopDialog(MainActivity.this)
                        .setLayoutResourceId(R.layout.layout_tip_content_horizontal)
                        .setBackgroundColor(MainActivity.this.getResources().getColor(R.color.background_color_blue))
                        .setLocationByAttachedView(btnMiddleTop)
                        .setAnimationTranslationShow(PopDialog.DIRECTION_Y, 1000, -800, 100, -50, 50, 0)
                        .setAnimationTranslationDismiss(PopDialog.DIRECTION_Y, 500, 0, -800)
                        .setGravity(PopDialog.GRAVITY_TOP)
                        .setTouchOutsideDismiss(true)
                        .setMatchParent(false)
                        .setMarginLeftAndRight(24, 24)
                        .setOutsideColor(MainActivity.this.getResources().getColor(R.color.outside_color_pink))
                        .show();
                break;
            case R.id.btnMiddleLeft:
                new PopDialog(MainActivity.this)
                        .setLayoutResourceId(R.layout.layout_tip_text)
                        .setBackgroundColor(MainActivity.this.getResources().getColor(R.color.background_color_purple))
                        .setLocationByAttachedView(btnMiddleLeft)
                        .setGravity(PopDialog.GRAVITY_RIGHT)
                        .setAnimationAlphaShow(300, 0.0f, 1.0f)
                        .setAnimationAlphaDismiss(300, 1.0f, 0.0f)
                        .setTouchOutsideDismiss(true)
                        .setMatchParent(false)
                        .setOutsideColor(MainActivity.this.getResources().getColor(R.color.outside_color_gray))
                        .show();
                break;
            case R.id.btnMiddleRight:
                new PopDialog(MainActivity.this)
                        .setLayoutResourceId(R.layout.layout_tip_text)
                        .setBackgroundColor(MainActivity.this.getResources().getColor(R.color.background_color_red))
                        .setLocationByAttachedView(btnMiddleRight)
                        .setGravity(PopDialog.GRAVITY_LEFT)
                        .setAnimationAlphaShow(300, 0.0f, 1.0f)
                        .setAnimationAlphaDismiss(300, 1.0f, 0.0f)
                        .setTouchOutsideDismiss(true)
                        .setMatchParent(false)
                        .setOutsideColor(MainActivity.this.getResources().getColor(R.color.outside_color_gray))
                        .show();
                break;
            case R.id.btnMiddleBottom:
                new PopDialog(MainActivity.this)
                        .setLayoutResourceId(R.layout.layout_tip_content_horizontal)
                        .setGravity(PopDialog.GRAVITY_BOTTOM)
                        .setBackgroundColor(MainActivity.this.getResources().getColor(R.color.background_color_brown))
                        .setLocationByAttachedView(btnMiddleBottom)
                        .setAnimationTranslationShow(PopDialog.DIRECTION_Y, 1000, 800, -100, -50, 50, 0)
                        .setAnimationTranslationDismiss(PopDialog.DIRECTION_Y, 500, 0, 800)
                        .setAnimationAlphaShow(1000, 0.3f, 1.0f)
                        .setTouchOutsideDismiss(true)
                        .setMatchParent(true)
                        .setMarginLeftAndRight(24, 24)
                        .setOutsideColor(MainActivity.this.getResources().getColor(R.color.outside_color_gray))
                        .show();
                break;
            case R.id.btnBottomLeft:
                new PopDialog(MainActivity.this)
                        .setLayoutResourceId(R.layout.layout_tip_text)
                        .setBackgroundColor(MainActivity.this.getResources().getColor(R.color.background_color_pink))
                        .setLocationByAttachedView(btnBottomLeft)
                        .setGravity(PopDialog.GRAVITY_TOP)
                        .setAnimationAlphaShow(600, 0.0f, 1.0f)
                        .setAnimationAlphaDismiss(600, 1.0f, 0.0f)
                        .setTouchOutsideDismiss(true)
                        .setMatchParent(false)
                        .setMarginLeftAndRight(24, 24)
                        .setOutsideColor(MainActivity.this.getResources().getColor(R.color.outside_color_trans))
                        .show();
                break;
            case R.id.btnBottomRight:
                new PopDialog(MainActivity.this)
                        .setLayoutResourceId(R.layout.layout_tip_content_horizontal)
                        .setOnViewItemClickListener((clickView, dialog) -> { // 气泡子控件点击事件
                            dialog.dismiss();
                            switch (clickView.getId()) {
                                case R.id.btn_got_it:
                                    Toast.makeText(this, "R.id.btn_got_it", Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.tv_content:
                                    Toast.makeText(this, "R.id.tv_content", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }, R.id.btn_got_it, R.id.tv_content)
                        .setBackgroundColor(MainActivity.this.getResources().getColor(R.color.background_color_black))
                        .setLocationByAttachedView(btnBottomRight)
                        .setGravity(PopDialog.GRAVITY_TOP)
                        .setAnimationTranslationShow(PopDialog.DIRECTION_X, 300, 400, 0)
                        .setAnimationTranslationShow(PopDialog.DIRECTION_Y, 300, 400, 0)
                        .setAnimationTranslationDismiss(PopDialog.DIRECTION_X, 300, 0, 400)
                        .setAnimationTranslationDismiss(PopDialog.DIRECTION_Y, 300, 0, 400)
                        .setTouchOutsideDismiss(false)
                        .setMatchParent(false)
                        .setMarginLeftAndRight(24, 24)
                        .setOutsideColor(MainActivity.this.getResources().getColor(R.color.outside_color_gray))
                        .show();
                break;
        }
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = this.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private int getActionBarHeight() {
        return this.getSupportActionBar().getHeight();
    }
}

