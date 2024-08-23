package pureapps.antlreportgen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;

@Configuration
class Config
{
    @Bean
    PdfGenaratorUtil pdfGenaratorUtil(TemplateEngine templateEngine)
    {
        return new PdfGenaratorUtil(templateEngine);
    }

}
