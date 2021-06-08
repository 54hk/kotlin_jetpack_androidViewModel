package cn.hk.androidviewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import cn.hk.androidviewmodel.MainActivity.Companion.KEY

class MyViewModel(application: Application, savedStateHandle: SavedStateHandle) :
    AndroidViewModel(application) {

    private var mSavedStateHandle: SavedStateHandle? = savedStateHandle
    private val shpName = "shp_name"

    init {
        if (!mSavedStateHandle!!.contains(KEY)) {
            load()
        }
    }

    fun getNumber(): LiveData<Int> {
        return mSavedStateHandle!!.getLiveData(KEY)
    }

    private fun load() {
        val sharedPreferences =
            getApplication<Application>().getSharedPreferences(shpName, Context.MODE_PRIVATE)
        mSavedStateHandle!!.set(KEY, sharedPreferences.getInt(KEY, 0))
    }

    fun save() {
        val sharedPreferences =
            getApplication<Application>().getSharedPreferences(shpName, Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putInt(KEY, getNumber().value!!)
        edit.apply()
    }

    fun add(x: Int) {
        mSavedStateHandle!!.set(KEY, getNumber().value!! + x)
    }
}