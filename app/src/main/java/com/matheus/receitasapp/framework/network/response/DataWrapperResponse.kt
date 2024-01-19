package com.matheus.receitasapp.framework.network.response

data class DataWrapperResponse(
    val from: Int,
    val to: Int,
    val more: Boolean,
    val count: Int,
    val hits: List<DataContainerResponse>
)
