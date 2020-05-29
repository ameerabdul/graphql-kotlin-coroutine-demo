package com.graphql.kotlin.coroutine.demo.query

import com.graphql.kotlin.coroutine.demo.client.GaiaApi
import com.graphql.kotlin.coroutine.demo.query.model.PropertyResults
import com.graphql.kotlin.coroutine.demo.service.PropertyResultsService
import com.expediagroup.graphql.spring.operations.Query
import org.springframework.stereotype.Component

@Component
class PropertyResultsQuery(
    private val gaiaApi: GaiaApi,
    private val propertyResultsService: PropertyResultsService
): Query {

    suspend fun propertyResults() = PropertyResults(
        gaiaApi,
        propertyResultsService
    )
}