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

    var player: MediaPlayer = MediaPlayer()
    var audioFinished = false;


    public var lastPos = -1;
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderPodcast {
        return ViewHolderPodcast(LayoutInflater.from(context).inflate(R.layout.podcast_element, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    override fun onBindViewHolder(holder: ViewHolderPodcast, position: Int) {
        holder?.question?.setText(items.get(position).description)



        if(position==lastPos){
            if(items[lastPos].isRunning){
                holder?.playButton.setBackgroundResource(R.drawable.ic_pause)

            }else{
                holder?.playButton.setBackgroundResource(R.drawable.ic_play)

            }
        }else{
            holder?.playButton.setBackgroundResource(R.drawable.ic_play)
        }

        holder?.playButton.setOnClickListener {



        //Log.i("TEST1", "${position}  |  ${lastPos}")
            if (!items[position].isRunning) {
                holder?.oldPosition
                //Jeigu ta patį paleidžia po pauzės
                if(position==lastPos && !audioFinished){
                    player.start()
                    items[position].isRunning = true
                    notifyDataSetChanged()
                    return@setOnClickListener
                }

                //Jeigu paleidžia visiškai kitą įrašą
                if(player.isPlaying && player!=null && !audioFinished){
                    items[lastPos].isRunning = false;
                    player.stop()
                    player.reset()
                    player.release()

                }

                items[position].isRunning = true;
                lastPos = position;
                player = MediaPlayer.create(context, items[position].fileName)
                player.start()
                audioFinished=false;
                Log.i("TEST1", "${player.duration}")
                player.setOnCompletionListener(){
                    Log.i("TEST1", "Audio finished")
                    player.reset()
                    audioFinished = true;
                    items[position].isRunning = false;
                    notifyDataSetChanged()
                }

                //Įrašo pauzė
            } else {
                items[position].isRunning = false;
                player.pause()
            }
            notifyDataSetChanged()

        }



    }


}

class ViewHolderPodcast (view: View) : RecyclerView.ViewHolder(view) {

    val question = view.txt_question
    val playButton  = view.btn_play


}


