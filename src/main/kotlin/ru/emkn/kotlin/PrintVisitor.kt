package ru.emkn.kotlin

sealed class BracketsStrategy {
    abstract val leftLiteralBracket: String
    abstract val rightLiteralBracket: String
    abstract val leftExprBracket: String
    abstract val rightExprBracket: String

    class Default : BracketsStrategy() {
        override val leftExprBracket = "("
        override val rightExprBracket = ")"
        override val leftLiteralBracket = "("
        override val rightLiteralBracket = ")"
    }

    class OmitBrackets : BracketsStrategy() {
        override val leftExprBracket = ""
        override val rightExprBracket = ""
        override val leftLiteralBracket = "("
        override val rightLiteralBracket = ")"
    }
}

class PrintVisitor(
    private val bracketsStrategy: BracketsStrategy = BracketsStrategy.Default()
) : ExprNodeVisitor<Unit> {

    override fun visit(node: LiteralNode) {
        if (node.number < 0)
            printNegativeLiteral(node.number)
        else
            printNonNegativeLiteral(node.number)
    }

    private fun printNegativeLiteral(number: Int) {
        print(String.format(
            "%s%d%s",
            bracketsStrategy.leftLiteralBracket,
            number,
            bracketsStrategy.rightLiteralBracket
        ))
    }

    private fun printNonNegativeLiteral(number: Int) {
        print(number)
    }

    override fun visit(node: AdditionNode) {
        visitBinaryOperation(node.left, node.right, " + ")
    }

    override fun visit(node: MultiplicationNode) {
        visitBinaryOperation(node.left, node.right, " * ")
    }

    private fun visitBinaryOperation(left: ExprNode, right: ExprNode, opLiteral: String) {
        print(bracketsStrategy.leftExprBracket)
        left.accept(this)
        print(opLiteral)
        right.accept(this)
        print(bracketsStrategy.rightExprBracket)
    }
}