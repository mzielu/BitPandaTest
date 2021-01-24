package com.bitpanda.developertest.model.mapper

import com.bitpanda.developertest.model.Resource
import com.bitpanda.developertest.model.ResourceType
import com.bitpanda.developertest.model.dto.ResourceDto
import javax.inject.Inject

class ResourceMapper @Inject constructor() {
    fun map(resource: ResourceDto, type: ResourceType): Resource {
        return when (type) {
            ResourceType.CRYPTOCOIN -> {
                Resource.Cryptocoin(
                    id = resource.id,
                    name = resource.name,
                    logo = resource.logo,
                    symbol = resource.symbol,
                    price = resource.price ?: 0.0
                )
            }
            ResourceType.METAL -> {
                Resource.Metal(
                    id = resource.id,
                    name = resource.name,
                    logo = resource.logo,
                    symbol = resource.symbol,
                    price = resource.price ?: 0.0
                )
            }
            ResourceType.FIAT -> {
                Resource.Fiat(
                    id = resource.id,
                    name = resource.name,
                    logo = resource.logo,
                    symbol = resource.symbol
                )
            }
        }
    }
}