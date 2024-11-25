package com.example.ppapb_p13_kpu.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ppapb_p13_kpu.R
import com.example.ppapb_p13_kpu.database.Voter
import com.example.ppapb_p13_kpu.database.VoterRoomDatabase
import com.example.ppapb_p13_kpu.databinding.ActivityEditVoterBinding
import kotlinx.coroutines.launch

class EditVoterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditVoterBinding
    private lateinit var database: VoterRoomDatabase
    private var voterId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditVoterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = VoterRoomDatabase.getDatabase(this)
        voterId = intent.getIntExtra("VOTER_ID", -1)

        if (voterId != -1) {
            lifecycleScope.launch {
                val voter = database.voterDao().getVoterById(voterId)
                voter?.let {
                    with(binding) {
                        nameInput.setText(it.name)
                        nikInput.setText(it.nik)
                        addressInput.setText(it.address)
                        if (it.gender == "Laki-Laki") {
                            maleRadio.isChecked = true
                        } else {
                            femaleRadio.isChecked = true
                        }
                    }
                }
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.saveButton.setOnClickListener {
            with(binding) {
                val name = nameInput.text.toString()
                val nik = nikInput.text.toString()
                val gender = if (radioGroup.checkedRadioButtonId == R.id.maleRadio) "Laki-Laki" else "Perempuan"
                val address = addressInput.text.toString()

                if (voterId != -1) {
                    lifecycleScope.launch {
                        database.voterDao().update(
                            Voter(
                                id = voterId,
                                name = name,
                                nik = nik,
                                gender = gender,
                                address = address
                            )
                        )
                        finish()
                    }
                }
            }
        }
    }
}
