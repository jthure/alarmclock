package com.jonasthuresson.onealarmclock.android.ui.addalarm.soundsources

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

import com.jonasthuresson.onealarmclock.R
import kotlinx.android.synthetic.main.alarm_list_item_view.view.*
import kotlinx.android.synthetic.main.spotify_sound_source_search_result_list_item_view.view.*

class SpotifySoundSourceSearchResultListItemView(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    var text: String = "Default"
    set(_text: String) {
        field = _text
        text_view.text = _text
    }

    // override all constructors to ensure custom logic runs in all cases
    constructor(context: Context?) : this(context, null) {}
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    ) {
    }

    init {

        // put all custom logic in this constructor, which always runs
        View.inflate(getContext(), R.layout.spotify_sound_source_search_result_list_item_view, this)
    }
}