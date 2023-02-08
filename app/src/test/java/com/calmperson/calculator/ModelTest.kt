package com.calmperson.calculator

import org.junit.Assert.*
import org.junit.Test

class ModelTest {

    private val model = Model()

    @Test
    fun calculate_addingPositiveNumbers() {
        assertEquals(model.calculate("3 + 5"), 8.0)
        assertEquals(model.calculate("25 + 30"), 55.0)
        assertEquals(model.calculate("10000 + 10000"), 20000.0)
        assertEquals(model.calculate("35454 + 453543 + 32321 + 342432"), 863750.0)
        assertEquals(model.calculate("976585 + 597083 + 4732785 + 4743 + 37233"), 6348429.0)
    }

    @Test
    fun calculate_addingPositiveNumbersToNegativeNumbers() {
        assertEquals(model.calculate("3 + -5"), -2.0)
        assertEquals(model.calculate("25 + -30"), -5.0)
        assertEquals(model.calculate("10000 + -10000"), 0.0)
        assertEquals(model.calculate("35454 + -453543 + -32321 + 342432"), -107978.0)
        assertEquals(model.calculate("976585 + -597083 + -4732785 + 4743 + -37233"), -4385773.0)
    }

    @Test
    fun calculate_subtractingPositiveNumbers() {
        assertEquals(model.calculate("3 - 5"), -2.0)
        assertEquals(model.calculate("25 - 30"), -5.0)
        assertEquals(model.calculate("10000 - 10000"), 0.0)
        assertEquals(model.calculate("35454 - 453543 - 32321 - 342432"), -792842.0)
        assertEquals(model.calculate("976585 - 597083 - 4732785 - 4743 - 37233"), -4395259.0)
    }

    @Test
    fun calculate_subtractingPositiveNumbersFromNegativeNumbers() {
        assertEquals(model.calculate("-3 - 5"), -8.0)
        assertEquals(model.calculate("25 - -30"), 55.0)
        assertEquals(model.calculate("-10000 - 10000"), -20000.0)
        assertEquals(model.calculate("35454 - -453543 - 32321 - -342432"), 799108.0)
        assertEquals(model.calculate("-976585 - 597083 - -4732785 - 4743 - 37233"), 3117141.0)
    }

    @Test
    fun calculate_addingPositiveDecimalNumbers() {
        assertEquals(model.calculate("0.5 + 0.2"), 0.7)
        assertEquals(model.calculate("0.25 + 0.30"), 0.55)
        assertEquals(model.calculate("0.10000 + 0.10000"), 0.20000)
        assertEquals(model.calculate("2144.30004 + 23.00234 + 455.200321 + 34.4324"), 2656.935101)
        assertEquals(model.calculate("2133.0004324 + 323.432 + 9746.00304 + 0.5532 + 32213.985"), 44416.9736724)
    }

    @Test
    fun calculate_addingPositiveDecimalNumbersToNegativeDecimalNumbers() {
        assertEquals(model.calculate("0.5 + -0.2"), 0.3)
        assertEquals(model.calculate("-0.25 + 0.30"), 0.04999999999999999)
        assertEquals(model.calculate("0.10000 + -0.10000"), 0.0)
        assertEquals(model.calculate("2144.30004 + -23.00234 + 455.200321 + -34.4324"), 2542.0656209999997)
        assertEquals(model.calculate("-2133.0004324 + -323.432 + 9746.00304 + -0.5532 + 32213.985"), 39503.0024076)
    }

    @Test
    fun calculate_subtractingPositiveDecimalNumbersFromPositiveDecimalNumbers() {
        assertEquals(model.calculate("0.5 - 0.2"), 0.3)
        assertEquals(model.calculate("0.25 - 0.30"), -0.04999999999999999)
        assertEquals(model.calculate("0.10000 - 0.10000"), 0.0)
        assertEquals(model.calculate("2144.30004 - 23.00234 - 455.200321 - 34.4324"), 1631.6649790000001)
        assertEquals(model.calculate("2133.0004324 - 323.432 - 9746.00304 - 0.5532 - 32213.985"), -40150.9728076)
    }

