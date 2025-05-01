package cn.org.javaweb.sstivuln.demos.web;

import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

//freemarker SSTI 测试
//spring默认使用Thymeleaf，所以在application.yml禁用Thymeleaf
@Controller
public class HelloController {
    private Configuration freemarkerConfig = new Configuration();

    private final StringTemplateLoader dynamicLoader = new StringTemplateLoader();

    @PostConstruct
    public void setupTemplateLoader() {
        TemplateLoader[] loaders = new TemplateLoader[] {
                dynamicLoader,
                freemarkerConfig.getTemplateLoader()
        };
        freemarkerConfig.setTemplateLoader(new MultiTemplateLoader(loaders));
    }

    @PostMapping("/template")
    public String render(@RequestParam Map<String, String> templates) throws Exception {
        // 设置模板
        for (Map.Entry<String, String> entry : templates.entrySet()) {
            dynamicLoader.putTemplate(entry.getKey(), entry.getValue());
        }

        // 获取模板
        Template t = freemarkerConfig.getTemplate("payload");
        Map<String, Object> model = new HashMap<>();

        StringWriter out = new StringWriter();
        t.process(model, out);
        return out.toString(); // 返回渲染结果
    }
}