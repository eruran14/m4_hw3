package com.eru.les3_m4.ui

import android.content.Context
import android.net.Uri


class Prefs(context: Context) {

    private var preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun saveState(){
        preferences.edit().putBoolean("isShown", true).apply()
    }

    fun isShown():Boolean = preferences.getBoolean("isShown", false)

    fun saveImage(key:String, uri:Uri?){
        uri?.let{
        preferences.edit().putString("image", uri.toString()).apply()}
    }

//    fun isChosen(key: String) = preferences.getString(key, "")

    fun saveText(enteredText:String){
        preferences.edit().putString("edited", enteredText)
    }

    fun getImage(): Uri? {
        val s = preferences.getString("image", "")
        return Uri.parse(s)
    }

}
