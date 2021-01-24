package com.bitpanda.developertest.model.mapper

import com.bitpanda.developertest.model.Resource
import com.bitpanda.developertest.model.ResourceType
import com.bitpanda.developertest.model.dto.ResourceDto
import javax.inject.Inject

class ResourceMapper @Inject constructor() {
    fun map(resource: ResourceDto, type: ResourceType): Resource {
        return with(resource) {
            when (type) {
                ResourceType.CRYPTOCOIN -> {
                    Resource.Cryptocoin(
                        id = id,
                        name = name,
                        logo = logo,
                        symbol = symbol,
                        price = price ?: 0.0
                    )
                }
                ResourceType.METAL -> {
                    Resource.Metal(
                        id = id,
                        name = name,
                        logo = logo,
                        symbol = symbol,
                        price = price ?: 0.0
                    )
                }
                ResourceType.FIAT -> {
                    Resource.Fiat(
                        id = id,
                        name = name,
                        logo = logo,
                        symbol = symbol
                    )
                }
            }
        }
    }
}