package com.trodar.domain.catalog

import com.trodar.data.catalog.BrandRepository
import com.trodar.model.Brand
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ListBrandsUseCase @Inject constructor(
    private val brandRepository: BrandRepository
) {

    suspend operator fun invoke(): List<Brand> = brandRepository.getBrands().first()
}