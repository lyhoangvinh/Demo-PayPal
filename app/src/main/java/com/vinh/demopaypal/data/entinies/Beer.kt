package com.vinh.demopaypal.data.entinies

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Beer(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    var name: String? = "",

    var urlPreview: String? = "",

    var price: Double? = 0.0
)