package com.recipebook.data

import com.recipebook.data.repository.UserRepositoryImpl
import com.recipebook.util.TestDatabase
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class UserRepositoryTest {

    private val repository = UserRepositoryImpl()

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = TestDatabase.init()

        @JvmStatic
        @AfterAll
        fun teardown() = TestDatabase.clean()
    }

    @Test
    @Order(1)
    fun `upsert creates new user when not exists`() = runTest {
        val user = repository.upsert("uid_001", "test@example.com", "Test User")

        assertEquals("uid_001", user.firebaseUid)
        assertEquals("test@example.com", user.email)
        assertEquals("Test User", user.displayName)
        assertFalse(user.isSubscriber)
    }

    @Test
    @Order(2)
    fun `upsert updates email when user already exists`() = runTest {
        val updated = repository.upsert("uid_001", "updated@example.com", null)

        assertEquals("uid_001", updated.firebaseUid)
        assertEquals("updated@example.com", updated.email)
    }

    @Test
    @Order(3)
    fun `findByFirebaseUid returns user when exists`() = runTest {
        val user = repository.findByFirebaseUid("uid_001")

        assertNotNull(user)
        assertEquals("uid_001", user!!.firebaseUid)
    }

    @Test
    @Order(4)
    fun `findByFirebaseUid returns null when not exists`() = runTest {
        val user = repository.findByFirebaseUid("nonexistent_uid")

        assertNull(user)
    }

    @Test
    @Order(5)
    fun `findById returns user when exists`() = runTest {
        val created = repository.upsert("uid_002", "other@example.com", null)
        val found = repository.findById(created.id)

        assertNotNull(found)
        assertEquals(created.id, found!!.id)
    }

    @Test
    @Order(6)
    fun `update changes displayName`() = runTest {
        val user = repository.upsert("uid_003", "u3@example.com", "Old Name")
        val updated = repository.update(user.id, "New Name")

        assertEquals("New Name", updated.displayName)
    }
}
