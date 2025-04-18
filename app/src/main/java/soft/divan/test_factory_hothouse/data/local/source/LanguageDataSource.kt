package soft.divan.world.words.data.local.source

import soft.divan.test_factory_hothouse.domain.model.Language
import soft.divan.world.words.domain.utils.Rezult

interface LanguageDataSource {
    suspend  fun getPopularLanguages(): Rezult<List<Language>>
    suspend fun getAllLanguages(): Rezult<List<Language>>
    suspend fun getLastSavedLanguageTitle(): Rezult<String?>
    suspend fun saveLastSavedLanguageTitle(title: String):Rezult<Unit>
}