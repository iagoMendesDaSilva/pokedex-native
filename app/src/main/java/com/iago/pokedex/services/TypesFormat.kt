package com.iago.pokedex.services

interface TypesFormat {
    fun getTypes(types: List<String>): ArrayList<String> {

        var correctTypes = arrayListOf<String>()

        if (types.isNotEmpty()) {
            correctTypes.add(types[0])
            if (types.size > 1) {
                correctTypes.add(types[1])
            }else{
                correctTypes.add("")
            }
        }

        return correctTypes
    }
}