package com.example.javatest;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Slf4j
public class DateTest {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        log.info("formatted: {}", formatted);

        // 원본 날짜 문자열
        String inputDate = "Thu Sep 12 14:15:02 KST 2024";

        // 입력 날짜 형식과 출력 날짜 형식을 정의
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH); // 로케일 설정
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        try {
            // 문자열을 Date 객체로 파싱
            Date date = inputFormat.parse(inputDate);

            // Date 객체를 원하는 문자열 형식으로 변환
            String outputDate = outputFormat.format(date);

            // 변환된 날짜 출력
            log.info("outputDate: {}", outputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
