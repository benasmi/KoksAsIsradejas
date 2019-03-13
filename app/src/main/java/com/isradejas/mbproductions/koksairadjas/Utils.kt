package com.isradejas.mbproductions.koksairadjas

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.content.res.TypedArray
import android.R





class Utils{

    companion object {

        fun loadJSONFromAsset(jsonFile : String, context : Context): String {
            val inputStream = context.getAssets().open(jsonFile)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            return String(buffer)
        }


         fun getResourceID(resName: String, resType: String, ctx: Context): Int {
            val ResourceID = ctx.getResources().getIdentifier(resName, resType,
                    ctx.getApplicationInfo().packageName)
            return if (ResourceID == 0) {
                throw IllegalArgumentException(
                        "No resource string found with name $resName"
                )
            } else {
                ResourceID
            }
        }

        fun getToolBarHeight(context: Context): Int {
            val attrs = intArrayOf(R.attr.actionBarSize)
            val ta = context.obtainStyledAttributes(attrs)
            val toolBarHeight = ta.getDimensionPixelSize(0, -1)
            ta.recycle()
            return toolBarHeight
        }

        fun getScreenHeight(activity: Activity):Int{
            val displayMetrics = DisplayMetrics()
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
            return displayMetrics.heightPixels
        }
    }




}