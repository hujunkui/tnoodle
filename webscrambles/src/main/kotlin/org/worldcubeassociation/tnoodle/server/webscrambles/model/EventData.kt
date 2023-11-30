package org.worldcubeassociation.tnoodle.server.model

enum class EventData(val key: String, val description: String, val scrambler: PuzzleData, val legalFormats: Set<FormatData>) {
    THREE("333","三阶速拧",PuzzleData.THREE, FormatData.BIG_AVERAGE_FORMATS),
    TWO("222","二阶速拧",PuzzleData.TWO, FormatData.BIG_AVERAGE_FORMATS),
    FOUR("444","四阶速拧",PuzzleData.FOUR, FormatData.BIG_AVERAGE_FORMATS),
    FIVE("555","五阶速拧",PuzzleData.FIVE, FormatData.BIG_AVERAGE_FORMATS),
    SIX("666","六阶速拧",PuzzleData.SIX, FormatData.SMALL_AVERAGE_FORMATS),
    SEVEN("777","七阶速拧",PuzzleData.SEVEN, FormatData.SMALL_AVERAGE_FORMATS),
    THREE_BLD("333bf", "三阶盲拧", PuzzleData.THREE_BLD, FormatData.BLD_SPECIAL_FORMATS),
    THREE_FM("333fm", "三阶最少步",PuzzleData.THREE_FMC, FormatData.SMALL_AVERAGE_FORMATS),
    THREE_OH("333oh", "三阶单手", PuzzleData.THREE, FormatData.BIG_AVERAGE_FORMATS),
    CLOCK("clock", "魔表",PuzzleData.CLOCK, FormatData.BIG_AVERAGE_FORMATS),
    MEGA("minx", "五魔方",PuzzleData.MEGA, FormatData.BIG_AVERAGE_FORMATS),
    PYRA("pyram", "金字塔",PuzzleData.PYRA, FormatData.BIG_AVERAGE_FORMATS),
    SKEWB("skewb", "斜转",PuzzleData.SKEWB, FormatData.BIG_AVERAGE_FORMATS),
    SQ1("sq1", "Square-1",PuzzleData.SQ1, FormatData.BIG_AVERAGE_FORMATS),
    FOUR_BLD("444bf", "四阶盲拧", PuzzleData.FOUR_BLD, FormatData.BLD_SPECIAL_FORMATS),
    FIVE_BLD("555bf", "五阶盲拧", PuzzleData.FIVE_BLD, FormatData.BLD_SPECIAL_FORMATS),
    THREE_MULTI_BLD("333mbf", "三阶多盲", PuzzleData.THREE_BLD, FormatData.BLD_SPECIAL_FORMATS);

    constructor(scrambler: PuzzleData, legalFormats: Set<FormatData>) : this(scrambler.key, scrambler.description, scrambler, legalFormats)

    companion object {
        val WCA_EVENTS = values().associateBy { it.key }.toSortedMap()

        val ONE_HOUR_EVENTS = setOf(THREE_FM, THREE_MULTI_BLD)
        val ATTEMPT_BASED_EVENTS = setOf(THREE_FM, THREE_MULTI_BLD)
    }
}
