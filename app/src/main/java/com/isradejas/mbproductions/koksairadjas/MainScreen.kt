package com.isradejas.mbproductions.koksairadjas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main_screen.app_logo
import kotlinx.android.synthetic.main.activity_main_screen.button_test
import kotlinx.android.synthetic.main.activity_main_screen.button_podcasts
import kotlinx.android.synthetic.main.activity_main_screen.imageView2
import kotlinx.android.synthetic.main.activity_main_screen.quatation_layout
import kotlinx.android.synthetic.main.activity_main_screen.img_quat
import kotlinx.android.synthetic.main.activity_main_screen.txt_quat
import kotlinx.android.synthetic.main.activity_main_screen.txt_quat_author


class MainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        startAnim()
    }


    fun startAnim(){
        val left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)
        val left_to_right_delay = AnimationUtils.loadAnimation(this,R.anim.left_to_right_delay)
        val right_to_left = AnimationUtils.loadAnimation(this,R.anim.right_to_left)
        val right_to_left_delay = AnimationUtils.loadAnimation(this,R.anim.right_to_left_delay)
        val top_to_bottom_delay = AnimationUtils.loadAnimation(this,R.anim.top_to_bottom_delay)
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
