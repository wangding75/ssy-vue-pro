package cn.ssy.module.finance.utils;

import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class FreeMarkerUtils {

    private static Configuration cfg;

    private static StringTemplateLoader stringLoader;

    static {
        cfg = new Configuration(Configuration.VERSION_2_3_30);
        stringLoader = new StringTemplateLoader();
        cfg.setTemplateLoader(stringLoader);
    }

    public static String load(String templateStr, Map<String, String> dataModel) throws IOException, TemplateException {
        stringLoader.putTemplate("template", templateStr);
        Template template = cfg.getTemplate("template");
        StringWriter out = new StringWriter();
        template.process(dataModel, out);
        return out.toString();
    }

}
