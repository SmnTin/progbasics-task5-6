package ru.emkn.kotlin

class ExpandVisitor : ExprNodeVisitor {
    var expansion: ExprNode = LiteralNode(0)
        private set

    override fun visit(node: AdditionNode) {
        visitBinaryOperation(node.left, node.right) { a, b -> AdditionNode(a, b) }
    }

    override fun visit(node: LiteralNode) {
        expansion = LiteralNode(node.number)
    }

    override fun visit(node: MultiplicationNode) {
        when {
            node.left is AdditionNode ->
                expandLeftAddition(node.left, node.right)
            node.right is AdditionNode ->
                expandRightAddition(node.left, node.right)
            else ->
                expandMultiplication(node.left, node.right)
        }
    }

    private fun expandLeftAddition(left: AdditionNode, right: ExprNode) {
        AdditionNode(
            MultiplicationNode(left.left, right),
            MultiplicationNode(left.right, right)
        ).accept(this)
    }

    private fun expandRightAddition(left: ExprNode, right: AdditionNode) {
        AdditionNode(
            MultiplicationNode(left, right.left),
            MultiplicationNode(left, right.right)
        ).accept(this)
    }

    private fun expandMultiplication(left: ExprNode, right: ExprNode) {
        visitBinaryOperation(left, right) { a, b -> MultiplicationNode(a, b) }
    }

    private fun visitBinaryOperation(left: ExprNode, right: ExprNode, transform: (ExprNode, ExprNode) -> ExprNode) {
        left.accept(this)
        val leftExpansion = expansion
        right.accept(this)
        val rightExpansion = expansion
        expansion = transform(leftExpansion, rightExpansion)
    }
}