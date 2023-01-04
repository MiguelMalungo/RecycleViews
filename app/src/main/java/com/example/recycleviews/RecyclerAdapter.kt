package com.example.recycleviews

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter (val context: Context,
                       val vans : List<Van>  ) :

        RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.van_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val van = vans[position]
        holder.nameTextView.text = van.name
        holder.nameDescriptionView.text = van.description
        holder.vanView.setImageResource(van.image)
        /*holder.viewButton.setImageResource(van.viewButton)
        holder.viewButton.setOnClickListener {

                val intent = Intent ( context, MapsActivityH::class.java)
            intent.putExtra("latitude",van.location.latitude)
            intent.putExtra("longitude",van.location.longitude)

                context.startActivity(intent)
            }*/


    }

    override fun getItemCount()=vans.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTextView= itemView.findViewById<TextView>(R.id.name)
        val nameDescriptionView= itemView.findViewById<TextView>(R.id.description)
        //val viewButton = itemView.findViewById<ImageButton>(R.id.view)
        val vanView= itemView.findViewById<ImageView>(R.id.imageView2)
        //val button = findViewById<Button>(R.id.button)

    }



}