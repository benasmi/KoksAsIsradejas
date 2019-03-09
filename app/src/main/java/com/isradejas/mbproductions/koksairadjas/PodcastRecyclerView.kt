package com.isradejas.mbproductions.koksairadjas

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SeekBar
import kotlinx.android.synthetic.main.podcast_element.view.*



class PodcastRecyclerView(val items: ArrayList<Podcast>, val context: Context, seekBar: ProgressBar) : RecyclerView.Adapter<ViewHolderPodcast>() {

    companion object {
        var player: MediaPlayer = MediaPlayer()
        public lateinit var runnable:Runnable
        public var handler: Handler = Handler()
    }

    var audioFinished = false;
    var lastPos = -1;
    var progressBar = seekBar;

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
                setUpProgressBar()
                player.setOnCompletionListener(){
                    player.reset()
                    PodcastRecyclerView.handler.removeCallbacks(PodcastRecyclerView.runnable)
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

    // Method to initialize seek bar and audio stats
    private fun setUpProgressBar() {
        progressBar.max = player.duration

        runnable = Runnable {
            progressBar.progress = player.currentPosition

            //tv_pass.text = "${player.currentSeconds} sec"
            //val diff = player.seconds - player.currentSeconds
            //tv_due.text = "$diff sec"

            handler.postDelayed(runnable, 100)
        }
        handler.postDelayed(runnable, 100)
    }


}

class ViewHolderPodcast (view: View) : RecyclerView.ViewHolder(view) {

    val question = view.txt_question
    val playButton  = view.btn_play


}


