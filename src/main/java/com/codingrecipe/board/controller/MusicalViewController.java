package com.codingrecipe.board.controller;


import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.CommentDTO;
import com.codingrecipe.board.dto.MusicalDTO;
import com.codingrecipe.board.service.MusicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MusicalViewController {

    private final MusicalService musicalService;

    @GetMapping("/api-page")
    public String api_page(Model model){
        List<MusicalDTO> musicalDTOList = musicalService.findAll();
        model.addAttribute("musicalList", musicalDTOList);
        return "/musical/musical_list";

    }

    @GetMapping("/api-detail")
    public ModelAndView api_detail(MusicalDTO musicalDTO) {

        String title = musicalDTO.getTitle();
        MusicalDTO musicalDTO1 = musicalService.findByTitle(title);


        ModelAndView mv = new ModelAndView();
        mv.setViewName("/musical/musical_detail");
        mv.addObject("list",musicalDTO1);

        return mv;
    }


    @GetMapping("/test")
    public String test(){
        return "/basic/navbar";

    }
}
