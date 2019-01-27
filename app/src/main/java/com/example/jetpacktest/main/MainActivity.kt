package com.example.jetpacktest.main


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.jetpacktest.R
import com.example.jetpacktest.noviewmodel.NoViewModelActivity
import com.example.jetpacktest.viewmodel.ViewModelActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        // Get the support action bar
//        val actionBar = supportActionBar
//
//        // Set the action bar title, subtitle and elevation
//        actionBar!!.title = "JETPACK Rappi Demo"
//        actionBar.elevation = 4.0F
        setSupportActionBar(mainToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_nvm -> {
                goToNoViewModelActivity()
                return true
            }
            R.id.action_vm -> {
                goToViewModelActivity()
                return true
            }
            R.id.action_room -> {
                goToRoomActivity()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun goToNoViewModelActivity() {
        val intent = NoViewModelActivity.newIntent(this)
        startActivity(intent)
    }

    fun goToViewModelActivity() {
        val intent = ViewModelActivity.newIntent(this)
        startActivity(intent)
    }

    fun goToRoomActivity() {
        val intent = NoViewModelActivity.newIntent(this)
        startActivity(intent)
    }

}



