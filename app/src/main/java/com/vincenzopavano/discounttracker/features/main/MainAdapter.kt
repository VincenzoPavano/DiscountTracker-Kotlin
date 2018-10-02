package com.vincenzopavano.discounttracker.features.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.vincenzopavano.discounttracker.data.model.Discount
import javax.inject.Inject

class MainAdapter @Inject
constructor() : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var discountsList: List<Discount> = emptyList<Discount>()
    private var clickListener: ClickListener? = null

    fun setDiscountsList(discounts: List<Discount>) {
        discountsList = discounts
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val textView = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        return MainViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return discountsList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val discount = discountsList[position]
        holder.textView.setText(discount.Company)
    }

    interface ClickListener {
        fun onDiscountClick(discount: Discount)
    }

    class MainViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

}