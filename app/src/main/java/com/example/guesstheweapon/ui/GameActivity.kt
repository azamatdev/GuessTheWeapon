package com.example.guesstheweapon.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.guesstheweapon.R
import com.facebook.ads.AudienceNetworkAds
import kotlinx.android.synthetic.main.fragment_game.view.*
import com.facebook.ads.InterstitialAd
import androidx.core.os.HandlerCompat.postDelayed
import android.os.Handler
import android.widget.Toast
import com.facebook.ads.AdSettings


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GameActivity : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var interstitialAd:InterstitialAd?=null

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
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        //AdSettings.setIntegrationErrorMode(INTEGRATION_ERROR_CALLBACK_MODE)
        AudienceNetworkAds.initialize(context)
        interstitialAd = InterstitialAd(context,"YOUR_PLACEMENT_ID")

        view.trueOrFalse.setOnClickListener { v -> v.findNavController().navigate(R.id.action_gameActivity_to_trueOrFalse) }
        view.findItsName.setOnClickListener { v -> v.findNavController().navigate(R.id.action_gameActivity_to_findItsName) }
        view.whatIsThisWeapon.setOnClickListener { v -> v.findNavController().navigate(R.id.action_gameActivity_to_whatIsThisWeapon) }

        interstitialAd!!.loadAd()
        //showAdWithDelay()
        Handler().postDelayed({
            if(interstitialAd!!.isAdLoaded)
                interstitialAd!!.show()
            else
                Toast.makeText(context,"Failed", Toast.LENGTH_LONG).show()
        }, 120000)
        return view
    }

    private fun showAdWithDelay() {
        // Handler handler = new Handler();
        Handler().postDelayed(Runnable {
            // Check if interstitialAd has been loaded successfully
            if (!interstitialAd!!.isAdLoaded) {
                return@Runnable
            }
            // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
            if (interstitialAd!!.isAdInvalidated) {
                return@Runnable
            }
            // Show the ad
            interstitialAd!!.show()
        }, 1000 * 60) // Show the ad after 1 minute
    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onDestroy() {
        interstitialAd!!.destroy()
        super.onDestroy()
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
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
         * @return A new instance of fragment GameActivity.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameActivity().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
