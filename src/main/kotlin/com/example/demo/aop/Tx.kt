package com.example.demo.aop

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class Tx(
    _txAdvice : TxAdvice
) {
    init {
        Tx.txAdvice = _txAdvice
    }
    companion object {
        private lateinit var txAdvice: TxAdvice

        fun <T> run(function:() -> T): T{
            return txAdvice.run(function)
        }
    }

    @Component
    class TxAdvice {

        @Transactional
        fun <T> run(function: () -> T): T {
            return function()
        }
    }
}