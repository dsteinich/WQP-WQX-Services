package gov.usgs.cida.wqp.parameter.transform;

import static org.junit.Assert.assertEquals;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.validation.ValidationConstants;

import org.junit.Test;

public class SplitAndReplaceTransformerTest extends BaseSpringTest implements ValidationConstants {

    @Test
    public void testSplitAndReplace() {
        SplitAndReplaceTransformer transformer = new SplitAndReplaceTransformer(DEFAULT_DELIMITER, REGEX_HUC_WILDCARD_IN, REGEX_HUC_WILDCARD_OUT);
        String[] split = (String[]) transformer.transform("a;b*;c");
        assertEquals(3, split.length);
        assertEquals("a", split[0]);
        assertEquals("b%", split[1]);
        assertEquals("c", split[2]);
        
        transformer = new SplitAndReplaceTransformer("x", "duck", "quack");
        split = (String[]) transformer.transform("axbxduckxd");
        assertEquals(4, split.length);
        assertEquals("a", split[0]);
        assertEquals("b", split[1]);
        assertEquals("quack", split[2]);
        assertEquals("d", split[3]);
    }
}
