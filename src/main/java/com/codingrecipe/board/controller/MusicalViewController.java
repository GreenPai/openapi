package com.codingrecipe.board.controller;


import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.CommentDTO;
import com.codingrecipe.board.dto.MusicalDTO;
import com.codingrecipe.board.dto.ReservationDTO;
import com.codingrecipe.board.repository.ReservationRepository;
import com.codingrecipe.board.service.MusicalService;
import com.codingrecipe.board.service.ReservationService;
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
    private final ReservationService reservationService;

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


    @PostMapping("/reservation")
    public ModelAndView reservation2(MusicalDTO musicalDTO,
                                     @RequestParam("price") String price,
                                     @RequestParam("seat") String[] seat,
                                     @RequestParam("date") String date){

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setDate(date);
        reservationDTO.setPrice(Integer.parseInt(price));
        reservationDTO.setTitle(musicalDTO.getTitle());

        for(String seatNumber : seat){
            reservationDTO.setSeat(seatNumber);
            musicalService.save(reservationDTO);
            System.out.println(reservationDTO);
        }



        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }



    @PostMapping("/sit")
    public ModelAndView postsit(@RequestParam("date") String date) {
        System.out.println(date);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/sit")
    public ModelAndView getsit(@RequestParam("title") String title,
                               @RequestParam("selectedDate") String Date) {

        System.out.println(title);
        System.out.println(Date);

        ReservationDTO reservationDTO = new ReservationDTO();

        List<String> seat = ReservationService.findSeats(title, Date);

        ModelAndView mv = new ModelAndView();

        mv.setViewName("/musical/musical_sit");
        return mv;
    }


}
