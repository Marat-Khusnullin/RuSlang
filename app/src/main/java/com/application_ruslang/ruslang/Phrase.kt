package com.application_ruslang.ruslang


class Phrase {

    var name: String? = null
    var definition: String? = null
    var type: String? = null
    var group: String? = null
    var examples: String? = null
    var hashtags: String? = null
    var origin: String? = null
    var synonyms: String? = null
    var id: String? = null

    constructor(
        _name: String,
        _definition: String,
        _type: String,
        _group: String,
        _examples: String,
        _hastags: String,
        _origin: String,
        _synonyms: String,
        _id: String
    ) {
        name = _name
        definition = _definition
        type = _type
        group = _group
        examples = _examples
        hashtags = _hastags
        origin = _origin
        synonyms = _synonyms
        id = _id
    }
}