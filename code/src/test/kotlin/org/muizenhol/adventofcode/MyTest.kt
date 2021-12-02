package org.muizenhol.adventofcode

import io.kotest.core.spec.style.FunSpec
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class MyTest : FunSpec({

    test("ex1") {
        Ex1().go()
    }

    test("ex1 part2") {
        Ex1().gopart2()
    }

})
