package com.expedia.api.lodging.adapters

import com.expedia.api.lodging.client.GaiaProperty
import com.expedia.api.lodging.client.PropertyContent
import com.expedia.api.lodging.client.Region
import com.expedia.api.lodging.query.model.Property
import org.springframework.stereotype.Component

@Component
class PropertiesAdapter {

    fun adapt(gaiaProperties: List<GaiaProperty>, propertyContent: Map<Long, PropertyContent>, neighborhoods: Map<Long, Region>): List<Property> =
        gaiaProperties.map {
            val propertyId = it.propertyId
            val neighborhoodId = it.neighborhoodId
            Property(propertyId, propertyContent[propertyId]?.name, neighborhoods[neighborhoodId]?.name)
        }
}