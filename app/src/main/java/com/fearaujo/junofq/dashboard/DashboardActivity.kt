package com.fearaujo.junofq.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fearaujo.data.Repository
import com.fearaujo.data.di.Params
import com.fearaujo.junofq.R
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.android.ext.android.inject

class DashboardActivity : AppCompatActivity() {

    val repository by inject<Repository>(name = Params.MAIN_REPOSITORY)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(customToolbar)

        if (repository != null) {
            if (repository != null) {

            }
        }
    }
}
