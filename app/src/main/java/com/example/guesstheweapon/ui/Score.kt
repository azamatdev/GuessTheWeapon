package com.example.guesstheweapon.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.guesstheweapon.R
import kotlinx.android.synthetic.main.fragment_score.view.*

/**
 * A simple [Fragment] subclass.
 */
class Score : Fragment() {
    private var scores = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scores = arguments?.getInt("score")!!
        val view = inflater.inflate(R.layout.fragment_score, container, false)

        view.home.setOnClickListener {
            v ->  v.findNavController().navigate(R.id.action_score2_to_home2)
        }
        view.play_again.setOnClickListener { v -> v.findNavController().navigate(R.id.action_score2_to_gameActivity) }
        view.score.text = "Score: $scores/10"
        return view
    }


}
