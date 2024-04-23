package com.codingrecipe.board.controller;


import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.CommentDTO;
import com.codingrecipe.board.dto.MusicalDTO;
import com.codingrecipe.board.service.MusicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("musical")
public class MusicalViewController {

    private final MusicalService musicalService;

    @GetMapping("/api-page")
    public String api_page(Model model){
        //        LocalDate date = LocalDate.of(2010,3,1);
        //        List<MusicalDTO> musicalDTOList = musicalService.findByDate(date);
        List<MusicalDTO> musicalDTOList = musicalService.findAll();
        model.addAttribute("musicalList", musicalDTOList);
        return "/musical/musical_list";

    }

    @GetMapping("/api-detail")
    public ModelAndView api_detail(MusicalDTO musicalDTO) {

        String title = musicalDTO.getTitle();
        MusicalDTO musicalDTO1 = musicalService.findByTitle(title);
        System.out.println(musicalDTO1.getRes_no());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/musical/musical_detail");
        mv.addObject("list",musicalDTO1);

        return mv;
    }


    @GetMapping("/test")
    public String test(){
        return "/basic/navbar";

    }

    @GetMapping("/reservation")
    public ModelAndView reservation(MusicalDTO musicalDTO){
        MusicalDTO musicalDTO1 = musicalService.findByResno(musicalDTO.getRes_no());
        System.out.println(musicalDTO1);

        ModelAndView mv = new ModelAndView();
        mv.addObject("list", musicalDTO1);
        mv.setViewName("/musical/musical_reservation");
        return mv;
    }

    @PostMapping("/sit")
    public ModelAndView sit(@RequestParam("date") String date) {
        System.out.println(date);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;

    }


}
