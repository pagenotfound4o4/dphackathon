package org.emnets.dianping.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.jayfang.dropdownmenu.DropDownMenu;
import com.jayfang.dropdownmenu.OnMenuSelectedListener;

import org.emnets.dianping.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kea on 15-8-9.
 */
public class FilterTestActivity extends Activity {
    private DropDownMenu mMenu;
    final String[] arr1=new String[]{"全部城市","北京","上海","广州","深圳","北京","上海","广州","深圳","北京","上海","广州","深圳"};
    final String[] arr2=new String[]{"男","女"};
    final String[] arr3=new String[]{"全部年龄","10","20","30","40","50","60","70"};

    final String[] strings=new String[]{"选择城市","选择性别","选择年龄"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.filter_main);

//        locationView = findViewById()

        init();
    }

    private void init() {
        mMenu=(DropDownMenu)findViewById(R.id.menu);
        mMenu.setMenuCount(3);//Menu的个数
        mMenu.setShowCount(6);//Menu展开list数量太多是只显示的个数
        mMenu.setShowCheck(true);//是否显示展开list的选中项
        mMenu.setMenuTitleTextSize(16);//Menu的文字大小
        mMenu.setMenuTitleTextColor(Color.BLACK);//Menu的文字颜色
        mMenu.setMenuListTextSize(16);//Menu展开list的文字大小
        mMenu.setMenuListTextColor(Color.BLACK);//Menu展开list的文字颜色
        mMenu.setMenuBackColor(Color.WHITE);//Menu的背景颜色
        mMenu.setMenuPressedBackColor(Color.WHITE);//Menu按下的背景颜色
        mMenu.setCheckIcon(R.drawable.ico_make);//Menu展开list的勾选图片
        mMenu.setUpArrow(R.drawable.arrow_up);//Menu默认状态的箭头
        mMenu.setDownArrow(R.drawable.arrow_down);//Menu按下状态的箭头
        mMenu.setShowDivider(true);
        mMenu.setMenuListBackColor(getResources().getColor(R.color.white));
        mMenu.setMenuListSelectorRes(R.color.white);
        mMenu.setArrowMarginTitle(20);
        mMenu.setDefaultMenuTitle(strings);
        mMenu.setMenuPressedTitleTextColor(Color.BLACK);

        List<String[]> items = new ArrayList<>();
        items.add(arr1);
        items.add(arr2);
        items.add(arr3);
        mMenu.setMenuItems(items);
        mMenu.setIsDebug(false);

        mMenu.setMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            //Menu展开的list点击事件  RowIndex：list的索引  ColumnIndex：menu的索引
            public void onSelected(View listview, int RowIndex, int ColumnIndex) {


            }
        });
    }
}
