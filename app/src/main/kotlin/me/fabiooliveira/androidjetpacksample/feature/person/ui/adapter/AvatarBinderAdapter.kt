package me.fabiooliveira.androidjetpacksample.feature.person.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import me.fabiooliveira.androidjetpacksample.R
import android.support.v7.widget.LinearLayoutCompat
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import kotlinx.android.synthetic.main.avatar_list_item.view.*

class AvatarBinderAdapter(private val avatarList: MutableList<Int>): RecyclerView.Adapter<AvatarBinderAdapter.AvatarBinderHolder>() {

    private var onAvatarClickListener: AdapterView.OnItemClickListener? = null

    private var imageViewSelected: ImageView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarBinderHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.avatar_list_item, parent, false)
        val layoutParams = LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        itemView.layoutParams = layoutParams
        return AvatarBinderHolder(itemView)
    }

    override fun getItemCount(): Int {
        return avatarList.size
    }

    override fun onBindViewHolder(holder: AvatarBinderHolder, position: Int) {
        val avatar = avatarList[position]
        holder.bind(avatar, this)
    }

    private fun onAvatarHolderClick(itemHolder: AvatarBinderHolder) {
        onAvatarClickListener?.let {
            it.onItemClick(null, itemHolder.itemView, itemHolder.adapterPosition, itemHolder.itemId)
        }
    }

    fun setOnAvatarClickListener(onAvatarClickListener: AdapterView.OnItemClickListener) {
        this.onAvatarClickListener = onAvatarClickListener
    }

    inner class AvatarBinderHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(avatar: Int, adapter: AvatarBinderAdapter) = with(itemView) {
            Picasso.get()
                    .load(avatar)
                    .placeholder(R.color.colorPrimaryDark)
                    .error(R.drawable.img_error_avatar)
                    .into(itemView.iv_avatar)
            itemView.iv_avatar.setOnClickListener {
                adapter.onAvatarHolderClick(this@AvatarBinderHolder)
                imageViewSelected?.visibility = View.GONE
                itemView.iv_avatarSelected.visibility = View.VISIBLE
                imageViewSelected = itemView.iv_avatarSelected
            }
        }
    }

}