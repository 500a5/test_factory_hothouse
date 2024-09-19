package soft.divan.test_factory_hothouse.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import soft.divan.test_factory_hothouse.precentation.AuthorizationViewModel


val presentationModule = module {
    viewModel{ AuthorizationViewModel(get()) }

}