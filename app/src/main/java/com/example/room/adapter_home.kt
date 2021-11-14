package com.example.room

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.home_item.view.*

class adapter_home():RecyclerView.Adapter<adapter_home.ViewHolderIndex>(){
    val config : RealmConfiguration = RealmConfiguration.Builder().name("cplass.realm").build()
    val realm: Realm = Realm.getInstance(config) //to make configration of database realm

    val data = realm.where(DataBase::class.java).findAll()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolderIndex {
        var myViewInflater = LayoutInflater.from(viewGroup.context).inflate(R.layout.home_item2,viewGroup,false)
        return ViewHolderIndex(myViewInflater)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: ViewHolderIndex, position: Int) {
        val mydata=data[position]
        holder.bind(mydata!!)
    }
    class ViewHolderIndex(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(mydata :DataBase)
        {
            val name_lesson=itemView.title as TextView
            name_lesson.text=mydata.index_name
               itemView.setOnClickListener {
                   val intent =Intent(itemView.context,lesson_activity::class.java)
                   intent.putExtra("title",mydata.index_name)
                   intent.putExtra("con",mydata.lesson)
                   itemView.context.startActivity(intent)
               }
        }

    }

}