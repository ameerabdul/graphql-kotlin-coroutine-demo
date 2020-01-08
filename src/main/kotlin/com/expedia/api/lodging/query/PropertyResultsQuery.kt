package com.expedia.api.lodging.query

import com.expedia.api.lodging.query.model.PropertyResults
import com.expedia.api.lodging.service.PropertyResultsService
import com.expediagroup.graphql.spring.operations.Query
import org.springframework.stereotype.Component

@Component
class PropertyResultsQuery(
    private val propertyResultsService: PropertyResultsService
): Query {

    suspend fun propertyResults() = PropertyResults(propertyResultsService)
}