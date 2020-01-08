package com.graphql.kotlin.coroutine.demo.query.model

import com.expediagroup.graphql.annotations.GraphQLDescription
import com.graphql.kotlin.coroutine.demo.client.GaiaProperty
import com.graphql.kotlin.coroutine.demo.client.PropertyContent
import com.graphql.kotlin.coroutine.demo.client.Region
import kotlinx.coroutines.Deferred

class Property(
    private val gaiaProperty: GaiaProperty,
    private val propertyContentResponse: Deferred<Map<Long, PropertyContent>>,
    private val gaiaNeighborhoodResponse: Deferred<Map<Long, Region>>
) {

    @GraphQLDescription("Property id")
    val id = gaiaProperty.propertyId

    @GraphQLDescription("Property name")
    suspend fun name(): String? = propertyContentResponse.await()[gaiaProperty.propertyId]?.name

    @GraphQLDescription("Neighborhood name in which the property is located")
    suspend fun neighborhoodName(): String? = gaiaNeighborhoodResponse.await()[gaiaProperty.neighborhoodId]?.name
}