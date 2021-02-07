package com.example.roomexample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


//sistema de anotaciones para que seoa que es la entidad se pone @Entity y se pone nombre de la tabla
//task_table
//

//Se incluye el @Entity y en parentesis esta la tabla
@Entity(tableName = "task_table")
data class TaskEntity(
    //@Primarykey @NotNull tambien la incluye uno.  Auto generate true para que la PK sea autoincrementable
    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Int = 0,
    val title: String,
    val description:String,
    val author: String )
