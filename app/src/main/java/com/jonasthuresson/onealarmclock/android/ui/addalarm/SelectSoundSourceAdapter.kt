package com.jonasthuresson.onealarmclock.android.ui.addalarm

import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.RecyclerView
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmListItemView
import com.jonasthuresson.onealarmclock.model.Alarm

class SelectSoundSourceAdapter(private val navController: NavController) :
    RecyclerView.Adapter<SelectSoundSourceAdapter.MyViewHolder>() {
    private var _myDataset: List<SoundSource> = listOf(
        SoundSource(
            "Spotify",
            SelectSoundSourceDialogFragmentDirections.actionSelectSoundSourceDialogFragmentToSpotifySoundSourceFragment()
        )
    )

    class MyViewHolder(val textView: SoundSourceListItemView) : RecyclerView.ViewHolder(textView)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView =
            SoundSourceListItemView(
                parent.context
            )
        itemView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        itemView.setOnClickListener {view ->
            (view as SoundSourceListItemView).alarm?.let {soundSource ->
                navController.navigate(soundSource.direction)
            }
        }
        return MyViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.alarm = _myDataset[position]
    }

    override fun getItemCount() = _myDataset.size

    fun setData(data: List<SoundSource>) {
        _myDataset = data
        notifyDataSetChanged()
    }

    class SoundSource(private val text: String, val direction: NavDirections) {
        override fun toString(): String = text
    }
}