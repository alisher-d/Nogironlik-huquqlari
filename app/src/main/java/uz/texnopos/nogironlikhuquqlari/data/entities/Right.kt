package uz.texnopos.nogironlikhuquqlari.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "huquqlar")
data class Right(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="title") var title: String,
    @ColumnInfo(name="text") var text: String
)