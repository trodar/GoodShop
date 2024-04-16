package com.trodar.data.catalog

import com.trodar.model.Brand
import kotlinx.coroutines.flow.Flow


interface BrandRepository {

    suspend fun getBrands(): Flow<List<Brand>>
}