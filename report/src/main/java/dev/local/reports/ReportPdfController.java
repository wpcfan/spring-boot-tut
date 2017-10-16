package dev.local.reports;

import net.sf.jasperreports.engine.JREmptyDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportPdfController {

    @RequestMapping(path = "/pdf", method = RequestMethod.GET)
    public ModelAndView report(ModelMap modelMap) {
        modelMap.put("datasource", new JREmptyDataSource());
        modelMap.put("title", "王芃");
        return new ModelAndView("pdf", modelMap);
    }
}

