package com.sourabhverma.cultino.login

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sourabhverma.cultino.R
import com.sourabhverma.cultino.databinding.ActivityLoginBinding
import com.sourabhverma.cultino.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginVM: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = loginVM

        binding.ProfileCard.setOnClickListener{
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            startForResult.launch(intent)
        }

        binding.goButton.setOnClickListener{
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startForResult.launch(intent)
        }

    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as Bitmap
            loginVM.setImageBitmap(imageBitmap)
            binding.ProfileImage.setImageBitmap(imageBitmap)
        }
    }
}