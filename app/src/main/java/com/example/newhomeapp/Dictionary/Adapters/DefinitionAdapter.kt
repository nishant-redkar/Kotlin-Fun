package com.example.newhomeapp.Dictionary.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newhomeapp.Dictionary.Models.Definitions
import com.example.newhomeapp.Dictionary.ViewHolders.DefinitionViewHolder
import com.example.newhomeapp.R

class DefinitionAdapter(private val context: Context, private val definitionsList: List<Definitions>) :
    RecyclerView.Adapter<DefinitionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.definitions_list_items, parent, false)
        return DefinitionViewHolder(view)
    }

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        val definition = definitionsList[position]
        holder.textView_definitions.text = "Definition: ${definition.definition}"

        if (definition.example.isNotEmpty()) {
            holder.textView_example.text = "Example: ${definition.example}"
        } else {
            holder.textView_example.text = "Example: N/A"
        }
    }


    override fun getItemCount(): Int {
        return definitionsList.size
    }
}