package com.example.newhomeapp.Dictionary.Adapters

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.newhomeapp.Dictionary.Models.Phonetics
import com.example.newhomeapp.Dictionary.ViewHolders.PhoneticViewHolder
import com.example.newhomeapp.R
import java.io.IOException

class PhoneticAdapter(private val context: Context, private val phoneticsList: List<Phonetics>) :
    RecyclerView.Adapter<PhoneticViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneticViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.phonetic_list_items, parent, false)
        return PhoneticViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhoneticViewHolder, position: Int) {
        val phonetic = phoneticsList[position]
        holder.textView_phonetic.text = phonetic.text
        holder.imageButton_audio.setOnClickListener {
            val player = MediaPlayer()
            try {
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
                player.setAudioAttributes(audioAttributes)
                player.setDataSource("https:${phonetic.audio}")
                player.prepare()
                player.start()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(context, "Couldn't play audio!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return phoneticsList.size
    }
}