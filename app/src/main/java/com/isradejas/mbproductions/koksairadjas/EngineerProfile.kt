package com.isradejas.mbproductions.koksairadjas

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_engineer_profile.podcast_recycler
import kotlinx.android.synthetic.main.activity_engineer_profile.img_back_arrow
import kotlinx.android.synthetic.main.activity_engineer_profile.profile_image
import kotlinx.android.synthetic.main.activity_engineer_profile.profile_name
import kotlinx.android.synthetic.main.activity_engineer_profile.profile_description

class EngineerProfile : AppCompatActivity() {

    var edmundas_questions: Array<String> = arrayOf("Ką mėgote veikti žaidimo aikštelėje?",
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

    var sergejus_questions: Array<String> = arrayOf("Ką mėgote veikti žaidimo aikštelėje?",
            "Ką įdomaus turėjote galimybę kurti universiteto studijų metu, kas jus išlaikė šiame kelyje",
            "Kada buvo Jūsų sėkmės pradžia",
            "Galbūt galite papasakoti apie paskutinę ar įsimintiną savo inžinerinę sėkmę bei nesėkmes?",
            "Kokio gyvenimo norėtumėte, Elono Musko, Bilo Geitso, Eriko Schmito, Steve Jobso, Prezidentės Grybauskaitės, Premjero ar galbūt kt.?",
            "Ar išdrįstumėt kardinaliai pakeisti savo darbo sritį? Jei taip, tai kokia linkme dirbtumėte?",
            "Kokios srities specialistai, asmenybės buvo Jūsų siekiamybė vaikystėje/pauglystėje?",
            "Ar buvo lengva pasirinkti profesiją?",
            "Kaip Jūs renkatės bendradarbius? Kaip pas jus vyksta kūrybinis darbas? Ar turite specialų sėkmingo produkto receptą?",
            "Kokia būtų jūsų ideali diena? Ko šiuo metu trūksta?",
            "Ką prisimenate statę darželio, mokyklos laikais",
            "Kiek kito Jūsų pomėgiai paauglystėje?",
            "Ar jūsų sukurto produkto sėkmė pasirodė netikėta?",
            "Ar šiuo metu turite autoritetą, kuris jus įkvepia labiausiai?")

    var vytautaas_questions: Array<String> = arrayOf("Ką mėgote veikti žaidimo aikštelėje?",
            "Ką įdomaus turėjote galimybę kurti universiteto studijų metu, kas jus išlaikė šiame kelyje",
            "Kada buvo Jūsų sėkmės pradžia?",
            "Galbūt galite papasakoti apie paskutinę ar įsimintiną savo inžinerinę sėkmę bei nesėkmes?",
            "Kokio gyvenimo norėtumėte, Elono Musko, Bilo Geitso, Eriko Schmito, Steve Jobso, Prezidentės Grybauskaitės, Premjero ar galbūt kt.?",
            "Ar išdrįstumėt kardinaliai pakeisti savo darbo sritį? Jei taip, tai kokia linkme dirbtumėte?",
            "Kokios srities specialistai, asmenybės buvo Jūsų siekiamybė vaikystėje/pauglystėje?",
            "Kokie yra jaunimo profesijos iššūkiai?",
            "Kaip Jūs renkatės bendradarbius? Kaip pas jus vyksta kūrybinis darbas? Ar turite specialų sėkmingo produkto receptą?",
            "Kokia būtų jūsų ideali diena? Ko šiuo metu trūksta?",
            "Kažką taisydavote?",
            "Kokios mėgstamiausios technologijos?",
            "Ar jau turėjote produktą, kai pradėjote kurti įmonę?",
            "Kaip nesėkmės Jūsų nesužlugdė?",
            "Kaip jaunam žmogui atrasti motyvacijos?",
            "Ko palinkėtumėt jaunam žmogui?",
            "Kaip įsivaizduojate inžinieriaus gyvenimo, kelia, gimsiančio šiandien ligoninėje?")


    companion object {
     lateinit var progressTextview:TextView
    }

    val podcasts: ArrayList<Podcast> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_engineer_profile)

        img_back_arrow.setOnClickListener {

            if(PodcastRecyclerView.player.isPlaying && PodcastRecyclerView.player!=null){
                PodcastRecyclerView.player.reset()
                PodcastRecyclerView.handler.removeCallbacks(PodcastRecyclerView.runnable)
            }

            onBackPressed()
        }

        var seekBar = findViewById<ProgressBar>(R.id.progress_bar);
        progressTextview = findViewById<TextView>(R.id.tv_progress)

        val engineer = intent.extras.get("Engineer") as Engineer
        profile_image.setImageDrawable(resources.getDrawable(engineer.PhotoResource))
        profile_name.setText(engineer.FullName)
        profile_description.setText(engineer.About)
        getPodcasts(engineer.FullName);

