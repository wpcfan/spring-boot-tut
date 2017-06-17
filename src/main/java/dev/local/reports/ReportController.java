package dev.local.reports;

import net.sf.jasperreports.engine.JREmptyDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {

    @RequestMapping(path = "/pdf", method = RequestMethod.GET)
    public ModelAndView report(ModelMap modelMap, ModelAndView modelAndView) {
        modelMap.put("datasource", new JREmptyDataSource());
        modelMap.put("title", "王芃");
        modelAndView = new ModelAndView("pdf", modelMap);
        return modelAndView;
    }
}

