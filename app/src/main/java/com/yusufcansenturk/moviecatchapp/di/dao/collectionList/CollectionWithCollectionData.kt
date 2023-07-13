package com.yusufcansenturk.moviecatchapp.di.dao.collectionList

import androidx.room.Embedded
import androidx.room.Relation

data class CollectionWithCollectionData(
    @Embedded val collection: Collection,
    @Relation(
        parentColumn = "collectionName",
        entityColumn = "collectionName"
    )
    val collectionDataList: List<CollectionData>
)
