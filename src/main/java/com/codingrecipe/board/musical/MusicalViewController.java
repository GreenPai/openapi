package com.codingrecipe.board.musical;


import com.codingrecipe.board.login.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Comparator;
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
        System.out.println(musicalDTOList);
        return "/musical/musical_list";

    }

    @GetMapping("/api-detail/{res_no}")
    public ModelAndView api_detail(@PathVariable("res_no") Long res_no) {

        MusicalDTO musicalDTO1 = musicalService.findByResno(res_no);
        ModelAndView mv = new ModelAndView();
        mv.addObject("list",musicalDTO1);
        mv.setViewName("/musical/musical_detail");
        return mv;
    }

    @GetMapping("/api-detail/title/{title}")
    public ModelAndView api_detail(@PathVariable("title") String title) {
        MusicalDTO musicalDTO1 = musicalService.findByTitle(title);
        ModelAndView mv = new ModelAndView();
        mv.addObject("list",musicalDTO1);
        mv.setViewName("/musical/musical_detail");
        return mv;
    }


    // 마이페이지 예약에서 사진 클릭했을 때 정보페이지
    @GetMapping("/api-detail2")
    public ModelAndView api_detail2(MusicalDTO musicalDTO) {

        String title = musicalDTO.getTitle();
        MusicalDTO musicalDTO1 = musicalService.findByTitle(title);
        System.out.println(musicalDTO1.getRes_no());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/musical/musical_userpage_detail");
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
                                     @RequestParam("user") String username,
                                     @RequestParam("res_no") String res_no) {

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setDate(date);
        reservationDTO.setPrice(Integer.parseInt(price));
        reservationDTO.setTitle(musicalDTO.getTitle());
        reservationDTO.setUser(username);
        reservationDTO.setRes_no(Long.parseLong(res_no));

        for(String seatNumber : seat){
            reservationDTO.setSeat(seatNumber);
            musicalService.save(reservationDTO);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }



    @PostMapping("/sit")
    public ModelAndView postsit(@RequestParam("date") String date) {
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

    // 마이페이지 유저 뮤지컬 정보 호출
    @GetMapping("/my_reservation")
    public ModelAndView my_reservation(@RequestParam("user") String user ){
        String username = user;
        List<ReservationDTO> reservationDTOS = reservationService.findReservation(username);

        List<ReservationDTO> DTOS = new ArrayList<>();

        for(ReservationDTO reservationDTO : reservationDTOS){

            int check = 0;
            int index = 0;
            if(!DTOS.isEmpty()){
                for(int i=0; i<DTOS.size(); i++) {
                    if (DTOS.get(i).getTitle().equals(reservationDTO.getTitle()) && DTOS.get(i).getDate().equals(reservationDTO.getDate())) {
                        check = 1;
                        break;
                        }
                    index++;
                }

                if (check == 1) {
                    DTOS.get(index).setCount(DTOS.get(index).getCount() + 1); // 좌석 수 증가
                    DTOS.get(index).addSeat(reservationDTO.getSeat()); // DTO에 좌석 추가
                } else {
                    String place = reservationService.findPlaceByresno(reservationDTO.getRes_no());
                    reservationDTO.setPlace(place);
                    DTOS.add(reservationDTO);
                    DTOS.get(index).addSeat(reservationDTO.getSeat());
                }

            }else{
                String place = reservationService.findPlaceByresno(reservationDTO.getRes_no());
                reservationDTO.setPlace(place);
                DTOS.add(reservationDTO);
                DTOS.get(0).addSeat(reservationDTO.getSeat());
            }

        }

        DTOS.sort(Comparator.comparing(ReservationDTO::getDate));

        ModelAndView mv = new ModelAndView();
        mv.addObject("list", DTOS);
        mv.addObject("count",reservationDTOS.size());

        mv.setViewName("/musical/musical_userpage");

        return mv;
    }

    //마이페이지에서 예약 삭제하기
    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity<List<ReservationDTO>> reservation_delete(@ModelAttribute ReservationDTO DTO){


        String date = DTO.getDate();
        Long resno = DTO.getRes_no();
        String user = DTO.getUser();

        reservationService.deleteMusical(date, resno, user);

        List<ReservationDTO> reservationDTOS = reservationService.findReservation(user);

        List<ReservationDTO> DTOS = new ArrayList<>();

        for(ReservationDTO reservationDTO : reservationDTOS){

            int check = 0;
            int index = 0;
            if(!DTOS.isEmpty()){
                for(int i=0; i<DTOS.size(); i++) {
                    if (DTOS.get(i).getTitle().equals(reservationDTO.getTitle()) && DTOS.get(i).getDate().equals(reservationDTO.getDate())) {
                        check = 1;
                        break;
                    }
                    index++;
                }

                if (check == 1) {
                    DTOS.get(index).setCount(DTOS.get(index).getCount() + 1); // 좌석 수 증가
                    DTOS.get(index).addSeat(reservationDTO.getSeat()); // DTO에 좌석 추가
                } else {
                    String place = reservationService.findPlaceByresno(reservationDTO.getRes_no());
                    reservationDTO.setPlace(place);
                    DTOS.add(reservationDTO);
                    DTOS.get(index).addSeat(reservationDTO.getSeat());
                }

            }else{
                String place = reservationService.findPlaceByresno(reservationDTO.getRes_no());
                reservationDTO.setPlace(place);
                DTOS.add(reservationDTO);
                DTOS.get(0).addSeat(reservationDTO.getSeat());
            }

        }

        DTOS.sort(Comparator.comparing(ReservationDTO::getDate));

        return ResponseEntity.ok(DTOS);
    }

}
