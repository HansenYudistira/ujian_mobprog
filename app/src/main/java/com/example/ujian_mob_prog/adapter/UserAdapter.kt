package com.example.ujian_mob_prog.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ujian_mob_prog.MapActivity
import com.example.ujian_mob_prog.R
import com.example.ujian_mob_prog.model.User

class UserAdapter(private val context: Context, private val userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: User = userList[position]

        holder.userName.text = user.name
        holder.userUsername.text = user.username
        holder.userEmail.text = user.email
        holder.userAddress.text = "${user.address.street}, ${user.address.city}, ${user.address.zipcode}"

        val imageResourceId =
            context.resources.getIdentifier("drawable/ic_${user.id}", null, context.packageName)

        Glide.with(context)
            .load(imageResourceId)
            .placeholder(R.drawable.default_image)
            .error(R.drawable.default_image)
            .into(holder.userImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MapActivity::class.java).apply {
                putExtra("latitude", user.address.geo.lat)
                putExtra("longitude", user.address.geo.lng)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage: ImageView = itemView.findViewById(R.id.user_image)
        val userName: TextView = itemView.findViewById(R.id.user_name)
        val userUsername: TextView = itemView.findViewById(R.id.user_username)
        val userEmail: TextView = itemView.findViewById(R.id.user_email)
        val userAddress: TextView = itemView.findViewById(R.id.user_address)
    }
}
