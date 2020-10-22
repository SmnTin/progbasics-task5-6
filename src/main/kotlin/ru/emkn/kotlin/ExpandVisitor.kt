package ru.emkn.kotlin

class ExpandVisitor : ExprNodeVisitor<ExprNode> {
    override fun visit(node: AdditionNode) =
        AdditionNode(
            node.left.accept(this),
            node.right.accept(this)
        )

    override fun visit(node: LiteralNode) =
        LiteralNode(node.number)

    override fun visit(node: MultiplicationNode) =
        when {
            node.left is AdditionNode ->
                expandLeftAddition(node.left, node.right)
            node.right is AdditionNode ->
                expandRightAddition(node.left, node.right)
            else ->
                expandMultiplication(node.left, node.right)
        }

    private fun expandLeftAddition(left: AdditionNode, right: ExprNode) =
        AdditionNode(
            MultiplicationNode(left.left, right),
            MultiplicationNode(left.right, right)
        ).accept(this)

    private fun expandRightAddition(left: ExprNode, right: AdditionNode) =
        AdditionNode(
            MultiplicationNode(left, right.left),
            MultiplicationNode(left, right.right)
        ).accept(this)

    private fun expandMultiplication(left: ExprNode, right: ExprNode) =
        MultiplicationNode(
            left.accept(this),
            right.accept(this)
        )
}