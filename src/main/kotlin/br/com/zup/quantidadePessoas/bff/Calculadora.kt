package br.com.zup.quantidadePessoas.bff

import org.springframework.stereotype.Service

@Service
class Calculadora {

    fun soma1(qtPessoas: String): String{

        val qtPessoasInt = qtPessoas.toInt()
        val subtrai = qtPessoasInt

        return subtrai.toString()
    }

    fun subtrai1(qtPessoas: String): String{

        val qtPessoasInt = qtPessoas.toInt()
        val subtrai = qtPessoasInt - 1

        return subtrai.toString()
    }
}