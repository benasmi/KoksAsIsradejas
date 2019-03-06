package com.isradejas.mbproductions.koksairadjas

import android.content.Context
import android.media.MediaPlayer
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.podcast_element.view.*

class PodcastRecyclerView(val items : ArrayList<Podcast>, val context: Context) : RecyclerView.Adapter<ViewHolderPodcast>() {

    private var player: MediaPlayer = MediaPlayer()
    private var lastPos = -1;
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderPodcast {
        return ViewHolderPodcast(LayoutInflater.from(context).inflate(R.layout.podcast_element, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    override fun onBindViewHolder(holder: ViewHolderPodcast, position: Int) {
        holder?.question?.setText(items.get(position).description)
        holder?.playLayout.setOnClickListener {
            if (!items[position].isRunning) {
                if(position==lastPos){
                    player.start()
                    items[position].isRunning = true
                    return@setOnClickListener
                }

                if(player.isPlaying && player!=null){
                    items[lastPos].isRunning = false;
                    player.stop()
                    player.reset()
                    player.release()
                }

                items[position].isRunning = true;
                lastPos = position;
                player = MediaPlayer.create(context, items[position].fileName)
                player.start()
            } else {
                items[position].isRunning = false;
                player.pause()
            }
        }


    }
}

class ViewHolderPodcast (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val question = view.txt_question
    val playButton  = view.btn_play
    val playLayout = view.play_layout

}
