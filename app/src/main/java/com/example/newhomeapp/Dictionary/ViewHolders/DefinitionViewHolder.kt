package com.example.newhomeapp.Dictionary.ViewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newhomeapp.R

class DefinitionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView_definitions: TextView = itemView.findViewById(R.id.textView_definitions)
    val textView_example: TextView = itemView.findViewById(R.id.textView_example)
}