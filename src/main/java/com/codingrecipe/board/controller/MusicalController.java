package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.MusicalDTO;

import com.codingrecipe.board.service.MusicalService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


@RestController
@RequiredArgsConstructor
public class MusicalController {

    private final MusicalService musicalService;


    @GetMapping("/api")
    public String load_save() throws IOException, ParseException {

        MusicalDTO musicalDTO = new MusicalDTO();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/BusanCultureMusicalService/getBusanCultureMusical"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=BrjHJ90yRTyYUg5oXeTm2jkYZqSjX1PwSP7Deo4z9S1eZflecDH0maN6daAzOYp2Yrar15uGqecubtJ1%2Fh%2Fl7w%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("110", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미터 resultType=json 입력*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(sb.toString());

        // "getBusanCultureMusical" 객체에서 "item" 키에 해당하는 값 가져오기
        JSONObject getBusanCultureMusical = (JSONObject) jsonObject.get("getBusanCultureMusical");
        JSONArray items = (JSONArray) getBusanCultureMusical.get("item");

        // 아이템 배열을 순회하며 각 아이템을 처리하고 저장
        for (Object itemObject : items) {
            JSONObject itemJson = (JSONObject) itemObject;

            // 각 아이템의 필드 가져오기
            String res_no = (String) itemJson.get("res_no");
            String title = (String) itemJson.get("title");
            String op_st_dt = (String) itemJson.get("op_st_dt");
            String op_ed_dt = (String) itemJson.get("op_ed_dt");
            String op_at = (String) itemJson.get("op_at");
            String place_nm = (String) itemJson.get("place_nm");
            String pay_at = (String) itemJson.get("pay_at");

            musicalDTO.setRes_no(res_no);
            musicalDTO.setTitle(title);
            musicalDTO.setOp_st_dt(op_st_dt);
            musicalDTO.setOp_ed_dt(op_ed_dt);
            musicalDTO.setOp_at(op_at);
            musicalDTO.setPlace_nm(place_nm);
            musicalDTO.setPay_at(pay_at);

            musicalService.addNewMusical(musicalDTO);
        }

        return sb.toString();
    }





}

