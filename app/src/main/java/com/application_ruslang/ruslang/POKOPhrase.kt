package com.application_ruslang.ruslang

class POKOPhrase() {

    var name: String? = null
    var definition: String? = null
    var type: String? = null
    var group: String? = null
    var examples: String? = null
    var hashtags: String? = null
    var origin: String? = null
    var synonyms: String? = null
    var id: String? = null
    var rating: Double = 0.0

    fun setPhrase(phrase: Phrase) {
        name = phrase.name
        definition = phrase.definition
        type = phrase.type
        group = phrase.group
        examples = phrase.examples
        hashtags = phrase.hashtags
        origin = phrase.origin
        synonyms = phrase.synonyms
        id = phrase.id
    }

    fun getPhrase() {

    }


}