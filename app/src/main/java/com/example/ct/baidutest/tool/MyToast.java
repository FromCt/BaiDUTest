package com.example.ct.baidutest.tool;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ct on 2015/9/15.
 */
public class MyToast {

    private  static Toast toast;

    public static void showToast(Context context,String string) {

        if (context==null){
            return;
        }else{
            if (toast == null) {
                toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
            }else{
                toast.setText(string);
            }
            toast.show();
        }
    }
}

