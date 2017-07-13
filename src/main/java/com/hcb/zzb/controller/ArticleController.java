package com.hcb.zzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.service.IArticleService;
@Controller
public class ArticleController extends BaseControllers {
@Autowired
private IArticleService ArticleService;
}
