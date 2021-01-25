package com.bitpanda.developertest.ui.main.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bitpanda.developertest.model.FilterType
import com.bitpanda.developertest.model.Resource
import com.bitpanda.developertest.model.ResourceType
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.repository.Repository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ListViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val wallet1 = Wallet(
        id = "1",
        name = "name",
        isDefault = false,
        balance = 42.42,
        deleted = false,
        resource = Resource.Cryptocoin(
            id = "1",
            name = "name",
            symbol = "btc",
            logo = "logo.svg",
            price = 42.42
        )
    )

    private val wallet2 = wallet1.copy(id = "2")
    private val wallet3 = wallet1.copy(id = "3", deleted = true)
    private val wallet4 = wallet1.copy(id = "4")
    private val wallet5 = wallet1.copy(id = "5")
    private val wallet6 = wallet1.copy(id = "6", deleted = true)

    private val repository = mock<Repository> {
        on { getWallets(*ResourceType.values()) } doReturn listOf(
            wallet1,
            wallet2,
            wallet3,
            wallet4,
            wallet5,
            wallet6
        )

        on { getWallets(ResourceType.CRYPTOCOIN) } doReturn listOf(wallet1, wallet2, wallet6)
        on { getWallets(ResourceType.METAL) } doReturn listOf(wallet3, wallet4, wallet6)
        on { getWallets(ResourceType.FIAT) } doReturn listOf(wallet1, wallet2, wallet5, wallet6)
    }

    @Test
    fun `when get all types wallets then return appropriate list`() {
        val listViewModel = ListViewModel(repository)

        assertEquals(emptyList<Wallet>(), listViewModel.wallets.value)

        listViewModel.filterChangeAction(FilterType.ALL)

        assertEquals(listOf(wallet1, wallet2, wallet4, wallet5), listViewModel.wallets.value)
        verify(repository).getWallets(*ResourceType.values())
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `when get fiat type wallets then return appropriate list`() {
        val listViewModel = ListViewModel(repository)

        assertEquals(emptyList<Wallet>(), listViewModel.wallets.value)

        listViewModel.filterChangeAction(FilterType.FIAT)

        assertEquals(listOf(wallet1, wallet2, wallet5), listViewModel.wallets.value)
        verify(repository).getWallets(ResourceType.FIAT)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `when get metal type wallets then return appropriate list`() {
        val listViewModel = ListViewModel(repository)

        assertEquals(emptyList<Wallet>(), listViewModel.wallets.value)

        listViewModel.filterChangeAction(FilterType.METAL)

        assertEquals(listOf(wallet4), listViewModel.wallets.value)
        verify(repository).getWallets(ResourceType.METAL)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `when get crypto type wallets then return appropriate list`() {
        val listViewModel = ListViewModel(repository)

        assertEquals(emptyList<Wallet>(), listViewModel.wallets.value)

        listViewModel.filterChangeAction(FilterType.CRYPTO)

        assertEquals(listOf(wallet1, wallet2), listViewModel.wallets.value)
        verify(repository).getWallets(ResourceType.CRYPTOCOIN)
        verifyNoMoreInteractions(repository)
    }
}