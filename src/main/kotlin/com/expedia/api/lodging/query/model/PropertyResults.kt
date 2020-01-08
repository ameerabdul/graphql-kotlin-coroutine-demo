package com.expedia.api.lodging.query.model

import com.expedia.api.lodging.client.GaiaApi
import com.expedia.api.lodging.service.PropertyResultsService
import com.expediagroup.graphql.annotations.GraphQLDescription
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlin.coroutines.EmptyCoroutineContext

@GraphQLDescription("Property results for a destination")
class PropertyResults(
    private val gaiaApi: GaiaApi,
    private val propertyResultsService: PropertyResultsService
) {

    /* Lazy Downstream service calls */
    private val gaiaNeighborhoodResponse = CoroutineScope(EmptyCoroutineContext + Dispatchers.IO)
        .async(start = CoroutineStart.LAZY) {
            gaiaApi.getNeighborhoods()
        }
    /* Lazy Downstream service calls */

    @GraphQLDescription("Properties in a destination")
    suspend fun properties(): List<Property> = propertyResultsService.properties(
        gaiaNeighborhoodResponse.await()
    )

    @GraphQLDescription("Filter options to narrow down properties")
    suspend fun filters(): List<FilterViewModel> = propertyResultsService.filters(
        gaiaNeighborhoodResponse.await()
    )
}