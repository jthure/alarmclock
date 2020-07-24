package com.jonasthuresson.onealarmclock.android.ui.addalarm.soundsources

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonasthuresson.onealarmclock.model.AlarmSound


class SpotifySoundSourceSearchResultListAdapter(private val onItemClickAction: (selectedSound: AlarmSound) -> Unit) :
    RecyclerView.Adapter<SpotifySoundSourceSearchResultListAdapter.MyViewHolder>() {
    private var _myDataset: List<AlarmSound> = listOf()

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val textView: SpotifySoundSourceSearchResultListItemView) :
        RecyclerView.ViewHolder(textView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpotifySoundSourceSearchResultListAdapter.MyViewHolder {
        val itemView = SpotifySoundSourceSearchResultListItemView(parent.context)
        // manually set the CustomView's size
        // manually set the CustomView's size
        itemView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return MyViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.text = _myDataset[position].getTitle()
        holder.textView.setOnClickListener { onItemClickAction(_myDataset[position]) }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = _myDataset.size

    fun setData(data: List<AlarmSound>) {
        _myDataset = data
        notifyDataSetChanged()
    }

}