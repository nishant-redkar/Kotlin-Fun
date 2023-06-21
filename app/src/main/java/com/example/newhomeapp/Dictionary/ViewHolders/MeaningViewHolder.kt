package com.example.newhomeapp.Dictionary.ViewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newhomeapp.R

class MeaningViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView_partsOfSpeech: TextView = itemView.findViewById(R.id.textView_partsOfSpeech)
    val recycler_definitions: RecyclerView = itemView.findViewById(R.id.recycler_definitions)
}