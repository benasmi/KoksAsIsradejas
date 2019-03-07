package com.isradejas.mbproductions.koksairadjas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_engineer_profile.podcast_recycler
import kotlinx.android.synthetic.main.activity_engineer_profile.img_back_arrow
import kotlinx.android.synthetic.main.activity_engineer_profile.profile_image
import kotlinx.android.synthetic.main.activity_engineer_profile.profile_name
import kotlinx.android.synthetic.main.activity_engineer_profile.profile_description

class EngineerProfile : AppCompatActivity() {

    var questions: Array<String> = arrayOf("Ką mėgote veikti žaidimo aikštelėje?",
            "Ką įdomaus turėjote galimybę kurti universiteto studijų metu, kas jus išlaikė šiame kelyje",
            "Kada buvo Jūsų sėkmės pradžia",
            "Galbūt galite papasakoti apie paskutinę ar įsimintiną savo inžinerinę sėkmę bei nesėkmes?",
            "Kokio gyvenimo norėtumėte, Elono Musko, Bilo Geitso, Eriko Schmito, Steve Jobso, Prezidentės Grybauskaitės, Premjero ar galbūt kt.?",
            "Ar išdrįstumėt kardinaliai pakeisti savo darbo sritį? Jei taip, tai kokia linkme dirbtumėte?",
            "Ko palinkėtumėt jaunam žmogui?",
            "Kaip įsivaizduojate inžinieriaus gyvenimo kelia, gimsiančio šiandien ligoninėje?",
            "Kokios srities specialistai, asmenybės buvo Jūsų siekiamybė vaikystėje/pauglystėje?",
            "Ar buvo lengva pasirinkti profesiją?",
            "Koks sprendimas, Jūsų manymų, tai nulėmė?",
            "Kaip Jūs renkatės bendradarbius? Kaip pas jus vyksta kūrybinis darbas? Ar turite specialų sėkmingo produkto receptą?",
            "Kokia būtų jūsų ideali diena? Ko šiuo metu trūksta?",
            "Jūsų motyvacijos šaltinis?")

    val podcasts: ArrayList<Podcast> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_engineer_profile)

        img_back_arrow.setOnClickListener {
            onBackPressed()
        }

        val engineer = intent.extras.get("Engineer") as Engineer
        profile_image.setImageDrawable(resources.getDrawable(engineer.PhotoResource))
        profile_name.setText(engineer.FullName)
        profile_description.setText(engineer.About)
        getPodcasts(engineer.FullName);

        // Creates a vertical Layout Manager
        podcast_recycler.layoutManager = LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        podcast_recycler.adapter = PodcastRecyclerView(podcasts, this)

    }


    fun getPodcasts(engineerName:String){

        if(engineerName.equals("Edmundas Balcikonis")){
            podcasts.add(Podcast(false, R.raw.dolphi,questions[0]))
            podcasts.add(Podcast(false, R.raw.edmundas_universiteto_metu,questions[1]))
            podcasts.add(Podcast(false, R.raw.edmundas_sekmes_pradzia,questions[2]))
            podcasts.add(Podcast(false, R.raw.edmundas_kokio_gyvenimo,questions[3]))
            podcasts.add(Podcast(false, R.raw.edmundas_keicia_profesija,questions[4]))
            podcasts.add(Podcast(false, R.raw.edmundas_palinkejimas,questions[5]))
            podcasts.add(Podcast(false, R.raw.edmundas_naujagimiai,questions[6]))
            podcasts.add(Podcast(false, R.raw.edmundas_kas_skatino,questions[7]))
            podcasts.add(Podcast(false, R.raw.edmundas_profesijos_pasirinkimas,questions[8]))
            podcasts.add(Podcast(false, R.raw.edmundas_kas_leme_sekme,questions[9]))
            podcasts.add(Podcast(false, R.raw.edmundas_renkasi_darbuotojus,questions[10]))
            podcasts.add(Podcast(false, R.raw.edmundas_ko_truksta,questions[11]))
            podcasts.add(Podcast(false, R.raw.edmundas_motyvacija,questions[12]))

        }


    }

}
