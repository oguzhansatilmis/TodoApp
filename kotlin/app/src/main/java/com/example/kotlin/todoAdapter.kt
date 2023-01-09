package com.example.kotlin
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_row.view.*

class RecyclerAdapter(var context:Context,val todoListText :ArrayList<String>,val todoArrayListId :ArrayList<String>,val todoArrayDate:ArrayList<String>,val todoArrayDetail:ArrayList<String>) : RecyclerView.Adapter<RecyclerAdapter.todoVH>(){

    class todoVH(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoVH {
        //Inflater ,LayoutInflater,MenuInflater

            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.todo_row,parent,false) // tasarımı aktardık
            return todoVH(itemView)

    }

    override fun onBindViewHolder(holder: todoVH, position: Int) {
        holder.itemView.todoRowText.text = todoListText.get(position) // Texti yazıdrdık
        holder.itemView.todoRowDate.text = todoArrayDate.get(position) // Tarih texti yazıdrdık
        //todoya tıklama
        holder.itemView.todoRowText.setOnClickListener{
            val intent = Intent(context,todoDetails::class.java)
            intent.putExtra("todoText", todoListText.get(position).toString())
            intent.putExtra("todoId", todoArrayListId.get(position).toString())
            intent.putExtra("todoDate", todoArrayDate.get(position).toString())
            intent.putExtra("todoDetail", todoArrayDetail.get(position).toString())
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return todoListText.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var user_text_name: TextView
        var kullaniciGoster: RelativeLayout


        init {
            user_text_name = itemView.findViewById(R.id.todoRowText)
            kullaniciGoster = itemView.findViewById(R.id.todoRowDate)
        }
    }

}