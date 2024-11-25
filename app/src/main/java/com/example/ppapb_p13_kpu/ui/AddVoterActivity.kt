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
import com.example.ppapb_p13_kpu.databinding.ActivityAddVoterBinding
import kotlinx.coroutines.launch

class AddVoterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddVoterBinding
    private lateinit var voterViewModel: VoterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddVoterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        voterViewModel = ViewModelProvider(this)[VoterViewModel::class.java]

        binding.saveButton.setOnClickListener {
            val name = binding.nameInput.text.toString()
            val nik = binding.nikInput.text.toString()
            val gender = if (binding.maleRadio.isChecked) "Laki Laki" else "Perempuan"
            val address = binding.addressInput.text.toString()

            if (name.isNotEmpty() && nik.isNotEmpty() && address.isNotEmpty()) {
                lifecycleScope.launch {
                    voterViewModel.insert(Voter(
                        name = name,
                        nik = nik,
                        gender = gender,
                        address = address
                    ))
                    finish()
                }
            }
        }
    }
}