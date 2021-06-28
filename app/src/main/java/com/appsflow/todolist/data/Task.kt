package com.appsflow.todolist.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat

@Entity(tableName = "task_table")
@Parcelize
data class Task(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                val title: String = "Example task",
                val isPriority: Boolean = false,
                val isCompleted: Boolean = false,
                val created: Long = System.currentTimeMillis()
) :Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }
}