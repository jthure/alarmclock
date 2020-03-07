package com.jonasthuresson.onealarmclock.ui.alarms

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jonasthuresson.onealarmclock.R
import com.jonasthuresson.onealarmclock.model.alarms.Alarm

class AlarmsAdapter () :
    RecyclerView.Adapter<AlarmsAdapter.MyViewHolder>() {
    private var _myDataset: Array<Alarm> = arrayOf()

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AlarmsAdapter.MyViewHolder {
        // create a new view
        val textView = TextView(parent.context)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.text = _myDataset[position].time.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = _myDataset.size

    fun setData(data: Array<Alarm>) {
        _myDataset = data
        notifyDataSetChanged()
    }

}