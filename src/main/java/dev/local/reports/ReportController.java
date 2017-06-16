package dev.local.reports;

import dev.local.project.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class ReportController {

    @RequestMapping(path = "/pdf", method = RequestMethod.GET)
    public ModelAndView report(ModelMap modelMap, ModelAndView modelAndView) {
        Project project = new Project();
        project.setName("Wang");
        ArrayList list = new ArrayList();
        list.add((project));
        modelMap.put("datasource", list);

        return new ModelAndView("pdf", modelMap);
    }
}

