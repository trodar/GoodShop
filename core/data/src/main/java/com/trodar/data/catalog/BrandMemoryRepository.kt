package com.trodar.data.catalog

import com.trodar.model.Brand
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BrandMemoryRepository @Inject constructor() : BrandRepository {
    override suspend fun getBrands(): Flow<List<Brand>> = flow {
        emit(
            listOf(
                Brand(
                    1,
                    "Levis",
                    "https://1000logos.net/wp-content/uploads/2017/10/Levis-Logo.jpg",
                    "Description",
                ),
                Brand(
                    2,
                    "Liu Jo Junior",
                    "https://1000logos.net/wp-content/uploads/2020/02/0023_Logo-Liu-Jo-Junior-500x281-1.jpg",
                    "Description",
                ),
                Brand(
                    3,
                    "Adidas",
                    "https://1000logos.net/wp-content/uploads/2016/10/Adidas-Logo-500x281.png",
                    "Description",
                ),
                Brand(
                    4,
                    "Lee Cooper",
                    "https://1000logos.net/wp-content/uploads/2020/10/Lee-Cooper-logo-tumb.jpg",
                "Description",
                ),
                Brand(
                    5,
                    "Dickies",
                    "https://1000logos.net/wp-content/uploads/2020/07/Dickies-Logo-tumb.jpg",
                    "Description",
                ),
                Brand(
                    6,
                    "Viceroy",
                    "https://1000logos.net/wp-content/uploads/2020/04/Viceroy-Logo-tumb.jpg",
                    "Description",
                ),
            )
        )
    }
}