package com.graphql.kotlin.coroutine.demo.service

import com.graphql.kotlin.coroutine.demo.query.model.Property
import com.graphql.kotlin.coroutine.demo.adapters.FiltersAdapter
import com.graphql.kotlin.coroutine.demo.adapters.PropertiesAdapter
import com.graphql.kotlin.coroutine.demo.client.ContentApi
import com.graphql.kotlin.coroutine.demo.client.GaiaApi
import com.graphql.kotlin.coroutine.demo.client.Region
import com.graphql.kotlin.coroutine.demo.query.model.FilterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.springframework.stereotype.Service
import kotlin.coroutines.EmptyCoroutineContext

@Service
class PropertyResultsService(
    private val contentApi: ContentApi,
    private val gaiaApi: GaiaApi,
    private val propertiesAdapter: PropertiesAdapter,
    private val filtersAdapter: FiltersAdapter
) {

    suspend fun properties(
        gaiaNeighborhoodResponse: Deferred<Map<Long, Region>>
    ): List<Property> {
        val gaiaPropertyResponse = gaiaApi.getProperties()
        val propertyContentResponse = CoroutineScope(EmptyCoroutineContext + Dispatchers.IO)
            .async(start = CoroutineStart.LAZY) {
                contentApi.getPropertyContent()
            }

        return propertiesAdapter.adapt(gaiaPropertyResponse, propertyContentResponse, gaiaNeighborhoodResponse)
    }

    suspend fun filters(
        gaiaNeighborhoodResponse: Map<Long, Region>
    ): List<FilterViewModel> {
        val amenityContentResponse = contentApi.getAmenities()
        return filtersAdapter.adapt(gaiaNeighborhoodResponse, amenityContentResponse)
    }
}