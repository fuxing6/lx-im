package com.lx.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("io")
public class IoController {

  private final Logger logger = LoggerFactory.getLogger(IoController.class);

}
