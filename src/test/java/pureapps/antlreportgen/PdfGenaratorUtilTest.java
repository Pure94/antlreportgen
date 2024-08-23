package pureapps.antlreportgen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootTest
class PdfGenaratorUtilTest
{

    private final static String TITLE = "EWIDENCJONOWANIE CZASU WYKONYWANIA UMOWY O SWIADCZENIE USŁUG";
    private final boolean isSignature = true;
    private final static String month = "czerwiec";

    @Autowired
    PdfGenaratorUtil pdfGenaratorUtil;


    @Test
    void shouldCreatePdf() throws Exception
    {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("timetable", new Timetable(1, 10, 31, 190));
        data.put("pageTitle",TITLE);
        data.put("showSignature",isSignature);
        data.put("month",month);
        data.put("year","2024");
        pdfGenaratorUtil.createPdf("report", data, "test_out");
    }
}