package com.kirillborichevskiy.domain.usecase.impl

import com.kirillborichevskiy.domain.repository.InMemoryRepository
import com.kirillborichevskiy.domain.usecase.GetSelectedIdsUseCase
import javax.inject.Inject

class GetSelectedIdsUseCaseImpl @Inject constructor(
    private val inMemoryRepository: InMemoryRepository,
) : GetSelectedIdsUseCase {
    override fun invoke(): List<Int> = inMemoryRepository.getSelectedIds()
}
