package ru.emkn.kotlin

class CalculateVisitor : ExprNodeVisitor {
    // Elegant (to my mind) solution that allows us not to introduce accept() return type
    // It is done because different visitors may require different return types
    // and that might break the pattern versatility
    var result = 0
        private set

    override fun visit(node: LiteralNode) {
        result = node.number
    }

    override fun visit(node: AdditionNode) {
        visitBinaryOperation(node.left, node.right) { a, b -> a + b }
    }

    override fun visit(node: MultiplicationNode) {
        visitBinaryOperation(node.left, node.right) { a, b -> a * b }
    }

    private fun visitBinaryOperation(left: ExprNode, right: ExprNode, op: (Int, Int) -> Int) {
        left.accept(this)
        val leftVal = result
        right.accept(this)
        val rightVal = result
        result = op(leftVal, rightVal)
    }
}