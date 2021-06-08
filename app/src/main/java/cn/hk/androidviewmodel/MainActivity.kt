package cn.hk.androidviewmodel

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import cn.hk.androidviewmodel.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val KEY = "KEY"
    }
    lateinit var myViewModel:MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        myViewModel = ViewModelProviders.of(
            this,
            SavedStateViewModelFactory(application,this),
        )[MyViewModel::class.java]
        binding.also {
            it.data = myViewModel
            it.lifecycleOwner  = this@MainActivity
        }

        reset.setOnClickListener {
            Log.e("tttt" , "可是当时看见的 ")
                myView3.reSet()
        }
    }

    override fun onPause() {
        super.onPause()
        myViewModel.save()
    }
}