package com.lebaillyapp.composeretrouikit.segmentsDisplay.multiDisplay


fun iteratorConfigGenerator(sevenSegCfg: SevenSegmentConfig,nbrDigit: Int): List<SevenSegmentConfig>{
    val list = mutableListOf<SevenSegmentConfig>()
    for (i in 1..nbrDigit){
        list.add(sevenSegCfg)
    }
    return list
}