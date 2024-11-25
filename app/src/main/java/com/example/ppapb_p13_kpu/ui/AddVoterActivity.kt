package com.example.ppapb_p13_kpu.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ppapb_p13_kpu.R
import com.example.ppapb_p13_kpu.database.Voter
import com.example.ppapb_p13_kpu.database.VoterRoomDatabase
import com.example.ppapb_p13_kpu.databinding.ActivityAddVoterBinding
import kotlinx.coroutines.launch

class AddVoterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddVoterBinding
    private lateinit var database: VoterRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddVoterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = VoterRoomDatabase.getDatabase(this)

        with(binding) {
            saveButton.setOnClickListener {
                val name = nameInput.text.toString()
                val nik = nikInput.text.toString()
                val gender =
                    if (radioGroup.checkedRadioButtonId == R.id.maleRadio) "Laki-Laki" else "Perempuan"
                val address = addressInput.text.toString()

                if (name.isNotBlank() && nik.isNotBlank() && address.isNotBlank()) {
                    val voter = Voter(
                        name = name,
                        nik = nik,
                        gender = gender,
                        address = address
                    )

                    lifecycleScope.launch {
                        database.voterDao().insert(voter)
                        Toast.makeText(
                            this@AddVoterActivity,
                            "Data berhasil ditambahkan!",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                } else {
                    Toast.makeText(this@AddVoterActivity, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
                }
            }

            backButton.setOnClickListener {
                finish()
            }
        }
    }
}
