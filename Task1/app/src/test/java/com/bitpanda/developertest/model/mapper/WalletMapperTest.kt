package com.bitpanda.developertest.model.mapper

import com.bitpanda.developertest.model.Resource
import com.bitpanda.developertest.model.ResourceType
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.model.dto.ResourceDto
import com.bitpanda.developertest.model.dto.WalletDto
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertEquals
import org.junit.Test

class WalletMapperTest {

    private val resourceDto = mock<ResourceDto>()
    private val resource = mock<Resource>()
    private val resourceMapper = mock<ResourceMapper> {
        on { map(any(), any()) } doReturn resource
    }

    private val walletMapper = WalletMapper(resourceMapper)

    private val walletDto = WalletDto(
        id = "1",
        name = "name",
        isDefault = false,
        balance = 42.42,
        deleted = false,
        resourceId = "4"
    )

    @Test
    fun `when map wallet dto then return Wallet model`() {
        val result = walletMapper.map(walletDto, resourceDto, ResourceType.CRYPTOCOIN)
        assertEquals(
            Wallet(
                id = "1",
                name = "name",
                isDefault = false,
                balance = 42.42,
                deleted = false,
                resource = resource
            ), result
        )

        verify(resourceMapper).map(resourceDto, ResourceType.CRYPTOCOIN)
        verifyNoMoreInteractions(resourceMapper)
    }
}