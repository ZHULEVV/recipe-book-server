package com.recipebook.domain.exception

sealed class DomainException(message: String) : Exception(message)

class NotFoundException(entity: String, id: Any) : DomainException("$entity not found: $id")
class ForbiddenException(message: String = "Access denied") : DomainException(message)
class ConflictException(message: String) : DomainException(message)
class ValidationException(message: String) : DomainException(message)
