package cn.org.javaweb.sstivuln.demos.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

    @PostMapping("/vuln1")
    public String test1(@RequestParam String payload) {
        return payload+"::unsafe";
    }

    @PostMapping("/vuln2")
    public String test2(@RequestParam String payload) {
        return "index::"+payload;
    }

    @GetMapping("/vuln3/{payload}")
    public void test(@PathVariable String payload) {
    }
}