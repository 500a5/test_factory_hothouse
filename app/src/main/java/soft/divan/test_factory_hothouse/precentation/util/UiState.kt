package soft.divan.test_factory_hothouse.precentation.util

sealed  class  UiState < out T > {
    data object Loading : UiState< Nothing >()
    data  class  Success < T >( val  data : T) : UiState<T>()
    data  class  Error ( val message: String) : UiState< Nothing >()
    data object Init : UiState<Nothing>()
}