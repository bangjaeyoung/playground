package com.example.batch

import org.jeasy.random.EasyRandomParameters
import org.jeasy.random.api.Randomizer
import org.slf4j.LoggerFactory
import java.io.FileOutputStream
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.stream.LongStream
import java.util.zip.GZIPOutputStream
import kotlin.random.Random
import kotlin.reflect.KProperty

private val log = LoggerFactory.getLogger("PaymentBatch")

internal fun <T, R> EasyRandomParameters.randomize(
    property: KProperty<T>,
    randomizer: Randomizer<R>,
) =
    randomize(
        { it.name == property.name },
        randomizer,
    )

internal fun <T> Number.mapParallel(
    block: () -> T,
) =
    LongStream.range(0, this.toLong())
        .parallel()
        .mapToObj {
            block()
        }
        .toList()

data class Payment(
    val sellerId: Long,
    val productId: Long,
    val productName: String,
    val price: BigDecimal,
    val paymentDate: LocalDateTime,
)

internal object PaymentFactory {
    fun generateRandom(): Payment {
        val productId = Random.nextLong(1, 101)
        return Payment(
            sellerId = Random.nextLong(1, 31),
            productId = productId,
            productName = "item-$productId",
            price = Random.nextInt(100, 1_000_001).toBigDecimal(),
            paymentDate = LocalDateTime.of(
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

fun main() {
    val totalRecords = 1_000_000L
    val payments = totalRecords.mapParallel(PaymentFactory::generateRandom)

    GZIPOutputStream(FileOutputStream("payments.csv.gz")).bufferedWriter().use { writer ->
        payments.forEachIndexed { index, payment ->
            writer.write(payment.toString() + "\n")
            if ((index + 1) % 100_000 == 0) {
                log.info("{} records written so far.", index + 1)
            }
        }
    }

    log.info("All records have been successfully written and compressed!")
}
