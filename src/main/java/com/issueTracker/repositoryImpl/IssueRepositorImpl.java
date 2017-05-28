package com.issueTracker.repositoryImpl;

import com.issueTracker.entities.issues.Issue;
import com.issueTracker.repositories.IssueRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class IssueRepositorImpl implements IssueRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Issue issue) {
        this.entityManager.persist(issue);
    }

    @Override
    public List<Issue> findAllIssues() {
        Query query = this.entityManager.createQuery("SELECT i FROM Issue AS i");
        List<Issue> issues = query.getResultList();
        return issues;
    }

    @Override
    public Issue findById(long id) {
        return this.entityManager.find(Issue.class, id);
    }

    @Override
    public void deleteById(long id) {
        Query query = this.entityManager.createQuery("DELETE FROM Issue AS i " +
                "WHERE i.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
