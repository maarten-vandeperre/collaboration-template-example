package com.redhat.demo.core.maartendomain.functional

abstract class Response<DATA_TYPE> {

    fun <NEW_TYPE> map(mapper: (data: DATA_TYPE) -> NEW_TYPE): Response<NEW_TYPE> {
        return if (this is SuccessResponse) {
            SuccessResponse(mapper(data))
        } else {
            ErrorResponse((this as ErrorResponse).errorMessages)
        }
    }

    companion object {
        fun <DATA_TYPE> fail(errorMessages: List<String>): Response<DATA_TYPE> {
            return ErrorResponse(errorMessages)
        }

        fun <DATA_TYPE> success(data: DATA_TYPE): Response<DATA_TYPE> {
            return SuccessResponse(data)
        }
    }
}

class ErrorResponse<DATA_TYPE>(
    val errorMessages: List<String>
) : Response<DATA_TYPE>()

class SuccessResponse<DATA_TYPE>(
    val data: DATA_TYPE
) : Response<DATA_TYPE>()