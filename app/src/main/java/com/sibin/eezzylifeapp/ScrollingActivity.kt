package com.sibin.eezzylifeapp

import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.michalsvec.singlerowcalendar.calendar.CalendarChangesObserver
import com.michalsvec.singlerowcalendar.calendar.CalendarViewManager
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendarAdapter
import com.michalsvec.singlerowcalendar.selection.CalendarSelectionManager
import com.michalsvec.singlerowcalendar.utils.DateUtils
import kotlinx.android.synthetic.main.calender_item.view.*
import kotlinx.android.synthetic.main.comment.*
import kotlinx.android.synthetic.main.comment_evening.*
import kotlinx.android.synthetic.main.comment_night.*
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.event1_afternoon.*
import kotlinx.android.synthetic.main.event1_evening.*
import kotlinx.android.synthetic.main.event1_night.*
import kotlinx.android.synthetic.main.event2_afternoon.*
import kotlinx.android.synthetic.main.event2_evening.*
import kotlinx.android.synthetic.main.event2_night.*
import kotlinx.android.synthetic.main.event_details_host_me.*
import kotlinx.android.synthetic.main.event_details_host_me_evening.*
import kotlinx.android.synthetic.main.event_details_host_me_night.*
import java.util.*


class ScrollingActivity : AppCompatActivity() {

    private val calendar = Calendar.getInstance()
    private var currentMonth = 0
    val dateFormat = "EEE dd, MMM yyyy"
    private var margin: Int = 0
    private var cardElevation: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setupCalenderView()
        margin = dpToPx(10f)
        cardElevation = dpToPx(5f)

