package co.asinfotech.mymoneymanager.hilt

import android.content.Context
import android.os.Build
import androidx.multidex.BuildConfig
import androidx.room.Room
import co.asinfotech.mymoneymanager.config.Config
import co.asinfotech.mymoneymanager.database.AppDatabase
import co.asinfotech.mymoneymanager.database.dao.DBExpenseDao
import co.asinfotech.mymoneymanager.network.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitService(): ApiService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(120, TimeUnit.SECONDS)
        builder.callTimeout(120, TimeUnit.SECONDS)
        builder.readTimeout(120, TimeUnit.SECONDS)
        builder.addInterceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val newRequest = original.newBuilder()
                .header(
                    "User-Agent",
                    "/" + BuildConfig.VERSION_NAME
                )
                .addHeader("Application", BuildConfig.APPLICATION_ID)
                .addHeader("Version", BuildConfig.VERSION_NAME)
                .addHeader("Device", Build.BRAND + " " + Build.MODEL)
                .build()
            chain.proceed(newRequest)
        }.addInterceptor(logging)
        val client: OkHttpClient = builder.build()
        return Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideDatabaseInstance(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "MM_DATABASE"
        ).fallbackToDestructiveMigration().build()

    }

    @Provides
    @Singleton
    fun provideTransactionDao(appDatabase: AppDatabase): DBExpenseDao {
        return appDatabase.expensesDao()
    }
}