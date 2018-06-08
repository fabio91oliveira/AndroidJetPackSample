package me.fabiooliveira.androidjetpacksample.feature.person.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.person_add_fragment.*
import me.fabiooliveira.androidjetpacksample.MainApplication
import me.fabiooliveira.androidjetpacksample.R
import me.fabiooliveira.androidjetpacksample.entity.Person
import me.fabiooliveira.androidjetpacksample.feature.person.viewModel.PersonAddViewModel
import javax.inject.Inject
import android.support.v7.app.AppCompatActivity



class PersonAddFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(PersonAddViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.person_add_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        initListeners()
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

    private fun initListeners(){
        toolbar.setNavigationOnClickListener {
            goBack()
        }
        bv_addPerson.setOnClickListener {
            savePerson()
        }
    }

    private fun savePerson(){
        val idText = ti_personId.text.toString()
        val nameText = ti_personName.text.toString()

        if(!viewModel.isFieldEmpty(idText) && !viewModel.isFieldEmpty(nameText)) {
            val person = Person(idText, nameText)
            viewModel.savePerson(person)
            viewModel.longMutableLive.observe(this, Observer {
                Toast.makeText(activity, id, Toast.LENGTH_SHORT).show()
                goBack()
            })
        } else {
            Toast.makeText(activity, activity?.resources?.getString(R.string.person_add_toast_error_required), Toast.LENGTH_SHORT).show()
        }
    }

    private fun goBack(){
        Navigation.findNavController(view!!).navigate(R.id.action_personAddFragment_to_personFragment)
    }

}
