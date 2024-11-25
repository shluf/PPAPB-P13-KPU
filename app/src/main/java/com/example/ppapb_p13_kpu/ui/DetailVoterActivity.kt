package com.example.ppapb_p13_kpu.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ppapb_p13_kpu.database.VoterRoomDatabase
import com.example.ppapb_p13_kpu.databinding.ActivityDetailVoterBinding
import kotlinx.coroutines.launch

class DetailVoterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailVoterBinding
    private lateinit var database: VoterRoomDatabase
    private var voterId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailVoterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = VoterRoomDatabase.getDatabase(this)
        voterId = intent.getIntExtra("VOTER_ID", -1)

        if (voterId != -1) {
            lifecycleScope.launch {
                val voter = database.voterDao().getVoterById(voterId)
                voter?.let {
                    with(binding) {
                        nameText.text = it.name
                        nikText.text = it.nik
                        genderText.text = it.gender
                        addressText.text = it.address
                    }
                }
            }
        }
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}
