package com.jonasthuresson.onealarmclock.android.ui.addalarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonasthuresson.onealarmclock.R
import kotlinx.android.synthetic.main.fragment_select_sound_source_dialog.*

class SelectSoundSourceDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_sound_source_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sound_source_list.apply {
            setHasFixedSize(true)
            adapter = SelectSoundSourceAdapter(this@SelectSoundSourceDialogFragment.findNavController())
            layoutManager = LinearLayoutManager(this@SelectSoundSourceDialogFragment.context)
        }
    }

}