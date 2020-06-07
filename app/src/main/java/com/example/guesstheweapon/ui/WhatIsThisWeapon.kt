package com.example.guesstheweapon.ui

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.guesstheweapon.Data.DataGenerator
import com.example.guesstheweapon.Data.NameSouns
import com.example.guesstheweapon.R
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.fragment_score.*
import kotlinx.android.synthetic.main.fragment_what_is_this_weapon.view.*
import kotlin.random.Random

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class WhatIsThisWeapon : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var numberOfquestions = 0
    private var correct = 0
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var randomWeapon: NameSouns
    private var choice:Int=0
    private var chooseSound = 0
    private var data = DataGenerator.getData()
    private var the_sound = 0
    private var answer = ""

    //Exoplayer Resources
    private var player : SimpleExoPlayer? = null
    private lateinit var mediaSource : MediaSource
    private lateinit var dataSpec : DataSpec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_what_is_this_weapon, container, false)
        initExoplayer()
        chooseRandomWeapon()
        view.sound.setOnClickListener {
            playTheSound()
        }
        view.check.setOnClickListener {
            if (view.weaponame.text.toString().equals(answer, ignoreCase = true))
            {
                correct++
                view.yep.visibility = View.VISIBLE
                view.sound.visibility = View.INVISIBLE
                view.question.visibility = View.INVISIBLE
                view.true_answer.visibility = View.VISIBLE
                view.true_answer.text = answer
                Handler().postDelayed(
                    {
                        view.yep.visibility = View.INVISIBLE
                        view.sound.visibility = View.VISIBLE
                        view.question.visibility = View.VISIBLE
                        view.true_answer.visibility = View.INVISIBLE
                    }, 1000
                )
            }else{
                view.nope.visibility = View.VISIBLE
                view.sound.visibility = View.INVISIBLE
                view.question.visibility = View.INVISIBLE
                view.true_answer.visibility = View.VISIBLE
                view.true_answer.text = answer
                Handler().postDelayed(
                    {
                        view.nope.visibility = View.INVISIBLE
                        view.sound.visibility = View.VISIBLE
                        view.question.visibility = View.VISIBLE
                        view.true_answer.visibility = View.INVISIBLE
                    }, 1000
                )
            }

            chooseRandomWeapon()
            view.weaponame.setText("")
        }
        return view
    }

    fun chooseRandomWeapon(){
        if(numberOfquestions == 10){
            numberOfquestions=0
            val bundle = bundleOf("score" to correct)
            findNavController().navigate(R.id.action_whatIsThisWeapon_to_score2, bundle)
            correct=0
        }
        else {
            choice = Random.nextInt(0, data.size-1)
            chooseSound = Random.nextInt(0, 3)
            randomWeapon = data[choice]
            answer = randomWeapon.name
            if (chooseSound == 0) {
                the_sound = randomWeapon.reoading
            } else if (chooseSound == 1) {
                the_sound = randomWeapon.shooting
            } else {
                the_sound = randomWeapon.surpressor
            }
            numberOfquestions++
        }
    }
    fun playTheSound(){
        dataSpec = DataSpec(RawResourceDataSource.buildRawResourceUri(the_sound))
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
            },
            3000 // value in milliseconds
        )
    }
    override fun onDestroy() {
        super.onDestroy()
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

    private fun releasePlayer(){
        if (player != null) {
            player!!.release()
            player = null
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WhatIsThisWeapon.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WhatIsThisWeapon().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
