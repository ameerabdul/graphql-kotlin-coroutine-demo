package com.graphql.kotlin.coroutine.demo.query.model

import com.expediagroup.graphql.annotations.GraphQLDescription

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