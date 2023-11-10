package com.kirillborichevskiy.domain.usecase

import com.kirillborichevskiy.domain.repository.InMemoryRepository
import javax.inject.Inject

class GetSelectedIdsUseCase @Inject constructor(
    private val inMemoryRepository: InMemoryRepository,
) : BaseUseCase<List<Int>, None>() {
    override suspend fun invoke(params: None): List<Int> = inMemoryRepository.getSelectedIds()
}
