package com.trodar.domain.catalog

import com.trodar.data.catalog.CardRepository
import com.trodar.model.Card
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListCardUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {

    suspend operator fun invoke(): Flow<List<Card>> = cardRepository.getCards()
}