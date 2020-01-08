package com.expedia.api.lodging.service

import com.expedia.api.lodging.adapters.FiltersAdapter
import com.expedia.api.lodging.adapters.PropertiesAdapter
import com.expedia.api.lodging.client.ContentApi
import com.expedia.api.lodging.client.GaiaApi
import com.expedia.api.lodging.query.model.PropertyResults
import org.springframework.stereotype.Service

@Service
class PropertyResultsService(
    private val gaiaApi: GaiaApi,
    private val contentApi: ContentApi,
    private val propertiesAdapter: PropertiesAdapter,
    private val filtersAdapter: FiltersAdapter
) {

    suspend fun propertyResults(): PropertyResults {

        /* Downstream service calls */
        val gaiaPropertyResponse = gaiaApi.getProperties()
        val gaiaNeighborhoodResponse = gaiaApi.getNeighborhoods()
        val propertyContentResponse = contentApi.getPropertyContent()
        val amenityContentResponse = contentApi.getAmenities()
        /* Downstream service calls */

        /* Response adapters */
        val properties = propertiesAdapter.adapt(gaiaPropertyResponse, propertyContentResponse, gaiaNeighborhoodResponse)
        val filters = filtersAdapter.adapt(gaiaNeighborhoodResponse, amenityContentResponse)
        /* Response adapters */

        return PropertyResults(properties, filters)
    }
}