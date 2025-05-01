import freemarker.template.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

//Freemarker SSTI手动测试
public class nospringToFTL {
    public static void main(String[] args) throws Exception {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(nospringToFTL.class, "/templates"); // 模板文件目录
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("hello.ftl");

        Map<String, Object> data = new HashMap<>();

        StringWriter writer = new StringWriter();
        template.process(data, writer);

        System.out.println(writer.toString());
    }
}

