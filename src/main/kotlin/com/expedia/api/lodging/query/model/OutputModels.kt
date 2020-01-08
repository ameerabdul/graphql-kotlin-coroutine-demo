package com.expedia.api.lodging.query.model

import com.expediagroup.graphql.annotations.GraphQLDescription

data class Property(
    @GraphQLDescription("Property id")
    val id: Long,
    @GraphQLDescription("Property name")
    val name: String?,
    @GraphQLDescription("Neighborhood name in which the property is located")
    val neighborhoodName: String?
)

data class FilterViewModel(
    @GraphQLDescription("Filter type. Example: amenities, neighborhood")
    val filterType: String,
    @GraphQLDescription("List of available filter options")
    val options: List<String>
)

data class Neighborhood(
    @GraphQLDescription("Neighborhood region id")
    val regionId: Long,
    @GraphQLDescription("Neighborhood name")
    val name: String
)