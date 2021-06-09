package com.example.demoappwithrxtech.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoappwithrxtech.model.PostModel
import com.example.demoappwithrxtech.retrofit.RestApi
import com.example.demoappwithrxtech.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class PostViewModel : ViewModel() {

    var postList: MutableLiveData<PostModel> = MutableLiveData()
    var restApi: RestApi? = null
    var retrofit: Retrofit? = null
    var subscription: Disposable? = null

    init {
        retrofit = RetrofitClient.getClient()
        restApi = retrofit!!.create(RestApi::class.java)
    }

    fun getPostListObserver(): MutableLiveData<PostModel>{
        return postList
    }

    fun getPost() {
        subscription = restApi!!.getPost()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {

            }
            .subscribe(
                { result ->
                    postList.postValue(result)
                },
                {
                    postList.postValue(null)
                }
            )
    }


    fun disposeSubscriber() {
        if (subscription != null)
            subscription!!.dispose()
    }

}