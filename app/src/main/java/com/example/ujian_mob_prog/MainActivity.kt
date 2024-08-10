package com.example.ujian_mob_prog

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ujian_mob_prog.adapter.UserAdapter
import com.example.ujian_mob_prog.model.User
import com.example.ujian_mob_prog.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "User List"

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchUsers()
    }

    private fun fetchUsers() {
        RetrofitInstance.api.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful && response.body() != null) {
                    val users = response.body()!!
                    adapter = UserAdapter(this@MainActivity, users)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("MainActivity", "Error fetching users", t)
            }
        })
    }
}
