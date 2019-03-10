package com.isradejas.mbproductions.koksairadjas

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_question_test.emotionView
import kotlinx.android.synthetic.main.activity_question_test.ratingView
import kotlinx.android.synthetic.main.activity_question_test.gradientBackgroundView
import kotlinx.android.synthetic.main.activity_question_test.txt_question
import kotlinx.android.synthetic.main.activity_question_test.submit_question_button
import org.json.JSONArray
import kotlin.math.max


class QuestionTestActivity : AppCompatActivity() {

    var topic1:Int = 0;
    var topic2:Int = 0;
    var topic3:Int = 0;
    var topic4:Int = 0;
    var topic5:Int = 0;

    var selectedTopic:Int = 0;

    var questionId: Int = -1;
    lateinit var questions: String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_test)

        ratingView.setRatingChangeListener { previousRating, newRating ->
            emotionView.setRating(previousRating, newRating)
            gradientBackgroundView.changeBackground(previousRating, newRating)

        }

        questions = loadJSONFromAsset("test.json")
        setQuestion(questions)
        setFonts()



        submit_question_button.setOnClickListener {
            setQuestion(questions)

        }



    }

    fun selectedTopic() {
        var maxtopic = max(max(topic1, topic2), max(max(topic3, topic4), topic5))
        if (maxtopic == topic1) {
            selectedTopic = 0;
        }
        if (maxtopic == topic2) {
            selectedTopic = 1;

        }
        if (maxtopic == topic3) {
            selectedTopic = 2;

        }
        if (maxtopic == topic4) {
            selectedTopic = 3;

        }
        if (maxtopic == topic5) {
            selectedTopic = 4;

        }
    }
    fun setQuestion(questionJson : String){
            questionId++;
            var jsonArray = JSONArray(questionJson)
            if(questionId<jsonArray.length()){
                var obj = jsonArray.getJSONObject(questionId)
                var question = obj.get("question")
                var topic = obj.get("topic")
                txt_question.setText("${question}")

                sumTopics(topic as Int)
            }else{
                submit_question_button.setText("Pamatyti rezultatus!")
                startActivity(Intent(this, TestResultsActivity::class.java).putExtra("Topic",selectedTopic))
                Log.i("TEST1", "${topic1}|${topic2}|${topic3}|${topic4}|${topic5}")
            }


    }

    fun sumTopics(currentTopic : Int){
        if(currentTopic==0){
            topic1+=ratingView.getCurrentRating();
        }else if(currentTopic==1){
            topic2+=ratingView.getCurrentRating();
        }else if(currentTopic==2){
            topic3+=ratingView.getCurrentRating();
        }else if(currentTopic==3){
            topic4+=ratingView.getCurrentRating();
        }else if(currentTopic==4){
            topic5+=ratingView.getCurrentRating();
        }
    }

    fun loadJSONFromAsset(jsonFile : String): String {
        var json: String? = null
        val inputStream = getAssets().open(jsonFile)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer)
        return json
    }

    fun setFonts(){
        val typeface = Typeface.createFromAsset(applicationContext.assets, "fonts/larseit.otf")

        txt_question.setTypeface(typeface)
    }
}
