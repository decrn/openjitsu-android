package com.openjitsu.android.openjitsu.di

import android.app.Application
import com.openjitsu.android.openjitsu.data.repositories.CommentRepository
import com.openjitsu.android.openjitsu.data.repositories.PositionRepository
import com.openjitsu.android.openjitsu.data.repositories.Repository
import com.openjitsu.android.openjitsu.data.repositories.SubmissionRepository
import com.openjitsu.android.openjitsu.di.modules.*
import com.openjitsu.android.openjitsu.ui.explore.ExploreDetailActivity
import com.openjitsu.android.openjitsu.ui.explore.ExploreItemRecyclerViewAdapter
import com.openjitsu.android.openjitsu.ui.explore.ExploreListActivity
import com.openjitsu.android.openjitsu.ui.explore.ProfileFragment
import com.openjitsu.android.openjitsu.ui.explore.detail.CommentFragment
import com.openjitsu.android.openjitsu.ui.explore.detail.CommentRecyclerViewAdapter
import com.openjitsu.android.openjitsu.ui.explore.detail.ExploreDetailFragment
import com.openjitsu.android.openjitsu.ui.user.LoginFragment
import com.openjitsu.android.openjitsu.ui.user.RegisterFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class, AppModule::class, DataModule::class])
interface AppComponent {

    fun inject(exploreItemRecyclerViewAdapter: ExploreItemRecyclerViewAdapter)

    fun inject(commentRecyclerViewAdapter: CommentRecyclerViewAdapter)

    fun inject(exploreDetailFragment: ExploreDetailFragment)

    fun inject(loginFragment: LoginFragment)

    fun inject(registerFragment: RegisterFragment)

    fun inject(profileFragment: ProfileFragment)

    fun inject(commentFragment: CommentFragment)

    fun inject(exploreListActivity: ExploreListActivity)

    fun inject(exploreDetailActivity: ExploreDetailActivity)

    fun inject(positionRepository: PositionRepository)

    fun inject(submissionRepository: SubmissionRepository)

    fun inject(commentRepository: CommentRepository)

    companion object Factory {
        fun create(app: Application, baseUrl: String): AppComponent {
            return DaggerAppComponent
                    .builder()
                    .appModule(AppModule(app))
                    .netModule(NetModule(baseUrl))
                    .dataModule(DataModule())
                    .build()
        }
    }
}
