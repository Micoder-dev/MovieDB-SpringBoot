package com.micoder.sbdemo.domain.model

data class ResponseModel(
    val success: Boolean,
    val message: String,
    val data: Any? = null
)