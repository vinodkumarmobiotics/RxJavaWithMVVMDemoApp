package com.example.demoappwithrxtech.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoappwithrxtech.R
import com.example.demoappwithrxtech.databinding.ActivityMainBinding
import com.example.demoappwithrxtech.model.PostModel
import com.example.demoappwithrxtech.model.PostModelItem
import com.example.demoappwithrxtech.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var viewModel: PostViewModel? = null
    private var postList = ArrayList<PostModelItem>()
    private var postAdapter: PostAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        binding!!.lifecycleOwner = this
        setAdapter()
        viewModel!!.getPost()
        loadApiData()
    }

    private fun setAdapter() {
        var layoutManager = LinearLayoutManager(this)
        binding!!.recyclerView.layoutManager = layoutManager
        binding!!.recyclerView.setHasFixedSize(true)
        postAdapter = PostAdapter(this, postList)
        binding!!.recyclerView.adapter = postAdapter

    }

    fun loadApiData() {
        viewModel!!.getPostListObserver().observe(this, Observer<PostModel> {
            if (it != null) {
                postList.addAll(it)
                postAdapter!!.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
            }
        })
    }


}