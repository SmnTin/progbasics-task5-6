package ru.emkn.kotlin

interface ExprNodeVisitor {
    fun visit(node: LiteralNode)
    fun visit(node: AdditionNode)
    fun visit(node: MultiplicationNode)
}

interface ExprNode {
    fun accept(visitor: ExprNodeVisitor)
}

class LiteralNode(val number: Int) : ExprNode {
    override fun accept(visitor: ExprNodeVisitor) {
        visitor.visit(this)
    }
}

class AdditionNode(val left: ExprNode, val right: ExprNode) : ExprNode {
    override fun accept(visitor: ExprNodeVisitor) {
        visitor.visit(this)
    }
}

class MultiplicationNode(val left: ExprNode, val right: ExprNode) : ExprNode {
    override fun accept(visitor: ExprNodeVisitor) {
        visitor.visit(this)
    }
}