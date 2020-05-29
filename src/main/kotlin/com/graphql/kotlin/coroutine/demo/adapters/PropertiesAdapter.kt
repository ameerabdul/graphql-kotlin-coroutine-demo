package com.graphql.kotlin.coroutine.demo.adapters

import com.graphql.kotlin.coroutine.demo.client.GaiaProperty
import com.graphql.kotlin.coroutine.demo.client.PropertyContent
import com.graphql.kotlin.coroutine.demo.client.Region
import com.graphql.kotlin.coroutine.demo.query.model.Property
import org.springframework.stereotype.Component

@Component
class PropertiesAdapter {

    fun adapt(gaiaProperties: List<GaiaProperty>, propertyContent: Map<Long, PropertyContent>, neighborhoods: Map<Long, Region>): List<Property> =
        gaiaProperties.map {
            val propertyId = it.propertyId
            val neighborhoodId = it.neighborhoodId
            /*
             * TODO(4): Pass in both `propertyContent` and `neighborhoods` as `Deferred`
             *  and update `Property` to await at appropriate place
             */
            Property(propertyId, propertyContent[propertyId]?.name, neighborhoods[neighborhoodId]?.name)
        }
}