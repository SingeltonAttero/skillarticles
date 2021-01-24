package ru.skillbranch.skillarticles.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.skillbranch.skillarticles.R
import ru.skillbranch.skillarticles.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}