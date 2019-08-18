import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CarpetQuoteOOTest {

    @Test
    public void quoteNoRounding(){
        assertThat(new CarpetQuote().quote(2.5,2.5, 10.0, false), equalTo(62.5));
    }

    @Test
    public void quoteWithRounding(){
        assertThat(new CarpetQuote().quote(2.5,2.5, 10.0, true), equalTo(70.0));
    }
}
