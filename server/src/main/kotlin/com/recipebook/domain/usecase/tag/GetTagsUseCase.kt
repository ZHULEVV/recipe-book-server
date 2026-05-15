package com.recipebook.domain.usecase.tag

import com.recipebook.domain.entity.Tag
import com.recipebook.domain.repository.TagRepository

class GetTagsUseCase(private val tagRepository: TagRepository) {
    suspend operator fun invoke(): List<Tag> =
        tagRepository.findAll()
}
