package me.fabiooliveira.androidjetpacksample.feature.person.ui.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import me.fabiooliveira.androidjetpacksample.BR
import me.fabiooliveira.androidjetpacksample.R
import me.fabiooliveira.androidjetpacksample.entity.Person
import android.databinding.BindingAdapter



class PersonBinderAdapter: RecyclerView.Adapter<PersonBinderAdapter.PersonBinderHolder>() {

    var personList = listOf<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonBinderHolder {
        val viewDataBinding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.person_list_item, parent, false)
        return PersonBinderHolder(viewDataBinding)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: PersonBinderHolder, position: Int) {
        val person = personList[position]
        holder.bind(person)
    }

    object BindingAdapters {
        @BindingAdapter("avatar")
        @JvmStatic
        fun ImageView.loadImage(avatar: Int) {
            Picasso.get()
                    .load(avatar)
                    .placeholder(R.color.colorPrimaryDark)
                    .error(R.drawable.img_error_avatar)
                    .into(this)
        }
    }

    inner class PersonBinderHolder constructor(private val viewDataBinding: ViewDataBinding): RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(person:Person) {
            viewDataBinding.setVariable(BR.person, person)
        }
    }

}