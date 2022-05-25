package com.yousufsohail.alligitor.network.response

import com.google.gson.annotations.SerializedName
import com.yousufsohail.alligitor.network.model.RepositoryDto

class RepositorySearchResponse (

    @SerializedName("total_count")
    var totalCount: Long,

    @SerializedName("incomplete_results")
    var isResultIncomplete: Boolean,

    @SerializedName("items")
    var repositories: List<RepositoryDto>
)
