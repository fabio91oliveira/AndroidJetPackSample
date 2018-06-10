package me.fabiooliveira.androidjetpacksample.feature.person.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
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
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import me.fabiooliveira.androidjetpacksample.feature.person.ui.adapter.AvatarBinderAdapter

class PersonAddFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(PersonAddViewModel::class.java) }

    private var selectedAvatar: Int = R.drawable.img_error_avatar

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
        initToolbar()
        initRecyclerView()
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
            hideKeyboard()
            goBack()
        }
        bv_savePerson.setOnClickListener {
            savePerson()
        }
    }

    private fun initToolbar(){
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initRecyclerView(){
        val avatarList = mutableListOf<Int>()
        avatarList.add(R.drawable.avatar_1)
        avatarList.add(R.drawable.avatar_2)
        avatarList.add(R.drawable.avatar_3)
        avatarList.add(R.drawable.avatar_4)

        val avatarBinderAdapter = AvatarBinderAdapter(avatarList)
        rv_avatar.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_avatar.adapter = avatarBinderAdapter

        avatarBinderAdapter.setOnAvatarClickListener( AdapterView.OnItemClickListener { _, _ , position, _ ->
            selectedAvatar = avatarList[position]
        })
    }

    private fun savePerson(){
        val personNameText = ti_personName.text.toString()
        val personDescriptionText = ti_personDescription.text.toString()

        if(!viewModel.isFieldEmpty(personNameText) && !viewModel.isFieldEmpty(personDescriptionText)) {
            val person = Person(personNameText, personDescriptionText, selectedAvatar)
            viewModel.savePerson(person)
            viewModel.longMutableLive.observe(this, Observer {
                Toast.makeText(activity, id, Toast.LENGTH_SHORT).show()
                hideKeyboard()
                goBack()
            })
        } else { Toast.makeText(activity, activity?.resources?.getString(R.string.person_add_toast_error_required), Toast.LENGTH_SHORT).show() }
    }

    private fun goBack(){
        Navigation.findNavController(view!!).navigate(R.id.action_personAddFragment_to_personFragment)
    }

    private fun hideKeyboard(){
        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view?.windowToken, 0)
    }

}
