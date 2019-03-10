package com.isradejas.mbproductions.koksairadjas

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.similar_engineer.view.*;
import java.io.Serializable


class SimilarEngineersRecyclerview(val items : ArrayList<Engineer>, val context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<SimilarEngineerViewHolder>() {

    val typeface = Typeface.createFromAsset(context.assets, "fonts/larseit.otf")

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SimilarEngineerViewHolder {
        return SimilarEngineerViewHolder(LayoutInflater.from(context).inflate(R.layout.similar_engineer,p0,false ))
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    override fun onBindViewHolder(holder: SimilarEngineerViewHolder, position: Int) {
        holder?.profile_name.setText(items[position].FullName)
        holder?.profile_desc.setText(items[position].About)
        holder?.profile_pic?.setImageDrawable(context.resources.getDrawable(items.get(position).PhotoResource));
    }
}

class SimilarEngineerViewHolder (view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to

    val profile_name = view.profile_name
    val profile_desc = view.profile_description
    val profile_pic = view.profile_image

}