package com.expedia.api.lodging.service

import com.expedia.api.lodging.adapters.FiltersAdapter
import com.expedia.api.lodging.adapters.PropertiesAdapter
import com.expedia.api.lodging.client.ContentApi
import com.expedia.api.lodging.client.GaiaApi
import com.expedia.api.lodging.query.model.FilterViewModel
import com.expedia.api.lodging.query.model.Property
import org.springframework.stereotype.Service

@Service
class PropertyResultsService(
    private val gaiaApi: GaiaApi,
    private val contentApi: ContentApi,
    private val propertiesAdapter: PropertiesAdapter,
    private val filtersAdapter: FiltersAdapter
) {
    suspend fun properties(): List<Property> {
        val gaiaPropertyResponse = gaiaApi.getProperties()
        val gaiaNeighborhoodResponse = gaiaApi.getNeighborhoods()
        val propertyContentResponse = contentApi.getPropertyContent()

        return propertiesAdapter.adapt(gaiaPropertyResponse, propertyContentResponse, gaiaNeighborhoodResponse)
    }
    suspend fun filters(): List<FilterViewModel> {
        val gaiaNeighborhoodResponse = gaiaApi.getNeighborhoods()
        val amenityContentResponse = contentApi.getAmenities()

        return filtersAdapter.adapt(gaiaNeighborhoodResponse, amenityContentResponse)
    }
}