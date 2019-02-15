package com.bms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bms.api.constants.UrlConstants;
import com.bms.api.model.BmsData;
import com.bms.api.repository.BmsRepository;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path="/bms")
public class BmsDataController {
	
	@Autowired	
	private BmsRepository bmsRepository;
	
	@RequestMapping(value="/data", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Page<BmsData>> getAllBmissData(
			@ApiParam(name = UrlConstants.PARAM_SORT_COLUMN, value = "Sorts the records according to field provided. The field can be separated by Comma") @RequestParam(value = UrlConstants.PARAM_SORT_COLUMN, required = false) String sortColumn,
            @ApiParam(name = UrlConstants.PARAM_SORT_ORDER, value = "Sorts the records in ascending or descending order. The value can be asc or desc. by default the value is desc") @RequestParam(value = UrlConstants.PARAM_SORT_ORDER, defaultValue = UrlConstants.DEFAULT_SORT_ORDER, required = false) String sortOrder,
			@ApiParam(name = UrlConstants.PARAM_PAGE_NUMBER, value = "Shows the page number when pagination is created. The initial page number shall be set to 0 if no previous page sequence exists in the document. If a preceding page-sequence exists, then the page number will be one greater than the last number for that sequence.", required = false, example="0") @RequestParam(value = UrlConstants.PARAM_PAGE_NUMBER, required=false) Integer pageNo,
			@ApiParam(name = UrlConstants.PARAM_PAGE_SIZE, value = "Specifies the number of records it can return in a Single Page", required = false, example="10") @RequestParam(value = UrlConstants.PARAM_PAGE_SIZE, required=false) Integer pageSize) {
		sortColumn = StringUtils.isEmpty(sortColumn) ? "time": sortColumn;
		sortOrder = StringUtils.isEmpty(sortOrder) ? UrlConstants.DEFAULT_SORT_ORDER: sortOrder;
		Direction direction =  sortOrder.equals("asc") ? Direction.ASC : Direction.DESC;
		pageNo = StringUtils.isEmpty(pageNo) ? 0: pageNo;
		pageSize = StringUtils.isEmpty(pageSize) ? 10: pageSize;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortColumn));
		return new ResponseEntity<Page<BmsData>>(bmsRepository.findAll(pageable), HttpStatus.OK);
	}

}
