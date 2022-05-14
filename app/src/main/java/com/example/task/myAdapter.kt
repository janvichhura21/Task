package com.example.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task.model.User

class myAdapter(val user:List<User>):RecyclerView.Adapter<myAdapter.myViewHolder>() {
    class myViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val name=itemView.findViewById<TextView>(R.id.edtUsername)
        val age=itemView.findViewById<TextView>(R.id.edtUserAge)
        val Email=itemView.findViewById<TextView>(R.id.edtUserEmail)
        val phoneNumber=itemView.findViewById<TextView>(R.id.edtUserPhoneNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
      val inflater=LayoutInflater.from(parent.context).inflate(R.layout.detailsitems,parent,false)
        return myViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val data=user[position]
        holder.name.text=data.name
        holder.age.text= data.age.toString()
        holder.Email.text=data.email
        holder.phoneNumber.text=data.phoneNumber
    }

    override fun getItemCount(): Int {
        return user.size
    }
}