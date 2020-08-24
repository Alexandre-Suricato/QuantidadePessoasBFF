package br.com.zup.quantidadePessoas.bff

import org.springframework.stereotype.Service

@Service
class Calculadora {

    fun soma1(qtPessoas: String): Result{

        val qtPessoasInt = qtPessoas.toInt()
        val soma = qtPessoasInt + 1

        return Result(value = soma.toString())
    }

    fun subtrai1(qtPessoas: String): Result {

        val qtPessoasInt = qtPessoas.toInt()
        val subtrai = qtPessoasInt - 1

        return Result(value = subtrai.toString())
    }
}

data class Result (val value: String)