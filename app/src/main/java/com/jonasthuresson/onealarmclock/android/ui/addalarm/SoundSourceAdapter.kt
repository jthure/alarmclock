package com.jonasthuresson.onealarmclock.android.ui.addalarm

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections

class SoundSourceAdapter(context: Context, private val items: List<SoundSource>, resourceId: Int, private val navController: NavController): ArrayAdapter<SoundSource>(context, resourceId, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v = super.getView(position, convertView, parent)
        v.setOnClickListener {
            navController.navigate(items[position].direction)
        }
        return v
    }

}

class SoundSource(private val text: String, val direction: NavDirections){
    override fun toString(): String = text
}