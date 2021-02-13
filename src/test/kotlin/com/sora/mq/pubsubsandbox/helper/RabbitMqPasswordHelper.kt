package com.sora.mq.pubsubsandbox.helper

import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*

class RabbitMqPasswordHelper {

    companion object {
        val log = org.slf4j.LoggerFactory.getLogger(this::class.java.enclosingClass)!!
    }

    /**
     * Generates a salted SHA-256 hash of a given password.
     */
    @Test
    fun getPasswordHash() {
        val password = "test" // please input password text to here
        val salt = getSalt()
        val saltedPassword =
            concatenateByteArray(salt!!, password.toByteArray(StandardCharsets.UTF_8))
        val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
        val hash: ByteArray = digest.digest(saltedPassword)
        val generated = Base64.getEncoder().encodeToString(concatenateByteArray(salt, hash))

        log.info("generated: $generated")
    }

    /**
     * Generates a 32 bit random salt.
     */
    private fun getSalt(): ByteArray? {
        val ba = ByteArray(4)
        SecureRandom().nextBytes(ba)
        return ba
    }

    /**
     * Concatenates two byte arrays.
     */
    private fun concatenateByteArray(a: ByteArray, b: ByteArray): ByteArray? {
        val lenA = a.size
        val lenB = b.size
        val c: ByteArray = Arrays.copyOf(a, lenA + lenB)
        System.arraycopy(b, 0, c, lenA, lenB)
        return c
    }
}