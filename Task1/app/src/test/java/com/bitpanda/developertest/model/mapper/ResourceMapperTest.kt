package com.bitpanda.developertest.model.mapper

import com.bitpanda.developertest.model.Resource
import com.bitpanda.developertest.model.ResourceType
import com.bitpanda.developertest.model.dto.ResourceDto
import org.junit.Assert.assertEquals
import org.junit.Test

class ResourceMapperTest {
    private val resourceMapper = ResourceMapper()

    private val resourceDto = ResourceDto(
        id = "1",
        name = "name",
        symbol = "btc",
        logo = "logo.svg",
        price = 42.42
    )

    @Test
    fun `when map resource dto with cryptocoin type then return Resource Cryptocoin model`() {
        val result = resourceMapper.map(resourceDto, ResourceType.CRYPTOCOIN)
        assertEquals(
            Resource.Cryptocoin(
                id = "1",
                name = "name",
                symbol = "btc",
                logo = "logo.svg",
                price = 42.42
            ), result
        )
    }

    @Test
    fun `when map resource dto with metal type then return Resource Metal model`() {
        val result = resourceMapper.map(resourceDto, ResourceType.METAL)
        assertEquals(
            Resource.Metal(
                id = "1",
                name = "name",
                symbol = "btc",
                logo = "logo.svg",
                price = 42.42
            ), result
        )
    }

    @Test
    fun `when map resource dto with fiat type then return Resource Fiat model`() {
        val result = resourceMapper.map(resourceDto, ResourceType.FIAT)
        assertEquals(
            Resource.Fiat(
                id = "1",
                name = "name",
                symbol = "btc",
                logo = "logo.svg"
            ), result
        )
    }
}