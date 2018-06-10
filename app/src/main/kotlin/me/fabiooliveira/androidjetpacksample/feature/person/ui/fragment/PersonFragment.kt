package me.fabiooliveira.androidjetpacksample.feature.person.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.person_fragment.*
import me.fabiooliveira.androidjetpacksample.MainApplication
import me.fabiooliveira.androidjetpacksample.R
import me.fabiooliveira.androidjetpacksample.feature.person.ui.adapter.PersonBinderAdapter
import me.fabiooliveira.androidjetpacksample.feature.person.viewModel.PersonViewModel
import javax.inject.Inject

class PersonFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var personBinderAdapter: PersonBinderAdapter

    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(PersonViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        viewModel.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.person_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        initAdapter()
        initRecyclerView()
        initListeners()
        getPersons()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    private fun initDagger(){
        (activity?.application as MainApplication)
                .getMainApplication()
                .inject(this)
    }

    private fun initAdapter(){
        personBinderAdapter = PersonBinderAdapter()
    }

    private fun initRecyclerView(){
        rv_person.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv_person.adapter = personBinderAdapter
    }

    private fun initListeners(){
        bv_savePerson.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_personFragment_to_personAddFragment)
        }
    }

    private fun getPersons() {
        viewModel.personListMutableLive.observe(this, Observer {
            it?.run {
                personBinderAdapter.personList = it
                personBinderAdapter.notifyDataSetChanged()
                hideLoading()
            }
        })
    }

    private fun showLoading(){
        ns_scrollView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading(){
        ns_scrollView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

}
