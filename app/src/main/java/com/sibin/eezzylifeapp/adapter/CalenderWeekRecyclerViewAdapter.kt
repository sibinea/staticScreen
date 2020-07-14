package com.sibin.eezzylifeapp.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sibin.eezzylifeapp.R
import com.sibin.eezzylifeapp.extension.DateUtils
import com.sibin.eezzylifeapp.extension.invisible
import com.sibin.eezzylifeapp.extension.visible
import com.sibin.eezzylifeapp.listener.OnItemClickListener
import com.sibin.eezzylifeapp.model.CalenderItem
import kotlinx.android.synthetic.main.calender_item.view.*


class CalenderWeekRecyclerViewAdapter(
    private var mValues: List<CalenderItem>,
    private val mListener: OnItemClickListener?,
    private val context: Context
) : RecyclerView.Adapter<CalenderWeekRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.calender_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]

        when(position){
            1,2,3,4->{
               holder.itemView.new_event.invisible()
            }
            else -> {
                holder.itemView.new_event.visible()
            }
        }

        holder.itemView.tv_date_calendar_item.text = DateUtils.getDayNumber(item.date)
        holder.itemView.tv_day_calendar_item.text = DateUtils.getDay1LetterName(item.date)
        if (item.isSelected) {
            holder.itemView.tv_date_calendar_item.background =
                ContextCompat.getDrawable(context, R.drawable.date_bg_selected)
            holder.itemView.tv_date_calendar_item.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )
        } else {
            holder.itemView.tv_date_calendar_item.background =
                ContextCompat.getDrawable(context, R.drawable.date_bg_unselected)
            holder.itemView.tv_date_calendar_item.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.black
                )
            )
        }
        with(holder.mView) {
            setOnClickListener {
                changeValues(position)
//                mValues[position].isSelected = true
                mListener?.onDateClick(mValues[position].date, position)
            }
        }

    }

    override fun getItemCount(): Int = 7

    fun changeValues(selectedPosition:Int) {
        mValues.map { it.isSelected = false }
        mValues[selectedPosition].isSelected=true
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

    }
}
