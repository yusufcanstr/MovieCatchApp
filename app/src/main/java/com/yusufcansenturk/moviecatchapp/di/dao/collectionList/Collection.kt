package com.yusufcansenturk.moviecatchapp.di.dao.collectionList

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yusufcansenturk.moviecatchapp.util.Constants.COLLECTION

@Entity(tableName = COLLECTION)
data class Collection(
    @PrimaryKey(autoGenerate = false) val collectionName:String
)
