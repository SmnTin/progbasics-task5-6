package ru.emkn.kotlin

class CalculateVisitor : ExprNodeVisitor<Int> {
    override fun visit(node: LiteralNode) =
        node.number

    override fun visit(node: AdditionNode) =
        node.left.accept(this) + node.right.accept(this)

    override fun visit(node: MultiplicationNode) =
        node.left.accept(this) * node.right.accept(this)
}