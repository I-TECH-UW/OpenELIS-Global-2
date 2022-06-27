package org.openelisglobal.common.services;
import java.util.List;


import org.openelisglobal.resultlimit.service.ResultLimitService;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope("prototype")
@DependsOn({ "springContext" })
public class ResultService {

    private static ResultLimitService resultLimitService = SpringContext.getBean(ResultLimitService.class);

    public static List<? extends Number> getHighRangeHigh(){
        List<? extends Number> results = resultLimitService.getHighRangeHigh();

        return results;
    }

    public static List<? extends Number> getHighRangeLow(){
        List<? extends Number> results = resultLimitService.getHighRangeLow();

        return results;
    }

    public static List<? extends Number> getLowRangeHigh(){
        List<? extends Number> results = resultLimitService.getLowRangeHigh();

        return results;
    }

    public static List<? extends Number> getLowRangeLow(){
        List<? extends Number> results = resultLimitService.getLowRangeLow();

        return results;
    }
}
