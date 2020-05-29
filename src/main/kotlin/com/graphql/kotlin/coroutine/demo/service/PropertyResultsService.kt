package com.graphql.kotlin.coroutine.demo.service

import com.graphql.kotlin.coroutine.demo.adapters.FiltersAdapter
import com.graphql.kotlin.coroutine.demo.adapters.PropertiesAdapter
import com.graphql.kotlin.coroutine.demo.client.ContentApi
import com.graphql.kotlin.coroutine.demo.client.GaiaApi
import com.graphql.kotlin.coroutine.demo.client.Region
import com.graphql.kotlin.coroutine.demo.query.model.FilterViewModel
import com.graphql.kotlin.coroutine.demo.query.model.Property
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
        // Don't worry about making `gaiaPropertyResponse` lazy. Lets assume we always need this call
        val gaiaPropertyResponse = gaiaApi.getProperties()

        // TODO(2): Update `propertyContentResponse` to a lazy Coroutine
        val propertyContentResponse = contentApi.getPropertyContent()

        // TODO(3): Pass in both `propertyContentResponse` and `gaiaNeighborhoodResponse` as `Deferred`
        return propertiesAdapter.adapt(gaiaPropertyResponse, propertyContentResponse, gaiaNeighborhoodResponse)
    }

    suspend fun filters(
        gaiaNeighborhoodResponse: Map<Long, Region>
    ): List<FilterViewModel> {
        val amenityContentResponse = contentApi.getAmenities()
        return filtersAdapter.adapt(gaiaNeighborhoodResponse, amenityContentResponse)
    }
}