package seevibes

import org.junit.Test
import org.scalatest.Assertions

class HelloWorldTest extends Assertions {
    @Test
    def whenTwoAndTwoAreAdded_thenFourIsExpected() {
        expect(4) {
            2 + 2
        }
    }

    @Test
    def whenTwoAndThreeAreAdded_thenFiveIsExpected() {
        expect(5) {
            2 + 3
        }
    }
}
