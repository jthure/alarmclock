package com.jonasthuresson.onealarmclock.android.ui.alarms

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.jonasthuresson.onealarmclock.R
import com.jonasthuresson.onealarmclock.model.Alarm
import kotlinx.android.synthetic.main.alarm_list_item_view.view.*
import java.time.LocalTime

class AlarmListItemView(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    var alarm: Alarm = Alarm(time = LocalTime.now())
        set(_alarm) {
            field = _alarm
            time_text_view.text = _alarm.time.toString()
        }

    // override all constructors to ensure custom logic runs in all cases
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    init {

        // put all custom logic in this constructor, which always runs
        View.inflate(getContext(), R.layout.alarm_list_item_view, this)
    }
}