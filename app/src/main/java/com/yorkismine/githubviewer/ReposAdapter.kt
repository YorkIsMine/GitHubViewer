package com.yorkismine.githubviewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yorkismine.githubviewer.entity.Repo
import com.yorkismine.githubviewer.utils.OnClickListener
import kotlinx.android.synthetic.main.item_repo.view.*
import java.util.ArrayList

class ReposAdapter(private val callback: OnClickListener) : ListAdapter<Repo, ReposAdapter.ReposHolder>(REPO_COMPARATOR) {
    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem == newItem
        }
    }


    var repos: ArrayList<Repo> = arrayListOf()
    fun setData(data: ArrayList<Repo>) {
        repos.addAll(data)
        notifyDataSetChanged()
    }

    fun getTheLastRepo(): Repo {
        return repos[itemCount - 1]
    }


    class ReposHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(repo: Repo, callback: OnClickListener) {
            view.owner_tv.text = repo.owner?.login
            view.repo_name_tv.text = repo.name
            view.repo_desc_tv.text = if (repo.description.isNullOrEmpty()) "No Description" else repo.description
            view.repo_id_tv.text = repo.id.toString()
            view.setOnClickListener {
                callback.onClick(repo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repo, parent, false)
        return ReposHolder(view)
    }

    override fun getItemCount(): Int = repos.size;

    override fun onBindViewHolder(holder: ReposHolder, position: Int) {
        holder.bind(repos[position], callback)
    }
}