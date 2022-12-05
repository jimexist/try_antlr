package me.jiayu.tryantlr

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file
import me.jiayu.json.JsonLexer
import me.jiayu.json.JsonParser
import org.antlr.v4.runtime.ANTLRFileStream
import org.antlr.v4.runtime.CommonTokenStream

class ParseJson : CliktCommand() {
    private val file by option(help = "input file").file(
        mustBeReadable = true,
        canBeFile = true,
        canBeDir = false,
        mustExist = true
    ).required()

    override fun run() {
        val fileStream = ANTLRFileStream(file.path)
        val lexer = JsonLexer(fileStream)
        val tokens = CommonTokenStream(lexer);
        val parser = JsonParser(tokens)
        val jsonParsed = parser.file().json()
        val kp = jsonParsed.obj().kvPair(0)
        val key = kp.key().string().STRING()
        val value = kp.value().json().jsonNumber().NUMBER()
        println("$key $value")
    }

}

fun main(args: Array<String>) = ParseJson().main(args)
