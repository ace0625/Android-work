package com.example.toastproject;

import android.content.Context;
import android.widget.Toast;

public class Mytoast
{
	public static void showToast(Context context, String text)
	{
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
}
