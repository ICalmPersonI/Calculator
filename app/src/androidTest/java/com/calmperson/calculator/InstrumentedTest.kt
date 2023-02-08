package com.calmperson.calculator

import Calculator
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun before() {
        composeRule.setContent { Calculator() }
    }

    @Test
    fun addingNumbers() {
        with(composeRule) {

            onNodeWithTag("1").performClick()
            onNodeWithTag("+").performClick()
            onNodeWithTag("3").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText("4.0"))

            onNodeWithTag("clear").performClick()
            onNodeWithTag("display").assert(hasText(""))

            onNodeWithTag("3").performClick()
            onNodeWithTag(".").performClick()
            onNodeWithTag("1").performClick()
            onNodeWithTag("+").performClick()
            onNodeWithTag("2").performClick()
            onNodeWithTag(".").performClick()
            onNodeWithTag("1").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText("5.2"))

            onNodeWithTag("clear").performClick()
            onNodeWithTag("display").assert(hasText(""))

            onNodeWithTag("plus_minus_variant").performClick()
            onNodeWithTag("1").performClick()
            onNodeWithTag("+").performClick()
            onNodeWithTag("plus_minus_variant").performClick()
            onNodeWithTag("3").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText("-4.0"))
        }
    }

    @Test
    fun subtractNumbers() {
        with(composeRule) {

            onNodeWithTag("1").performClick()
            onNodeWithTag("-").performClick()
            onNodeWithTag("3").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText("-2.0"))

            onNodeWithTag("clear").performClick()
            onNodeWithTag("display").assert(hasText(""))

            onNodeWithTag("3").performClick()
            onNodeWithTag(".").performClick()
            onNodeWithTag("1").performClick()
            onNodeWithTag("-").performClick()
            onNodeWithTag("2").performClick()
            onNodeWithTag(".").performClick()
            onNodeWithTag("1").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText("1.0"))

            onNodeWithTag("clear").performClick()
            onNodeWithTag("display").assert(hasText(""))

            onNodeWithTag("plus_minus_variant").performClick()
            onNodeWithTag("1").performClick()
            onNodeWithTag("-").performClick()
            onNodeWithTag("plus_minus_variant").performClick()
            onNodeWithTag("3").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText("2.0"))
        }
    }

    @Test
    fun multiplicationNumbers() {
        with(composeRule) {

            onNodeWithTag("1").performClick()
            onNodeWithTag("*").performClick()
            onNodeWithTag("3").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText("3.0"))

            onNodeWithTag("clear").performClick()
            onNodeWithTag("display").assert(hasText(""))

            onNodeWithTag("3").performClick()
            onNodeWithTag(".").performClick()
            onNodeWithTag("1").performClick()
            onNodeWithTag("*").performClick()
            onNodeWithTag("2").performClick()
            onNodeWithTag(".").performClick()
            onNodeWithTag("1").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText("6.510000000000001"))

            onNodeWithTag("clear").performClick()
            onNodeWithTag("display").assert(hasText(""))

            onNodeWithTag("plus_minus_variant").performClick()
            onNodeWithTag("1").performClick()
            onNodeWithTag("*").performClick()
            onNodeWithTag("plus_minus_variant").performClick()
            onNodeWithTag("3").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText("3.0"))
        }
    }

    @Test
    fun divisionNumbers() {
        with(composeRule) {

            onNodeWithTag("1").performClick()
            onNodeWithTag("/").performClick()
            onNodeWithTag("3").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText("0.3333333333333333"))

            onNodeWithTag("clear").performClick()
            onNodeWithTag("display").assert(hasText(""))

            onNodeWithTag("3").performClick()
            onNodeWithTag(".").performClick()
            onNodeWithTag("1").performClick()
            onNodeWithTag("/").performClick()
            onNodeWithTag("2").performClick()
            onNodeWithTag(".").performClick()
            onNodeWithTag("1").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText("1.4761904761904763"))

            onNodeWithTag("clear").performClick()
            onNodeWithTag("display").assert(hasText(""))

            onNodeWithTag("plus_minus_variant").performClick()
            onNodeWithTag("1").performClick()
            onNodeWithTag("/").performClick()
            onNodeWithTag("plus_minus_variant").performClick()
            onNodeWithTag("3").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText("0.3333333333333333"))
        }
    }

    @Test
    fun wrongExp() {
        with(composeRule) {

            onNodeWithTag("1").performClick()
            onNodeWithTag("/").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText(""))

            onNodeWithTag("3").performClick()
            onNodeWithTag("/").performClick()
            onNodeWithTag("4").performClick()
            onNodeWithTag("+").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText(""))

            onNodeWithTag("3").performClick()
            onNodeWithTag(".").performClick()
            onNodeWithTag("+").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText(""))

            onNodeWithTag("3").performClick()
            onNodeWithTag(".").performClick()
            onNodeWithTag("plus_minus_variant").performClick()
            onNodeWithTag("+").performClick()
            onNodeWithTag("plus_minus_variant").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText(""))
        }
    }

    @Test
    fun calculationHistory() {
        with(composeRule) {

            onNodeWithTag("1").performClick()
            onNodeWithTag("+").performClick()
            onNodeWithTag("3").performClick()
            onNodeWithTag("=").performClick()
            onNodeWithTag("display").assert(hasText("4.0"))

            onNodeWithTag("Calculations history").performClick()
            onNodeWithText("1 + 3 = 4.0").assertIsDisplayed()

        }
    }
}