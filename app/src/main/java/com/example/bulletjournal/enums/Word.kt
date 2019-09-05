package com.example.bulletjournal.enums

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bulletjournal.R
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "work_table")
data class Word(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val offsetDateTime: OffsetDateTime = OffsetDateTime.now(),
    val text: String,
    val bullet: Bullet,
    val state: Boolean = false
)

enum class Bullet {

    TASK,
    MEMO,
    EVENT
}

fun Bullet.getDrawable(state: Boolean) = when (this) {
    Bullet.TASK -> if (state) R.drawable.ic_check_box_black_24dp else R.drawable.ic_check_box_outline_blank_black_24dp
    Bullet.MEMO -> if (state) R.drawable.ic_border_color_black_24dp else R.drawable.ic_create_black_24dp
    Bullet.EVENT -> if (state) R.drawable.ic_event_available_black_24dp else R.drawable.ic_event_black_24dp
}
