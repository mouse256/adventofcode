package org.muizenhol.adventofcode

import mu.KotlinLogging
import java.nio.charset.StandardCharsets

private val logger = KotlinLogging.logger {}

class Ex1 {
    fun go() {
        logger.info { "ex1" }
        var prev = Integer.MAX_VALUE
        var count = 0
        this.javaClass.getResourceAsStream("/ex1.txt")
            .use { stream ->
                stream?.bufferedReader(StandardCharsets.UTF_8)
                    ?.lines()
                    ?.forEach{ l ->
                        logger.info { "line: $l" }
                        val cur = Integer.parseInt(l)
                        if (cur > prev) {
                            count++
                        }
                        prev = cur
                    }

            }
        logger.info { "count: $count" }
    }

    fun gopart2() {
        logger.info { "ex1 part 2" }
        var prev = Integer.MAX_VALUE
        var count = 0

        var v1 = 9999
        var v2 = 9999
        var v3 = 9999
        this.javaClass.getResourceAsStream("/ex1.txt")
            .use { stream ->
                stream?.bufferedReader(StandardCharsets.UTF_8)
                    ?.lines()
                    ?.forEach{ l ->
                        //logger.info { "line: $l" }
                        val cur = Integer.parseInt(l)
                        var oldSum = v1 + v2 +v3
                        var newSum = v2 + v3 + cur
                        //logger.info { "old: $oldSum -- new: $newSum" }
                        if (newSum > oldSum) {
                            count++
                        }
                        v1 = v2
                        v2 = v3
                        v3 = cur
                    }

            }
        logger.info { "count: $count" }
    }
}