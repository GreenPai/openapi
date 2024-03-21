package com.codingrecipe.board.controller;


import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.MusicalDTO;
import com.codingrecipe.board.service.MusicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MusicalViewController {

    private final MusicalService musicalService;

    @GetMapping("api_page")
    public String api_page(Model model){
        List<MusicalDTO> musicalDTOList = musicalService.findAll();
        model.addAttribute("musicalList", musicalDTOList);
        return "api_list";


    }


}
