package arju.ali.happybirthday

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rec_view_to_do_items.view.*

class ToDoItemsRecViewAdapter(val listItems: MutableList<ToDoItemsModel>) : RecyclerView.Adapter<ToDoItemsRecViewAdapter.ToDoItemsViewHolder>()
{
    class ToDoItemsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemsViewHolder
    {
        return ToDoItemsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rec_view_to_do_items, parent, false))
    }

    override fun getItemCount(): Int
    {
        return listItems.size
    }

    private fun toggleStrike(tvItem : TextView, isChecked: Boolean)
    {
        if(isChecked)
        {
            tvItem.paintFlags = tvItem.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else
        {
            tvItem.paintFlags = tvItem.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }

    }

    fun addItems(newItem: ToDoItemsModel)
    {
        listItems.add(newItem)
        notifyItemInserted(listItems.size - 1)
    }

    fun deleteItem()
    {
        listItems.removeAll { removeItems -> removeItems.isChecked }
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ToDoItemsViewHolder, position: Int)
    {
        val currToDo = listItems[position]
        //holder.itemView.text_view_to_do_items.setText(currToDo.toDoItemTitle)
        //holder.itemView.checkBox.isChecked(currToDo.isChecked)
        holder.itemView.apply() 
        {  
            text_view_to_do_items.text = currToDo.toDoItemTitle
            checkBox.isChecked = currToDo.isChecked
            toggleStrike(text_view_to_do_items, currToDo.isChecked)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                currToDo.isChecked = isChecked
                toggleStrike(text_view_to_do_items, currToDo.isChecked)
            }
        }
    }
}