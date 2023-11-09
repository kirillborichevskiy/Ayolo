package com.kirillborichevskiy.domain.usecase

import com.kirillborichevskiy.domain.model.DomainChat
import com.kirillborichevskiy.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetChatsUseCase {
    operator fun invoke(): Resource<Flow<List<DomainChat>>>
}
