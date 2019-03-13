package com.isradejas.mbproductions.koksairadjas

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {

    companion object {
        private const val DELAY_MILLIS:Long = 3000;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        setFonts()
        startAnims()
    }

    fun setFonts(){
        val typeface = Typeface.createFromAsset(applicationContext.assets, "fonts/larseit.otf")
        app_name.setTypeface(typeface);
    }

    fun startAnims(){
        val bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        val fade = AnimationUtils.loadAnimation(this,R.anim.fade_in)
        app_name.alpha = 0f;
        fade.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                Handler().postDelayed(object: Runnable{
                    override fun run() {
                        this@IntroActivity.startActivity(Intent(this@IntroActivity, MainScreen::class.java))
                        this@IntroActivity.finish()
                    }

                }, DELAY_MILLIS)
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
        bottom_to_top.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                app_name.startAnimation(fade)
                app_name.alpha = 1f
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })
        app_logo.startAnimation(bottom_to_top)
    }
}
