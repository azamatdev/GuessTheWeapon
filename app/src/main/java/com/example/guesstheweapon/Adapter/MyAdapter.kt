package com.example.guesstheweapon.Adapter

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.guesstheweapon.Data.DataGenerator
import com.example.guesstheweapon.R
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.weapon_info.view.*

class WeaponsAdapter() : RecyclerView.Adapter<WeaponsAdapter.MapHolder>(){
    var data = DataGenerator.getData()

    //Exoplayer Resources
    private var player : SimpleExoPlayer? = null
    private lateinit var mediaSource : MediaSource
    private lateinit var dataSpec : DataSpec
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapHolder {
        context = parent.context
        return MapHolder(LayoutInflater.from(parent.context).inflate(R.layout.weapon_info, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MapHolder, position: Int) {
        holder.myView.my_weapon.setImageResource(data[position].image)
        holder.myView.name_of_weapon.text = data[position].name
        holder.myView.shooting_sound.setOnClickListener {
            initExoplayer()
            if(!player!!.isPlaying)
                playTheSound(data[position].shooting, holder.myView)
        }
        holder.myView.reloading_sound.setOnClickListener { initExoplayer()
            if(!player!!.isPlaying)
                playTheSound(data[position].reoading, holder.myView) }
        holder.myView.surpressor_sound.setOnClickListener { initExoplayer()
            if(!player!!.isPlaying)
                playTheSound(data[position].surpressor, holder.myView) }
    }

    private fun playTheSound(sound:Int, view: View){
        view.surpressor_sound.isClickable = false
        view.shooting_sound.isClickable = false
        view.reloading_sound.isClickable = false
        dataSpec = DataSpec(RawResourceDataSource.buildRawResourceUri(sound))
        val dataSourceFactory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, "guessTheWeapon")
        )
        mediaSource =
            ProgressiveMediaSource.Factory { dataSourceFactory.createDataSource() }
                .setTag(0)
                .createMediaSource(dataSpec.uri)
        player?.prepare(mediaSource)
        player?.playWhenReady = true

        Handler().postDelayed(
            {
                //action after waiting
                if(player!!.isPlaying)
                    player?.stop()
                view.surpressor_sound.isClickable = true
                view.shooting_sound.isClickable = true
                view.reloading_sound.isClickable = true
            },
            3000 // value in milliseconds
        )
    }

    private fun initExoplayer(){
        if(player == null){

            player = ExoPlayerFactory.newSimpleInstance(
                context,
                DefaultTrackSelector(),
                DefaultLoadControl()
            )
        }
    }

    class MapHolder(view: View): RecyclerView.ViewHolder(view) {
        var myView = view


    }
}