package com.example.ppapb_p13_kpu.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ppapb_p13_kpu.database.PrefManager
import com.example.ppapb_p13_kpu.database.VoterRoomDatabase
import com.example.ppapb_p13_kpu.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefManager: PrefManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var database: VoterRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.voterRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        database = VoterRoomDatabase.getDatabase(this)

        prefManager = PrefManager.getInstance(this)
        checkLoginStatus()

        loadDataVoters()
        with(binding) {
            addButton.setOnClickListener {
                startActivity(Intent(this@MainActivity, AddVoterActivity::class.java))
            }

            btnLogout.setOnClickListener {
                prefManager.setLoggedIn(false)
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    fun checkLoginStatus() {
        val isLoggedIn = prefManager.isLoggedIn()
        if (!isLoggedIn) {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun loadDataVoters() {
        lifecycleScope.launch {
            val voters = withContext(Dispatchers.IO) {
                database.voterDao().getAllVoters()
            }

            recyclerView.adapter = VoterDataAdapter(
                voters,
                onEditClick = { voter ->
                    val intent = Intent(this@MainActivity, EditVoterActivity::class.java)
                    intent.putExtra("VOTER_ID", voter.id)
                    startActivity(intent)
                },
                onDeleteClick = { voter ->
                    lifecycleScope.launch {
                        database.voterDao().delete(voter)
                        loadDataVoters()
                    }
                },
                onDetailClick = { voter ->
                    val intent = Intent(this@MainActivity, DetailVoterActivity::class.java)
                    intent.putExtra("VOTER_ID", voter.id)
                    startActivity(intent)
                }
            )
        }
    }
}