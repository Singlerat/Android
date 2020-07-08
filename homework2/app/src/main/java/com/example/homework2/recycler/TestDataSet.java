package com.example.homework2.recycler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.homework2.R;

import java.util.ArrayList;
import java.util.List;

public class TestDataSet {

    public static List<TestData> getData() {
        List<TestData> result = new ArrayList();
        result.add(new TestData("让人忘记原唱的歌手","12:30"));
        result.add(new TestData("林丹退役","12:30"));
        result.add(new TestData("你在教我做事？","11:30"));
        result.add(new TestData("投身乡村教育的燃灯者","12:40"));
        result.add(new TestData("暑期嘉年华","12:07"));
        result.add(new TestData("2020年三伏天有40天","12:09"));
        result.add(new TestData("会跟游客合照的老虎","14:08"));
        result.add(new TestData("苏州暴雨","11:07"));
        result.add(new TestData( "6月全国菜价上涨","11:08"));
        result.add(new TestData("猫的第六感有多强","12:08"));
        result.add(new TestData("IU真好看","12:07"));
        return result;
    }

}

