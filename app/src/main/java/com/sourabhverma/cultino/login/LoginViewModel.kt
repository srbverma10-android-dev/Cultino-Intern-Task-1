package com.sourabhverma.cultino.login

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Build
import android.util.Patterns
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.sourabhverma.cultino.utils.CacheHelperClass
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val cacheHelperClass: CacheHelperClass) : ViewModel() {
    private var imageBitmap : Bitmap? = null
    @RequiresApi(Build.VERSION_CODES.O)
    fun setOnSubmitButtonClick(name : String, email : String, context: Context, nameText : TextView, emailText : TextView){
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && imageBitmap != null) {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("MySharedPref", MODE_PRIVATE)
            val myEdit = sharedPreferences.edit()
            val currentDateTime = LocalDateTime.now()
            cacheHelperClass.putImage(context, currentDateTime.toString(), imageBitmap!!)
            myEdit.putString("Name", name)
            myEdit.putString("Email", email)
            myEdit.putString("ProfileImage", currentDateTime.toString())
            if(myEdit.commit()){
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                nameText.text = name
                emailText.text = email
            } else {
                Toast.makeText(context, "Not Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun setName(context: Context) : String? {
        return context.getSharedPreferences("MySharedPref", MODE_PRIVATE).getString("Name", "")
    }
    fun setEmail(context: Context) : String? {
        return context.getSharedPreferences("MySharedPref", MODE_PRIVATE).getString("Email", "")
    }
    fun setImageBitmap(bitmap: Bitmap){
            this.imageBitmap = bitmap
    }
    companion object{
        @BindingAdapter("checkAndShowError")
        @JvmStatic
        fun checkAndShowError(view : TextView, str: String){
            if(!Patterns.EMAIL_ADDRESS.matcher(str).matches() || str.isEmpty()){
                view.error = "Enter a Valid Email Address"
            } else {
                view.error = null
            }
        }
        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(view : ImageView, context: Context){
            val profileKey = context.getSharedPreferences("MySharedPref", MODE_PRIVATE).getString("ProfileImage", "")
            if (!profileKey.equals("") && !profileKey.isNullOrEmpty()){
                val bitmap = CacheHelperClass().getImage(context, profileKey)
                view.setImageBitmap(bitmap)
            }
        }
    }
}