package com.fearaujo.junofq.details

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fearaujo.junofq.R
import com.fearaujo.model.Venue
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val item = intent?.extras?.getParcelable<Venue>("item")
        title = item?.name

        bindInfos(item)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun bindInfos(item: Venue?) {
        item?.location?.let { location ->
            tvAddress.text = "${location.address} - ${location.city}"
            addressContainer.visibility = View.VISIBLE
        }

        item?.contact?.formattedPhone?.let { phone ->
            tvContact.text = "$phone"
            contactContainer.visibility = View.VISIBLE
        }
    }
}
