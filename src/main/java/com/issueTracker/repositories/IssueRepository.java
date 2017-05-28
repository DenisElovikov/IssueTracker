package com.issueTracker.repositories;

import com.issueTracker.entities.issues.Issue;

import java.util.List;

public interface IssueRepository {

    void create(Issue issue);

    List<Issue> findAllIssues();

    void update(Issue issue);

    Issue findById(long id);

    void deleteById(long id);
}
