package com.example.guesstheweapon.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guesstheweapon.Adapter.WeaponsAdapter
import com.example.guesstheweapon.R
import kotlinx.android.synthetic.main.fragment_learn_weapons.view.*

/**
 * A simple [Fragment] subclass.
 */
class LearnWeapons : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView = inflater.inflate(R.layout.fragment_learn_weapons, container, false)
        viewManager = LinearLayoutManager(context)
        viewAdapter = WeaponsAdapter()

        val recyclerView = myView.list_of_weapons
        recyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
        return myView
    }


}
