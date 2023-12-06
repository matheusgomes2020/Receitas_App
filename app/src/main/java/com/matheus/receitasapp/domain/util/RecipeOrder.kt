package com.matheus.receitasapp.domain.util

sealed class RecipeOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): RecipeOrder(orderType)
    class Date(orderType: OrderType): RecipeOrder(orderType)
    class Time(orderType: OrderType): RecipeOrder(orderType)

    fun copy(orderType: OrderType): RecipeOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Time -> Time(orderType)
        }
    }
}