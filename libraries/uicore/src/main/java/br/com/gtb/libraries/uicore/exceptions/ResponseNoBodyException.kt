package br.com.gtb.libraries.uicore.exceptions

class ResponseNoBodyException : Exception() {
    override val message: String
        get() = "response no body"
}