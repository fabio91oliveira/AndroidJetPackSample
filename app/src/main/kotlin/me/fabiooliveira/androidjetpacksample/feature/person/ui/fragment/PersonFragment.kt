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
import android.widget.Toast
import kotlinx.android.synthetic.main.person_fragment.*
import me.fabiooliveira.androidjetpacksample.MainApplication
import me.fabiooliveira.androidjetpacksample.R
import me.fabiooliveira.androidjetpacksample.entity.Person
import me.fabiooliveira.androidjetpacksample.feature.person.ui.adapter.PersonBinderAdapter
import me.fabiooliveira.androidjetpacksample.feature.person.viewModel.PersonViewModel
import javax.inject.Inject

class PersonFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var personBinderAdapter: PersonBinderAdapter

    companion object {
        fun newInstance() = PersonFragment()
    }

    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(PersonViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.person_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initRecyclerView()
        savePerson("1", "Fabio Oliveira")
        savePerson("2", "Fabio Oliveira")
        savePerson("3", "Fabio Oliveira")
        savePerson("4", "Fabio Oliveira")
        savePerson("5", "Fabio Oliveira")
        savePerson("6", "Fabio Oliveira")
        savePerson("7", "Fabio Oliveira")
        savePerson("8", "Fabio Oliveira")
        savePerson("9", "Fabio Oliveira")
        savePerson("10", "Fabio Oliveira")
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

    private fun savePerson(id: String, name: String){
        val person = Person(id,name)
        viewModel.savePerson(person)
        viewModel.longMutableLive.observe(this, Observer {
            Toast.makeText(activity, Constants.OK_MESSAGE + id, Toast.LENGTH_SHORT).show()
        })
    }

    private fun getPersons() {
        viewModel.personListMutableLive.observe(this, Observer {
            it?.run {
                personBinderAdapter.personList = it
                personBinderAdapter.notifyDataSetChanged()
            }
        })
    }

    object Constants {
        const val OK_MESSAGE = "Salvou pessoa número "
    }

}
