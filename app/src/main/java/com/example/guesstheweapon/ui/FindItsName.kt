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
import kotlinx.android.synthetic.main.fragment_find_its_name.view.*
import kotlinx.android.synthetic.main.fragment_score.*
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FindItsName.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FindItsName.newInstance] factory method to
 * create an instance of this fragment.
 */
class FindItsName : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var numOfquestions = 0
    private var correctans = 0
    private var listener: OnFragmentInteractionListener? = null
    private var data = DataGenerator.getData()
    private lateinit var randomWeapon:NameSouns
    private var choice:Int=0
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
        val view = inflater.inflate(R.layout.fragment_find_its_name, container, false)
        chooseRandomWeapon(view)
        view.send.setOnClickListener {
            if (view.answer.text.toString().equals(randomWeapon.name, ignoreCase = true))
            {
                correctans++
                view.correct.visibility = View.VISIBLE
                view.weapon.visibility = View.INVISIBLE
                view.question.visibility = View.INVISIBLE
                Handler().postDelayed(
                    {
                        view.correct.visibility = View.INVISIBLE
                        view.weapon.visibility = View.VISIBLE
                        view.question.visibility = View.VISIBLE
                    }, 1000
                )
            }else{
                view.wrong.visibility = View.VISIBLE
                view.weapon.visibility = View.INVISIBLE
                view.question.visibility = View.INVISIBLE
                Handler().postDelayed(
                    {
                        view.wrong.visibility = View.INVISIBLE
                        view.weapon.visibility = View.VISIBLE
                        view.question.visibility = View.VISIBLE
                    }, 1000
                )
            }

            chooseRandomWeapon(view)
            view.answer.setText("")
        }
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun chooseRandomWeapon(view:View){
        if(numOfquestions==10){
            val bundle = bundleOf("score" to correctans)
            findNavController().navigate(R.id.action_findItsName_to_score2, bundle)
            numOfquestions=0
            correctans=0
        }else {
            numOfquestions++
            choice = Random.nextInt(0, data.size - 1)
            randomWeapon = data[choice]
            view.weapon.setImageResource(randomWeapon.image)
        }
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FindItsName().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
