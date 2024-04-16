package com.trodar.data.catalog

import com.trodar.model.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    suspend fun getCards(): Flow<List<Card>>
}