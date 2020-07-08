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
public class TestDataSet_horizon {
    public static List<TestData> getData() {
        List<TestData> result = new ArrayList();
        result.add(new TestData("粉丝","12:30"));
        result.add(new TestData("赞","12:30"));
        result.add(new TestData( "@我的","11:30"));
        result.add(new TestData("评论","12:40"));
        return result;
    }
}
