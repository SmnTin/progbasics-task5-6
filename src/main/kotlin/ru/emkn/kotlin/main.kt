package ru.emkn.kotlin

fun main() {
    val root =
        MultiplicationNode(
            AdditionNode(
                LiteralNode(2),
                AdditionNode(
                    LiteralNode(3),
                    LiteralNode(5)
                )
            ),
            MultiplicationNode(
                LiteralNode(4),
                LiteralNode(-2)
            )
        )
    root.accept(PrintVisitor())
    println()

    val calcVisitor = CalculateVisitor()
    root.accept(calcVisitor)
    println(calcVisitor.result)

    val expandVisitor = ExpandVisitor()
    root.accept(expandVisitor)

    expandVisitor.expansion.accept(PrintVisitor(BracketsStrategy.OmitBrackets()))
    println()
}