    @Test
    fun calculate_subtractingNegativeDecimalNumbersFromPositiveDecimalNumbers() {
        assertEquals(model.calculate("-0.5 - 0.2"), -0.7)
        assertEquals(model.calculate("-0.25 - 0.30"), -0.55)
        assertEquals(model.calculate("0.10000 - -0.10000"), 0.20000)
        assertEquals(model.calculate("-2144.30004 - -23.00234 - 455.200321 - -34.4324"), -2542.0656209999997)
        assertEquals(model.calculate("2133.0004324 - 323.432 - -9746.00304 - 0.5532 - -32213.985"), 43769.0032724)
    }

    @Test
    fun calculate_multiplicationPositiveDecimalNumbers() {
        assertEquals(model.calculate("0.5 * 0.2"), 0.1)
        assertEquals(model.calculate("0.25 * 0.30"), 0.075)
        assertEquals(model.calculate("0.10000 * 0.10000"), 0.010000000000000002)
        assertEquals(model.calculate("2144.30004 * 23.00234 * 455.200321 * 34.4324"), 773085320.2009305)
        assertEquals(model.calculate("23.0004 * 33.432 * 96.00304 * 0.5532 * 32.985"), 1347042.7921107917)
    }

    @Test
    fun calculate_multiplicationPositiveDecimalNumbersByNegativeDecimalNumbers() {
        assertEquals(model.calculate("0.5 * -0.2"), -0.1)
        assertEquals(model.calculate("-0.25 * 0.30"), -0.075)
        assertEquals(model.calculate("0.10000 * -0.10000"), -0.010000000000000002)
        assertEquals(model.calculate("2144.30004 * -23.00234 * 455.200321 * -34.4324"), 773085320.2009305)
        assertEquals(model.calculate("-213.0004324 * -33.432 * 974.00304 * -0.5532 * 313.985"), -1204742490.9103801)
    }

    @Test
    fun calculate_divisionPositiveDecimalNumbers() {
        assertEquals(model.calculate("0.5 / 0.2"), 2.5)
        assertEquals(model.calculate("0.25 / 0.30"), 0.8333333333333334)
        assertEquals(model.calculate("0.10000 / 0.10000"), 1.0)
        assertEquals(model.calculate("2144.30004 / 23.00234 / 455.200321 / 34.4324"), 0.005947626402153053)
        assertEquals(model.calculate("23.0004 / 33.432 / 96.00304 / 0.5532 / 32.985"), 0.0003927257569383061)
    }

    @Test
    fun calculate_divisionPositiveDecimalNumbersIntoNegativeDecimalNumbers() {
        assertEquals(model.calculate("0.5 / -0.2"), -2.5)
        assertEquals(model.calculate("-0.25 / 0.30"), -0.8333333333333334)
        assertEquals(model.calculate("0.10000 / -0.10000"), -1.0)
        assertEquals(model.calculate("2144.30004 / -23.00234 / 455.200321 / -34.4324"), 0.005947626402153053)
        assertEquals(model.calculate("-213.0004324 / -33.432 / 974.00304 / -0.5532 / 313.985"), -0.00003765882298075427)
    }

    @Test
    fun calculate_powPositiveNumbers() {
        assertEquals(model.calculate("2 ^ 0.3"), 1.2311444133449163)
        assertEquals(model.calculate("3.2 ^ -3.13"), 0.02623496975504178)
        assertEquals(model.calculate("42 ^ 0.34 ^ 2"), 12.700169507626645)
        assertEquals(model.calculate("35 ^ 2 ^ 1.0"), 1225.0)
        assertEquals(model.calculate("-0.3 ^ 4 ^ 1.0 ^ 0.2"), 0.3816778909618176)
    }

    @Test
    fun calculate_wrongFormatExpressions() {
        assertEquals(model.calculate("-0.5 -"), null)
        assertEquals(model.calculate("- 0.30"), null)
        assertEquals(model.calculate("Text"), null)
        assertEquals(model.calculate("- -2144.30004 -"), null)
        assertEquals(model.calculate("3 = 3"), null)
    }

}