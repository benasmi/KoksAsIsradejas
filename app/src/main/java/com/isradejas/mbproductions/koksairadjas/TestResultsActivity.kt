package com.isradejas.mbproductions.koksairadjas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_question_test.*
import kotlinx.android.synthetic.main.activity_test_results.*
import kotlinx.android.synthetic.main.activity_test_results.results_card


import org.json.JSONArray


class TestResultsActivity : AppCompatActivity() {

    val engineers: ArrayList<Engineer> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_results)

        val topic: Int = intent.getIntExtra("Topic",0)
        setUpResults(topic)
        addEngineers(topic)
        // Creates a vertical Layout Manager
        similar_engineers_recycler.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this) as androidx.recyclerview.widget.RecyclerView.LayoutManager?

        // Access the RecyclerView Adapter and load the data into it
        similar_engineers_recycler.adapter = SimilarEngineersRecyclerview(engineers, this)


        img_back_arrow.setOnClickListener{
            startActivity(Intent(this,MainScreen::class.java))
        }
        startAnim()
    }

    fun addEngineers(currentTopic : Int) {
        var jsonFile = Utils.loadJSONFromAsset("kurejai.json", this)
        var jsonArray = JSONArray(jsonFile)

        for (x in 0 until jsonArray.length()){
            var obj = jsonArray.getJSONObject(x)
            var name:String = obj.get("name") as String
            var about:String = obj.get("specialty") as String
            var drawable:String = obj.get("drawable") as String

            var topicsArray = obj.getJSONArray("topics");
            for(y in 0 until topicsArray.length()){
                var topic = topicsArray.getInt(y)
                if(topic == currentTopic){
                    engineers.add(Engineer(name,about, Utils.getResourceID(drawable,"drawable",this)))
                }
            }
        }
    }

    fun setUpResults(currenTopic : Int){
        var jsonFile = Utils.loadJSONFromAsset("topics.json",this)
        var jsonArray = JSONArray(jsonFile)
        var obj = jsonArray.getJSONObject(currenTopic)

        test_result_image.setImageDrawable(getDrawable(Utils.getResourceID(obj.getString("drawable"),"drawable",this)))
        test_result_specialty.setText(obj.getString("name"))
        test_result_specialty_description.setText(obj.getString("description"))
    }


    fun startAnim(){

        val bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)


        results_card.startAnimation(bottom_to_top)

    }




}
