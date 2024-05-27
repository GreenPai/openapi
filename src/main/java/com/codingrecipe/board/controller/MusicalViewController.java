package com.codingrecipe.board.controller;


import com.codingrecipe.board.dto.*;
import com.codingrecipe.board.entity.ReservationEntity;
import com.codingrecipe.board.repository.ReservationRepository;
import com.codingrecipe.board.service.JoinService;
import com.codingrecipe.board.service.MusicalService;
import com.codingrecipe.board.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("musical")
public class MusicalViewController {

    private final MusicalService musicalService;
    private final ReservationService reservationService;
    private final JoinService joinService;

    // 뮤지컬 페이지
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

    // 뮤지컬 예약 페이지

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
                                     @RequestParam("date") String date,
                                     @RequestParam("user") String username) {

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setDate(date);
        reservationDTO.setPrice(Integer.parseInt(price));
        reservationDTO.setTitle(musicalDTO.getTitle());
        reservationDTO.setUser(username);

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

        ReservationDTO reservationDTO = new ReservationDTO();
        List<ReservationDTO> seat = reservationService.findSeats(title, Date);

        ModelAndView mv = new ModelAndView();

        mv.addObject("seats",seat);

        mv.setViewName("/musical/musical_sit");
        return mv;
    }

    @GetMapping("/my_reservation")
    public ModelAndView my_reservation(){
        String username = "admin123";
        List<ReservationDTO> reservationDTOS = reservationService.findReservation(username);
        System.out.println("reservationDTO: " + reservationDTOS );

        // 제목 하나에 C1, C2 수 표시.
        System.out.println(reservationDTOS.size());

        List<ReservationDTO> DTOS = new ArrayList<>();

        for(ReservationDTO reservationDTO : reservationDTOS){
            for(ReservationDTO reservationDTO1 : DTOS){
                if(reservationDTO1.getTitle().equals(reservationDTO.getTitle()) && reservationDTO1.getDate().equals(reservationDTO.getDate())){

                }else{
                    DTOS.add(reservationDTO);
                }
            }
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("list",reservationDTOS);
        mv.addObject("count",reservationDTOS.size());
        mv.setViewName("/musical/musical_userpage");

        return mv;
    }

}
