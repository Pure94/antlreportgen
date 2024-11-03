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
    private final static String month = "Pazdziernik";

    @Autowired
    PdfGenaratorUtil pdfGenaratorUtil;

    @Test
    void generateReportPdf() throws Exception
    {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("timetable", new Timetable(2, 10, 31, 204.15));
        data.put("pageTitle",TITLE);
        data.put("showSignature",isSignature);
        data.put("month",month);
        data.put("year","2024");
        pdfGenaratorUtil.createPdf("report", data, month);
    }
}