package com.roberts.tvshows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.roberts.tvshows.databinding.TvshowsRowBinding
import com.roberts.tvshows.model.Shows

class TvShowsAdapter : ListAdapter<Shows, TvShowsAdapter.MyViewHolder>(MyDiffUtil) {
    class MyViewHolder (private val binding: TvshowsRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shows: Shows?){
            binding.tvName.text = shows?.name
            binding.tvType.text = shows?.type
            binding.tvLanguage.text = shows?.language
            binding.tvLanguage.text = shows?.premiered
            binding.tvStatus.text = shows?.status
            binding.tvSummary.text = shows?.summary

            Glide.with(binding.tvShowImage).load(shows?.image?.medium).into(binding.tvShowImage)
        }
    }

    object MyDiffUtil : DiffUtil.ItemCallback<Shows>() {
        override fun areItemsTheSame(oldItem: Shows, newItem: Shows): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Shows, newItem: Shows): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(TvshowsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val shows = getItem(position)
        holder.bind(shows)
    }
}