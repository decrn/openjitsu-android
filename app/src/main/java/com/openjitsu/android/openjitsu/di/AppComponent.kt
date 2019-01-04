package com.openjitsu.android.openjitsu.di

import com.openjitsu.android.openjitsu.di.modules.ApiModule
import com.openjitsu.android.openjitsu.di.modules.NetModule
import com.openjitsu.android.openjitsu.ui.explore.ExploreDetailActivity
import com.openjitsu.android.openjitsu.ui.explore.ExploreItemRecyclerViewAdapter
import com.openjitsu.android.openjitsu.ui.explore.ExploreListActivity
import com.openjitsu.android.openjitsu.ui.explore.ProfileFragment
import com.openjitsu.android.openjitsu.ui.explore.detail.ExploreDetailFragment
import com.openjitsu.android.openjitsu.ui.user.LoginFragment
import com.openjitsu.android.openjitsu.ui.user.RegisterFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, NetModule::class])
interface AppComponent {

    fun inject(exploreItemRecyclerViewAdapter: ExploreItemRecyclerViewAdapter)

    fun inject(exploreDetailFragment: ExploreDetailFragment)

    fun inject(loginFragment: LoginFragment)

    fun inject(registerFragment: RegisterFragment)

    fun inject(profileFragment: ProfileFragment)

    fun inject(exploreListActivity: ExploreListActivity)

    fun inject(exploreDetailActivity: ExploreDetailActivity)

    companion object Factory {
        fun create(baseUrl: String): AppComponent {
            return DaggerAppComponent.builder().apiModule(ApiModule()).netModule(NetModule(baseUrl)).build()
        }
    }
}
