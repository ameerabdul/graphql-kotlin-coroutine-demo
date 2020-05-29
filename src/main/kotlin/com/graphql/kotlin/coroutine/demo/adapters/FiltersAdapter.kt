package com.graphql.kotlin.coroutine.demo.adapters

import com.graphql.kotlin.coroutine.demo.client.Amenity
import com.graphql.kotlin.coroutine.demo.client.Region
import com.graphql.kotlin.coroutine.demo.query.model.FilterViewModel
import org.springframework.stereotype.Component

@Component
class FiltersAdapter {

    fun adapt(regions: Map<Long, Region>, amenities: List<Amenity>): List<FilterViewModel> =
        listOf(
            FilterViewModel("neighborhood", regions.values.map { it.name }),
            FilterViewModel("amenities", amenities.map { it.name })
        )
}