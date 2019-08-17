import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class CarpetQuoteTest {
    @Test fun quoteNoRounding(){
        assertThat(quote(Pair(2.5,2.5), Pair(10.0, false)), equalTo(62.5))
    }

    @Test fun quoteWithRounding(){
        assertThat(quote(Pair(2.5,2.5), Pair(10.0, true)), equalTo(70.0))
    }
}