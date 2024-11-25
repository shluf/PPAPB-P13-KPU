package com.example.ppapb_p13_kpu.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ppapb_p13_kpu.R
import com.example.ppapb_p13_kpu.database.Voter
import com.example.ppapb_p13_kpu.databinding.ActivityDetailVoterBinding

class DetailVoterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailVoterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailVoterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val voter = intent.getParcelableExtra<Voter>("voter")

        voter?.let {
            binding.nameText.text = it.name
            binding.nikText.text = it.nik
            binding.genderText.text = it.gender
            binding.addressText.text = it.address
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}