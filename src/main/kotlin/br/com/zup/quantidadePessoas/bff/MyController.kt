package br.com.zup.quantidadePessoas.bff

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MyController(private val myService: MyService) {

    @GetMapping("/screen")
    fun getScreen() = myService.createScreen()

    @GetMapping("/soma/{qtPessoas}")
    fun soma(@PathVariable("qtPessoas") qtPessoas: String) = Calculadora().soma1(qtPessoas)

    @GetMapping("/subtrai/{qtPessoas}")
    fun subtrai(@PathVariable("qtPessoas") qtPessoas: String) = Calculadora().subtrai1(qtPessoas)

}