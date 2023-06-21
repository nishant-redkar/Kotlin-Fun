package com.example.newhomeapp.Dictionary.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newhomeapp.Dictionary.Models.Meanings
import com.example.newhomeapp.Dictionary.ViewHolders.MeaningViewHolder
import com.example.newhomeapp.R

class MeaningAdapter(private val context: Context, private val meaningsList: List<Meanings>) :
    RecyclerView.Adapter<MeaningViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.meanings_list_items, parent, false)
        return MeaningViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        val meaning = meaningsList[position]
        holder.textView_partsOfSpeech.text = "${meaning.partOfSpeech}"

        holder.recycler_definitions.setHasFixedSize(true)
        holder.recycler_definitions.layoutManager = GridLayoutManager(context, 1)
        val definitionAdapter = DefinitionAdapter(context, meaning.definitions?: emptyList())
        holder.recycler_definitions.adapter = definitionAdapter
    }

    override fun getItemCount(): Int {
        return meaningsList.size
    }
}