package com.isradejas.mbproductions.koksairadjas

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.engineer.view.*;
import java.io.Serializable


class EngineersRecyclerView(val items : ArrayList<Engineer>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    val typeface = Typeface.createFromAsset(context.assets, "fonts/larseit.otf")

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.engineer,p0,false ))
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.engineerPhoto?.setImageDrawable(context.resources.getDrawable(items.get(position).PhotoResource));
        holder?.engineerName?.setText(items.get(position).FullName)
        holder?.engineerName.setTypeface(typeface)
        holder?.engineerDescription?.setText(items.get(position).About)
        holder?.engineerName.setTypeface(typeface)
        holder?.buttonLayout?.setOnClickListener {
            context.startActivity(Intent(context,EngineerProfile::class.java).putExtra("Engineer",items[position] as Serializable))
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to


    val buttonLayout = view.button_podcasts
    val engineerPhoto = view.profile_image
    val engineerName = view.engineer_name
    val engineerDescription = view.enginer_description

}