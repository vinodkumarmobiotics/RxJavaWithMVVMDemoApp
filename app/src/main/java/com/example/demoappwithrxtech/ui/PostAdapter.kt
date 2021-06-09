package com.example.demoappwithrxtech.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.demoappwithrxtech.R
import com.example.demoappwithrxtech.model.PostModelItem

class PostAdapter(private var context: Context, private var postList: ArrayList<PostModelItem>) :
    RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        var textViewDescription: TextView = view.findViewById(R.id.textViewDescription)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val postModel = postList[position]
        holder.textViewTitle.text = postModel.title
        holder.textViewDescription.text = postModel.body
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}