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

    // Initializing an empty ArrayList to be filled with animals
    val engineers: ArrayList<Engineer> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_engineers)

        img_back_arrow.setOnClickListener {
            onBackPressed()
        }

        // Loads animals into the ArrayList
        addEngineers()

        // Creates a vertical Layout Manager
        engineers_recyclerview.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        engineers_recyclerview.adapter = EngineersRecyclerView(engineers, this)

    }

        // Adds animals to the empty animals ArrayList
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

}
