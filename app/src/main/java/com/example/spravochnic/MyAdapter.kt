package com.example.spravochnic

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(listArray: ArrayList<ListItem>, context: Context) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var listArrarR = listArray
    var contextR = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvContent = view.findViewById<TextView>(R.id.tvContent)
        val im = view.findViewById<ImageView>(R.id.im)

        fun bind(listItem: ListItem, context: Context) {  // заполняем itemLayout
            val textCon = listItem.contentText.substring(0,70) +"..."
            tvTitle.text = listItem.titleText
            tvContent.text = textCon
            im.setImageResource(listItem.image_id)
            itemView.setOnClickListener() {
                Toast.makeText(context, "Выбрано: ${tvTitle.text}", Toast.LENGTH_SHORT).show()

                val i = Intent(context,ContentActivity::class.java).apply {
                    putExtra("title",tvTitle.text.toString())
                    putExtra("content",listItem.contentText)
                    putExtra("image",listItem.image_id)
                }
                context.startActivity(i)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextR) // получили класс который будет рисовать
        return ViewHolder(inflater.inflate(R.layout.item_layout, parent, false)) // находим все эл
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = listArrarR[position]
        holder.bind(listItem, contextR)
    }

    override fun getItemCount(): Int {
        return listArrarR.size // получаем длуну массива
    }

    fun updateAdapter(listArray: List<ListItem>) { // обновляем массив
        listArrarR.clear()
        listArrarR.addAll(listArray)
        notifyDataSetChanged()
    }
}