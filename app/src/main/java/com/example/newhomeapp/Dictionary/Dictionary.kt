package com.example.newhomeapp.Dictionary

import android.app.ProgressDialog
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newhomeapp.Dictionary.Adapters.MeaningAdapter
import com.example.newhomeapp.Dictionary.Adapters.PhoneticAdapter
import com.example.newhomeapp.Dictionary.Models.APIResponse
import com.example.newhomeapp.R

class Dictionary : Fragment() {
    private lateinit var searchView: SearchView
    private lateinit var textViewWord: TextView
    private lateinit var textviewm: TextView
    private lateinit var recyclerPhonetics: RecyclerView
    private lateinit var recyclerMeanings: RecyclerView
    private lateinit var dialog: ProgressDialog
    private lateinit var phoneticAdapter: PhoneticAdapter
    private lateinit var meaningAdapter: MeaningAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the dictionary fragment layout
        val view = inflater.inflate(R.layout.activity_dictionary, container, false)

        searchView = view.findViewById(R.id.search_view)
        textViewWord = view.findViewById(R.id.textView_word)
        textviewm = view.findViewById(R.id.textviewm)
        recyclerPhonetics = view.findViewById(R.id.recycler_phonetics)
        recyclerMeanings = view.findViewById(R.id.recycler_meanings)
        dialog = ProgressDialog(requireContext())

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val manager = RequestManager(requireContext())
                manager.getWordMeaning(listener, query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return view
    }

    private val listener = object : OnFetchDataListener {
        override fun onFetchData(apiResponse: APIResponse?, message: String) {
            dialog.dismiss()
            if (apiResponse == null) {
                Toast.makeText(requireContext(), "No Data Found!", Toast.LENGTH_SHORT).show()
                return
            }
            showResult(apiResponse)
        }

        override fun onError(message: String) {
            dialog.dismiss()
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showResult(response: APIResponse) {
        textViewWord.text = "Word: ${response.word}"
        textViewWord.visibility = View.VISIBLE
        textviewm.visibility = View.VISIBLE

        recyclerMeanings.setHasFixedSize(true)
        recyclerMeanings.layoutManager = GridLayoutManager(requireContext(), 1)
        meaningAdapter = response.meanings?.let { MeaningAdapter(requireContext(), it) }!!
        recyclerMeanings.adapter = meaningAdapter
    }
}
