package com.issueTracker.services;

import com.issueTracker.models.bindingModels.IssueBindingModel;
import com.issueTracker.models.viewModels.IssueViewModel;

import java.util.List;

public interface IssueService {

    List<IssueViewModel> findAllIssues();

    void create(IssueBindingModel issueBindingModel, String username);

}
