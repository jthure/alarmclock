package com.jonasthuresson.onealarmclock.android.ui.addalarm.soundsources

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class SpotifySoundSourceSearchResultListAdapter (private val onItemClickAction: ()->Unit) :
    RecyclerView.Adapter<SpotifySoundSourceSearchResultListAdapter.MyViewHolder>() {
    private var _myDataset: List<String> = listOf()

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val textView: SpotifySoundSourceSearchResultListItemView) : RecyclerView.ViewHolder(textView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SpotifySoundSourceSearchResultListAdapter.MyViewHolder {
        val itemView = SpotifySoundSourceSearchResultListItemView(parent.context)
        // manually set the CustomView's size
        // manually set the CustomView's size
        itemView.setLayoutParams(
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        itemView.setOnClickListener(){
            onItemClickAction()
        }
        return MyViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.text = _myDataset[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = _myDataset.size

    fun setData(data: List<String>) {
        _myDataset = data
        notifyDataSetChanged()
    }

}