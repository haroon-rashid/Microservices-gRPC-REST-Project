package com.paris.agreement.controller;


import com.paris.agreement.model.TextAnalysis;
import com.paris.agreement.service.ShowStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class ShowStatisticController {

    @Autowired
    private ShowStatisticService showStatisticService;


    @GetMapping("/")
    public String show(Model model) {
        List<TextAnalysis> statisticData = showStatisticService.getStatisticData();

        System.out.println(statisticData);
        model.addAttribute("statisticData", statisticData);

        return "index.html";
    }

}
