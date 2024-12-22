package com.example.batch

import org.slf4j.LoggerFactory
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong
import java.util.stream.LongStream
import java.util.zip.GZIPOutputStream
import kotlin.random.Random

internal fun <T> Number.mapParallel(
    block: () -> T,
) =
    LongStream.range(0, this.toLong())
        .parallel()
        .mapToObj {
            block()
        }
        .toList()

data class Member(
    val memberId: Long,
    val deleted: Boolean,
    val deletedAt: LocalDateTime
)

internal object MemberFactory {
    private val counter = AtomicLong(1)

    fun generateRandom(): Member {
        return Member(
            memberId = counter.getAndIncrement(), // 순차적인 memberId 생성
            deleted = Random.nextBoolean(),
            deletedAt = LocalDateTime.of(
                Random.nextInt(2020, 2023),
                Random.nextInt(1, 13),
                Random.nextInt(1, 29),
                Random.nextInt(0, 24),
                Random.nextInt(0, 60),
                Random.nextInt(0, 60),
            )
        )
    }
}

data class Image(
    val imageId: Long,
    val memberId: Long,
    val imageUrl: String,
    val deleted: Boolean = false,
    val deletedAt: LocalDateTime? = null
)

internal object ImageFactory {
    private val counter = AtomicLong(1)
    private const val BASE_URL = "https://example.com/images/"

    fun generateImage(): Image {
        return Image(
            imageId = counter.getAndIncrement(), // 순차적인 imageId 생성,
            memberId = Random.nextLong(1, 31),
            imageUrl = "$BASE_URL${counter.getAndIncrement()}.jpg",
        )
    }
}

fun main() {
    val log = LoggerFactory.getLogger("Main")
    val totalRecords = 1_000_000L
    val images = totalRecords.mapParallel(ImageFactory::generateImage)

    GZIPOutputStream(FileOutputStream("images.csv.gz")).bufferedWriter().use { writer ->
        images.forEachIndexed { index, image ->
            writer.write(image.toString() + "\n")
            if ((index + 1) % 10_000 == 0) {
                log.info("{} records written so far.", index + 1)
            }
        }
    }

    log.info("All records have been successfully written and compressed!")
//    val log = LoggerFactory.getLogger("Main")
//    val totalRecords = 30
//    val members = totalRecords.mapParallel(MemberFactory::generateRandom)
//
//    GZIPOutputStream(FileOutputStream("members.csv.gz")).bufferedWriter().use { writer ->
//        members.forEachIndexed { index, member ->
//            writer.write(member.toString() + "\n")
//            if ((index + 1) % 10 == 0) {
//                log.info("{} records written so far.", index + 1)
//            }
//        }
//    }
//
//    log.info("All records have been successfully written and compressed!")
}

