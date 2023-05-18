package com.example.cornconvert.corn;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CornDto {

    private String cornType;
    private String sec;
    private String min;
    private String hour;
    private String day;
    private String month;

    private List<String> weekly; //요일 문자열

    private String dayOfWeek; // 요일

    private String whatWeek; //몇번째 주


}
