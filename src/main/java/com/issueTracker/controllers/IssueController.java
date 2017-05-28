package com.issueTracker.controllers;

import com.issueTracker.models.bindingModels.IssueBindingModel;
import com.issueTracker.models.bindingModels.LoggedUserModel;
import com.issueTracker.models.viewModels.IssueViewModel;
import com.issueTracker.services.IssueService;
import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Stateless
@Controller
public class IssueController {

    @Inject
    private IssueService issueService;

    @GetMapping("/issues")
    public String getIssuePage(Model model){
        List<IssueViewModel> issueViewModels = this.issueService.findAllIssues();
        model.addAttribute("issueViewModels", issueViewModels);

        return "/templates/issues";
    }


    @GetMapping("/issues/add")
    public String getAddIssuePage(Model model){
        return "/templates/add-issue";
    }

    @PostMapping("/issues/add")
    public String addIssue(@ModelAttribute IssueBindingModel issueBindingModel, HttpSession session){
        LoggedUserModel loggedUserModel = (LoggedUserModel) session.getAttribute("user");
        String username = loggedUserModel.getUsername();
        this.issueService.create(issueBindingModel, username);
        return "redirect:/issues";
    }
}
