package pureapps.antlreportgen;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PdfGenaratorUtil
{
    private final TemplateEngine templateEngine;
    private final static String fontPath = "./src/main/resources/font/Arial.ttf";

    public void createPdf(String templateName, Map<String, Object> map, String fileName) throws Exception
    {
        Context ctx = new Context();
        Iterator<Map.Entry<String, Object>> itMap = map.entrySet().iterator();
        while (itMap.hasNext())
        {
            Map.Entry<String, Object> pair = itMap.next();
            ctx.setVariable(pair.getKey(), pair.getValue());
        }

        String processedHtml = templateEngine.process(templateName, ctx);
        FileOutputStream os = null;
        try
        {
            final File outputFile = File.createTempFile(fileName, ".pdf", new File("./"));
            os = new FileOutputStream(outputFile);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);

            renderer.getFontResolver().addFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.layout();
            renderer.createPDF(os, false);
            renderer.finishPDF();

        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e)
                { /*ignore*/ }
            }
        }
    }
}