package me.fabiooliveira.androidjetpacksample.feature.person.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.fabiooliveira.androidjetpacksample.R
import me.fabiooliveira.androidjetpacksample.feature.person.viewModel.PersonViewModel

class PersonFragment : Fragment() {

    companion object {
        fun newInstance() = PersonFragment()
    }

    private val viewModel by lazy { ViewModelProviders.of(this).get(PersonViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.person_fragment, container, false)
    }

}
