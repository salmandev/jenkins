package hudson.security.csrf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class CrumbFilterTest {

    @Test
    void legacyFieldNameFallbackIsUsedWhenCurrentCrumbFieldIsMissing() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Jenkins-Crumb")).thenReturn(null);
        when(request.getHeader(".crumb")).thenReturn("legacy-crumb");
        when(request.getParameterNames()).thenReturn(Collections.emptyEnumeration());

        assertEquals("legacy-crumb", CrumbFilter.getCrumbFromRequest(request, "Jenkins-Crumb"));
    }
}

