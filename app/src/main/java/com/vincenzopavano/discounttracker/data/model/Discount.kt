package com.vincenzopavano.discounttracker.data.model

data class Discount(
        val Id: Int,
        val Company: String,
        val Description: String,
        val Address: String,
        val Website: String,
        val Phone: String,
        val Latitude: Double,
        val Longitude: Double)