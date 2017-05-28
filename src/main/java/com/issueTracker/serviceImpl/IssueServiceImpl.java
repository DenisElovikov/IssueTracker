package com.issueTracker.serviceImpl;

import com.issueTracker.entities.issues.Issue;
import com.issueTracker.entities.priority.Priority;
import com.issueTracker.entities.status.Status;
import com.issueTracker.entities.users.User;
import com.issueTracker.models.bindingModels.IssueBindingModel;
import com.issueTracker.models.bindingModels.IssueEditBindingModel;
import com.issueTracker.models.viewModels.IssueEditViewModel;
import com.issueTracker.models.viewModels.IssueViewModel;
import com.issueTracker.repositories.IssueRepository;
import com.issueTracker.repositories.UserRepository;
import com.issueTracker.services.IssueService;
import com.issueTracker.utils.parser.interfaces.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class IssueServiceImpl implements IssueService {



    @Inject
    private IssueRepository issueRepository;

    @Inject
    private UserRepository userRepository;;

    @Inject
    private ModelParser modelParser;

    @Override
    public List<IssueViewModel> findAllIssues() {
        List<Issue> issues = this.issueRepository.findAllIssues();
        List<IssueViewModel> issueViewModels = new ArrayList<>();
        for (Issue issue : issues) {
            IssueViewModel issueViewModel = this.modelParser.convert(issue, IssueViewModel.class);
            issueViewModel.setPriority(issue.getPriority().toString());
            issueViewModel.setStatus(issue.getStatus().toString());
            issueViewModels.add(issueViewModel);
        }

        return issueViewModels;
    }

    @Override
    public void create(IssueBindingModel issueBindingModel, String username) {
        Issue issue  = this.modelParser.convert(issueBindingModel, Issue.class);
        issue.setPriority(Priority.valueOf(issueBindingModel.getPriority().toUpperCase()));
        issue.setStatus(Status.valueOf(issueBindingModel.getStatus().toUpperCase()));
        issue.setCreationDate(new Date());
        User user = this.userRepository.findByUsername(username);
        issue.setAuthor(user);
        this.issueRepository.create(issue);
    }

    @Override
    public void update(IssueEditBindingModel issueBindingModel) {
        Issue issue  = this.modelParser.convert(issueBindingModel, Issue.class);
        issue.setPriority(Priority.valueOf(issueBindingModel.getPriority().toUpperCase()));
        issue.setStatus(Status.valueOf(issueBindingModel.getStatus().toUpperCase()));
        this.issueRepository.update(issue);
    }

    @Override
    public IssueEditViewModel getIssueById(long id) {
        Issue issue = this.issueRepository.findById(id);
        IssueEditViewModel issueEditViewModel = this.modelParser.convert(issue, IssueEditViewModel.class);
        issueEditViewModel.setPriority(issue.getPriority().toString());
        issueEditViewModel.setStatus(issue.getStatus().toString());
        return issueEditViewModel;
    }
}
