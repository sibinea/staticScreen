package com.sibin.eezzylifeapp.view

import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sibin.eezzylifeapp.*
import com.sibin.eezzylifeapp.adapter.CalenderWeekRecyclerViewAdapter
import com.sibin.eezzylifeapp.listener.OnDateSelectedListener
import com.sibin.eezzylifeapp.listener.OnItemClickListener
import com.sibin.eezzylifeapp.model.CalenderItem
import kotlinx.android.synthetic.main.fragment_calender_week.view.*
import java.util.*


class CalenderWeekFragment : Fragment(), OnItemClickListener {

    companion object {
        private var position = 0
        var selectedPosition = -1
        fun newInstance(pos: Int, selectedPos: Int): CalenderWeekFragment {
            position = pos
            selectedPosition = selectedPos
            return CalenderWeekFragment()
        }
    }

    private var calendar = Calendar.getInstance().apply {
        firstDayOfWeek = Calendar.MONDAY
    }
    private var currentMonth = 0
    private var currentWeek = 0
    private var notifyValue = false
    private lateinit var onDateSelectedListener: OnDateSelectedListener
    private lateinit var adapter: CalenderWeekRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onDateSelectedListener = context as OnDateSelectedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calender_week, container, false)
        recyclerView = view.recycler_view
        adapter =
            CalenderWeekRecyclerViewAdapter(getFutureDatesOfCurrentMonth(), this, requireContext())
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (notifyValue) {
            adapter.notifyDataSetChanged()
            onDateSelectedListener.onDateClick(
                Calendar.getInstance().time,
                selectedPosition
            )
        }
    }

    override fun onResume() {
        super.onResume()
        selectedPosition = (activity as ScrollingActivity).getSelected()
        if (selectedPosition != -1) {
            adapter.changeValues(selectedPosition)
        }
    }


    override fun onDateClick(date: Date, position: Int) {
        adapter.notifyDataSetChanged()
        onDateSelectedListener.onDateClick(date, position)
    }

    private fun getFutureDatesOfCurrentMonth(): List<CalenderItem> {
        currentMonth = calendar[Calendar.MONTH]
        currentWeek = calendar[Calendar.WEEK_OF_MONTH]
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return getWeekDates(currentWeek + position, mutableListOf())
    }


    private fun getWeekDates(
        currentWeek: Int,
        list: MutableList<CalenderItem>
    ): MutableList<CalenderItem> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        calendar.set(Calendar.WEEK_OF_MONTH, currentWeek)
        for (i in 0..6) {
            if (selectedPosition == -1 && DateUtils.isToday(calendar.timeInMillis)) {
                selectedPosition = i
                list.add(CalenderItem(true, calendar.time))
                (activity as ScrollingActivity).setSelected(selectedPosition)
                notifyValue = true
            } else {
                list.add(CalenderItem(selectedPosition == i, calendar.time))
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        return list
    }
}