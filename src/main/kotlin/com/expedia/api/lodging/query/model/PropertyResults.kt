package com.expedia.api.lodging.query.model

import com.expedia.api.lodging.service.PropertyResultsService
import com.expediagroup.graphql.annotations.GraphQLDescription

@GraphQLDescription("Property results for a destination")
class PropertyResults(
    private val propertyResultsService: PropertyResultsService
) {
    @GraphQLDescription("Properties in a destination")
    suspend fun properties(): List<Property> = propertyResultsService.properties()

    @GraphQLDescription("Filter options to narrow down properties")
    suspend fun filters(): List<FilterViewModel> = propertyResultsService.filters()
}