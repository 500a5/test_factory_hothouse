package soft.divan.test_factory_hothouse.data.local.source.impl

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import soft.divan.test_factory_hothouse.R
import soft.divan.world.words.data.local.source.LanguageDataSource

import soft.divan.test_factory_hothouse.domain.model.Language
import soft.divan.test_factory_hothouse.domain.utils.Reazon
import soft.divan.test_factory_hothouse.domain.utils.Rezult
import soft.divan.test_factory_hothouse.domain.utils.safeResult

import java.io.InputStream

class LanguageDataSourceImpl(
    private val context: Context
) : LanguageDataSource {

    companion object {
        private val Context.dataStoreLanguage by preferencesDataStore("language_preferences")
        private val LANGUAGE_TITLE_LAST_SAVED_KEY = stringPreferencesKey("language_title_last_saved")

    }

    private val dataStoreLanguage = context.dataStoreLanguage

    private suspend fun parseLanguages(xmlResId: Int): Rezult<List<Language>> {

        return safeResult {
            val languages = mutableListOf<Language>()
            val inputStream: InputStream = context.resources.openRawResource(xmlResId)
            val parser: XmlPullParser = XmlPullParserFactory.newInstance().newPullParser()
            parser.setInput(inputStream, null)

            var eventType = parser.eventType
            var currentLanguage: Language? = null
            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        if (parser.name == "language") {
                            val flag = parser.getAttributeValue(null, "flag")
                            val nativeTitle = parser.getAttributeValue(null, "nativeTitle")
                            val title = parser.getAttributeValue(null, "title")
                            currentLanguage = Language(flag, title, nativeTitle)
                        }
                    }

                    XmlPullParser.END_TAG -> {
                        if (parser.name == "language" && currentLanguage != null) {
                            languages.add(currentLanguage)
                            currentLanguage = null
                        }
                    }
                }
                eventType = parser.next()
            }
            inputStream.close() // закрываем поток

            languages
        }
    }


    override suspend fun getPopularLanguages(): Rezult<List<Language>> {
        return parseLanguages(R.raw.popular_languages)
    }

    override suspend fun getAllLanguages(): Rezult<List<Language>> {
        return parseLanguages(R.raw.all_languages)
    }

    override suspend fun getLastSavedLanguageTitle(): Rezult<String?> {
        return safeResult {
            dataStoreLanguage.data.map { preferences -> preferences[LANGUAGE_TITLE_LAST_SAVED_KEY] }
                .first()
        }
    }

    override suspend fun saveLastSavedLanguageTitle(title: String): Rezult<Unit> {
        return when (safeResult {
            dataStoreLanguage.edit { preferences ->
                preferences[LANGUAGE_TITLE_LAST_SAVED_KEY] = title
            }
        }) {
            is Rezult.Error -> Rezult.Error(Reazon.DATABASE_ERROR)
            is Rezult.Success -> Rezult.Success(Unit)
        }
    }
}
