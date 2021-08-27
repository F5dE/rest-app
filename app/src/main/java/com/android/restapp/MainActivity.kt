package com.android.restapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null

    val url: String = "https://private-8ce77c-tmobiletest.apiary-mock.com/test/home"
    lateinit var requestQueue: RequestQueue
    lateinit var objectRequest: JsonObjectRequest

    private lateinit var mainContainer: RecyclerView
    private lateinit var mainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>
    private lateinit var mainManager: RecyclerView.LayoutManager

    private val gson = Gson()
    private lateinit var gsonObject: GsonParse


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar);

        mainContainer = findViewById(R.id.main)

        requestQueue = Volley.newRequestQueue(this)
        objectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val sharedPreferences = getSharedPreferences("info", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("savedValue", response.toString())
                editor.apply()
                initAdapter(response.toString())
            },
            { error ->
                val sharedPreferences = getSharedPreferences("info", MODE_PRIVATE)
                val str = sharedPreferences.getString("savedValue", null)
                if (str != null) {
                    initAdapter(str)
                } else {
                    Toast.makeText(this, getString(R.string.internet), Toast.LENGTH_SHORT).show()
                }

            }
        )

        update()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.refresh) {
            update()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun update() {
        requestQueue.add(objectRequest)
    }

    private fun initAdapter(string: String) {
        gsonObject = gson.fromJson(string, GsonParse::class.java)

        mainManager = LinearLayoutManager(this)
        mainAdapter = MainAdapter(gsonObject.page.cards)

        mainContainer.layoutManager = mainManager
        mainContainer.adapter = mainAdapter
    }
}