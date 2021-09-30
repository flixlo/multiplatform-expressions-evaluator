package com.murzagalin.evaluator.tokenizer

import com.murzagalin.evaluator.Token
import com.murzagalin.evaluator.Tokenizer
import kotlin.test.Test
import kotlin.test.assertContentEquals

class VariablesTests{

    private val subject = Tokenizer()

    @Test
    fun just_var() {
        assertContentEquals(
            listOf(
                Token.Operand.Variable("var")
            ),
            subject.tokenize("var")
        )
    }

    @Test
    fun sum_of_var_and_number() {
        assertContentEquals(
            listOf(
                Token.Operand.Variable("var"),
                Token.Operator.Sum,
                Token.Operand.Num(1.2)
            ),
            subject.tokenize("var+1.2")
        )
    }

    @Test
    fun expression_with_variables() {
        assertContentEquals(
            listOf(
                Token.Bracket.Left,
                Token.Operand.Variable("var_one"),
                Token.Operator.Sum,
                Token.Operand.Num(1.2),
                Token.Bracket.Right,
                Token.Operator.Pow,
                Token.Operand.Variable("var_two"),
                Token.Operator.Mult,
                Token.Operand.Variable("var_three")
            ),
            subject.tokenize("(var_one+1.2)^var_two*var_three")
        )
    }

    @Test
    fun just_variable_with_number() {
        assertContentEquals(
            listOf(
                Token.Operand.Variable("var1")
            ),
            subject.tokenize("var1")
        )
    }

    @Test
    fun expression_with_variables_with_numbers() {
        assertContentEquals(
            listOf(
                Token.Bracket.Left,
                Token.Operand.Variable("var1"),
                Token.Operator.Sum,
                Token.Operand.Num(1.2),
                Token.Bracket.Right,
                Token.Operator.Pow,
                Token.Operand.Variable("var2"),
                Token.Operator.Mult,
                Token.Operand.Variable("var3")
            ),
            subject.tokenize("(var1+1.2)^var2*var3")
        )
    }
}