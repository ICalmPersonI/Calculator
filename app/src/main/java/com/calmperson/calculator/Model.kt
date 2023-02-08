package com.calmperson.calculator

import java.util.*
import kotlin.math.pow

class Model {

    companion object {
        private const val PLUS = "+"
        private const val MINUS = "-"
        private const val MULTIPLICATION_SING = "*"
        private const val DIVISION_SING = "/"
        private const val EXPONENT_SING = "^"
        private const val ANGEL_BRACKET_LEFT = "("
        private const val ANGEL_BRACKET_RIGHT = ")"
    }

    fun calculate(infixExp: String): Double? {
        try {
            val stack: Deque<Double> = ArrayDeque()
            val postfixExp = infixToPostfix(infixExp)

            for (str in postfixExp!!) {
                if (str.isDigit()) {
                    stack.push(str.toDouble())
                    continue
                }

                val first = stack.pop()
                val second = stack.pop()
                when (str) {
                    PLUS -> stack.push(second + first)
                    MINUS -> stack.push(second - first)
                    MULTIPLICATION_SING -> stack.push(second * first)
                    DIVISION_SING -> stack.push(second / first)
                    EXPONENT_SING -> stack.push(second.pow(first))
                }
            }
            return stack.pop()
        } catch (_: Exception) {
            return null
        }
    }

    private fun infixToPostfix(exp: String): List<String>? {
        val result = mutableListOf<String>()
        val stack: Deque<String> = ArrayDeque()

        for (str in exp.trim().split(' ')) {

            if (str.isDigit()) {
                result.add(str)
            } else if (str == ANGEL_BRACKET_LEFT) {
                stack.push(str)
            } else if (str == ANGEL_BRACKET_RIGHT) {
                while (!stack.isEmpty() && stack.peek() != ANGEL_BRACKET_LEFT) {
                    result.add(stack.peek())
                    stack.pop()
                }
                stack.pop()
            } else {
                while (!stack.isEmpty() && getPriority(str) <= getPriority(stack.peek())) {
                    result.add(stack.peek())
                    stack.pop()
                }
                stack.push(str)
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == ANGEL_BRACKET_LEFT) {
                return null
            }
            result += stack.peek()
            stack.pop()
        }
        return result
    }

    private fun getPriority(ch: String): Int = when (ch) {
        PLUS -> 1
        MINUS -> 1
        MULTIPLICATION_SING -> 2
        DIVISION_SING -> 2
        EXPONENT_SING -> 3
        else -> -1
    }

    private fun String.isDigit(): Boolean =
        try {
            this.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
}