package com.isradejas.mbproductions.koksairadjas

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main_screen.app_logo
import kotlinx.android.synthetic.main.activity_main_screen.button_test
import kotlinx.android.synthetic.main.activity_main_screen.button_podcasts
import kotlinx.android.synthetic.main.activity_main_screen.imageView2
import kotlinx.android.synthetic.main.activity_main_screen.img_quat
import kotlinx.android.synthetic.main.activity_main_screen.txt_quat
import kotlinx.android.synthetic.main.activity_main_screen.txt_quat_author
import kotlinx.android.synthetic.main.activity_main_screen.txt_test
import kotlinx.android.synthetic.main.activity_main_screen.txt_test_description
import kotlinx.android.synthetic.main.activity_main_screen.txt_famous_engineer
import kotlinx.android.synthetic.main.activity_main_screen.txt_famous_engineer_descp
import org.json.JSONArray
import java.util.*
import java.util.Calendar.DAY_OF_YEAR


class MainScreen : AppCompatActivity() {


    lateinit var preferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        preferences = getSharedPreferences("QUOTES", Context.MODE_PRIVATE);
        var quoteId : Int = preferences.getInt("QuoteId",0)
        var day : Int = preferences.getInt("CurrentDay",0)

        setQuote(quoteId,day,Utils.loadJSONFromAsset("quotes.json",this))

        startAnim()
        onClicks()
    }

    fun onClicks(){

        button_podcasts.setOnClickListener{
            startActivity(Intent(this, EngineersActivity::class.java))
        }

        button_test.setOnClickListener{
            startActivity(Intent(this,QuestionTestActivity::class.java))
        }
    }


    fun setQuote(quoteId : Int, day : Int, jsonFile:String){
        var jsonArray = JSONArray(jsonFile)
        var dayOfYear = Calendar.getInstance().get(DAY_OF_YEAR);
        Log.i("TEST1", "${day}|${dayOfYear}|${quoteId}");

        var currentQuoteId = quoteId;
        if(dayOfYear!=day){
         currentQuoteId++;
            if(currentQuoteId>jsonArray.length()){
                currentQuoteId=0;
            }
            preferences.edit().putInt("QuoteId",currentQuoteId).commit()
            preferences.edit().putInt("CurrentDay", dayOfYear).commit()
        }

        var obj = jsonArray.getJSONObject(currentQuoteId)
        var quote = obj.get("quote")
        var author = obj.get("author")
        txt_quat.setText("${quote}")
        txt_quat_author.setText("-${author}")
    }


    fun startAnim(){

        val typeface = Typeface.createFromAsset(applicationContext.assets, "fonts/larseit.otf")

        txt_quat.setTypeface(typeface)
        txt_quat_author.setTypeface(typeface)
        txt_test.setTypeface(typeface)
        txt_test_description.setTypeface(typeface)
        txt_famous_engineer.setTypeface(typeface)
        txt_famous_engineer_descp.setTypeface(typeface)

        val left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)
        val left_to_right_delay = AnimationUtils.loadAnimation(this,R.anim.left_to_right_delay)
        val right_to_left = AnimationUtils.loadAnimation(this,R.anim.right_to_left)
        val right_to_left_delay = AnimationUtils.loadAnimation(this,R.anim.right_to_left_delay)
        val top_to_bottom = AnimationUtils.loadAnimation(this,R.anim.top_to_bottom)
        val spin = AnimationUtils.loadAnimation(this,R.anim.spin);

        imageView2.startAnimation(spin)
        app_logo.startAnimation(top_to_bottom)
        img_quat.startAnimation(left_to_right_delay)
        txt_quat.startAnimation(right_to_left_delay)
        txt_quat_author.startAnimation(right_to_left_delay)
        button_test.startAnimation(left_to_right)
        button_podcasts.startAnimation(right_to_left)



    }
}
