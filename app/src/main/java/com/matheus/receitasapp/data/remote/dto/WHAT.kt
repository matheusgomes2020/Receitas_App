package com.matheus.receitasapp.data.remote.dto

data class WHAT(
    val _links: Links,
    val count: Int,
    val from: Int,
    val hits: List<Hit>,
    val to: Int
)