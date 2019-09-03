package com.example.bulletjournal.enums

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bulletjournal.R
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "work_table")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dateText: String =
        SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN).format(Calendar.getInstance().time),
    val text: String,
    val bulletId: Int,
    val state: Boolean = false
)

enum class Bullet(val id: Int, val text: String) {
    TASK(1, "Task"),
    MEMO(2, "Memo"),
    EVENT(3, "Event");

    companion object {
        fun getBullet(id: Int) = when (id) {
            1 -> TASK
            2 -> MEMO
            3 -> EVENT
            else -> null
        }
    }
}

fun Bullet.drawable(state: Boolean) = when (this) {
    Bullet.TASK -> if (state) R.drawable.ic_check_box_black_24dp else R.drawable.ic_check_box_outline_blank_black_24dp
    Bullet.MEMO -> if (state) R.drawable.ic_border_color_black_24dp else R.drawable.ic_create_black_24dp
    Bullet.EVENT -> if (state) R.drawable.ic_event_available_black_24dp else R.drawable.ic_event_black_24dp
}
