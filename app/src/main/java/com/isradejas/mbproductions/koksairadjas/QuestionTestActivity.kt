package com.isradejas.mbproductions.koksairadjas

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import com.dm.emotionrating.library.RatingView
import kotlinx.android.synthetic.main.activity_question_test.emotionView
import kotlinx.android.synthetic.main.activity_question_test.ratingView
import kotlinx.android.synthetic.main.activity_question_test.gradientBackgroundView
import kotlinx.android.synthetic.main.activity_question_test.txt_question
import kotlinx.android.synthetic.main.activity_question_test.submit_question_button
import org.json.JSONArray
import java.util.*


class QuestionTestActivity : AppCompatActivity() {
    var currentTopic:Int = 0;
    var questionId: Int = -1;

    private lateinit var questions: JSONArray;
    private lateinit var topicScores: IntArray

    companion object {
        private val RATING_OFFSET: Int = -3;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_test)

        ratingView.setRatingChangeListener { previousRating, newRating ->
            if(newRating == 0){
                emotionView.setRating(previousRating, 3);
            }else{
                emotionView.setRating(previousRating, newRating)
                submit_question_button.isEnabled = true;
                submit_question_button.setTextColor(resources.getColor(R.color.white))
            }
            gradientBackgroundView.changeBackground(previousRating, newRating)
        }

        questions = JSONArray(Utils.loadJSONFromAsset("test.json",this))

        val topicCount = JSONArray(Utils.loadJSONFromAsset("topics.json",this)).length()
        topicScores = IntArray(topicCount){0};

        currentTopic = setQuestion()
        setFonts()

        submit_question_button.setOnClickListener {
            addScoreToTopic(currentTopic, ratingView.getCurrentRating()+RATING_OFFSET)
            Log.i("TOPIC_LOG", "Current topic: ${currentTopic}")
            Log.i("TOPIC_LOG", "Topic count: ${topicScores.size}")
            Log.i("TOPIC_LOG", "Current scores:")
            Log.i("TOPIC_LOG", Arrays.toString(topicScores))
            Log.i("TOPIC_LOG", "=================");
            ratingView.setRating(0);
            submit_question_button.isEnabled = false;
            submit_question_button.setTextColor(resources.getColor(R.color.gray))
            currentTopic = setQuestion()
        }

        startAnim()
    }

    fun addScoreToTopic(topic: Int, score:Int){
        this.topicScores[topic]+=score;
    }

    fun selectedTopic() :Int{
        return topicScores.indexOf(topicScores.max()!!)
    }

    fun setQuestion() : Int{
            questionId++;
            if(questionId<questions.length()){
                val obj = questions.getJSONObject(questionId)
                val question = obj.get("question")
                val topic = obj.getInt("topic")
                txt_question.setText("${question}")
                return topic
            }else{
                startActivity(Intent(this, TestResultsActivity::class.java).putExtra("Topic",selectedTopic()))
                return -1
            }
    }

    fun setFonts(){
        val typeface = Typeface.createFromAsset(applicationContext.assets, "fonts/larseit.otf")

        txt_question.setTypeface(typeface)
    }


    fun startAnim(){
        val left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)
        val right_to_left = AnimationUtils.loadAnimation(this,R.anim.right_to_left)
        val top_to_bottom = AnimationUtils.loadAnimation(this,R.anim.top_to_bottom)
        val bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)

        txt_question.startAnimation(top_to_bottom)
        emotionView.startAnimation(bottom_to_top)
        submit_question_button.startAnimation(left_to_right)
        ratingView.startAnimation(right_to_left)
    }
}
