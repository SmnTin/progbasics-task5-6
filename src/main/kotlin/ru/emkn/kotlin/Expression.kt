package ru.emkn.kotlin

interface ExprNodeVisitor<R> {
    fun visit(node: LiteralNode) : R
    fun visit(node: AdditionNode) : R
    fun visit(node: MultiplicationNode) : R
}

interface ExprNode {
    fun <R> accept(visitor: ExprNodeVisitor<R>) : R
}

class LiteralNode(val number: Int) : ExprNode {
    override fun <R> accept(visitor: ExprNodeVisitor<R>) : R {
        return visitor.visit(this)
    }
}

class AdditionNode(val left: ExprNode, val right: ExprNode) : ExprNode {
    override fun <R> accept(visitor: ExprNodeVisitor<R>) : R {
        return visitor.visit(this)
    }
}

class MultiplicationNode(val left: ExprNode, val right: ExprNode) : ExprNode {
    override fun <R> accept(visitor: ExprNodeVisitor<R>) : R {
        return visitor.visit(this)
    }
}