package com.expedia.api.lodging.adapters

import com.expedia.api.lodging.client.Amenity
import com.expedia.api.lodging.client.Region
import com.expedia.api.lodging.query.model.FilterViewModel
import org.springframework.stereotype.Component

@Component
class FiltersAdapter {

    fun adapt(regions: Map<Long, Region>, amenities: List<Amenity>): List<FilterViewModel> =
        listOf(
            FilterViewModel("neighborhood", regions.values.map { it.name }),
            FilterViewModel("amenities", amenities.map { it.name })
        )
}