        afternoon.setOnClickListener {
            if (host_view_after_noon.visibility == View.VISIBLE) {
                host_view_after_noon.invisible()
                afternoon_text.setTextColor(getColor(android.R.color.darker_gray))
                afternoon_degree.setTextColor(getColor(android.R.color.darker_gray))
                setTextViewDrawableColor(afternoon_degree, android.R.color.darker_gray)
                plus_afternoon.visible()
                cardHidden(afternoon)
                afternoon_top_view.setBackgroundColor(getColor(R.color.white))
            } else {
                loadImages()
                afternoon_text.setTextColor(getColor(R.color.white))
                afternoon_degree.setTextColor(getColor(R.color.white))
                setTextViewDrawableColor(afternoon_degree, R.color.white)
                host_view_after_noon.visible()
                plus_afternoon.invisible()
                afternoon_top_view.setBackgroundColor(getColor(R.color.colorPrimary))
                cardExpanded(afternoon)
            }
        }
        evening.setOnClickListener {
            if (host_view_evening.visibility == View.VISIBLE) {
                host_view_evening.invisible()
                evening_text.setTextColor(getColor(android.R.color.darker_gray))
                evening_degree.setTextColor(getColor(android.R.color.darker_gray))
                setTextViewDrawableColor(evening_degree, android.R.color.darker_gray)
                plus_evening.visible()
                cardHidden(evening)
                evening_top_view.setBackgroundColor(getColor(R.color.white))
            } else {
                loadImages()
                evening_text.setTextColor(getColor(R.color.white))
                evening_degree.setTextColor(getColor(R.color.white))
                setTextViewDrawableColor(evening_degree, R.color.white)
                host_view_evening.visible()
                plus_evening.invisible()
                evening_top_view.setBackgroundColor(getColor(R.color.colorPrimary))
                cardExpanded(evening)
            }
        }
        night.setOnClickListener {
            if (host_view_night.visibility == View.VISIBLE) {
                host_view_night.invisible()
                night_text.setTextColor(getColor(android.R.color.darker_gray))
                night_degree.setTextColor(getColor(android.R.color.darker_gray))
                setTextViewDrawableColor(night_degree, android.R.color.darker_gray)
                plus_night.visible()
                cardHidden(night)
                night_top_view.setBackgroundColor(getColor(R.color.white))
            } else {
                loadImages()
                night_text.setTextColor(getColor(android.R.color.white))
                night_degree.setTextColor(getColor(android.R.color.white))
                setTextViewDrawableColor(night_degree, android.R.color.white)
                host_view_night.visible()
                plus_night.invisible()
                night_top_view.setBackgroundColor(getColor(R.color.colorPrimary))
                cardExpanded(night)
            }
        }
        accept.setOnClickListener {
            accept.invisible()
            decline.invisible()
            accepted.visible()
        }
        accept1.setOnClickListener {
            accept1.invisible()
            decline1.invisible()
            accepted1.visible()
        }
    }

    private fun setTextViewDrawableColor(textView: TextView, color: Int) {
        for (drawable in textView.compoundDrawables) {
            if (drawable != null) {
                drawable.colorFilter = PorterDuffColorFilter(
                    getColor(color),
                    PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    private fun loadImages() {
        userImage.loadFromUrl(
            "https://randomuser.me/api/portraits/women/32.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        userImage1.loadFromUrl(
            "https://randomuser.me/api/portraits/women/30.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        userImage2.loadFromUrl(
            "https://randomuser.me/api/portraits/women/33.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        userImage3.loadFromUrl(
            "https://randomuser.me/api/portraits/women/34.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        userImageNight.loadFromUrl(
            "https://randomuser.me/api/portraits/women/32.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        userImage1Night.loadFromUrl(
            "https://randomuser.me/api/portraits/women/30.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        userImage2Night.loadFromUrl(
            "https://randomuser.me/api/portraits/women/33.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        userImage3Night.loadFromUrl(
            "https://randomuser.me/api/portraits/women/34.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        userImageEvening.loadFromUrl(
            "https://randomuser.me/api/portraits/women/38.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        userImage1Evening.loadFromUrl(
            "https://randomuser.me/api/portraits/women/30.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        userImage2Evening.loadFromUrl(
            "https://randomuser.me/api/portraits/women/33.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        userImage3Evening.loadFromUrl(
            "https://randomuser.me/api/portraits/women/34.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        CommentuserImage1.loadFromUrl(
            "https://randomuser.me/api/portraits/women/35.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        CommentuserImage2.loadFromUrl(
            "https://randomuser.me/api/portraits/women/35.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        CommentuserImage3.loadFromUrl(
            "https://randomuser.me/api/portraits/women/35.jpg",
            transformation = listOf(CenterCrop(), CircleCrop())
        )
        eventImage1.loadFromUrl(
            "https://picsum.photos/id/232/200/300",
            transformation = listOf(CenterCrop(), RoundedCorners(10))
        )
        eventImage2.loadFromUrl(
            "https://picsum.photos/id/238/200/300",
            transformation = listOf(CenterCrop(), RoundedCorners(10))
        )
        eventImage1evening.loadFromUrl(
            "https://picsum.photos/id/239/200/300",
            transformation = listOf(CenterCrop(), RoundedCorners(10))
        )
        eventImage2evening.loadFromUrl(
            "https://picsum.photos/id/236/200/300",
            transformation = listOf(CenterCrop(), RoundedCorners(10))
        )
        eventImage1night.loadFromUrl(
            "https://picsum.photos/id/235/200/300",
            transformation = listOf(CenterCrop(), RoundedCorners(10))
        )
        eventImage2night.loadFromUrl(
            "https://picsum.photos/seed/picsum/200/300",
            transformation = listOf(CenterCrop(), RoundedCorners(10))
        )
    }

    private fun cardExpanded(cardView: CardView) {
        cardView.cardElevation = 0.0f
        val params: ViewGroup.MarginLayoutParams =
            cardView.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(0, margin, 0, margin)
        cardView.requestLayout()
    }

    private fun cardHidden(cardView: CardView) {
        cardView.cardElevation = cardElevation.toFloat()
        val params: ViewGroup.MarginLayoutParams =
            cardView.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(margin, margin, margin, margin)
        cardView.requestLayout()
    }

    private fun setupCalenderView() {
        calendar.time = Date()
        currentMonth = calendar[Calendar.MONTH]
        selected_date.text = calendar.time.toString(dateFormat)
        val myCalendarViewManager = object :
            CalendarViewManager {
            override fun setCalendarViewResourceId(
                position: Int,
                date: Date,
                isSelected: Boolean
            ): Int {
                val cal = Calendar.getInstance()
                cal.time = date
                return when (cal[Calendar.DAY_OF_WEEK]) {
                    Calendar.MONDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY -> R.layout.calender_item_have_events
                    Calendar.SUNDAY -> R.layout.calender_item_new_events
                    else -> R.layout.calender_item
                }
            }

            override fun bindDataToCalendarView(
                holder: SingleRowCalendarAdapter.CalendarViewHolder,
                date: Date,
                position: Int,
                isSelected: Boolean
            ) {
                holder.itemView.tv_date_calendar_item.text = DateUtils.getDayNumber(date)
                holder.itemView.tv_day_calendar_item.text = DateUtils.getDay1LetterName(date)
                if (isSelected) {
                    holder.itemView.tv_date_calendar_item.background =
                        getDrawable(R.drawable.date_bg_selected)
                    holder.itemView.tv_date_calendar_item.setTextColor(getColor(R.color.white))
                }
            }
        }
        val myCalendarChangesObserver = object :
            CalendarChangesObserver {
            override fun whenSelectionChanged(isSelected: Boolean, position: Int, date: Date) {
                super.whenSelectionChanged(isSelected, position, date)
            }
        }
        val mySelectionManager = object : CalendarSelectionManager {
            override fun canBeItemSelected(position: Int, date: Date): Boolean {
                val cal = Calendar.getInstance()
                cal.time = date
                selected_date.text = cal.time.toString(dateFormat)
                return true

            }
        }
        val singleRowCalendar = calender_view.apply {
            calendarViewManager = myCalendarViewManager
            calendarChangesObserver = myCalendarChangesObserver
            calendarSelectionManager = mySelectionManager
            setDates(getFutureDatesOfCurrentMonth())
            init()
        }
    }


    private fun getFutureDatesOfCurrentMonth(): List<Date> {
        currentMonth = calendar[Calendar.MONTH]
        return getDates(mutableListOf())
    }

    private fun getDates(list: MutableList<Date>): List<Date> {
        calendar.set(Calendar.MONTH, currentMonth)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        list.add(calendar.time)
        while (currentMonth == calendar[Calendar.MONTH]) {
            calendar.add(Calendar.DATE, +1)
            if (calendar[Calendar.MONTH] == currentMonth)
                list.add(calendar.time)
        }
        calendar.add(Calendar.DATE, -1)
        return list
    }

    private fun dpToPx(dp: Float): Int {
        return dpToPx(dp, resources)
    }

    private fun dpToPx(dp: Float, resources: Resources): Int {
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics
        )
        return px.toInt()
    }

}