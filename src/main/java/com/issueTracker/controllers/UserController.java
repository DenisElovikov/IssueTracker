package com.issueTracker.controllers;

import com.mvcFramework.annotations.controller.Controller;

import javax.ejb.Stateless;


@Stateless
@Controller
public class UserController {
    public String getRegisterPage() {

        return "/templates/register";
    }
}
