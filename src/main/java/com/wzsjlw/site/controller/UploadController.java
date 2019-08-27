package com.wzsjlw.site.controller;

import com.wzsjlw.site.utils.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ll
 * @version: 1.0 2019-08-23
 * @see:
 * @since:
 */
@RestController
public class UploadController {

    @PostMapping("/upload")
    public ResultUtil uploadAvatar() {
        return null;
    }
}
