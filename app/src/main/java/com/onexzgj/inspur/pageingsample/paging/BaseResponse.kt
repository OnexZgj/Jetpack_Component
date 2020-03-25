package com.onexzgj.inspur.pageingsample.paging

data class BaseResponse<T>(
    var data: T?,
    var results: T?,
    val errorMsg: String? = null,
    var errorCode: Int? = -1,
    var error: Boolean? = true
)