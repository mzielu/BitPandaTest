package com.bitpanda.developertest.service

import androidx.navigation.NavController
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

typealias NavigationCommand = (NavController) -> Unit

@ActivityRetainedScoped
class NavigatorService @Inject constructor() {
    private val navigationCommandSubject = PublishSubject.create<NavigationCommand>()

    fun observeNavigationEmissions(): Observable<NavigationCommand> {
        return navigationCommandSubject
    }

    fun emit(navigationCommand: NavigationCommand) {
        navigationCommandSubject.onNext(navigationCommand)
    }
}