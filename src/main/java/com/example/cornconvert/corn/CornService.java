package com.example.cornconvert.corn;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CornService {

    public String cornConvert(CornDto cornDto){
        String cornType = cornDto.getCornType();
        String corn = "";

        if(cornType.equals("MIN_EVERY")){
            corn = CornType.MIN_EVERY;
            corn = corn.replace("MIN",cornDto.getMin());
        } else if (cornType.equals("HOUR_EVERY")) {
            corn = CornType.HOUR_EVERY;
            corn = corn.replace("HOUR",cornDto.getHour());
        } else if (cornType.equals("HOUR_START")) {
            corn = CornType.HOUR_START;
            corn = corn.replace("MIN",cornDto.getMin());
            corn = corn.replace("HOUR",cornDto.getHour());

        }else if(cornType.equals("DAY_EVERY")){ //매일
            corn = CornType.DAY_EVERY;
            corn = corn.replace("MIN",cornDto.getMin());
            corn = corn.replace("HOUR",cornDto.getHour());

        }else if(cornType.equals("DAY_START")){ // 평일
            corn = CornType.DAY_EVERY;
            corn = corn.replace("MIN",cornDto.getMin());
            corn = corn.replace("HOUR",cornDto.getHour());
        }else if(cornType.equals("WEEK_START")){
            corn = CornType.WEEK_START;
            corn = corn.replace("MIN",cornDto.getMin());
            corn = corn.replace("HOUR",cornDto.getHour());
            List<String> weekly = cornDto.getWeekly();
            String weeklyStr = StringUtils.join(weekly,',');
            corn = corn.replace("DAYS",weeklyStr);
        }else if(cornType.equals("MONTH_EVERY")){
            corn = CornType.MONTH_EVERY;
            corn = corn.replace("MIN",cornDto.getMin());
            corn = corn.replace("HOUR",cornDto.getHour());
            corn = corn.replace("MONTH",cornDto.getMonth());
        }else if(cornType.equals("MONTH_START")){
            corn = CornType.MONTH_START;
            corn = corn.replace("MIN",cornDto.getMin());
            corn = corn.replace("HOUR",cornDto.getHour());
            corn = corn.replace("MONTH",cornDto.getMonth());
            corn = corn.replace("DAY_WEEK",cornDto.getDayOfWeek());
            corn = corn.replace("WEEK",cornDto.getWhatWeek());
        }else if(cornType.equals("YEAR_EVERY")){
            corn = CornType.YEAR_EVERY;
            corn = corn.replace("MIN",cornDto.getMin());
            corn = corn.replace("HOUR",cornDto.getHour());
            corn = corn.replace("DAY",cornDto.getDay());
            corn = corn.replace("MONTH",cornDto.getMonth());
        }else if(cornType.equals("YEAR_START")){
            corn = CornType.YEAR_START;
            corn = corn.replace("MIN",cornDto.getMin());
            corn = corn.replace("HOUR",cornDto.getHour());
            corn = corn.replace("MONTH",cornDto.getMonth());
            corn = corn.replace("DAY_WEEK",cornDto.getDayOfWeek());
            corn = corn.replace("WEEK",cornDto.getWhatWeek());
        }


        return corn;

    }
}
