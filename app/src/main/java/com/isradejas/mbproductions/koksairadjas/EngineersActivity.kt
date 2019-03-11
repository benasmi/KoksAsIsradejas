package com.isradejas.mbproductions.koksairadjas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_engineers.img_back_arrow
import kotlinx.android.synthetic.main.activity_engineers.engineers_recyclerview
import org.json.JSONArray


class EngineersActivity : AppCompatActivity() {

    val engineers: ArrayList<Engineer> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_engineers)

        onClicks()
        addEngineers()

        engineers_recyclerview.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        engineers_recyclerview.adapter = EngineersRecyclerView(engineers, this)

    }

        fun addEngineers() {
            var jsonFile = Utils.loadJSONFromAsset("kurejai.json", this)
            var jsonArray = JSONArray(jsonFile)

            for (x in 0 until jsonArray.length()){
                var obj = jsonArray.getJSONObject(x)
                var name:String = obj.get("name") as String
                var about:String = obj.get("specialty") as String
                var drawable:String = obj.get("drawable") as String
                engineers.add(Engineer(name,about, Utils.getResourceID(drawable,"drawable",this)))
            }
        }

    fun onClicks(){
        img_back_arrow.setOnClickListener {
            onBackPressed()
        }
    }

}
