package com.example.ppapb_p13_kpu.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ppapb_p13_kpu.database.Voter
import com.example.ppapb_p13_kpu.databinding.ItemVoterDataBinding

class VoterDataAdapter(
    private val data: List<Voter>,
    private val onEditClick: (Voter) -> Unit,
    private val onDeleteClick: (Voter) -> Unit,
    private val onDetailClick: (Voter) -> Unit
) : RecyclerView.Adapter<VoterDataAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemVoterDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(voter: Voter) {
            binding.voterData = voter

            binding.ivDelete.setOnClickListener { onDeleteClick(voter) }
            binding.ivEdit.setOnClickListener { onEditClick(voter) }
            binding.ivView.setOnClickListener { onDetailClick(voter) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVoterDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size
}
