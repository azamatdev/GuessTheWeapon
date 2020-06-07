package com.example.guesstheweapon.ui

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.guesstheweapon.Data.DataGenerator
import com.example.guesstheweapon.Data.NameSouns
import com.example.guesstheweapon.R
import kotlinx.android.synthetic.main.fragment_true_or_false.view.*
import kotlin.random.Random

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TrueOrFalse : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var numOfquestions = 0
    private var correct = 0
    private var listener: OnFragmentInteractionListener? = null
    private var choice:Int=0
    private var trueFalse:Int=0
    private lateinit var checkName:String
    private lateinit var randomWeaponOne:NameSouns
    private lateinit var randomWeaponTwo:NameSouns
    private var data = DataGenerator.getData()

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
        val view = inflater.inflate(R.layout.fragment_true_or_false, container, false)
        chooseRandomWeapon(view)
        view.yes.setOnClickListener {
            if (view.weapon_name.text.toString().equals(checkName, ignoreCase = true))
        {
            correct++
            view.verycorrect.visibility = View.VISIBLE
            view.the_weapon.visibility = View.INVISIBLE
            view.weapon_name.visibility = View.INVISIBLE
            it.isClickable = false
            view.no.isClickable = false
            Handler().postDelayed(
                {
                    view.verycorrect.visibility = View.INVISIBLE
                    view.the_weapon.visibility = View.VISIBLE
                    view.weapon_name.visibility = View.VISIBLE
                    it.isClickable = true
                    view.no.isClickable = true
                }, 1000
            )
        }else{
            view.verywrong.visibility = View.VISIBLE
            view.the_weapon.visibility = View.INVISIBLE
            view.weapon_name.visibility = View.INVISIBLE
                it.isClickable = false
                view.no.isClickable = false
            Handler().postDelayed(
                {
                    view.verywrong.visibility = View.INVISIBLE
                    view.the_weapon.visibility = View.VISIBLE
                    view.weapon_name.visibility = View.VISIBLE
                    it.isClickable = true
                    view.no.isClickable = true
                }, 1000
            )
        }

            chooseRandomWeapon(view)
        }
        view.no.setOnClickListener { if (view.weapon_name.text.toString().equals(checkName, ignoreCase = true))
        {
            view.verywrong.visibility = View.VISIBLE
            view.the_weapon.visibility = View.GONE
            view.weapon_name.visibility = View.INVISIBLE
            it.isClickable = false
            view.yes.isClickable = false
            Handler().postDelayed(
                {
                    view.verywrong.visibility = View.GONE
                    view.the_weapon.visibility = View.VISIBLE
                    view.weapon_name.visibility = View.VISIBLE
                    it.isClickable = true
                    view.yes.isClickable = true
                }, 1000
            )
        }else{
            correct++
            view.verycorrect.visibility = View.VISIBLE
            view.the_weapon.visibility = View.GONE
            view.weapon_name.visibility = View.INVISIBLE
            it.isClickable = false
            view.yes.isClickable = false
            Handler().postDelayed(
                {
                    view.verycorrect.visibility = View.GONE
                    view.the_weapon.visibility = View.VISIBLE
                    view.weapon_name.visibility = View.VISIBLE
                    it.isClickable = true
                    view.yes.isClickable = true
                }, 1000
            )
        }

            chooseRandomWeapon(view)
        }
        return view
    }

    fun chooseRandomWeapon(view:View){
        if(numOfquestions==10){
            numOfquestions=0
            val bundle = bundleOf("score" to correct)
            findNavController().navigate(R.id.action_trueOrFalse_to_score2, bundle)
            correct=0
        }else{
            numOfquestions++
            choice = Random.nextInt(0, data.size-1)
            randomWeaponOne = data[choice]
            view.the_weapon.setImageResource(randomWeaponOne.image)
            trueFalse = Random.nextInt(0,2)
            checkName = randomWeaponOne.name
            if(trueFalse==1){
                view.weapon_name.text = randomWeaponOne.name
            }else{
                choice = Random.nextInt(0, data.size)
                randomWeaponTwo = data[choice]
                while(randomWeaponOne.name==randomWeaponTwo.name){
                    choice = Random.nextInt(0, data.size)
                    randomWeaponTwo = data[choice]
                }
                view.weapon_name.text = randomWeaponTwo.name
            }
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
         * @return A new instance of fragment TrueOrFalse.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TrueOrFalse().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
