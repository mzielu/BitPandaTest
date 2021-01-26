package com.bitpanda.developertest.ui.main.detail

import android.view.View
import com.bitpanda.developertest.model.Resource
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.ui.main.details.DetailsNavigator
import com.bitpanda.developertest.ui.main.details.DetailsViewModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertEquals
import org.junit.Test

class DetailsViewModelTest {
    private val detailsNavigator = mock<DetailsNavigator>()
    private val detailsViewModel = DetailsViewModel(detailsNavigator)

    private val fiatResource = mock<Resource.Fiat> {
        on { precision } doReturn 2
        on { price } doReturn null
    }
    private val metalResource = mock<Resource.Metal> {
        on { price } doReturn 12.3213123
        on { precision } doReturn 3
    }
    private val cryptoResource = mock<Resource.Cryptocoin> {
        on { price } doReturn 0.213123
        on { precision } doReturn 4
    }

    private val fiatWallet = mock<Wallet> {
        on { resource } doReturn fiatResource
        on { balance } doReturn 123.456734
    }

    private val metalWallet = mock<Wallet> {
        on { resource } doReturn metalResource
        on { balance } doReturn 234.345645
    }

    private val cryptoWallet = mock<Wallet> {
        on { resource } doReturn cryptoResource
        on { balance } doReturn 7643.1235423
    }

    @Test
    fun `when different types of resources then verify balance in euro visibility`() {
        with(detailsViewModel) {
            assertEquals(View.GONE, balanceEuroVisible(fiatWallet))
            assertEquals(View.VISIBLE, balanceEuroVisible(metalWallet))
            assertEquals(View.VISIBLE, balanceEuroVisible(cryptoWallet))
        }

        verifyZeroInteractions(detailsNavigator)
    }

    @Test
    fun `when different types of resources then verify balance value`() {
        with(detailsViewModel) {
            assertEquals("123.46", convertForBalance(fiatWallet))
            assertEquals("234.346", convertForBalance(metalWallet))
            assertEquals("7643.1235", convertForBalance(cryptoWallet))
        }

        verifyZeroInteractions(detailsNavigator)
    }

    @Test
    fun `when different types of resources then verify balance in euro value`() {
        with(detailsViewModel) {
            assertEquals("", convertForBalanceInEuro(fiatWallet))
            assertEquals("2887.446 €", convertForBalanceInEuro(metalWallet))
            assertEquals("1628.9254 €", convertForBalanceInEuro(cryptoWallet))
        }

        verifyZeroInteractions(detailsNavigator)
    }

    @Test
    fun `when go back then verify appropriate function executed`() {
        detailsViewModel.backPressed()

        verify(detailsNavigator).goBack()
        verifyNoMoreInteractions(detailsNavigator)
    }
}