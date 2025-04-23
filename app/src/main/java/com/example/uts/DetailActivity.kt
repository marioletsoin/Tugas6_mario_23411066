package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uts.databinding.ActivityDetailBinding
import com.example.uts.databinding.ActivityMainBinding
import com.example.uts.databinding.FragmentRcviewBinding
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari intent
        val itemData = intent.getParcelableExtra<ItemDataActivity>("itemDetail")
        if (itemData != null) {
            binding.detailImage.setImageResource(itemData.gambar)
            binding.detailTitle.text = itemData.nama
            binding.detailDesc.text = itemData.asal
            binding.detailDesc2.text = itemData.deskripsi
        }


    }
}

