package com.kirillborichevskiy.domain.usecase

interface GetSelectedIdsUseCase {
    operator fun invoke(): List<Int>
}
