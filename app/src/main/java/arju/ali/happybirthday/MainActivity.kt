package arju.ali.happybirthday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    private lateinit var adapter: ToDoItemsRecViewAdapter
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ToDoItemsRecViewAdapter(mutableListOf())

        rec_view_to_do_items.adapter = adapter

        rec_view_to_do_items.layoutManager = LinearLayoutManager(this)

        btnAddItems.setOnClickListener {
            val newItem = text_input_edit_text_to_do_title.text.toString()
            if(newItem.isNotEmpty())
            {
                adapter.addItems(ToDoItemsModel(newItem))
                text_input_edit_text_to_do_title.text!!.clear()
            }
        }

        btnDelete.setOnClickListener {
            adapter.deleteItem()
        }

    }
}
