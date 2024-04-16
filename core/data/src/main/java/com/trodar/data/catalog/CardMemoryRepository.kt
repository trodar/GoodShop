package com.trodar.data.catalog

import com.trodar.data.R
import com.trodar.model.Card
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CardMemoryRepository @Inject constructor() : CardRepository {
    override suspend fun getCards(): Flow<List<Card>> {
        return flow {
            emit(
                listOf(
                    Card(
                        id = "5678 1234 6543 0987",
                        image = R.drawable.visa,
                        extMonth = 2,
                        expYear = 2029,
                        cvcCode = 121,
                    ),
                    Card(
                        id = "5678 1234 6543 3456",
                        image = R.drawable.master_card,
                        extMonth = 3,
                        expYear = 2029,
                        cvcCode = 122,
                    ),
                    Card(
                        id = "5678 1234 6543 7890",
                        image = R.drawable.apple_pay,
                        extMonth = 4,
                        expYear = 2029,
                        cvcCode = 123,
                    ),
                )
            )
        }
    }
}