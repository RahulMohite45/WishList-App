package com.example.mywishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    @ColumnInfo(name="wish-title")
    val title : String = "",
    @ColumnInfo(name="wish-desc")
    val description : String = "",
)

object DummyWish{
    val wishList = listOf(
        Wish(title = "Google watch ", description = "An android watch which is cheap "),

        Wish(title = "Boat watch ",
            description = "An android watch which is high "),

        Wish(title = "Noise watch ",
            description = "An android watch which is medium rate "),

    )

}