        // Creates a vertical Layout Manager
        podcast_recycler.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this) as androidx.recyclerview.widget.RecyclerView.LayoutManager?

        // Access the RecyclerView Adapter and load the data into it
        podcast_recycler.adapter = PodcastRecyclerView(podcasts, this,seekBar)

        setFonts()
    }






    fun getPodcasts(engineerName:String){
        if(engineerName.equals("Edmundas Balcikonis")){
            podcasts.add(Podcast(false, R.raw.edmundas_zaidimu_aikstele,edmundas_questions[0]))
            podcasts.add(Podcast(false, R.raw.edmundas_studiju_metu,edmundas_questions[1]))
            podcasts.add(Podcast(false, R.raw.edmundas_sekmes_pradzia,edmundas_questions[2]))
            podcasts.add(Podcast(false, R.raw.edmundas_inzinerine_sekme,edmundas_questions[3]))
            podcasts.add(Podcast(false, R.raw.edmundas_kokio_gyvenimo_noretumet,edmundas_questions[4]))
            podcasts.add(Podcast(false, R.raw.edmundas_keicia_profesija,edmundas_questions[5]))
            podcasts.add(Podcast(false, R.raw.edmundas_jaunam_zmogui_linkejimas,edmundas_questions[6]))
            podcasts.add(Podcast(false, R.raw.edmundas_naujagimiai,edmundas_questions[7]))
            podcasts.add(Podcast(false, R.raw.edmundas_siekiamybe_vaikysteje,edmundas_questions[8]))
            podcasts.add(Podcast(false, R.raw.edmundas_ar_sunku_pasirinkti_profesija,edmundas_questions[9]))
            podcasts.add(Podcast(false, R.raw.edmundas_koks_sprendimas_nuleme,edmundas_questions[10]))
            podcasts.add(Podcast(false, R.raw.edmundas_renkasi_darbuotoja,edmundas_questions[11]))
            podcasts.add(Podcast(false, R.raw.edmundas_ko_truksta_dabar,edmundas_questions[12]))
            podcasts.add(Podcast(false, R.raw.edmundas_motyvacijos_paslaptis,edmundas_questions[13]))
        }
        if(engineerName.equals("Sergejus Trofimovas ir Ričardas Jaščemskas")){
            podcasts.add(Podcast(false, R.raw.sergejus_ka_megote_veikti_zaidimo,sergejus_questions[0]))
            podcasts.add(Podcast(false, R.raw.sergejus_universiteto_laikais,sergejus_questions[1]))
            podcasts.add(Podcast(false, R.raw.sergejus_inzinerine_sekme,sergejus_questions[2]))
            podcasts.add(Podcast(false, R.raw.sergejus_kokio_gyvenimo_noretumet,sergejus_questions[3]))
            podcasts.add(Podcast(false, R.raw.sergejus_ar_keistumete_profesija,sergejus_questions[4]))
            podcasts.add(Podcast(false, R.raw.sergejus_kokios_srities_specialistai,sergejus_questions[5]))
            podcasts.add(Podcast(false, R.raw.sergejus_ar_sunku_pasirinkti_profesija,sergejus_questions[6]))
            podcasts.add(Podcast(false, R.raw.sergejus_kaip_renkaties_darbuotoja,sergejus_questions[7]))
            podcasts.add(Podcast(false, R.raw.sergejus_ko_truksta,sergejus_questions[8]))
            podcasts.add(Podcast(false, R.raw.sergejus_darzelio_laikai,sergejus_questions[9]))
            podcasts.add(Podcast(false, R.raw.sergejus_kiek_kito_pomegiai,sergejus_questions[10]))
            podcasts.add(Podcast(false, R.raw.sergejus_ar_netiketa_sekme,sergejus_questions[11]))
            podcasts.add(Podcast(false, R.raw.sergejus_ar_turite_autoriteta,sergejus_questions[12]))
        }
        if(engineerName.equals("Vytautas Jokužis")){
            podcasts.add(Podcast(false, R.raw.vytautas_universiteto_metais,vytautaas_questions[1]))
            podcasts.add(Podcast(false, R.raw.vytautas_sekmes_pradzia,vytautaas_questions[2]))
            podcasts.add(Podcast(false, R.raw.vytautas_inzinerine_sekme_nesekme,vytautaas_questions[3]))
            podcasts.add(Podcast(false, R.raw.vytautas_kokio_gyvenimo,vytautaas_questions[4]))
            podcasts.add(Podcast(false, R.raw.vytautas_keicia_profesija,vytautaas_questions[5]))
            podcasts.add(Podcast(false, R.raw.vytautas_kokios_srities_specialistai,vytautaas_questions[6]))
            podcasts.add(Podcast(false, R.raw.vytautas_jaunimo_profesijos_issukiai,vytautaas_questions[7]))
            podcasts.add(Podcast(false, R.raw.vytautas_renkasi_darbuotojus,vytautaas_questions[8]))
            podcasts.add(Podcast(false, R.raw.vytautas_ko_truksta,vytautaas_questions[9]))
            podcasts.add(Podcast(false, R.raw.vytautas_ka_taisydavote,vytautaas_questions[10]))
            podcasts.add(Podcast(false, R.raw.vytautas_megstamiausios_technologijos,vytautaas_questions[11]))
            podcasts.add(Podcast(false, R.raw.vytautas_ar_jau_turejote_produkta,vytautaas_questions[12]))
            podcasts.add(Podcast(false, R.raw.vytautas_kaip_nesekmes_nesuzlugde,vytautaas_questions[13]))
            podcasts.add(Podcast(false, R.raw.vytautas_motyvacija,vytautaas_questions[14]))
            podcasts.add(Podcast(false, R.raw.vytautas_ko_palinketumet_jaunam_zmogui,vytautaas_questions[15]))
            podcasts.add(Podcast(false, R.raw.vytautas_naujagimiai,vytautaas_questions[16]))
        }



    }

    fun setFonts(){
        val typeface = Typeface.createFromAsset(applicationContext.assets, "fonts/larseit.otf")
        profile_name.setTypeface(typeface)
        profile_description.setTypeface(typeface)
    }

}
