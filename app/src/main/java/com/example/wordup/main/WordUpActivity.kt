package com.example.wordup.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.wordup.Application
import com.example.wordup.databinding.ActivityMainBinding
import javax.inject.Inject

class WordUpActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.dependencies?.inject(this)

        val layoutInflater = LayoutInflater.from(this)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.vm = viewModel

        setContentView(binding.root)
    }
}
