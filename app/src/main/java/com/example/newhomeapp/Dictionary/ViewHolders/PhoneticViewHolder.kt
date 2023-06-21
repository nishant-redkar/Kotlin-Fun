package com.example.newhomeapp.Dictionary.ViewHolders

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newhomeapp.R

class PhoneticViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView_phonetic: TextView = itemView.findViewById(R.id.textView_phonetic)
    val imageButton_audio: ImageButton = itemView.findViewById(R.id.imageButton_audio)
}