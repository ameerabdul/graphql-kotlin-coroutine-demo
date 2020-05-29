package com.graphql.kotlin.coroutine.demo.query.model

import com.graphql.kotlin.coroutine.demo.client.GaiaApi
import com.graphql.kotlin.coroutine.demo.service.PropertyResultsService
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
        gaiaNeighborhoodResponse
    )

    @GraphQLDescription("Filter options to narrow down properties")
    suspend fun filters(): List<FilterViewModel> = propertyResultsService.filters(
        gaiaNeighborhoodResponse.await()
    )
}