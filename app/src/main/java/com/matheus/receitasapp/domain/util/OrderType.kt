package com.matheus.receitasapp.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}