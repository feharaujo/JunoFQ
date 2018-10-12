package com.fearaujo.junofq.dashboard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.fearaujo.data.Repository
import com.fearaujo.junofq.R
import com.fearaujo.junofq.details.DetailsActivity
import com.fearaujo.mememaker.arch.BaseActivity
import com.fearaujo.model.Venue
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.jetbrains.anko.intentFor
import org.koin.android.ext.android.inject

class DashboardActivity : BaseActivity<DashboardContract.View,
        DashboardContract.Presenter>(), DashboardContract.View, VenueClickListener {

    private val adapter by lazy { VenueListAdapter(this) }

    override fun initPresenter(): DashboardContract.Presenter {
        val repository: Repository by inject()
        return DashboardPresenter(this, repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(customToolbar)
        venuesRecyclerView.adapter = adapter
        venuesRecyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()

        etSearch.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        etSearch.removeTextChangedListener(textWatcher)

        super.onPause()
    }

    override fun updateItems(items: List<Venue>) {
        adapter.updateItems(items)
    }

    override fun showNoConnectionWarning() {
        noConnectionContainer.visibility = View.VISIBLE
    }

    override fun hideNoConnectionWarning() {
        noConnectionContainer.visibility = View.GONE
    }

    override fun onSelectItem(item: Venue) {
        startActivity(intentFor<DetailsActivity>("item" to item))
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            presenter?.fetchVenues(text.toString())
        }

    }
}
