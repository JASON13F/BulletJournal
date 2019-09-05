package com.example.bulletjournal.database

import android.content.Context
import androidx.room.*
import com.example.bulletjournal.database.dao.WordDao
import com.example.bulletjournal.enums.Bullet
import com.example.bulletjournal.enums.Word
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

@Database(entities = [Word::class], version = 1)
@TypeConverters(
    OffsetDateTimeConverter::class,
    BulletConverter::class
)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        fun getDatabase(context: Context): WordRoomDatabase =
            synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext, WordRoomDatabase::class.java, "Word_database"
                ).fallbackToDestructiveMigration().build()
            }
    }
}

object OffsetDateTimeConverter {

    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun toOffsetDateTime(value: String?) = value?.let { formatter.parse(it, OffsetDateTime::from) }

    @TypeConverter
    @JvmStatic
    fun fromOffsetDateTime(dateTime: OffsetDateTime?) = dateTime?.format(formatter)
}

object BulletConverter {

    @TypeConverter
    @JvmStatic
    fun toBullet(value: Int?) = value?.let { Bullet.values()[value] }

    @TypeConverter
    @JvmStatic
    fun fromBullet(bullet: Bullet?) = bullet?.ordinal
}
