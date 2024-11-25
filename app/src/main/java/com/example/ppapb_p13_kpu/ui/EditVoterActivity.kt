package com.example.ppapb_p13_kpu.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.ppapb_p13_kpu.R
import com.example.ppapb_p13_kpu.database.Voter
import com.example.ppapb_p13_kpu.database.VoterViewModel
import com.example.ppapb_p13_kpu.databinding.ActivityEditVoterBinding
import kotlinx.coroutines.launch

class EditVoterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditVoterBinding
    private lateinit var voterViewModel: VoterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditVoterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        voterViewModel = ViewModelProvider(this)[VoterViewModel::class.java]

        // Load existing data
        val voter = intent.getParcelableExtra<Voter>("voter")
        if (voter != null) {
            binding.nameInput.setText(voter.name)
            binding.nikInput.setText(voter.nik)
            if (voter.gender == "Laki Laki") {
                binding.maleRadio.isChecked = true
            } else {
                binding.femaleRadio.isChecked = true
            }
            binding.addressInput.setText(voter.address)
        }

        binding.saveButton.setOnClickListener {
            voter?.let {
                val updatedVoter = it.copy(
                    name = binding.nameInput.text.toString(),
                    nik = binding.nikInput.text.toString(),
                    gender = if (binding.maleRadio.isChecked) "Laki Laki" else "Perempuan",
                    address = binding.addressInput.text.toString()
                )
                lifecycleScope.launch {
                    voterViewModel.update(updatedVoter)
                    finish()
                }
            }
        }
    }
}