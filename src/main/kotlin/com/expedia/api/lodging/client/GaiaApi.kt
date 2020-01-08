package com.expedia.api.lodging.client

import com.expedia.api.lodging.query.model.Neighborhood
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class GaiaApi {
    private val logger = LoggerFactory.getLogger(GaiaApi::class.java)

    suspend fun getProperties(): List<GaiaProperty> {
        logger.info("GAIA-API getProperties")
        delay(1000)
        return listOf(
            GaiaProperty(1, 1001),
            GaiaProperty(2, 1002)
        )
    }

    suspend fun getNeighborhoods(): Map<Long, Region> {
        logger.info("GAIA-API getNeighborhoods")
        delay(1000)
        return mapOf(
            1001L to Region(1001, "Lincoln Park"),
            1002L to Region(1002, "West loop")
        )
    }
}

data class GaiaProperty(
    val propertyId: Long,
    val neighborhoodId: Long
)

data class Region(
    val regionId: Long,
    val name: String
)