package com.expedia.api.lodging.query

import com.expediagroup.graphql.spring.operations.Query
import org.springframework.stereotype.Component

@Component
class HelloWorldQuery: Query {
    
    fun helloWorld() = "Hello World!"
}