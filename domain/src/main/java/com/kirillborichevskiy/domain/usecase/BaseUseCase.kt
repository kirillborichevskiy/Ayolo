package com.kirillborichevskiy.domain.usecase

abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun invoke(params: Params): Type
}

object None
