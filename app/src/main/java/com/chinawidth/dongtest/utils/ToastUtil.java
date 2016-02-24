package com.chinawidth.dongtest.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	private static Toast mToast;
	
	private static void showToast(Context context,String text,int duration){
		if(mToast!=null){
			mToast.setText(text);
			mToast.setDuration(duration);
		}
		else{
			mToast=new Toast(context).makeText(context, text, duration);
		}
		mToast.show();
	}
	
	private static void showToast(Context context,int res,int duration){
		if(mToast!=null){
			mToast.setText(res);
			mToast.setDuration(duration);
		}
		else{
			mToast=new Toast(context).makeText(context, res, duration);
		}
		mToast.show();
	}
	
	public static void showToastShort(Context context,String text){
		showToast(context,text, Toast.LENGTH_SHORT);
	}
	public static void showToastShort(Context context,int res){
		showToast(context,res, Toast.LENGTH_SHORT);
	}
	public static void showToastLong(Context context,String text){
		showToast(context,text, Toast.LENGTH_LONG);
	}
	public static void showToastLong(Context context,int res){
		showToast(context,res, Toast.LENGTH_LONG);
	}
}
