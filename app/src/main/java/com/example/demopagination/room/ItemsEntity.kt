package com.example.demopagination.room

import androidx.room.PrimaryKey
import com.example.demopagination.model.Owner
import com.google.gson.annotations.SerializedName

data class ItemsEntity(@PrimaryKey @SerializedName("id"                          ) var id                       : Int?              = null,
                       @SerializedName("node_id"                     ) var nodeId                   : String?           = null,
                       @SerializedName("name"                        ) var name                     : String?           = null,
                       @SerializedName("full_name"                   ) var fullName                 : String?           = null,
                       @SerializedName("private"                     ) var private                  : Boolean?          = null,
                       @SerializedName("owner"                       ) var owner                    : Owner?            = null,
                       @SerializedName("html_url"                    ) var htmlUrl                  : String?           = null,
                       @SerializedName("description"                 ) var description              : String?           = null,)