package com.isradejas.mbproductions.koksairadjas

import android.content.Context

class Utils{

    companion object {

        fun loadJSONFromAsset(jsonFile : String, context : Context): String {
            var json: String? = null
            val inputStream = context.getAssets().open(jsonFile)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
            return json
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
    }


}