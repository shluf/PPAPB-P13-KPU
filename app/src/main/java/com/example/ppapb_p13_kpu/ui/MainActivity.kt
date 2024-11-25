package com.example.ppapb_p13_kpu.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.ppapb_p13_kpu.R
import com.example.ppapb_p13_kpu.database.VoterViewModel
import com.example.ppapb_p13_kpu.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var voterViewModel: VoterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        voterViewModel = ViewModelProvider(this)[VoterViewModel::class.java]

        binding.addButton.setOnClickListener {
            startActivity(Intent(this, AddVoterActivity::class.java))
        }

        binding.logoutButton.setOnClickListener {
            getSharedPreferences("voter_prefs", Context.MODE_PRIVATE)
                .edit()
                .putBoolean("is_logged_in", false)
                .apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        lifecycleScope.launch {
            voterViewModel.allVoters.collect { voters ->
                // Update RecyclerView
                // Implementation of adapter and recycler view setup
            }
        }
    }
}