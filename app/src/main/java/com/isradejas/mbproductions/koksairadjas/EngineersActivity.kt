package com.isradejas.mbproductions.koksairadjas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_engineers.img_back_arrow
import kotlinx.android.synthetic.main.activity_engineers.engineers_recyclerview


class EngineersActivity : AppCompatActivity() {

    // Initializing an empty ArrayList to be filled with animals
    val engineers: ArrayList<Engineer> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_engineers)

        img_back_arrow.setOnClickListener {
            onBackPressed()
        }

        // Loads animals into the ArrayList
        addEngineers()

        // Creates a vertical Layout Manager
        engineers_recyclerview.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        engineers_recyclerview.adapter = EngineersRecyclerView(engineers, this)

    }
        // Adds animals to the empty animals ArrayList
        fun addEngineers() {
            Log.i("TEST", packageName);
            engineers.add(Engineer("Edmundas Balcikonis","CEO TrackDuck", R.drawable.edmundas_balcikonis))
            engineers.add(Engineer("Evaldas Pabreza"," 'Integrated Optics' direktorius", R.drawable.evaldas_pabreza))
            engineers.add(Engineer("Romas Puidokas","Automatikos inžinierius", R.drawable.romas_puidokas))
            engineers.add(Engineer("Vytautas Jokužis","Elinta įkūrėjas, technologijos mokslų daktaras", R.drawable.vytautas_jokuzis))
            engineers.add(Engineer("Vytenis Bužas","'LithuanicaSAT-ą' kūrėjas, kosminės misijos vadovas", R.drawable.vytenis_buzas))
            engineers.add(Engineer("Sergejus Trofimovas ir Ričardas Jaščemskas","'Nordcurrent' įkūrėjas ir komunikacijos", R.drawable.sergejus_ricardas))
        }

        fun startAnim(){

        }
}
