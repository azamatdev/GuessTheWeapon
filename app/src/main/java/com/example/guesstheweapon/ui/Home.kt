package com.example.guesstheweapon.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.guesstheweapon.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import com.facebook.ads.*;
import com.facebook.ads.AdView
import android.widget.LinearLayout
import com.facebook.ads.AdSize





private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Home : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var adView: AdView? = null

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
        // Find the Ad Container
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view.startGame.setOnClickListener { it.findNavController().navigate(R.id.action_home2_to_gameActivity) }
        view.learnWeapons.setOnClickListener { it.findNavController().navigate(R.id.action_home2_to_learnWeapons) }
        adView = AdView(context, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50)
        val adContainer = view.banner_container

        // Add the ad view to your activity layout
        adContainer.addView(adView)

        // Request an ad
        adView!!.loadAd()
        return view
    }

    override fun onDestroy() {
        if (adView != null) {
            adView!!.destroy()
        }
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
