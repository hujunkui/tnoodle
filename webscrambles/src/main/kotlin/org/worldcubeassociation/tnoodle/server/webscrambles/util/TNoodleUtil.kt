package org.worldcubeassociation.tnoodle.server.webscrambles.util;

import org.worldcubeassociation.tnoodle.server.webscrambles.pdf.ScrambleSheet
import org.worldcubeassociation.tnoodle.server.webscrambles.pdf.model.Document
import org.worldcubeassociation.tnoodle.server.webscrambles.wcif.WCIFDataBuilder
import org.worldcubeassociation.tnoodle.server.webscrambles.wcif.model.Competition
import org.worldcubeassociation.tnoodle.server.webscrambles.wcif.model.extension.SheetCopyCountExtension
import java.io.FileOutputStream

/**
 * @Author HuJunkui
 * @Date 2023 11 30
 **/
object TNoodleUtil {

    fun generatePdf(competition: Competition, uniqueTitles: Map<String, ScrambleSheet>, filePath: String, password: String) {
        val scrambleSheetsFlat = uniqueTitles.values.toList()
        val wcifRounds = competition.events.flatMap { it.rounds }
        val scrambleSheetsWithCopies = scrambleSheetsFlat.flatMap { sheet ->
            val sheetRound = wcifRounds.first { it.idCode.isParentOf(sheet.activityCode) }
            val copyCountExtension = sheetRound.findExtension<SheetCopyCountExtension>()
            val numCopies = copyCountExtension?.numCopies ?: 1
            Document.clone(sheet.document, numCopies)
        }
        val compileOutlinePdfBytes = WCIFDataBuilder.compileOutlinePdfBytes(scrambleSheetsWithCopies, password)
        val fileOutputStream = FileOutputStream(filePath)
        fileOutputStream.write(compileOutlinePdfBytes)
        fileOutputStream.close()
    }
}
