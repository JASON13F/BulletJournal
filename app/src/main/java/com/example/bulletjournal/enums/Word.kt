package com.example.bulletjournal.enums

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.bulletjournal.R
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

@Entity(tableName = "work_table")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val offsetDateTime: OffsetDateTime = OffsetDateTime.now(),
    val text: String,
    val bulletId: Int,
    val state: Boolean = false
)

enum class Bullet(val id: Int, val text: String) {
    TASK(1, "Task"),
    MEMO(2, "Memo"),
    EVENT(3, "Event");

    fun getDrawable(state: Boolean) = when (this) {
        TASK -> if (state) R.drawable.ic_check_box_black_24dp else R.drawable.ic_check_box_outline_blank_black_24dp
        MEMO -> if (state) R.drawable.ic_border_color_black_24dp else R.drawable.ic_create_black_24dp
        EVENT -> if (state) R.drawable.ic_event_available_black_24dp else R.drawable.ic_event_black_24dp
    }

    companion object {
        fun getBullet(id: Int) = when (id) {
            1 -> TASK
            2 -> MEMO
            3 -> EVENT
            else -> null
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
