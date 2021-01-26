package com.bitpanda.developertest.repository

import com.bitpanda.developertest.model.ResourceType
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.model.dto.ResourceDto
import com.bitpanda.developertest.model.dto.WalletDto
import com.bitpanda.developertest.model.mapper.WalletMapper
import com.bitpanda.developertest.remote.WebService
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.assertEquals
import org.junit.Test

class RepositoryTest {
    private fun createWallet(id: String): WalletDto {
        return WalletDto(
            id = id,
            name = "Test BTC Wallet",
            balance = 123.213,
            isDefault = false,
            deleted = false,
            resourceId = id
        )
    }

    private fun createResource(resourceId: String): ResourceDto {
        return ResourceDto(
            id = resourceId,
            name = "name",
            symbol = "btc",
            logo = "logo.svg",
            price = 42.42
        )
    }

    private val walletDto1 = createWallet("1")
    private val walletDto2 = createWallet("2")
    private val walletDto3 = createWallet("3")
    private val walletDto4 = createWallet("4")
    private val walletDto5 = createWallet("5")
    private val walletDto6 = createWallet("6")
    private val walletDto7 = createWallet("7")
    private val walletDto8 = createWallet("8")

    private val resourceDto1 = createResource("1")
    private val resourceDto2 = createResource("2")
    private val resourceDto3 = createResource("3")
    private val resourceDto4 = createResource("4")
    private val resourceDto5 = createResource("5")
    private val resourceDto6 = createResource("6")
    private val resourceDto7 = createResource("7")
    private val resourceDto8 = createResource("8")

    private val wallet1 = mock<Wallet> {
        on { balance } doReturn 123.123
    }
    private val wallet2 = mock<Wallet> {
        on { balance } doReturn 12.123
    }
    private val wallet3 = mock<Wallet> {
        on { balance } doReturn 1231.123
    }
    private val wallet4 = mock<Wallet> {
        on { balance } doReturn 12344.123
    }
    private val wallet5 = mock<Wallet> {
        on { balance } doReturn 1233.123
    }
    private val wallet6 = mock<Wallet> {
        on { balance } doReturn 1234.123
    }
    private val wallet7 = mock<Wallet> {
        on { balance } doReturn 12.123
    }
    private val wallet8 = mock<Wallet> {
        on { balance } doReturn 12365.123
    }

    private val webService = mock<WebService> {
        on { getCryptoWallets() } doReturn listOf(walletDto1, walletDto2, walletDto3)
        on { getMetalWallets() } doReturn listOf(walletDto4, walletDto5, walletDto6)
        on { getFiatWallets() } doReturn listOf(walletDto7, walletDto8)
        on { getCryptocoins() } doReturn listOf(resourceDto1, resourceDto2, resourceDto3)
        on { getMetals() } doReturn listOf(resourceDto4, resourceDto5, resourceDto6)
        on { getFiats() } doReturn listOf(resourceDto7, resourceDto8)
    }

    private val walletMapper = mock<WalletMapper> {
        on { map(walletDto1, resourceDto1, ResourceType.CRYPTOCOIN) } doReturn wallet1
        on { map(walletDto2, resourceDto2, ResourceType.CRYPTOCOIN) } doReturn wallet2
        on { map(walletDto3, resourceDto3, ResourceType.CRYPTOCOIN) } doReturn wallet3
        on { map(walletDto4, resourceDto4, ResourceType.METAL) } doReturn wallet4
        on { map(walletDto5, resourceDto5, ResourceType.METAL) } doReturn wallet5
        on { map(walletDto6, resourceDto6, ResourceType.METAL) } doReturn wallet6
        on { map(walletDto7, resourceDto7, ResourceType.FIAT) } doReturn wallet7
        on { map(walletDto8, resourceDto8, ResourceType.FIAT) } doReturn wallet8
    }

    private val repository = RepositoryImpl(webService, walletMapper)

    @Test
    fun `when filter by fiat type then return only fiat wallets in sorted order`() {
        val result = repository.getWallets(ResourceType.FIAT)

        assertEquals(listOf(wallet8, wallet7), result)

        verify(webService).getFiatWallets()
        verify(webService).getFiats()
        verifyNoMoreInteractions(webService)
        verify(walletMapper).map(walletDto7, resourceDto7, ResourceType.FIAT)
        verify(walletMapper).map(walletDto8, resourceDto8, ResourceType.FIAT)
        verifyNoMoreInteractions(walletMapper)
    }

    @Test
    fun `when filter by metal type then return only metal wallets in sorted order`() {
        val result = repository.getWallets(ResourceType.METAL)

        assertEquals(listOf(wallet4, wallet6, wallet5), result)

        verify(webService).getMetalWallets()
        verify(webService).getMetals()
        verifyNoMoreInteractions(webService)
        verify(walletMapper).map(walletDto4, resourceDto4, ResourceType.METAL)
        verify(walletMapper).map(walletDto5, resourceDto5, ResourceType.METAL)
        verify(walletMapper).map(walletDto6, resourceDto6, ResourceType.METAL)
        verifyNoMoreInteractions(walletMapper)
    }

    @Test
    fun `when filter by crypto type then return only crypto wallets in sorted order`() {
        val result = repository.getWallets(ResourceType.CRYPTOCOIN)

        assertEquals(listOf(wallet3, wallet1, wallet2), result)

        verify(webService).getCryptoWallets()
        verify(webService).getCryptocoins()
        verifyNoMoreInteractions(webService)
        verify(walletMapper).map(walletDto1, resourceDto1, ResourceType.CRYPTOCOIN)
        verify(walletMapper).map(walletDto2, resourceDto2, ResourceType.CRYPTOCOIN)
        verify(walletMapper).map(walletDto3, resourceDto3, ResourceType.CRYPTOCOIN)
        verifyNoMoreInteractions(walletMapper)
    }

    @Test
    fun `when filter by all types then return all wallets in sorted order`() {
        val result = repository.getWallets(*ResourceType.values())

        assertEquals(
            listOf(wallet8, wallet7, wallet3, wallet1, wallet2, wallet4, wallet6, wallet5),
            result
        )

        verify(webService).getCryptoWallets()
        verify(webService).getCryptocoins()
        verify(webService).getMetalWallets()
        verify(webService).getMetals()
        verify(webService).getFiatWallets()
        verify(webService).getFiats()
        verifyNoMoreInteractions(webService)
        verify(walletMapper).map(walletDto1, resourceDto1, ResourceType.CRYPTOCOIN)
        verify(walletMapper).map(walletDto2, resourceDto2, ResourceType.CRYPTOCOIN)
        verify(walletMapper).map(walletDto3, resourceDto3, ResourceType.CRYPTOCOIN)
        verify(walletMapper).map(walletDto4, resourceDto4, ResourceType.METAL)
        verify(walletMapper).map(walletDto5, resourceDto5, ResourceType.METAL)
        verify(walletMapper).map(walletDto6, resourceDto6, ResourceType.METAL)
        verify(walletMapper).map(walletDto7, resourceDto7, ResourceType.FIAT)
        verify(walletMapper).map(walletDto8, resourceDto8, ResourceType.FIAT)
        verifyNoMoreInteractions(walletMapper)
    }
}