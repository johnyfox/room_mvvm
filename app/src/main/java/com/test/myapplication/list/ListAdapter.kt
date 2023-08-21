package com.test.myapplication.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.domain.entity.Entity
import com.test.myapplication.R
import com.test.myapplication.databinding.ListItemBinding
import com.test.myapplication.utils.Utils

class ListAdapter(private val list : MutableList<Entity> = mutableListOf()) : RecyclerView.Adapter<ListViewHolder>() {

    private var iconUrl : String ?= null

    private var itemClickListener : EntityClickListener ?= null

    fun setIconUrl(url : String) {
        if (url.startsWith("http")) {
            iconUrl = url
        }
    }

    fun setItemClickListener(listener: EntityClickListener) {
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false)
        return ListViewHolder(binding as ListItemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val context = holder.itemView.context
        iconUrl?.let {
            val iconToLoad = it + Utils.getNameOfIcon(list[position].platform)
            Glide
                .with(context)
                .load(iconToLoad)
                .centerCrop()
                .into(holder.view.icon)
        }

        holder.view.name.text = list[position].title
        holder.view.name.setOnClickListener {
            itemClickListener?.onItemClicked(list[position])
        }
        holder.view.name.setOnLongClickListener {
            itemClickListener?.onLongClick(list[position])
            true
        }
        holder.view.edit.setOnClickListener {
            itemClickListener?.onEditClicked(list[position])
        }
    }

    fun updateItems(newList: List<Entity>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size
}