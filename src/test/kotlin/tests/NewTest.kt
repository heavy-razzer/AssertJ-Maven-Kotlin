package tests

import Logger.log
import org.testng.annotations.Test
import steps.Steps
import steps.Steps.printCaption

class NewTest {

    @Test(description = "Very simple test")
    fun myTest() {
        with(Steps) {
            printCaption()
            printMessage()
        }
    }
}