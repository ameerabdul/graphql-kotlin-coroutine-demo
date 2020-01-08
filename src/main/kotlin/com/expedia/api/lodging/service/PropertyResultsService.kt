package com.expedia.api.lodging.service

import com.expedia.api.lodging.adapters.FiltersAdapter
import com.expedia.api.lodging.adapters.PropertiesAdapter
import com.expedia.api.lodging.client.ContentApi
import com.expedia.api.lodging.client.GaiaApi
import com.expedia.api.lodging.client.Region
import com.expedia.api.lodging.query.model.FilterViewModel
import com.expedia.api.lodging.query.model.Property
import org.springframework.stereotype.Service

@Service
class PropertyResultsService(
    private val contentApi: ContentApi,
    private val gaiaApi: GaiaApi,
    private val propertiesAdapter: PropertiesAdapter,
    private val filtersAdapter: FiltersAdapter
) {

    suspend fun properties(
        gaiaNeighborhoodResponse: Map<Long, Region>
    ): List<Property> {
        val gaiaPropertyResponse = gaiaApi.getProperties()
        val propertyContentResponse = contentApi.getPropertyContent()

        return propertiesAdapter.adapt(gaiaPropertyResponse, propertyContentResponse, gaiaNeighborhoodResponse)
    }

    suspend fun filters(
        gaiaNeighborhoodResponse: Map<Long, Region>
    ): List<FilterViewModel> {
        val amenityContentResponse = contentApi.getAmenities()
        return filtersAdapter.adapt(gaiaNeighborhoodResponse, amenityContentResponse)
    }
}