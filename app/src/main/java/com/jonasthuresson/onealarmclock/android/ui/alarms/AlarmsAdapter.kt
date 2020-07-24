package com.jonasthuresson.onealarmclock.android.ui.alarms

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonasthuresson.onealarmclock.model.Alarm


class AlarmsAdapter(private val onItemClickAction: (Alarm) -> Unit) :
    RecyclerView.Adapter<AlarmsAdapter.MyViewHolder>() {
    private var _myDataset: List<Alarm> = listOf()

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val textView: AlarmListItemView) : RecyclerView.ViewHolder(textView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlarmsAdapter.MyViewHolder {
        val itemView = AlarmListItemView(parent.context)
        // manually set the CustomView's size
        // manually set the CustomView's size
        itemView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        itemView.setOnClickListener {
            onItemClickAction((it as AlarmListItemView).alarm)
        }
        return MyViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.alarm = _myDataset[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = _myDataset.size

    fun setData(data: List<Alarm>) {
        _myDataset = data
        notifyDataSetChanged()
    }

}