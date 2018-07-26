package com.ym.quartz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ym.quartz.core.quartz.SchedulerServiceImpl;
import com.ym.quartz.core.quartz.TaskScheduled;

@RestController
@RequestMapping("test/")
public class TestController {
	
	@Autowired
	private SchedulerServiceImpl schedulerService;
	
	@GetMapping("hello")
	public String helloWorld() {
		return "hello world";
	}
	
	@GetMapping("getAllTask")
	public List<TaskScheduled> getSchedulers() {
		List<TaskScheduled> allTaskDetail = this.schedulerService.getAllTaskDetail();
		return allTaskDetail;
	}
}
