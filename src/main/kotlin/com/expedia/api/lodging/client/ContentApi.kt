package com.expedia.api.lodging.client

import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ContentApi {

    private val logger = LoggerFactory.getLogger(ContentApi::class.java)

    suspend fun getPropertyContent(): Map<Long, PropertyContent> {
        logger.info("CONTENT-API getPropertyContent")
        delay(1000)
        return mapOf(
            1L to PropertyContent(1, "Property 1"),
            2L to PropertyContent(2, "Property 2")
        )
    }

    suspend fun getAmenities(): List<Amenity> {
        logger.info("CONTENT-API getAmenities")
        delay(1000)
        return listOf(
            Amenity("Free breakfast"),
            Amenity("Hot tube")
        )
    }
}

data class PropertyContent(
    val id: Long,
    val name: String
)

data class Amenity(
    val name: String
)