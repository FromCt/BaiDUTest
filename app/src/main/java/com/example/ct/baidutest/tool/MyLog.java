package com.example.ct.baidutest.tool;

import android.util.Log;

/**
 * Created by Administrator on 2015/7/30.
 * 打印日志的时候  tag不能有：号。不然打不出来。就被拒了。
 */
public class MyLog {
    private static boolean aBoolean=true;

    public static  void i(String tag,String string){
        if (aBoolean){
            if(tag!="" && !tag.equals("MyListView")){//tag=="login"
                Log.i(tag, string);
            }
        }
    }
}
