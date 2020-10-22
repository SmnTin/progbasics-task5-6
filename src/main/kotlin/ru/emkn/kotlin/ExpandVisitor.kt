package ru.emkn.kotlin

class ExpandVisitor : ExprNodeVisitor {
    var expansion: ExprNode = LiteralNode(0)
        private set

    override fun visit(node: AdditionNode) {
        node.left.accept(this)
        val leftExpansion = expansion
        node.right.accept(this)
        val rightExpansion = expansion
        expansion = AdditionNode(leftExpansion, rightExpansion)
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
        left.accept(this)
        val leftExpansion = expansion
        right.accept(this)
        val rightExpansion = expansion
        expansion = MultiplicationNode(leftExpansion, rightExpansion)
    }
}