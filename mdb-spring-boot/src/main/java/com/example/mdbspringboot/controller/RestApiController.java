package com.example.mdbspringboot.controller;

import com.example.mdbspringboot.apiDto.ApiResponse;
import com.example.mdbspringboot.constants.AppConstants;
import com.example.mdbspringboot.dbEntity.UsedCarEntity;
import com.example.mdbspringboot.service.DataParseService;
import com.example.mdbspringboot.service.UsedCarContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class RestApiController {

    @Autowired
    UsedCarContentService usedCarContentService;

    @Autowired
    DataParseService dataParseService;

    @RequestMapping(path = AppConstants.TEST_URI, method = RequestMethod.GET,
            headers = "Accept=application/json", produces = "application/json")
    public ApiResponse doTest() {
        return new ApiResponse(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE, HttpStatus.OK);
    }

    @RequestMapping(path = AppConstants.FIND_ALL_USED_CAR_URI, method = RequestMethod.GET,
            headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody List<UsedCarEntity> getCarAllDetails() {
        return usedCarContentService.getCarAllDetails();
    }

    @RequestMapping(path = AppConstants.FIND_USED_CAR_URI, method = RequestMethod.GET,
            headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody List<UsedCarEntity> getCarDetails(@RequestParam Map<String, String> reqParam ) {
        return usedCarContentService.getCarDetails(reqParam);
    }

    @RequestMapping(path = AppConstants.START_CONTENT_INGESTION, method = RequestMethod.GET,
            headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody ApiResponse startContentIngestion() throws IOException {
        dataParseService.startDataParsingFromHtmlToJavaPojo();
        return new ApiResponse(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE, HttpStatus.OK);
    }
//
    @RequestMapping(path = AppConstants.FIND_CAR_BY_PRICE_AND_COLOR, method = RequestMethod.GET,
            headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody List<UsedCarEntity> getCarDetailsWithQuery(@RequestParam Map<String, String> reqParam ) {
        return usedCarContentService.getCarDetailsWithQuery(reqParam);
    }

    @RequestMapping(path = AppConstants.FIND_CAR_BY_CITYNAME_AND_BRAND, method = RequestMethod.GET,
    headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody List<UsedCarEntity> getCarDetailsWithBrand(@RequestParam Map<String, String> reqParam ) {
        return usedCarContentService.getCarDetailsWithBrand(reqParam);
    }
}
