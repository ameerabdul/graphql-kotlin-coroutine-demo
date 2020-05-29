package com.graphql.kotlin.coroutine.demo.adapters

import com.graphql.kotlin.coroutine.demo.query.model.Property
import com.graphql.kotlin.coroutine.demo.client.GaiaProperty
import com.graphql.kotlin.coroutine.demo.client.PropertyContent
import com.graphql.kotlin.coroutine.demo.client.Region
import kotlinx.coroutines.Deferred
import org.springframework.stereotype.Component

@Component
class PropertiesAdapter {

    fun adapt(
        gaiaProperties: List<GaiaProperty>,
        propertyContentResponse: Deferred<Map<Long, PropertyContent>>,
        gaiaNeighborhoodResponse: Deferred<Map<Long, Region>>
    ) =
        gaiaProperties.map {
            Property(it, propertyContentResponse, gaiaNeighborhoodResponse)
        }
}